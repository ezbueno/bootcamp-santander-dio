package com.ezandro.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ezandro.model.dto.StockDTO;
import com.ezandro.service.StockService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/stock")
public class StockController {

	@Autowired
	private StockService stockService;

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<StockDTO> save(@Valid @RequestBody StockDTO dto) {
		dto = stockService.save(dto);
		return ResponseEntity.ok(dto);
	}

	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<StockDTO> update(@Valid @RequestBody StockDTO dto) {
		StockDTO newStockDTO = stockService.update(dto);
		return ResponseEntity.ok(newStockDTO);
	}

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<StockDTO>> findAll() {
		List<StockDTO> list = stockService.findAll();
		return ResponseEntity.ok(list);
	}

	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<StockDTO> findById(@PathVariable Long id) {
		StockDTO stockDTO = stockService.findById(id);
		return ResponseEntity.ok(stockDTO);
	}

	@DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<StockDTO> delete(@PathVariable Long id) {
		StockDTO stockDTO = stockService.delete(id);
		return ResponseEntity.ok(stockDTO);
	}

	@GetMapping(value = "/today", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<StockDTO>> findByToday() {
		List<StockDTO> listDTO = stockService.findByToday();
		return ResponseEntity.ok(listDTO);
	}

}