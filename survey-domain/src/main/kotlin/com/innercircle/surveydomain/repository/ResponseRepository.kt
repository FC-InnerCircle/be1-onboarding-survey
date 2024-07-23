package com.innercircle.surveydomain.repository

import com.innercircle.surveydomain.model.Response
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ResponseRepository : JpaRepository<Response, Int>
