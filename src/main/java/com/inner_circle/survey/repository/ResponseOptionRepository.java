package com.inner_circle.survey.repository;

import com.inner_circle.survey.entity.response.ResponseOption;
import com.inner_circle.survey.entity.response.UserResponse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ResponseOptionRepository extends JpaRepository<ResponseOption, Long> {

  List<ResponseOption> findByUserResponse(UserResponse userResponse);
}
