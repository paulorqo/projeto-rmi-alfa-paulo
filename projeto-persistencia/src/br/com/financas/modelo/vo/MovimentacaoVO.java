package br.com.financas.modelo.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

import br.com.financas.enumeracao.TipoMovimentacao;

public class MovimentacaoVO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7228246342884641630L;
	private Integer id;
	private BigDecimal valor;
	private TipoMovimentacao tipoMovimentacao;
	private Date data;
	private String descricao;
	private ContaVO conta;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public TipoMovimentacao getTipoMovimentacao() {
		return tipoMovimentacao;
	}

	public void setTipoMovimentacao(TipoMovimentacao tipoMovimentacao) {
		this.tipoMovimentacao = tipoMovimentacao;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public ContaVO getConta() {
		return conta;
	}

	public void setConta(ContaVO conta) {
		this.conta = conta;
	}

}
