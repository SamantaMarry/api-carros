package com.wswork.teste.samantamarry.apicarros.repository;

import com.wswork.teste.samantamarry.apicarros.domain.Cars;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarsRepository extends JpaRepository<Cars, Long> {


}
