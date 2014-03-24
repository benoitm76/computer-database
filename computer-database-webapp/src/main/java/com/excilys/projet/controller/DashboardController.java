package com.excilys.projet.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.excilys.projet.binding.ComputerDTO;
import com.excilys.projet.binding.ComputerDTOMapper;
import com.excilys.projet.model.Computer;
import com.excilys.projet.model.ComputerOrder;
import com.excilys.projet.model.Page;
import com.excilys.projet.service.ComputerService;

@Controller
@RequestMapping("/dashboard")
public class DashboardController {
	final Logger logger = LoggerFactory.getLogger(DashboardController.class);
	@Autowired
	private ComputerService computerService;

	@Autowired
	private ComputerDTOMapper computerDTOMapper;

	@RequestMapping(method = RequestMethod.GET)
	private String doGet(ModelMap model,
			@RequestParam(defaultValue = "1", required = false) int page,
			@RequestParam(required = false) String order,
			@RequestParam(required = false) String search) {

		int numberOfPage = 1;

		ComputerOrder computerOrder = getOrder(order, model);
		Map<String, String> queryParameters = new HashMap<>();
		Page<Computer, ComputerOrder> pageWrapper = null;

		int numberOfResult = 0;
		if (search == null) {
			numberOfResult = computerService.count(null);
			numberOfPage = (numberOfResult / 10) + 1;
			if (page < 1 || page > numberOfPage) {
				page = 1;
			}
			pageWrapper = computerService.findAllByCreteria(null,
					computerOrder, (page - 1) * 10, 10);
		} else {
			numberOfResult = computerService.count(search);
			numberOfPage = (numberOfResult / 10) + 1;
			queryParameters.put("search", search);
			if (page < 1 || page > numberOfPage) {
				page = 1;
			}
			pageWrapper = computerService.findAllByCreteria(search,
					computerOrder, (page - 1) * 10, 10);
		}
		Page<ComputerDTO, ComputerOrder> pageDTO = computerDTOMapper
				.convertPage(pageWrapper);
		model.addAttribute("page_wrapper", pageDTO);
		
		if (computerOrder != null) {
			queryParameters.put("order", computerOrder.getUrlParameter());
		}

		if (page > 1) {
			queryParameters.put("page", page + "");
		}
		model.addAttribute("query_parameters", queryParameters);
		return "dashboard";
	}

	@RequestMapping(method = RequestMethod.POST)
	private void doPost(ModelMap model, @RequestParam long id) {
		List<String> message = new ArrayList<>();

		computerService.delete(id);
		model.addAttribute("error", false);
		message.add("dashboard.success.delete");

		model.addAttribute("message", message);
		doGet(model, 0, null, null);
	}

	public ComputerOrder getOrder(String value, ModelMap model) {
		ComputerOrder order = null;
		if (value != null) {
			switch (value) {
			case "orderByNameAsc":
				order = ComputerOrder.ORDER_BY_NAME_ASC;
				break;
			case "orderByNameDesc":
				order = ComputerOrder.ORDER_BY_NAME_DESC;
				break;
			case "orderByIntroducedDateAsc":
				order = ComputerOrder.ORDER_BY_INTRODUCED_DATE_ASC;
				break;
			case "orderByIntroducedDateDesc":
				order = ComputerOrder.ORDER_BY_INTRODUCED_DATE_DESC;
				break;
			case "orderByDiscontinuedDateAsc":
				order = ComputerOrder.ORDER_BY_DISCONTINUED_DATE_ASC;
				break;
			case "orderByDiscontinuedDateDesc":
				order = ComputerOrder.ORDER_BY_DISCONTINUED_DATE_DESC;
				break;
			case "orderByCompanyNameAsc":
				order = ComputerOrder.ORDER_BY_COMPANY_NAME_ASC;
				break;
			case "orderByCompanyNameDesc":
				order = ComputerOrder.ORDER_BY_COMPANY_NAME_DESC;
				break;
			default:
				break;
			}
		}
		model.addAttribute("order", order);
		return order;
	}
}
