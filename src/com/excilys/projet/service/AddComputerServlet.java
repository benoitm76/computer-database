package com.excilys.projet.service;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.projet.dao.DaoCompany;
import com.excilys.projet.dao.DaoComputer;
import com.excilys.projet.model.Company;
import com.excilys.projet.model.Computer;

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
				Computer c = DaoComputer.getInstance().getComputer(
						Long.parseLong(request.getParameter("update")));
				request.setAttribute("computer", c);
			} catch (NamingException | SQLException | NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		RequestDispatcher rd = getServletContext().getRequestDispatcher(
				"/addComputer.jsp");

		try {
			request.setAttribute("list_companies", DaoCompany.getInstance()
					.getAllCompany());
		} catch (NamingException | SQLException e) {
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
		Computer computer = null;
		if (request.getParameter("update") != null) {
			try {
				computer = DaoComputer.getInstance().getComputer(
						Long.parseLong(request.getParameter("update")));
				// request.setAttribute("computer", c);
				isUpdate = true;
			} catch (NamingException | SQLException | NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (request.getParameter("name") == null
				|| request.getParameter("name").equals("")) {
			error = true;
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
				logger.error("Introduce date invalid", e);
			}
		} else if (request.getParameter("introducedDate") == null) {
			error = true;
			logger.error("Introduce date null");
		}

		if (request.getParameter("discontinuedDate") != null
				&& !request.getParameter("discontinuedDate").equals("")) {
			try {
				discontinuedDate = dtf.parse(request.getParameter(
						"discontinuedDate").toString());
			} catch (ParseException e) {
				error = true;
				logger.error("Discontinued date invalid", e);
			}
		} else if (request.getParameter("discontinuedDate") == null) {
			error = true;
			logger.error("Discontinued date null");
		}

		Company c = null;
		if (request.getParameter("company") == null) {
			error = true;
		} else if (!request.getParameter("company").equals("0")) {
			try {
				c = DaoCompany.getInstance().getCompany(
						Long.parseLong(request.getParameter("company")
								.toString()));
			} catch (NamingException | SQLException | NumberFormatException e) {
				// TODO Auto-generated catch block
				error = true;
				logger.error("Erreur lors de la récupération de la company", e);
			}
			if (c == null) {
				error = true;
				logger.error("Company non trouvé");
			}
		}

		if (!error) {
			if (isUpdate) {
				try {
					computer.setName((String) request
							.getParameter("name"));
					computer.setIntroduced(introducedDate);
					computer.setDiscontinued(discontinuedDate);
					computer.setCompany(c);
					DaoComputer.getInstance().updateComputer(computer);
				} catch (SQLException | NamingException e) {
					error = true;
					logger.error("Erreur lors de l'insertion de l'ordinateur",
							e);
				}
			} else {
				try {
					DaoComputer.getInstance().addComputer(
							new Computer(0, (String) request
									.getParameter("name"), introducedDate,
									discontinuedDate, c));
				} catch (SQLException | NamingException e) {
					error = true;
					logger.error("Erreur lors de l'insertion de l'ordinateur",
							e);
				}
			}
		}
		request.setAttribute("error", error);
		doGet(request, response);
	}
}
