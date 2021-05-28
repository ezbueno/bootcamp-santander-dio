package com.ezandro.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ezandro.exceptions.BusinessException;
import com.ezandro.exceptions.NotFoundException;
import com.ezandro.mapper.StockMapper;
import com.ezandro.model.Stock;
import com.ezandro.model.dto.StockDTO;
import com.ezandro.repository.StockRepository;
import com.ezandro.util.MessageUtils;

@Service
public class StockService {

	@Autowired
	private StockRepository stockRepository;

	@Autowired
	private StockMapper stockMapper;

	@Transactional
	public StockDTO save(StockDTO dto) {
		Optional<Stock> optionalStock = stockRepository.findByNameAndDate(dto.getName(), dto.getDate());

		if (optionalStock.isPresent()) {
			throw new BusinessException(MessageUtils.STOCK_ALREADY_EXISTS);
		}

		Stock stock = stockMapper.toEntity(dto);
		stockRepository.save(stock);
		return stockMapper.toDTO(stock);
	}

	@Transactional
	public StockDTO update(StockDTO dto) {
		Optional<Stock> optionalStock = stockRepository.findByStockUpdate(dto.getName(), dto.getDate(), dto.getId());

		if (optionalStock.isPresent()) {
			throw new BusinessException(MessageUtils.STOCK_ALREADY_EXISTS);
		}

		Stock stock = stockMapper.toEntity(dto);
		stockRepository.save(stock);
		return stockMapper.toDTO(stock);
	}

	@Transactional(readOnly = true)
	public List<StockDTO> findAll() {
		List<Stock> list = stockRepository.findAll();
		return stockMapper.toDTO(list);
	}

	@Transactional(readOnly = true)
	public StockDTO findById(Long id) {
		Optional<Stock> stock = stockRepository.findById(id);

		if (stock.isEmpty()) {
			throw new NotFoundException();
		}

		return stockMapper.toDTO(stock.get());
	}

	@Transactional
	public StockDTO delete(Long id) {
		StockDTO stockDTO = this.findById(id);
		stockRepository.deleteById(stockDTO.getId());
		return stockDTO;
	}

	@Transactional(readOnly = true)
	public List<StockDTO> findByToday() {
		return stockRepository.findByToday(LocalDate.now()).map(x -> stockMapper.toDTO(x))
				.orElseThrow(() -> new NotFoundException());
	}

}