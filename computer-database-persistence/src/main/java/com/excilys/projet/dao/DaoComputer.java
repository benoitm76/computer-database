package com.excilys.projet.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.excilys.projet.model.Company;
import com.excilys.projet.model.Computer;
import com.excilys.projet.model.ComputerOrder;

@Repository
public class DaoComputer extends Dao<Computer> implements DaoCriteria<Computer> {

	@PersistenceContext(unitName = "entityManagerFactory")
	private EntityManager entityManager;

	public void create(Computer c) {
		entityManager.persist(c);
	}

	public Computer find(long id) {
		return (Computer) entityManager.find(Computer.class, new Long(id));

	}

	public List<Computer> findAllByCreteria(String search, ComputerOrder order,
			int startAt, int numberOfRows) {

		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Computer> cQuery = builder.createQuery(Computer.class);
		Root<Computer> computerRoot = cQuery.from(Computer.class);
		cQuery.select(computerRoot);
		StringBuilder querySearch = new StringBuilder("%").append(search)
				.append("%");
		Join<Computer, Company> company = computerRoot.join("company",
				JoinType.LEFT);

		if (search != null) {
			cQuery.where(builder.or(builder.like(
					company.get("name").as(String.class),
					querySearch.toString()), builder.like(
					computerRoot.get("name").as(String.class),
					querySearch.toString())));
		}
		if (order != null) {
			switch (order) {
			case ORDER_BY_NAME_ASC:
				cQuery.orderBy(builder.asc(computerRoot.get("name")));
				break;
			case ORDER_BY_NAME_DESC:
				cQuery.orderBy(builder.desc(computerRoot.get("name")));
				break;
			case ORDER_BY_INTRODUCED_DATE_ASC:
				cQuery.orderBy(builder.asc(computerRoot.get("introduced")));
				break;
			case ORDER_BY_INTRODUCED_DATE_DESC:
				cQuery.orderBy(builder.desc(computerRoot.get("introduced")));
				break;
			case ORDER_BY_DISCONTINUED_DATE_ASC:
				cQuery.orderBy(builder.asc(computerRoot.get("discontinued")));
				break;
			case ORDER_BY_DISCONTINUED_DATE_DESC:
				cQuery.orderBy(builder.desc(computerRoot.get("discontinued")));
				break;
			case ORDER_BY_COMPANY_NAME_ASC:
				cQuery.orderBy(builder.asc(company.get("name")));
				break;
			case ORDER_BY_COMPANY_NAME_DESC:
				cQuery.orderBy(builder.desc(company.get("name")));
				break;
			}
		}
		return entityManager.createQuery(cQuery).setFirstResult(startAt)
				.setMaxResults(numberOfRows).getResultList();

	}

	public List<Computer> findAll() {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Computer> cQuery = builder.createQuery(Computer.class);
		Root<Computer> computerRoot = cQuery.from(Computer.class);
		cQuery.select(computerRoot);

		return entityManager.createQuery(cQuery).getResultList();

	}

	public int count(String search) {

		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> cQuery = builder.createQuery(Long.class);
		Root<Computer> computerRoot = cQuery.from(Computer.class);
		cQuery.select(builder.count(computerRoot));
		StringBuilder querySearch = new StringBuilder("%").append(search)
				.append("%");
		Join<Computer, Company> company = computerRoot.join("company",
				JoinType.LEFT);

		if (search != null) {
			cQuery.where(builder.or(builder.like(
					company.get("name").as(String.class),
					querySearch.toString()), builder.like(
					computerRoot.get("name").as(String.class),
					querySearch.toString())));
		}

		return (int) (long) entityManager.createQuery(cQuery).getSingleResult();
	}

	public void update(Computer c) {
		entityManager.merge(c);
	}

	public void delete(long id) {
		entityManager.remove(find(id));
	}
}
