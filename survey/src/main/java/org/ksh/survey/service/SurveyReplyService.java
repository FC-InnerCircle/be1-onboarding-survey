package org.ksh.survey.service;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.ksh.common.exception.DataNotFoundException;
import org.ksh.common.model.BaseResponse;
import org.ksh.common.model.BaseStatusCode;
import org.ksh.survey.entity.SurveyReply;
import org.ksh.survey.entity.SurveyReplyItem;
import org.ksh.survey.entity.SurveyTemplate;
import org.ksh.survey.entity.SurveyTemplateItem;
import org.ksh.survey.model.SurveyReplyItemResponses;
import org.ksh.survey.model.SurveyReplyRequest;
import org.ksh.survey.repository.SurveyReplyItemRepository;
import org.ksh.survey.repository.SurveyReplyRepository;
import org.ksh.survey.repository.SurveyTemplateItemRepository;
import org.ksh.survey.repository.SurveyTemplateRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SurveyReplyService {

    private final SurveyReplyRepository surveyReplyRepository;
    private final SurveyReplyItemRepository surveyReplyItemRepository;
    private final SurveyTemplateRepository surveyTemplateRepository;
    private final SurveyTemplateItemRepository surveyTemplateItemRepository;

    /**
     * 만약 제출하기 눌렀는데, 그 사이에 설문 내용이 바뀌었을 경우
     * 1번째 방법 : SurveyReplyRequest의 surveyItemIds 와 SurveyTemplate의 item 개수 비교 -> 비교 이후에, 바뀔수도 있음 타이밍이슈
     * 2번째 방법 : 버저닝 추가해서, 클라에서 설문조사 조회 시 버저닝을 가지고 있다가, 제출하기 눌렀을 때, 버저닝을 같이 보내줘 surveyTemplate의 버전을 SurveyReply에도 저장
     *  // 2번째 방법으로 진행
     * @param surveyReplyRequest
     * @return
     */
    @Transactional
    public BaseResponse saveSurveyReply(@RequestBody @Valid SurveyReplyRequest surveyReplyRequest) {

        SurveyTemplate surveyTemplate = surveyTemplateRepository.findById(surveyReplyRequest.getSurveyId())
                .orElseThrow(() -> new DataNotFoundException("아이디에 맞는 설문조사가 없습니다."));

        SurveyReply surveyReply = surveyReplyRepository.save(surveyReplyRequest.createSurveyReply(surveyTemplate));

        List<SurveyReplyItem> surveyReplyItems = surveyReplyRequest.getSurveyItemIds().stream().map(surveyReplyItemRequest -> {
            SurveyTemplateItem surveyReplyItem = surveyTemplateItemRepository.findById(surveyReplyItemRequest.getItemId())
                    .orElseThrow(() -> new DataNotFoundException("아이디에 맞는 설문항목이 없습니다."));

            return surveyReplyItemRequest.createSurveyReplyItem(surveyReply, surveyReplyItem);
        }).toList();

        surveyReplyItemRepository.saveAll(surveyReplyItems);

        return new BaseResponse(BaseStatusCode.SUCCESS);
    }

    @Transactional(readOnly = true)
    public BaseResponse<SurveyReplyItemResponses> findSurveyAll(Long id) {
        List<SurveyTemplateItem> surveyTemplateItems = surveyTemplateItemRepository.findBySurveyTemplateId(id);
        if(surveyTemplateItems.isEmpty()) {
            throw new IllegalArgumentException("빈 설문조사입니다.");
        }
        //예외처리
        List<SurveyReplyItem> surveyReplyItems = new ArrayList<>();
        // todo: 성능 이슈 개선
        surveyTemplateItems.forEach(surveyTemplateItem -> {
            surveyReplyItems.addAll(surveyReplyItemRepository.findBySurveyTemplateItemId(surveyTemplateItem.getId()));
        });

        return new BaseResponse<>(SurveyReplyItemResponses.createSurveyReplyItemResponses(surveyReplyItems));
    }

}
