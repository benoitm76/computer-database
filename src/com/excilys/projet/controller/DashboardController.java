package com.excilys.projet.controller;

import java.sql.SQLException;
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

import com.excilys.projet.dao.DaoComputer;
import com.excilys.projet.model.Computer;
import com.excilys.projet.model.ComputerOrder;
import com.excilys.projet.model.dto.ComputerDTO;
import com.excilys.projet.service.ComputerService;

@Controller
@RequestMapping("/dashboard")
public class DashboardController {
	final Logger logger = LoggerFactory.getLogger(DashboardController.class);
	@Autowired
	private ComputerService computerService;

	@RequestMapping(method = RequestMethod.GET)
	private String doGet(ModelMap model,
			@RequestParam(defaultValue = "1", required = false) int page,
			@RequestParam(required = false) String order,
			@RequestParam(required = false) String search) {

		int numberOfPage = 1;

		ComputerOrder computerOrder = getOrder(order, model);
		Map<String, String> queryParameters = new HashMap<>();
		if (computerOrder != null) {
			queryParameters.put("order", computerOrder.getUrlParameter());
		}
		List<Computer> computers = new ArrayList<>();
		try {
			int numberOfResult = 0;
			if (search == null) {
				numberOfResult = computerService.count(null);
				numberOfPage = (numberOfResult / 10) + 1;
				if (page < 1 || page > numberOfPage) {
					page = 1;
				}
				computers =  computerService
						.findAllByCreteria(null, computerOrder,
								(page - 1) * 10, 10);
			} else {
				numberOfResult = computerService.count(search);
				numberOfPage = (numberOfResult / 10) + 1;
				queryParameters.put("search", search);
				if (page < 1 || page > numberOfPage) {
					page = 1;
				}
				computers = computerService
						.findAllByCreteria(search, computerOrder,
								(page - 1) * 10, 10);
			}
			model.addAttribute("current_page", page);
			model.addAttribute("last_page", numberOfPage);
			model.addAttribute("number_of_result", numberOfResult);
			List<ComputerDTO> computersDTO = new ArrayList<>();
			for(Computer c : computers)
			{
				computersDTO.add(DaoComputer.createDTO(c));
			}
			model.addAttribute("list_computers", computersDTO);
		} catch (SQLException e) {
			logger.error("Erreur lors de l'accès à la liste", e);
		}
		model.addAttribute("query_parameters", queryParameters);
		return "dashboard";
	}

	@RequestMapping(method = RequestMethod.POST)
	private void doPost(ModelMap model, @RequestParam long id) {
		List<String> message = new ArrayList<>();

		try {
			computerService.delete(id);
			message.add("Computer deleted");
		} catch (SQLException e) {
			logger.error("Error when delete computer", e);
			message.add("Error when delete computer");
		}
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

	public ComputerService getComputerService() {
		return computerService;
	}

	public void setComputerService(ComputerService computerService) {
		this.computerService = computerService;
	}

}
