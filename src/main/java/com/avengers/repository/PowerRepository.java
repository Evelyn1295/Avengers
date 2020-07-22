package com.avengers.repository;

import com.avengers.model.Power;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PowerRepository  extends JpaRepository<Power, Integer> {
}
