package com.db.project.repository;

import com.db.project.model.Indicators;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IndicatorsRepository extends JpaRepository<Indicators,Integer> {

}
