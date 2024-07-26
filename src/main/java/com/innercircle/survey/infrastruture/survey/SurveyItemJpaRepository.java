package com.innercircle.survey.infrastruture.survey;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SurveyItemJpaRepository extends JpaRepository<SurveyItemEntity, Long> {
    List<SurveyItemEntity> findAllBySurvey_surveyId(Long surveyId);

//    @Query("SELECT si FROM SurveyItemEntity si " +
//            "LEFT JOIN FETCH si. " +
//            "LEFT JOIN FETCH si.surveyItemOption sio " +
//            "LEFT JOIN FETCH sio.selectOptions so " +
//            "WHERE s.surveyId = :surveyId")
//    List<SurveyEntity> findSurveyItemsAndOptions(@Param("surveyId") Long surveyId);
}
