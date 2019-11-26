package br.com.controlador.jdbc.modelo;

import java.util.Calendar;

public class Observacoes {
	
	private int idObs;
	private int idEmpenho;
	private String observacao;
	private Calendar dataObs;
	private int idUsuario;
	private Usuario usuario;
	
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public int getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}
	public int getIdObs() {
		return idObs;
	}
	public void setIdObs(int idObs) {
		this.idObs = idObs;
	}
	public int getIdEmpenho() {
		return idEmpenho;
	}
	public void setIdEmpenho(int idEmpenho) {
		this.idEmpenho = idEmpenho;
	}
	public String getObservacao() {
		return observacao;
	}
	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}
	public Calendar getDataObs() {
		return dataObs;
	}
	public void setDataObs(Calendar dataObs) {
		this.dataObs = dataObs;
	}
	@Override
	public String toString() {
		return "Observacoes [idObs=" + idObs + ", idEmpenho=" + idEmpenho + ", observacao=" + observacao + ", dataObs="
				+ dataObs + "]";
	}
}
