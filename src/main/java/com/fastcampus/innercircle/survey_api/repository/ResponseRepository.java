package com.fastcampus.innercircle.survey_api.repository;

import com.fastcampus.innercircle.survey_api.domain.entity.Response;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ResponseRepository extends JpaRepository<Response, Long> {

    List<Response> findByFormId(Long formId);
}
