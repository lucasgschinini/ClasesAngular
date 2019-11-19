package com.accenture.farm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.accenture.farm.model.Farm;

public interface DaoFarm extends JpaRepository<Farm, Long> {

}
