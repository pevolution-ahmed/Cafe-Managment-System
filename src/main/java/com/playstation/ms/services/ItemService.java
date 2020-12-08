package com.playstation.ms.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import com.playstation.ms.models.Item;
import com.playstation.ms.models.ItemForm;
@Service

public class ItemService {
	@Autowired
	private ItemRepository itemRepository;
	@Autowired
	private CategoryRepository categoryRepository;
	public List<Item> getAll(){
		return itemRepository.findAll();
	}
	
	public Item getOne(int id) {
		return itemRepository.findById(id).get();
	}
	public Page<Item> getItemsPerPage(int currentPage){
		return itemRepository.findAll(PageRequest.of(currentPage-1, 15));
	}
	public long getNumberOfAllItems(){
		return itemRepository.count();
	}
//	public long getNumberOfPages(int itemPerPage) {
//		long numberOfPages,numberOfAllItems = getNumberOfAllItems();
//		double percentageOfPagesPerTen = ((double)numberOfAllItems / itemPerPage);
//		if(numberOfAllItems <= 10) {
//			numberOfPages = 1;
//		}
//		else if(Math.round((double)(percentageOfPagesPerTen)) < percentageOfPagesPerTen) {
//			numberOfPages = Math.round((double)(percentageOfPagesPerTen)) + 1;
//		}
//		else {
//			numberOfPages = Math.round((double)(percentageOfPagesPerTen));
//		}
//		return numberOfPages;
//		
//	}
	public Item saveItem(ItemForm item) {
		Item newItem = new Item();
		newItem.setName(item.getName());
		newItem.setPrice(Double.valueOf(item.getPrice()));
		newItem.setStock(Double.valueOf(item.getStock()));
		newItem.setCategory(categoryRepository.getOne(Integer.valueOf(item.getCategory_id())));
		itemRepository.save(newItem);
		return newItem;
	}
	public Item updateItem(int id ,ItemForm item) {
		Item itemToUpdate = itemRepository.getOne(id);
		System.out.println("update id is::: "+id);
		itemToUpdate.setName(item.getName());
		itemToUpdate.setPrice(Double.valueOf(item.getPrice()));
		itemToUpdate.setStock(Double.valueOf(item.getStock()));
		itemToUpdate.setCategory(categoryRepository.getOne(Integer.valueOf(item.getCategory_id())));
		return itemRepository.save(itemToUpdate);
	}
	
	public String deleteItem(int id) {
		itemRepository.deleteById(id);
		return "Item "+id+" have been deleted";
	}

}
