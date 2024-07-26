package innerCircle.survey.entity

import innerCircle.survey.common.QuestionType
import innerCircle.survey.dto.QuestionRequest
import innerCircle.survey.dto.QuestionUpdateRequest
import jakarta.persistence.Entity
import jakarta.persistence.Enumerated
import jakarta.persistence.EnumType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import java.time.LocalDateTime

@Entity
data class SurveyQuestion(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    @ManyToOne @JoinColumn(name = "survey_version_id")
    val surveyVersion: SurveyVersion,
    val name: String,
    val description: String?,
    @Enumerated(EnumType.STRING)
    val questionType: QuestionType,
    val isRequired: Boolean,
    val deletedAt: LocalDateTime? = null
){
    companion object {
        fun create(name: String, description: String,
                   questionType: QuestionType, isRequired: Boolean,
                   surveyVersion: SurveyVersion): SurveyQuestion {
            return SurveyQuestion(
                surveyVersion = surveyVersion,
                name = name,
                description = description,
                questionType = questionType,
                isRequired = isRequired
            )
        }
    }

    fun update(name: String, description: String,
               questionType: QuestionType, isRequired: Boolean,
               newSurveyVersion: SurveyVersion): SurveyQuestion {
        return copy(
            name = name,
            description = description,
            questionType = questionType,
            isRequired = isRequired,
            surveyVersion = newSurveyVersion
        )
    }

    fun createOptions(options: List<String>?): List<SurveyQuestionOption> {
        return if (questionType == QuestionType.SINGLE_CHOICE || questionType == QuestionType.MULTIPLE_CHOICE) {
            options?.map { optionValue ->
                SurveyQuestionOption(
                    question = this,
                    optionValue = optionValue
                )
            } ?: emptyList()
        } else {
            emptyList()
        }
    }
}
