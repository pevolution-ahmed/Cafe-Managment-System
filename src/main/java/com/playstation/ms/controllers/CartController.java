package com.playstation.ms.controllers;

import java.util.ArrayList;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.jni.Time;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.AuthenticatedPrincipal;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.playstation.ms.models.Cart;
import com.playstation.ms.models.CartLine;
import com.playstation.ms.models.CheckoutWithoutCart;
import com.playstation.ms.models.Device;
import com.playstation.ms.models.Item;
import com.playstation.ms.models.ItemsList;
import com.playstation.ms.services.CartService;
import com.playstation.ms.services.DeviceService;
import com.playstation.ms.services.ItemService;
import com.playstation.ms.services.OrderReport;
import com.playstation.ms.services.OrderReportService;

// TODO: Do Functional Programming Processing
// TODO: Do Post request for important data
// TODO: DO PRG Pattern
// TODO: Separate Logic to Service Layer
@SessionAttributes({"cartt"})
@Controller
public class CartController {
	@Autowired
	private CartService cartService;
	@Autowired
	private DeviceService deviceService;
	@Autowired
	private ItemService itemService;
	@Autowired
	private OrderReportService orderReportService;

	@GetMapping("/staff/dashboard/devices")
	String mainView(Model model, @ModelAttribute("card") CheckoutWithoutCart card, HttpServletRequest req) {
		// TODO: get all devices to display

		List<Device> devices = deviceService.getAll();
		model.addAttribute("devices", devices);
		return "dashboard";
	}
	@GetMapping("/staff/dashboard/devices/{id}/redirect")
	String mainRedirect(RedirectAttributes model, @ModelAttribute("card") CheckoutWithoutCart card,
			@PathVariable int id,Model model2) {
		model2.addAttribute("cartt", card);
		System.out.println(card.getTypeOfDevice() + "asdsadasdasdasda");
		if (card.getSubmit().equals("Checkout")) {
			model.addAttribute("submit", "CheckoutwithoutCart");
			return "redirect:/staff/dashboard/devices/" + id + "/checkout";
		} else {
			return "redirect:/staff/dashboard/devices/" + id + "/add-items";

		}

	}

	@GetMapping(value = { "/staff/dashboard/devices/{deviceId}/add-items",
			"/staff/dashboard/devices/{deviceId}/add-items/{pageNumber}" })
	String addItemsToCart(Model model, @PathVariable int deviceId,
			@PathVariable(value = "pageNumber") Optional<Integer> page, RedirectAttributes reModel,
			@ModelAttribute ItemsList itemsList, HttpServletRequest req) {
//		String principal = SecurityContextHolder.getContext().getAuthentication().getName();
		System.out.println("PAGE:"+page);
		int currentPage = page.orElse(1);
		Page<Item> itemPages = itemService.getItemsPerPage(currentPage);
		model.addAttribute("items", itemPages.getContent());
		model.addAttribute("has_next", itemPages.hasNext());
		model.addAttribute("has_prev", itemPages.hasPrevious());
		model.addAttribute("current_page", currentPage);
		if (itemPages.getTotalPages() > 0) {
			List<Integer> pageNumbers = IntStream.rangeClosed(1, itemPages.getTotalPages()).boxed()
					.collect(Collectors.toList());

			model.addAttribute("pageNumbers", pageNumbers);
		}
		Device selectedDevice = deviceService.getOne(deviceId);
		model.addAttribute("selectedDevice", selectedDevice);
//		deviceService.getByName(name)
		return "add-items";
	}

	@GetMapping("/staff/dashboard/devices/{id}/checkout")
	String checkout(@PathVariable int id, Model model, @RequestParam(name = "submit") String subString, @ModelAttribute ItemsList itemsList) {
		// TODO: if the device have a null cart then proceed with Checkoutwithoutcart
		// else proceed for checkwithcart
		Device selectedDevice = deviceService.getOne(id);
//		System.out.println("subString=" + subString);
		CheckoutWithoutCart card = (CheckoutWithoutCart) model.getAttribute("cartt");
		System.out.println("CARD IN HERE:"+card.getTypeOfDevice());
		double totalPrice = 0;
		if (subString.equals("Checkout with cart")) {
			if (!itemsList.getNewDeviceType().equals("null") && !itemsList.getNewDeviceName().equals("null")) {
				card.setTypeOfDevice(itemsList.getNewDeviceType());
				selectedDevice = deviceService.getByName(itemsList.getNewDeviceName());
			}
			System.out.println("THIS DEVICE HAS A CARTTTT?!!!");
			int index = 0;
			for (Item i : itemsList.getItems()) {
				if (i != null) {
					totalPrice += i.getPrice() * itemsList.getQuantityList().get(index);
				}
				index++;
			}
		} else {
			System.out.println("This device hase no cart");
		}
		// TODO: how to know if he is coming from dashboard/devices or add-item?
		// GET the cart from device id and if the device doesn't hava a
		// cart then its solved
		// TODO: calculate the price according to time and NumOfPlayers
		// 30 / 1.98 = 15.15,48:06 ==> 48.06 / 1.98 = 24.27
		// price = time
		if (card.getCartTime() != null && !card.getCartTime().getMinutes().isEmpty()) {
			totalPrice += Double.parseDouble(card.getCartTime().getMinutes()) / 1.71;
		}
		// TODO: add attribute for checkout page
		model.addAttribute("deviceName", selectedDevice.getName());
		model.addAttribute("deviceTime", card.getCartTime());
		model.addAttribute("deviceType", card.getTypeOfDevice());
		model.addAttribute("devicePlayerNumber", card.getNumberOfPlayer());
		model.addAttribute("devicePrice", totalPrice);
		// TODO: create an Order Report and save it in the DB
		OrderReport orderReport = new OrderReport();
		String principal = SecurityContextHolder.getContext().getAuthentication().getName();
		orderReport.setCashierName(principal);
		// TODO: let the user enter the orderFor field in the add items page
		// TODO: then set it here in order Report
//		if(SecurityContextHolder.getContext().getAuthentication().getAuthorities().)
//		orderReport.se
//		orderReportService.createOrderReport()
		// TODO: delete the device cart from database
		selectedDevice.setCart(null);
		deviceService.saveDevice(selectedDevice);
		return "checkout";
	}

}
