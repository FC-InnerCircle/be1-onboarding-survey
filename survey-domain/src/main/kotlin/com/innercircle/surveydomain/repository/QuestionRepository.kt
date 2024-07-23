package com.innercircle.surveydomain.repository

import com.innercircle.surveydomain.model.Question
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface QuestionRepository : JpaRepository<Question, Int>
