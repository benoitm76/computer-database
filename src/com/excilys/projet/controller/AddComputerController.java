package com.excilys.projet.controller;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
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

		if (update != null) {

			try {
				Computer c = computerService.find(update);
				cDTO = DaoComputer.createDTO(c);
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
		}
		return "addComputer";
	}

	@RequestMapping(method = RequestMethod.POST)
	private String doPost(@RequestParam(required = false) Long update,
			@Valid @ModelAttribute("cDTO") ComputerDTO cDTO,
			BindingResult result, ModelMap model) {
		boolean isUpdate = false;

		if (update != null) {
			try {
				Computer computer = computerService.find(update);
				if (computer != null) {
					isUpdate = true;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (!result.hasErrors()) {
			if (isUpdate) {
				try {
					computerService.update(DaoComputer.createEntity(cDTO));

				} catch (SQLException e) {

					logger.error("Error when update computer", e);
				}
			} else {
				try {
					computerService.create(DaoComputer.createEntity(cDTO));

				} catch (SQLException e) {

					logger.error("Error when insert new computer", e);
				}
			}
		}

		System.out.println(cDTO);
		model.addAttribute("cDTO", cDTO);
		try {
			model.addAttribute("list_companies", companyService.findAll());
		} catch (SQLException e) {
			logger.error("Erreur lors de l'accès à la liste", e);
		}
		return "addComputer";
		// doGet(model, update);
	}

	@InitBinder
	private void dateBinder(WebDataBinder binder) {
		// The date format to parse or output your dates
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		// Create a new CustomDateEditor
		CustomDateEditor editor = new CustomDateEditor(dateFormat, true);
		// Register it as custom editor for the Date type
		binder.registerCustomEditor(Date.class, editor);
		binder.addValidators(computerValidator);
	}

	public CompanyService getCompanyService() {
		return companyService;
	}

	public void setCompanyService(CompanyService companyService) {
		this.companyService = companyService;
	}

	public ComputerService getComputerService() {
		return computerService;
	}

	public void setComputerService(ComputerService computerService) {
		this.computerService = computerService;
	}

}
