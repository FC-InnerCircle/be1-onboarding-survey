package com.inner_circle.survey.repository;

import com.inner_circle.survey.entity.request.Option;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OptionRepository extends JpaRepository<Option,Long> {
}
