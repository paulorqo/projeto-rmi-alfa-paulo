package br.com.financas.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import br.com.financas.modelo.entidade.Movimentacao;

public class MovimentacaoDao {

	EntityManagerFactory entityManagerFactory;
	EntityManager entityManager;
	
	public MovimentacaoDao(){
		entityManagerFactory = Persistence.createEntityManagerFactory("financas");	
	}
	
	public Movimentacao salvar(Movimentacao movimentacao) {
		entityManager = entityManagerFactory.createEntityManager();
		
		entityManager.getTransaction().begin();
		entityManager.persist(movimentacao);
		entityManager.getTransaction().commit();
		
		entityManager.close();
		return movimentacao;
    }
	
	public Movimentacao alterar(Movimentacao movimentacao) {
		entityManager = entityManagerFactory.createEntityManager();
		
		entityManager.getTransaction().begin();
		entityManager.merge(movimentacao);
		entityManager.getTransaction().commit();
		
		entityManager.close();
        return movimentacao;
    }
	
	public void excluir(Movimentacao movimentacao) {
		entityManager = entityManagerFactory.createEntityManager();
		
		entityManager.getTransaction().begin();
		movimentacao = entityManager.merge(movimentacao);
		entityManager.remove(movimentacao);
		entityManager.getTransaction().commit();
		
		entityManager.close();
    }
	
	public List<Movimentacao> listar() {
		entityManager = entityManagerFactory.createEntityManager();
		Query query = entityManager.createQuery("select m from Movimentacao m");
		return query.getResultList();
	}
}
