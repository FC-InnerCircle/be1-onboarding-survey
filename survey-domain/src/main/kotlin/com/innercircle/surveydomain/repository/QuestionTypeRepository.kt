package com.innercircle.surveydomain.repository

import com.innercircle.surveydomain.model.QuestionType
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface QuestionTypeRepository : JpaRepository<QuestionType, Int>
