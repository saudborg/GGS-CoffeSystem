package com.sauloborges.ggs.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sauloborges.ggs.domain.Statistic;

public interface StatisticRepository extends JpaRepository<Statistic, Integer> {

	@Query("SELECT s FROM Statistic s where s.machine like %:nameMachine%")
	public List<Statistic> findStatisticByMachine(@Param("nameMachine") String name);
	
	@Query("SELECT DISTINCT(s.machine) FROM Statistic s where s.machine like :nameMachine%")
	public List<String> getListNameMachine(@Param("nameMachine") String name);
}
