package br.com.financas.modelo.vo;

import br.com.financas.modelo.entidade.Conta;
import br.com.financas.modelo.entidade.Movimentacao;

public class EntidadeConverter {

	private ContaVO contaVO;
	private MovimentacaoVO movimentacaoVO;
	private Conta conta;
	private Movimentacao movimentacao;

	public ContaVO converterEntidadeParaVO(Conta conta) {
		contaVO = new ContaVO();
		contaVO.setAgencia(conta.getAgencia());
		contaVO.setBanco(conta.getBanco());
		contaVO.setNumero(conta.getNumero());
		contaVO.setTitular(conta.getTitular());
		contaVO.setId(conta.getId());
		
		return contaVO;
	}

	public Conta converterVOparaEntidade(ContaVO contaVO) {
		conta = new Conta();
		conta.setAgencia(contaVO.getAgencia());
		conta.setBanco(contaVO.getBanco());
		conta.setNumero(contaVO.getNumero());
		conta.setTitular(contaVO.getTitular());
		conta.setId(contaVO.getId());

		return conta;
	}

	public MovimentacaoVO converterEntidadeParaVO(Movimentacao movimentacao) {
		movimentacaoVO = new MovimentacaoVO();
		movimentacaoVO.setData(movimentacao.getData());
		movimentacaoVO.setDescricao(movimentacao.getDescricao());
		movimentacaoVO.setTipoMovimentacao(movimentacao.getTipoMovimentacao());
		movimentacaoVO.setValor(movimentacao.getValor());
		movimentacaoVO.setConta(converterEntidadeParaVO(movimentacao.getConta()));
		movimentacaoVO.setId(movimentacao.getId());
		
		return movimentacaoVO;
	}
	
	public Movimentacao converterVOparaEntidade(MovimentacaoVO movimentacaoVO){
		movimentacao = new Movimentacao();
		movimentacao.setData(movimentacaoVO.getData());
		movimentacao.setDescricao(movimentacaoVO.getDescricao());
		movimentacao.setTipoMovimentacao(movimentacaoVO.getTipoMovimentacao());
		movimentacao.setValor(movimentacaoVO.getValor());
		movimentacao.setConta(converterVOparaEntidade(movimentacaoVO.getConta()));
		movimentacao.setId(movimentacaoVO.getId());
		
		return movimentacao;
	}

}
