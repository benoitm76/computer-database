package com.excilys.projet.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

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

	@SuppressWarnings("unchecked")
	public List<Computer> findAllByCreteria(String search, ComputerOrder order,
			int startAt, int numberOfRows) {
		StringBuilder hql = new StringBuilder(
				"SELECT computer FROM Computer as computer LEFT JOIN computer.company company");
		if (search != null) {
			hql.append(" where computer.name LIKE :search OR company.name LIKE :search");
		}
		if (order != null) {
			hql.append(" ORDER BY " + order.getOrderStatement());
		}
		Query query = entityManager.createQuery(hql.toString());
		StringBuilder querySearch = new StringBuilder("%").append(search)
				.append("%");
		if (search != null) {
			query.setParameter("search", querySearch.toString());
		}
		query.setFirstResult(startAt);
		query.setMaxResults(numberOfRows);
		return query.getResultList();

	}

	@SuppressWarnings("unchecked")
	public List<Computer> findAll() {
		Query query = entityManager.createQuery("from Computer as Computer");
		return query.getResultList();

	}

	public int count(String search) {
		StringBuilder hql = new StringBuilder(
				"SELECT COUNT(computer.id) FROM Computer as computer LEFT JOIN computer.company company");
		StringBuilder querySearch = new StringBuilder("%").append(search)
				.append("%");
		if (search != null) {
			hql.append(" where computer.name LIKE :search OR company.name LIKE :search");
		}
		Query query = entityManager.createQuery(hql.toString());

		if (search != null) {
			query.setParameter("search", querySearch.toString());
		}
		return (int) (long) query.getResultList().get(0);
	}

	public void update(Computer c) {
		entityManager.merge(c);
	}

	public void delete(long id) {
		entityManager.remove(find(id));
	}
}
