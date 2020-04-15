package br.com.controlador.jdbc.modelo;

import java.util.Calendar;

public class ObservacoesEmpresa {
	
	private int idObs;
	private int idEmpresa;
	private String observacao;
	private Calendar dataObs;
	private Usuario usuario;
	
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public int getIdObs() {
		return idObs;
	}
	public void setIdObs(int idObs) {
		this.idObs = idObs;
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
	public int getIdEmpresa() {
		return idEmpresa;
	}
	public void setIdEmpresa(int idEmpresa) {
		this.idEmpresa = idEmpresa;
	}
	@Override
	public String toString() {
		return "ObservacoesEmpresa [idObs=" + idObs + ", idEmpresa=" + idEmpresa + ", observacao=" + observacao
				+ ", dataObs=" + dataObs + ", usuario=" + usuario + "]";
	}
	
}
