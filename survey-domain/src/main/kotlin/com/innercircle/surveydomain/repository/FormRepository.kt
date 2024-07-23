package com.innercircle.surveydomain.repository

import com.innercircle.surveydomain.model.Form
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface FormRepository : JpaRepository<Form, Int>
