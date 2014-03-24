package com.excilys.projet.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.excilys.projet.model.Company;
import com.excilys.projet.model.QCompany;
import com.mysema.query.jpa.impl.JPAQuery;

@Repository
public class DaoCompany extends Dao<Company> {

	@PersistenceContext(unitName = "entityManagerFactory")
	private EntityManager entityManager;

	public Company find(long id) {
		return (Company) entityManager.find(Company.class, new Long(id));
	}

	public List<Company> findAll() {

		QCompany company = QCompany.company;
		JPAQuery query = new JPAQuery(entityManager);
		return query.from(company).list(company);

	}

	@Override
	public void create(Company t) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(Company t) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(long id) {
		// TODO Auto-generated method stub

	}
}
