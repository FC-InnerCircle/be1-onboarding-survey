package com.example.innercircle_survey.service;

import com.example.innercircle_survey.repository.SurveyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class SurveyServiceImpl implements SurveyService {

    private final SurveyRepository repository;


}
