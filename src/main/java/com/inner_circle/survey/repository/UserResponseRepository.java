package com.inner_circle.survey.repository;

import com.inner_circle.survey.entity.response.Respondent;
import com.inner_circle.survey.entity.response.UserResponse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserResponseRepository extends JpaRepository<UserResponse, Long> {

  List<UserResponse> findByRespondent(Respondent respondent);
}
