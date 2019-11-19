package com.accenture.farm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.accenture.farm.model.Chicken;
import com.accenture.farm.model.Egg;
import com.accenture.farm.model.Farm;

public interface DaoEgg extends JpaRepository<Egg, Long> {
	
	public List<Egg> findByChick (Chicken chick);
	public List<Egg> findById (Egg egg);
	public List<Egg> findByFarm (Farm farm);

}
