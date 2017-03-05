package br.com.financas.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import br.com.financas.modelo.vo.ContaVO;
import br.com.financas.modelo.vo.MovimentacaoVO;

public interface PersistenciaModelo extends Remote {
	ContaVO salvar(ContaVO contaVO) throws RemoteException;
	ContaVO alterar(ContaVO contaVO) throws RemoteException;
	void excluir(ContaVO contaVO) throws RemoteException;
	MovimentacaoVO salvar(MovimentacaoVO movimentacaoVO) throws RemoteException;
	MovimentacaoVO alterar(MovimentacaoVO movimentacaoVO) throws RemoteException;
	void excluir(MovimentacaoVO movimentacaoVO) throws RemoteException;
	List<ContaVO> listarContas() throws RemoteException;
	List<MovimentacaoVO> listarMovimentacoes() throws RemoteException;
	ContaVO consultarPorId(Integer id) throws RemoteException;
}
