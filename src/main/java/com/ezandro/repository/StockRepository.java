package com.ezandro.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ezandro.model.Stock;

@Repository
public interface StockRepository extends JpaRepository<Stock, Long> {

	Optional<Stock> findByNameAndDate(String name, LocalDate date);

	@Query("SELECT stock FROM Stock stock WHERE stock.name = :name AND stock.date = :date AND stock.id <> :id")
	Optional<Stock> findByStockUpdate(@Param(value = "name") String name, @Param(value = "date") LocalDate date,
			@Param(value = "id") Long id);

	@Query("SELECT stock FROM Stock stock WHERE stock.date = :date")
	Optional<List<Stock>> findByToday(@Param(value = "date") LocalDate date);

}