package com.excilys.projet.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.excilys.projet.controller.validator.ComputerValidator;
import com.excilys.projet.dao.DaoComputer;
import com.excilys.projet.model.Computer;
import com.excilys.projet.model.dto.ComputerDTO;
import com.excilys.projet.service.CompanyService;
import com.excilys.projet.service.ComputerService;

@Controller
@RequestMapping("/addComputer")
public class AddComputerController {

	final Logger logger = LoggerFactory.getLogger(AddComputerController.class);

	@Autowired
	private CompanyService companyService;
	@Autowired
	private ComputerService computerService;

	@Autowired
	private ComputerValidator computerValidator;

	@RequestMapping(method = RequestMethod.GET)
	private String doGet(ModelMap model,
			@RequestParam(required = false) Long update) {

		ComputerDTO cDTO = new ComputerDTO();
		Map<String, String> queryParameters = new HashMap<>();

		if (update != null) {

			try {
				Computer c = computerService.find(update);
				cDTO = DaoComputer.createDTO(c);
				queryParameters.put("update", update + "");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		
		model.addAttribute("cDTO", cDTO);
		try {
			model.addAttribute("list_companies", companyService.findAll());
		} catch (SQLException e) {
			logger.error("Erreur lors de l'accès à la liste", e);
			List<String> message = new ArrayList<>();
			model.addAttribute("message", message);
			model.addAttribute("error", true);
			message.add("add_computer.error.list_companies");
		}
		model.addAttribute("query_parameters", queryParameters);
		return "addComputer";
	}

	@RequestMapping(method = RequestMethod.POST)
	private String doPost(@RequestParam(required = false) Long update,
			@Valid @ModelAttribute("cDTO") ComputerDTO cDTO,
			BindingResult result, ModelMap model) {
		boolean isUpdate = false;
		
		Map<String, String> queryParameters = new HashMap<>();		
		List<String> message = new ArrayList<>();
		
		model.addAttribute("message", message);
		if (update != null) {
			try {
				Computer computer = computerService.find(update);
				if (computer != null) {
					isUpdate = true;
					queryParameters.put("update", update + "");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				message.add(e.getMessage());
				e.printStackTrace();
			}
		}
		if (!result.hasErrors()) {
			if (isUpdate) {
				try {
					computerService.update(DaoComputer.createEntity(cDTO));
					message.add("add_computer.success.update");
					model.addAttribute("error", false);
				} catch (SQLException e) {

					logger.error("Error when update computer", e);
					message.add("add_computer.error.update");
					model.addAttribute("error", true);
				}
			} else {
				try {
					computerService.create(DaoComputer.createEntity(cDTO));
					cDTO = new ComputerDTO();
					message.add("add_computer.success.insert");
					model.addAttribute("error", false);
				} catch (SQLException e) {

					logger.error("Error when insert new computer", e);
					message.add("add_computer.error.insert");
					model.addAttribute("error", true);
				}
			}
		}
		else
		{
			
		}
		model.addAttribute("cDTO", cDTO);
		model.addAttribute("query_parameters", queryParameters);
		try {
			model.addAttribute("list_companies", companyService.findAll());
		} catch (SQLException e) {
			logger.error("Erreur lors de l'accès à la liste", e);
		}
		return "addComputer";
		// doGet(model, update);
	}

	@InitBinder
	private void binder(WebDataBinder binder) {
		binder.addValidators(computerValidator);
	}
}
