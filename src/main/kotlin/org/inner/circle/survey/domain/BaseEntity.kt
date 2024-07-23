package org.inner.circle.survey.domain

import jakarta.persistence.MappedSuperclass
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDateTime

@MappedSuperclass
open class BaseEntity {
    @CreationTimestamp
    val createdAt: LocalDateTime? = LocalDateTime.now()

    @UpdateTimestamp
    var updatedAt: LocalDateTime? = LocalDateTime.now()
        protected set
}
