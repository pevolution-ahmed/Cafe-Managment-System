package com.playstation.ms.services;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.playstation.ms.models.Item;

public interface ItemRepository extends JpaRepository<Item, Integer>{

	List<Item>findAllByPrice(double price,Pageable pageable);
	
}
