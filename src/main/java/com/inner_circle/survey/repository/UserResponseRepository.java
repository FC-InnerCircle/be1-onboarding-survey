package com.inner_circle.survey.repository;

import com.inner_circle.survey.entity.response.UserResponse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserResponseRepository extends JpaRepository<UserResponse, Long> {
}
