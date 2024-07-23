package org.inner.circle.survey.domain

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import org.inner.circle.survey.common.QuestionType

@Entity
class Question(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "survey_id", nullable = false)
    var survey: Survey,
    @Column(nullable = false)
    var title: String,
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    var type: QuestionType,
    @Column(nullable = false)
    var description: String,
    @Column(nullable = false)
    var requiredFlag: Boolean,
    @Column(nullable = false)
    var orderNumber: Int,
) : BaseEntity() {
    constructor() : this(survey = Survey(), title = "", type = QuestionType.SHOT, description = "", requiredFlag = false, orderNumber = 0)
}
