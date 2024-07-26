package org.ksh.survey.controller;

import lombok.RequiredArgsConstructor;
import org.ksh.common.model.BaseResponse;
import org.ksh.survey.model.SurveyReplyItemResponses;
import org.ksh.survey.model.SurveyReplyRequest;
import org.ksh.survey.service.SurveyReplyService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reply")
@RequiredArgsConstructor
public class SurveyReplyController {

    private final SurveyReplyService surveyReplyService;

    @PostMapping
    public BaseResponse submitSurveyReply(@RequestBody SurveyReplyRequest surveyReplyRequest) {
        return surveyReplyService.saveSurveyReply(surveyReplyRequest);
    }
    @GetMapping
    public BaseResponse<SurveyReplyItemResponses> getSurveyAll(@RequestParam Long id) {
        return surveyReplyService.findSurveyAll(id);
    }

}
