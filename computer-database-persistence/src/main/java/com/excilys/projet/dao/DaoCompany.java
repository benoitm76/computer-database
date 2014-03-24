package com.excilys.projet.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.excilys.projet.model.Company;

@Repository
public class DaoCompany extends Dao<Company> {

	@PersistenceContext(unitName = "entityManagerFactory")
	private EntityManager entityManager;

	public Company find(long id) {
		return (Company) entityManager.find(Company.class, new Long(id));
	}

	@SuppressWarnings("unchecked")
	public List<Company> findAll() {

		Query query = entityManager.createQuery("from Company");
		return query.getResultList();
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
