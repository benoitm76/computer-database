package com.excilys.projet.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.excilys.projet.model.Company;
import com.excilys.projet.model.Computer;
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

	@RequestMapping(method = RequestMethod.GET)
	private String doGet(ModelMap model,
			@RequestParam(required = false) Long update)
			throws ServletException, IOException {

		if (update != null) {

			try {
				Computer c = computerService.find(update);
				model.addAttribute("computer", c);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		try {
			model.addAttribute("list_companies", computerService.findAll());
		} catch (SQLException e) {
			logger.error("Erreur lors de l'accès à la liste", e);
		}
		return "addComputer";
	}

	@RequestMapping(method = RequestMethod.POST)
	private void doPost(ModelMap model,
			@RequestParam(required = false) Long update,
			@RequestParam String name,
			@RequestParam(required = false) String introducedDate,
			@RequestParam(required = false) String discontinuedDate,
			@RequestParam(required = false) Long company)
			throws ServletException, IOException {
		boolean error = false;
		boolean isUpdate = false;
		List<String> message = new ArrayList<>();
		Computer computer = null;
		if (update != null) {
			try {
				computer = computerService.find(update);
				// request.setAttribute("computer", c);
				isUpdate = true;
			} catch (SQLException | NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (name.equals("")) {
			error = true;
			message.add("Name invalid");
			logger.error("Name invalid");
		}

		SimpleDateFormat dtf = new SimpleDateFormat("yyyy-MM-dd");
		Date cIntroducedDate = null;
		Date cDiscontinuedDate = null;

		if (introducedDate != null && !introducedDate.equals("")) {
			try {
				cIntroducedDate = dtf.parse(introducedDate);
			} catch (ParseException e) {
				error = true;
				message.add("Introduced date invalid");
				logger.error("Introduced date invalid", e);
			}
		} /*
		 * else if (request.getParameter("introducedDate") == null) { error =
		 * true; errorMessage.add("Introduced date null");
		 * logger.error("Introduced date null"); }
		 */

		if (discontinuedDate != null && !discontinuedDate.equals("")) {
			try {
				cDiscontinuedDate = dtf.parse(discontinuedDate);
			} catch (ParseException e) {
				error = true;
				message.add("Discontinued date invalid");
				logger.error("Discontinued date invalid", e);
			}
		} /*
		 * else if (request.getParameter("discontinuedDate") == null) { error =
		 * true; errorMessage.add("Discontinued date null");
		 * logger.error("Discontinued date null"); }
		 */

		Company c = null;
		/*
		 * if (request.getParameter("company") == null) { error = true; } else
		 */if (company.equals("0")) {
			try {
				c = companyService.find(company);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				error = true;
				message.add("Error when get company");
				logger.error("Error when get company", e);
			}
			if (c == null) {
				error = true;
				message.add("Company not found");
				logger.error("Company non trouvé");
			}
		}

		if (!error) {
			if (isUpdate) {
				try {
					computer.setName(name);
					computer.setIntroduced(cIntroducedDate);
					computer.setDiscontinued(cDiscontinuedDate);
					computer.setCompany(c);
					computerService.update(computer);
					message.add("Computer updated");
				} catch (SQLException e) {
					error = true;
					message.add("Error when update computer");
					logger.error("Error when update computer", e);
				}
			} else {
				try {
					computerService.create(new Computer(0, name,
							cIntroducedDate, cDiscontinuedDate, c));
					message.add("Computer inserted");
				} catch (SQLException e) {
					error = true;
					message.add("Error when insert new computer");
					logger.error("Error when insert new computer", e);
				}
			}
		}
		model.addAttribute("error", error);
		model.addAttribute("message", message);
		doGet(model, update);
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
