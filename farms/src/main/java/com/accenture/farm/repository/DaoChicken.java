package com.accenture.farm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.accenture.farm.model.Chicken;
import com.accenture.farm.model.Farm;

public interface DaoChicken extends JpaRepository<Chicken, Long> {
	
	public List<Chicken> findByFarm (Farm far);
	
}
