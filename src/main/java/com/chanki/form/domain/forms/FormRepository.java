package com.chanki.form.domain.forms;

import org.springframework.data.jpa.repository.JpaRepository;

import jakarta.persistence.EntityManager;


public interface FormRepository extends JpaRepository<Form, Long> {

}
