package br.com.financas.rmi.impl;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

import br.com.financas.dao.ContaDao;
import br.com.financas.dao.MovimentacaoDao;
import br.com.financas.modelo.entidade.Conta;
import br.com.financas.modelo.entidade.Movimentacao;
import br.com.financas.modelo.vo.ContaVO;
import br.com.financas.modelo.vo.EntidadeConverter;
import br.com.financas.modelo.vo.MovimentacaoVO;
import br.com.financas.rmi.PersistenciaModelo;

public class PersistenciaModeloImpl extends UnicastRemoteObject implements PersistenciaModelo {

	private static final long serialVersionUID = 181353194560607760L;
	private ContaDao contaDao = new ContaDao();
	private MovimentacaoDao movimentacaoDao = new MovimentacaoDao();
	private EntidadeConverter entidadeConverter =  new EntidadeConverter();

	public PersistenciaModeloImpl() throws RemoteException {
		super();
	}

	public static void main(String args[]) {
		try {
			PersistenciaModeloImpl persistencia = new PersistenciaModeloImpl();
			// Naming.rebind("OlaServidor", obj);
			Registry registry = LocateRegistry.createRegistry(2001);
			registry.rebind("OlaServidor", persistencia);
			System.out.println("Servidor carregado no registry");
		} catch (Exception e) {
			System.out.println("OlaImpl erro: " + e.getMessage());
		}
	}

	@Override
	public ContaVO salvar(ContaVO contaVO) throws RemoteException {
		Conta conta = entidadeConverter.converterVOparaEntidade(contaVO);
		conta = contaDao.salvar(conta);
		
		return entidadeConverter.converterEntidadeParaVO(conta);
	}

	@Override
	public ContaVO alterar(ContaVO contaVO) throws RemoteException {
		Conta conta = entidadeConverter.converterVOparaEntidade(contaVO);
		conta = contaDao.alterar(conta);
		
		return entidadeConverter.converterEntidadeParaVO(conta);
	}

	@Override
	public void excluir(ContaVO contaVO) throws RemoteException {
		Conta conta = entidadeConverter.converterVOparaEntidade(contaVO);
		contaDao.excluir(conta);
	}

	@Override
	public MovimentacaoVO salvar(MovimentacaoVO movimentacaoVO) throws RemoteException {
		Movimentacao movimentacao = entidadeConverter.converterVOparaEntidade(movimentacaoVO);
		movimentacao = movimentacaoDao.salvar(movimentacao);
		
		return entidadeConverter.converterEntidadeParaVO(movimentacao);
	}

	@Override
	public MovimentacaoVO alterar(MovimentacaoVO movimentacaoVO) throws RemoteException {
		Movimentacao movimentacao = entidadeConverter.converterVOparaEntidade(movimentacaoVO);
		movimentacao = movimentacaoDao.alterar(movimentacao);
		
		return entidadeConverter.converterEntidadeParaVO(movimentacao);
	}

	@Override
	public void excluir(MovimentacaoVO movimentacaoVO) throws RemoteException {
		Movimentacao movimentacao = entidadeConverter.converterVOparaEntidade(movimentacaoVO);
		movimentacaoDao.excluir(movimentacao);
	}

	@Override
	public List<ContaVO> listarContas() throws RemoteException {
		List<ContaVO> contas = new ArrayList<>();
		for (Conta conta : contaDao.listar()) {
			ContaVO contaVO = entidadeConverter.converterEntidadeParaVO(conta);
			contas.add(contaVO);
		}
		return contas;
	}

	@Override
	public List<MovimentacaoVO> listarMovimentacoes() throws RemoteException {
		List<MovimentacaoVO> movimentacoes = new ArrayList<>();
		for (Movimentacao movimentacao: movimentacaoDao.listar()) {
			MovimentacaoVO movimentacaoVO = entidadeConverter.converterEntidadeParaVO(movimentacao);
			movimentacoes.add(movimentacaoVO);
		}
		return movimentacoes;
	}

	@Override
	public ContaVO consultarPorId(Integer id) throws RemoteException {
		return entidadeConverter.converterEntidadeParaVO(contaDao.consultarPorId(id));
	}
}
