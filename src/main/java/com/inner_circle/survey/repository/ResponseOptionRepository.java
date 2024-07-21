package com.inner_circle.survey.repository;

import com.inner_circle.survey.entity.response.ResponseOption;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResponseOptionRepository extends JpaRepository<ResponseOption, Long> {
}
