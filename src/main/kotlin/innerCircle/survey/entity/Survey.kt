package innerCircle.survey.entity

import innerCircle.survey.dto.CreateSurveyRequest
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
data class Survey(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val name: String,
    val description: String
){
    companion object {
        fun create(name: String, description: String): Survey {
            return Survey(
                name = name,
                description = description
            )
        }
    }
}