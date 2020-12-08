package com.playstation.ms.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.playstation.ms.models.Item;
import com.playstation.ms.models.ItemForm;
import com.playstation.ms.models.User;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	public User registerNewUser(User user) {
		return userRepository.save(user);
	}
	public List<User> getAllUsers(){
		return userRepository.findAll();
	}
	public User getOne(int id) {
		return userRepository.getOne(id);
	}
	
	public void deleteUser(int id) {
		userRepository.deleteById(id);
	}
	public Page<User> getItemsPerPage(int currentPage){
		return userRepository.findAll(PageRequest.of(currentPage-1, 15));
	}
//	public User saveUser(ItemForm item) {
//		User newUser = new User();
//		newUser.setName(item.getName());
//		newUser.setPrice(Double.valueOf(item.getPrice()));
//		newUser.setStock(Double.valueOf(item.getStock()));
//		newUser.setCategory(categoryRepository.getOne(Integer.valueOf(item.getCategory_id())));
//		itemRepository.save(newUser);
//		return newUser;
//	}
//	public Item updateItem(int id ,ItemForm item) {
//		User userToUpdate = userRepository.getOne(id);
//		userToUpdate.setUserName(item.getName());
//		userToUpdate.set(Double.valueOf(item.getPrice()));
//		userToUpdate.setStock(Double.valueOf(item.getStock()));
//		userToUpdate.setCategory(categoryRepository.getOne(Integer.valueOf(item.getCategory_id())));
//		return itemRepository.save(userToUpdate);
//	}
//	
//	public String deleteItem(int id) {
//		itemRepository.deleteById(id);
//		return "Item "+id+" have been deleted";
//	}
	
}
