package com.innercircle.survey.infrastruture.survey;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SurveyJpaRepository extends JpaRepository<SurveyEntity, Long> {

//    @Query("SELECT s FROM SurveyEntity s " +
//            "LEFT JOIN FETCH SurveyItemEntity si ON si.survey = s " +
//            "LEFT JOIN FETCH SurveyItemOptionEntity sio ON sio.surveyItem = si " +
//            "LEFT JOIN FETCH SelectOptionEntity so ON so.surveyItemOption = sio " +
//            "WHERE s.surveyId = :surveyId")
//    List<SurveyEntity> findSurveysWithItemsAndOptions(@Param("surveyId") Long surveyId);
}
