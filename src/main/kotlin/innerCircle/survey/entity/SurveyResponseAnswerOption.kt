package innerCircle.survey.entity

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne

@Entity
data class SurveyResponseAnswerOption(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    @ManyToOne @JoinColumn(name = "response_id")
    val response: SurveyResponse,
    @ManyToOne @JoinColumn(name = "question_id")
    val question: SurveyQuestion,
    val optionValue: String // For single/multiple choice responses
)
