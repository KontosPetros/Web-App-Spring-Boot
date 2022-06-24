package com.db.project.repository;

import com.db.project.model.Countries;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CountriesRepository extends JpaRepository<Countries,Integer> {

    @Query("FROM countries WHERE Country_Id= :Id")
    public List<Countries> findByCountryId(Integer Id);


}
