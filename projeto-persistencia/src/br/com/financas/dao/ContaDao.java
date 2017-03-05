package br.com.financas.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import br.com.financas.modelo.entidade.Conta;

public class ContaDao {

	EntityManagerFactory entityManagerFactory;
	EntityManager entityManager;

	public ContaDao() {
		entityManagerFactory = Persistence.createEntityManagerFactory("financas");
	}

	public Conta salvar(Conta conta) {
		entityManager = entityManagerFactory.createEntityManager();

		entityManager.getTransaction().begin();
		entityManager.persist(conta);
		entityManager.getTransaction().commit();

		entityManager.close();
		return conta;
	}

	public Conta alterar(Conta conta) {
		entityManager = entityManagerFactory.createEntityManager();

		entityManager.getTransaction().begin();
		entityManager.merge(conta);
		entityManager.getTransaction().commit();

		entityManager.close();
		return conta;
	}

	public void excluir(Conta conta) {
		entityManager = entityManagerFactory.createEntityManager();

		entityManager.getTransaction().begin();
		conta = entityManager.merge(conta);
		entityManager.remove(conta);
		entityManager.getTransaction().commit();

		entityManager.close();
	}

	public List<Conta> listar() {
		entityManager = entityManagerFactory.createEntityManager();
		Query query = entityManager.createQuery("select c from Conta c");
		return query.getResultList();
	}
	
	public Conta consultarPorId(Integer id) {
		entityManager = entityManagerFactory.createEntityManager();
		Query query = entityManager.createQuery("select c from Conta c where c.id =:idConsulta");
		query.setParameter("idConsulta", id);
		return (Conta) query.getSingleResult();
	}
}
