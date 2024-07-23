package com.inner_circle.survey.repository;

import com.inner_circle.survey.entity.request.Option;
import com.inner_circle.survey.entity.request.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OptionRepository extends JpaRepository<Option,Long> {
  List<Option> findByQuestionAndLatestTrue(Question question);
}
