package org.example.survey.repository

import org.example.survey.domain.Form
import org.springframework.data.jpa.repository.JpaRepository

interface FormRepository : JpaRepository<Form, Long>
