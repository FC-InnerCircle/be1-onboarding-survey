package com.innercircle.survey.repository;

import com.innercircle.survey.domain.Option;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OptionRepository extends JpaRepository<Option, Long> {
}
