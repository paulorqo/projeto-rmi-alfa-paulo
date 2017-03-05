package controle;

import java.io.IOException;
import java.math.BigDecimal;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.com.financas.enumeracao.TipoMovimentacao;
import br.com.financas.modelo.vo.ContaVO;
import br.com.financas.modelo.vo.MovimentacaoVO;
import br.com.financas.rmi.PersistenciaModelo;

@ManagedBean
@SessionScoped
public class MovimentacaoMB {

	public MovimentacaoMB() {
		super();
	}

	private PersistenciaModelo persistenciaModelo;
	private Registry registry;
	private MovimentacaoVO movimentacaoVO;
	List<MovimentacaoVO> movimentacoes = new ArrayList<>();
	List<ContaVO> contas = new ArrayList<>();
	private ContaVO contaVO;

	@PostConstruct
	public void popular() {
		movimentacaoVO = new MovimentacaoVO();
		try {
			registry = LocateRegistry.getRegistry("127.0.0.1", 2001);
			persistenciaModelo = (PersistenciaModelo) registry.lookup("OlaServidor");
			movimentacaoVO.setTipoMovimentacao(TipoMovimentacao.ENTRADA);
			movimentacaoVO.setData(new Date());
			movimentacaoVO.setValor(BigDecimal.ZERO);
			pesquisar();
			pesquisarContas();
		} catch (RemoteException | NotBoundException e) {
			e.printStackTrace();
		}
	}

	public void alterar(MovimentacaoVO movimentacaoVO){
		this.movimentacaoVO = movimentacaoVO;
		this.contaVO = movimentacaoVO.getConta();
	}
	
	public void salvar() throws RemoteException {
		if (movimentacaoVO.getId() != null) {
			movimentacaoVO.setConta(contaVO);
			movimentacaoVO = persistenciaModelo.alterar(movimentacaoVO);
		}else {
			movimentacaoVO.setConta(contaVO);
			movimentacaoVO = persistenciaModelo.salvar(movimentacaoVO);
		}
		
		limparCampos();
		pesquisar();
	}
	
	public void excluir(MovimentacaoVO movimentacaoVO) throws RemoteException{
		persistenciaModelo.excluir(movimentacaoVO);
		pesquisar();
	}
	
	public void limparCampos() throws RemoteException{
		movimentacaoVO = new MovimentacaoVO();
		movimentacaoVO.setTipoMovimentacao(TipoMovimentacao.ENTRADA);
		movimentacaoVO.setData(new Date());
		movimentacaoVO.setValor(BigDecimal.ZERO);
		contaVO = new ContaVO();
		pesquisar();
	}
	
	public void pesquisar() throws RemoteException {
		movimentacoes = persistenciaModelo.listarMovimentacoes();
	}

	public void pesquisarContas() throws RemoteException {
		contas = persistenciaModelo.listarContas();
	}
	
	public void adicionarConta(){
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("contas.xhtml");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public MovimentacaoVO getMovimentacaoVO() {
		return movimentacaoVO;
	}

	public void setMovimentacaoVO(MovimentacaoVO movimentacaoVO) {
		this.movimentacaoVO = movimentacaoVO;
	}

	public List<MovimentacaoVO> getMovimentacoes() {
		return movimentacoes;
	}

	public void setMovimentacoes(List<MovimentacaoVO> movimentacoes) {
		this.movimentacoes = movimentacoes;
	}

	public List<ContaVO> getContas() {
		return contas;
	}

	public void setContas(List<ContaVO> contas) {
		this.contas = contas;
	}

	public ContaVO getContaVO() {
		return contaVO;
	}

	public void setContaVO(ContaVO contaVO) {
		this.contaVO = contaVO;
	}
}
