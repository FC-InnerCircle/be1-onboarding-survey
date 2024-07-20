package lshh.be1onboardingsurvey.survey.controller;

import lombok.RequiredArgsConstructor;
import lshh.be1onboardingsurvey.survey.domain.command.AddSurveyItemCommand;
import lshh.be1onboardingsurvey.survey.domain.command.AddSurveyItemOptionCommand;
import lshh.be1onboardingsurvey.survey.domain.command.CreateSurveyCommand;
import lshh.be1onboardingsurvey.survey.domain.SurveyService;
import lshh.be1onboardingsurvey.survey.domain.dto.Result;
import lshh.be1onboardingsurvey.survey.domain.dto.SurveyView;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/survey")
public class SurveyController {

    private final SurveyService service;

    @PostMapping("")
    public Response create(@RequestBody CreateSurveyCommand command) {
        Result result = service.create(command);
        return Response.of(result);
    }

    @PostMapping("/item")
    public Response addItem(@RequestBody AddSurveyItemCommand command) {
        Result result = service.addItem(command);
        return Response.of(result);
    }

    @PostMapping("/item/option")
    public Response addOption(@RequestBody AddSurveyItemOptionCommand command) {
        Result result = service.addItemOption(command);
        return Response.of(result);
    }



    @GetMapping("all")
    public List<SurveyView> all(){
        return service.findAll();
    }



}
