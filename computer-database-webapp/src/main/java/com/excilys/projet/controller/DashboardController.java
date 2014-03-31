package com.excilys.projet.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.excilys.projet.domain.Computer;
import com.excilys.projet.service.ComputerService;

@Controller
@RequestMapping("/dashboard")
public class DashboardController {
	final Logger logger = LoggerFactory.getLogger(DashboardController.class);

	@Autowired
	private ComputerService computerService;

	@RequestMapping(method = RequestMethod.GET)
	private String doGet(ModelMap model, Pageable pageable,
			@RequestParam(required = false) String search) {
		Page<Computer> page = null;

		if (pageable.getSort() == null) {
			pageable = new PageRequest(pageable.getPageNumber(),
					pageable.getPageSize(), Direction.ASC, "name");
		}

		if (search == null) {
			page = computerService.findAll(pageable);
		} else {
			model.addAttribute("search", search);
			page = computerService.findAllByName(search, pageable);
		}

		if (page.getSort() != null) {
			Order order = page.getSort().iterator().next();
			model.addAttribute("order", order.getProperty());
			model.addAttribute("dir", order.getDirection().name());
		}
		model.addAttribute("page", page);
		return "dashboard";
	}

	@RequestMapping(method = RequestMethod.POST)
	private void doPost(ModelMap model, Pageable pageable,
			@RequestParam(required = false) String search, @RequestParam long id) {
		List<String> message = new ArrayList<>();

		computerService.delete(id);
		model.addAttribute("error", false);
		message.add("dashboard.success.delete");

		model.addAttribute("message", message);

		doGet(model, pageable, search);
	}
}
