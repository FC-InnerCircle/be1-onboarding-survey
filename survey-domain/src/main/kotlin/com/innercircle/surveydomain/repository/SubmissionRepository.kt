package com.innercircle.surveydomain.repository

import com.innercircle.surveydomain.model.Submission
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface SubmissionRepository : JpaRepository<Submission, Int> {
    fun findByFormIdAndRespondentInfo(
        formsId: Long,
        respondentInfo: String,
    ): Submission?
}
