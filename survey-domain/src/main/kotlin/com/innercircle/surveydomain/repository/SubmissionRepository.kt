package com.innercircle.surveydomain.repository

import com.innercircle.surveydomain.model.Submission
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

// 어노테이션은 주석으로 명시하는 역할 24.07.27
@Repository
interface SubmissionRepository : JpaRepository<Submission, Int> {
    fun findByFormIdAndRespondentInfo(
        formsId: Long,
        respondentInfo: String,
    ): Submission?
}
