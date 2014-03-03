package com.excilys.projet.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.projet.model.Company;
import com.excilys.projet.model.Computer;
import com.excilys.projet.service.CompanyService;
import com.excilys.projet.service.ComputerService;

/**
 * Servlet implementation class AddComputerServlet
 */
@WebServlet("/addComputer")
public class AddComputerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	final Logger logger = LoggerFactory.getLogger(DashboardServlet.class);

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddComputerServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		if (request.getParameter("update") != null) {

			try {
				Computer c = ComputerService.getInstance().find(
						Long.parseLong(request.getParameter("update")));
				request.setAttribute("computer", c);
			} catch (SQLException | NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		RequestDispatcher rd = getServletContext().getRequestDispatcher(
				"/addComputer.jsp");

		try {
			request.setAttribute("list_companies", CompanyService.getInstance()
					.findAll());
		} catch (SQLException e) {
			logger.error("Erreur lors de l'accès à la liste", e);
		}
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		boolean error = false;
		boolean isUpdate = false;
		List<String> message = new ArrayList<>();
		Computer computer = null;
		if (request.getParameter("update") != null) {
			try {
				computer = ComputerService.getInstance().find(
						Long.parseLong(request.getParameter("update")));
				// request.setAttribute("computer", c);
				isUpdate = true;
			} catch (SQLException | NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (request.getParameter("name") == null
				|| request.getParameter("name").equals("")) {
			error = true;
			message.add("Name invalid");
			logger.error("Name invalid");
		}

		SimpleDateFormat dtf = new SimpleDateFormat("yyyy-MM-dd");
		Date introducedDate = null;
		Date discontinuedDate = null;

		if (request.getParameter("introducedDate") != null
				&& !request.getParameter("introducedDate").equals("")) {
			try {
				introducedDate = dtf.parse(request.getParameter(
						"introducedDate").toString());
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

		if (request.getParameter("discontinuedDate") != null
				&& !request.getParameter("discontinuedDate").equals("")) {
			try {
				discontinuedDate = dtf.parse(request.getParameter(
						"discontinuedDate").toString());
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
		 */if (!request.getParameter("company").equals("0")) {
			try {
				c = CompanyService.getInstance().find(
						Long.parseLong(request.getParameter("company")
								.toString()));
			} catch (SQLException | NumberFormatException e) {
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
					computer.setName((String) request.getParameter("name"));
					computer.setIntroduced(introducedDate);
					computer.setDiscontinued(discontinuedDate);
					computer.setCompany(c);
					ComputerService.getInstance().update(computer);
					message.add("Computer updated");
				} catch (SQLException e) {
					error = true;
					message.add("Error when update computer");
					logger.error("Error when update computer", e);
				}
			} else {
				try {
					ComputerService.getInstance().create(
							new Computer(0, (String) request
									.getParameter("name"), introducedDate,
									discontinuedDate, c));
					message.add("Computer inserted");
				} catch (SQLException e) {
					error = true;
					message.add("Error when insert new computer");
					logger.error("Error when insert new computer", e);
				}
			}
		}
		request.setAttribute("error", error);
		request.setAttribute("message", message);
		doGet(request, response);
	}
}
