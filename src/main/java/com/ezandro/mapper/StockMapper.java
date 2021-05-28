package com.ezandro.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.ezandro.model.Stock;
import com.ezandro.model.dto.StockDTO;

@Component
public class StockMapper {

	public Stock toEntity(StockDTO dto) {
		Stock stock = new Stock();
		stock.setId(dto.getId());
		stock.setName(dto.getName());
		stock.setPrice(dto.getPrice());
		stock.setVariation(dto.getVariation());
		stock.setDate(dto.getDate());
		return stock;
	}

	public StockDTO toDTO(Stock stock) {
		StockDTO stockDTO = new StockDTO();
		stockDTO.setId(stock.getId());
		stockDTO.setName(stock.getName());
		stockDTO.setPrice(stock.getPrice());
		stockDTO.setVariation(stock.getVariation());
		stockDTO.setDate(stock.getDate());
		return stockDTO;
	}

	public List<StockDTO> toDTO(List<Stock> listStock) {
		return listStock.stream().map(x -> new StockMapper().toDTO(x)).collect(Collectors.toList());
	}

}