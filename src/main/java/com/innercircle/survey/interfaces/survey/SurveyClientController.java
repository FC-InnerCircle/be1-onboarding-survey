package com.innercircle.survey.interfaces.survey;

import com.innercircle.survey.common.response.CommonResponse;
import com.innercircle.survey.interfaces.doc.SurveyClientControllerDoc;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/survey")
public class SurveyClientController implements SurveyClientControllerDoc {


    @GetMapping("/{surveyToken}")
    public CommonResponse getSurvey(@PathVariable("surveyToken") String surveyToken) {
        return null;
    }

    @PostMapping("/{surveyToken}")
    public CommonResponse submitSurvey(HttpServletRequest request, SurveyDto.SurveySubmitRequest surveySubmitRequest) {
        return null;
    }
}
