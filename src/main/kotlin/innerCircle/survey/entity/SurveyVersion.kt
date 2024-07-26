package innerCircle.survey.entity

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import java.time.LocalDateTime

@Entity
data class SurveyVersion(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    @ManyToOne @JoinColumn(name = "survey_id")
    val survey: Survey,
    val version: Int,
    val createdAt: LocalDateTime = LocalDateTime.now()
){
    companion object {
        fun create(survey: Survey): SurveyVersion {
            return SurveyVersion(
                survey = survey,
                version = 1
            )
        }
    }
}
