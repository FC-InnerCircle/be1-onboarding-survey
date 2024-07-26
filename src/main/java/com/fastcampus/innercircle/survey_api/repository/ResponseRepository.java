package com.fastcampus.innercircle.survey_api.repository;

import com.fastcampus.innercircle.survey_api.domain.entity.Response;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResponseRepository extends JpaRepository<Response, Long> {
}
