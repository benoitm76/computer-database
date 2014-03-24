package com.excilys.projet.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.excilys.projet.model.Company;

@Repository
public class DaoCompany extends Dao<Company> {

	@PersistenceContext(unitName = "entityManagerFactory")
	private EntityManager entityManager;

	public Company find(long id) {
		return (Company) entityManager.find(Company.class, new Long(id));
	}

	public List<Company> findAll() {

		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Company> cQuery = builder.createQuery(Company.class);
		Root<Company> computerRoot = cQuery.from(Company.class);
		cQuery.select(computerRoot);
		return entityManager.createQuery(cQuery).getResultList();
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
