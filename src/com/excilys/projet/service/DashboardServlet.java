package com.excilys.projet.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.projet.dao.DaoComputer;
import com.excilys.projet.model.ComputerOrder;

/**
 * Servlet implementation class DashboardServlet
 */
@WebServlet({ "/dashboard" })
public class DashboardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	final Logger logger = LoggerFactory.getLogger(DashboardServlet.class);

	/**
	 * Default constructor.
	 */
	public DashboardServlet() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = getServletContext().getRequestDispatcher(
				"/dashboard.jsp");

		int page = 1;
		int numberOfPage = 1;
		if (request.getParameter("page") != null) {
			try {
				page = Integer.parseInt(request.getParameter("page"));
			} catch (NumberFormatException e) {

			}
		}

		ComputerOrder order = getOrder(request);
		List<String> queryParameters = new ArrayList<>();
		if (order != null) {
			queryParameters.add(order.getUrlParameter());
		}
		try {
			int numberOfResult = 0;
			if (request.getParameter("search") == null) {
				numberOfResult = DaoComputer.getInstance().getComputerCount(
						null);
				numberOfPage = (numberOfResult / 10) + 1;
				if (page < 1 || page > numberOfPage) {
					page = 1;
				}
				request.setAttribute(
						"list_computers",
						DaoComputer.getInstance().getAllComputer(order,
								(page - 1) * 10, 10));
			} else {
				numberOfResult = DaoComputer.getInstance().getComputerCount(
						request.getParameter("search"));
				numberOfPage = (numberOfResult / 10) + 1;
				queryParameters.add("search=" + request.getParameter("search"));
				if (page < 1 || page > numberOfPage) {
					page = 1;
				}
				request.setAttribute(
						"list_computers",
						DaoComputer.getInstance().searchComputer(
								request.getParameter("search"), order,
								(page - 1) * 10, 10));
			}
			request.setAttribute("current_page", page);
			request.setAttribute("last_page", numberOfPage);
			request.setAttribute("number_of_result", numberOfResult);
		} catch (NamingException | SQLException e) {
			logger.error("Erreur lors de l'accès à la liste", e);
		}
		request.setAttribute("query_parameters", queryParameters);
		rd.forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		List<String> message = new ArrayList<>();
		if (request.getParameter("id") != null) {
			try {
				DaoComputer.getInstance().deleteComputer(
						Long.parseLong(request.getParameter("id")));
				message.add("Computer deleted");
			} catch (NumberFormatException | SQLException | NamingException e) {
				logger.error("Error when delete computer", e);
				message.add("Error when delete computer");
			}
			request.setAttribute("message", message);
		}

		doGet(request, response);
	}

	public ComputerOrder getOrder(HttpServletRequest request) {
		ComputerOrder order = null;
		if (request.getParameter("orderByName") != null) {
			if (request.getParameter("orderByName").equals("asc")) {
				order = ComputerOrder.ORDER_BY_NAME_ASC;
			} else if (request.getParameter("orderByName").equals("desc")) {
				order = ComputerOrder.ORDER_BY_NAME_DESC;
			}
		} else if (request.getParameter("orderByIntroducedDate") != null) {
			if (request.getParameter("orderByIntroducedDate").equals("asc")) {
				order = ComputerOrder.ORDER_BY_INTRODUCED_DATE_ASC;
			} else if (request.getParameter("orderByIntroducedDate").equals(
					"desc")) {
				order = ComputerOrder.ORDER_BY_INTRODUCED_DATE_DESC;
			}
		} else if (request.getParameter("orderByDiscontinuedDate") != null) {
			if (request.getParameter("orderByDiscontinuedDate").equals("asc")) {
				order = ComputerOrder.ORDER_BY_DISCONTINUED_DATE_ASC;
			} else if (request.getParameter("orderByDiscontinuedDate").equals(
					"desc")) {
				order = ComputerOrder.ORDER_BY_DISCONTINUED_DATE_DESC;
			}
		} else if (request.getParameter("orderByCompanyName") != null) {
			if (request.getParameter("orderByCompanyName").equals("asc")) {
				order = ComputerOrder.ORDER_BY_COMPANY_NAME_ASC;
			} else if (request.getParameter("orderByCompanyName")
					.equals("desc")) {
				order = ComputerOrder.ORDER_BY_COMPANY_NAME_DESC;
			}
		}
		request.setAttribute("order", order);
		return order;
	}

}
