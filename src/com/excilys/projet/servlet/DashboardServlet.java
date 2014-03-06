package com.excilys.projet.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.excilys.projet.model.ComputerOrder;
import com.excilys.projet.service.ComputerService;

/**
 * Servlet implementation class DashboardServlet
 */
@WebServlet({ "/dashboard" })
public class DashboardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	final Logger logger = LoggerFactory.getLogger(DashboardServlet.class);

	@Autowired
	private ComputerService computerService;

	@Override
	public void init() throws ServletException {
		super.init();
		SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this,
				getServletContext());
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
		Map<String, String> queryParameters = new HashMap<>();
		if (order != null) {
			queryParameters.put("order", order.getUrlParameter());
		}
		try {
			int numberOfResult = 0;
			if (request.getParameter("search") == null) {
				numberOfResult = computerService.count(
						null);
				numberOfPage = (numberOfResult / 10) + 1;
				if (page < 1 || page > numberOfPage) {
					page = 1;
				}
				request.setAttribute(
						"list_computers",
						computerService.findAllByCreteria(null, order,
										(page - 1) * 10, 10));
			} else {
				numberOfResult = computerService.count(
						request.getParameter("search"));
				numberOfPage = (numberOfResult / 10) + 1;
				queryParameters.put("search", request.getParameter("search"));
				if (page < 1 || page > numberOfPage) {
					page = 1;
				}
				request.setAttribute(
						"list_computers",
						computerService
								.findAllByCreteria(
										request.getParameter("search"), order,
										(page - 1) * 10, 10));
			}
			request.setAttribute("current_page", page);
			request.setAttribute("last_page", numberOfPage);
			request.setAttribute("number_of_result", numberOfResult);
		} catch (SQLException e) {
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
				computerService.delete(
						Long.parseLong(request.getParameter("id")));
				message.add("Computer deleted");
			} catch (NumberFormatException | SQLException e) {
				logger.error("Error when delete computer", e);
				message.add("Error when delete computer");
			}
			request.setAttribute("message", message);
		}

		doGet(request, response);
	}

	public ComputerOrder getOrder(HttpServletRequest request) {
		ComputerOrder order = null;
		if (request.getParameter("order") != null) {
			switch (request.getParameter("order")) {
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
		request.setAttribute("order", order);
		return order;
	}

	public ComputerService getComputerService() {
		return computerService;
	}

	public void setComputerService(ComputerService computerService) {
		this.computerService = computerService;
	}

}
