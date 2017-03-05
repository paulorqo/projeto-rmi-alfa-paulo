package controle;

import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.com.financas.modelo.vo.ContaVO;
import br.com.financas.rmi.PersistenciaModelo;

@ManagedBean
@SessionScoped
public class ContaMB {

	public ContaMB() {
		super();
	}

	private PersistenciaModelo persistenciaModelo;
	private Registry registry;
	private ContaVO contaVO;
	List<ContaVO> contas = new ArrayList<>();

	@PostConstruct
	public void popular() {
		contaVO = new ContaVO();
		try {
			registry = LocateRegistry.getRegistry("127.0.0.1", 2001);
			persistenciaModelo = (PersistenciaModelo) registry.lookup("OlaServidor");
			pesquisar();
		} catch (RemoteException | NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void alterar(ContaVO contaVO){
		this.contaVO = contaVO;
	}
	
	public void salvar() throws RemoteException {
		if (contaVO.getId() != null) {
			contaVO = persistenciaModelo.alterar(contaVO);
		}else {
			contaVO = persistenciaModelo.salvar(contaVO);
		}
		
		limparCampos();
		pesquisar();
	}
	
	public void excluir(ContaVO contaVO) throws RemoteException{
		persistenciaModelo.excluir(contaVO);
		pesquisar();
	}
	
	public void limparCampos() throws RemoteException{
		contaVO = new ContaVO();
		pesquisar();
	}
	
	public void pesquisar() throws RemoteException {
		contas = persistenciaModelo.listarContas();
	}
	
	public void adicionarMovimentacao(){
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("movimentacoes.xhtml");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public ContaVO getContaVO() {
		return contaVO;
	}

	public void setContaVO(ContaVO contaVO) {
		this.contaVO = contaVO;
	}

	public List<ContaVO> getContas() {
		return contas;
	}

	public void setContas(List<ContaVO> contas) {
		this.contas = contas;
	}
}
