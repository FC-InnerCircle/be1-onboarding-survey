package org.inner.circle.survey.domain

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "survey")
class Survey(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    @Column(nullable = false)
    var title: String,
    @Column(nullable = true)
    var description: String,
    @Column(nullable = false)
    var writer: String,
) : BaseEntity() {
    constructor() : this(title = "", description = "", writer = "")
}
