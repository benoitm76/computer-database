package com.excilys.projet.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.excilys.projet.domain.Computer;
import com.excilys.projet.dto.ComputerDTO;
import com.excilys.projet.mapper.ComputerDTOMapper;
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
	private ComputerDTOMapper computerDTOMapper;

	@RequestMapping(method = RequestMethod.GET)
	private String doGet(ModelMap model,
			@RequestParam(required = false) Long update) {

		ComputerDTO cDTO = new ComputerDTO();

		if (update != null && update != 0) {
			Computer c = computerService.find(update);
			cDTO = computerDTOMapper.createDTO(c);
			model.addAttribute("update", update + "");
		}

		model.addAttribute("cDTO", cDTO);

		model.addAttribute("list_companies", companyService.findAll());
		return "addComputer";
	}

	@RequestMapping(method = RequestMethod.POST)
	private String doPost(@RequestParam(required = false) Long update,
			@Valid @ModelAttribute("cDTO") ComputerDTO cDTO,
			BindingResult result, ModelMap model) {
		boolean isUpdate = false;

		List<String> message = new ArrayList<>();

		model.addAttribute("message", message);
		if (update != null) {

			Computer computer = computerService.find(update);
			if (computer != null) {
				isUpdate = true;
				model.addAttribute("update", update + "");
			}

		}
		if (!result.hasErrors()) {
			if (isUpdate) {
				computerService.update(computerDTOMapper.createEntity(cDTO));
				message.add("add_computer.success.update");
				model.addAttribute("error", false);
			} else {
				computerService.create(computerDTOMapper.createEntity(cDTO));
				cDTO = new ComputerDTO();
				message.add("add_computer.success.insert");
				model.addAttribute("error", false);
			}
		}
		model.addAttribute("cDTO", cDTO);

		model.addAttribute("list_companies", companyService.findAll());

		return "addComputer";
		// doGet(model, update);
	}
}
