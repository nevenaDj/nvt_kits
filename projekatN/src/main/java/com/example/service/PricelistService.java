package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.model.Apartment;
import com.example.model.Pricelist;
import com.example.repository.ApartmentRepository;
import com.example.repository.PricelistRepository;

@Service
public class PricelistService {
	@Autowired
	PricelistRepository pricelistRepository;

	public Pricelist findOne(Long id) {
		return pricelistRepository.findOne(id);
	}

	public Page<Pricelist> findAll(Pageable page) {
		return pricelistRepository.findAll(page);
	}

	public Pricelist save(Pricelist pricelist) {
		return pricelistRepository.save(pricelist);
	}

	public void remove(Long id) {
		pricelistRepository.delete(id);
	}

}