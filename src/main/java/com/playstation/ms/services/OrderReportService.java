package com.playstation.ms.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class OrderReportService {

	@Autowired
	private OrderReportRepository orderReportRepository;
	
	public OrderReport createOrderReport(OrderReport oR) {
		return orderReportRepository.save(oR);
	}
}
