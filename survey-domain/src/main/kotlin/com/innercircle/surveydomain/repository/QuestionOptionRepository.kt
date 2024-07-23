package com.innercircle.surveydomain.repository

import com.innercircle.surveydomain.model.QuestionOption
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface QuestionOptionRepository : JpaRepository<QuestionOption, Int>
