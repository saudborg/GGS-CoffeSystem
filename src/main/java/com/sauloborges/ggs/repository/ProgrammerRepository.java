package com.sauloborges.ggs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sauloborges.ggs.domain.Programmer;

@Repository
public interface ProgrammerRepository extends JpaRepository<Programmer, Integer> {

}
