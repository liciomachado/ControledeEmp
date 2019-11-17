package br.com.controlador.jdbc.modelo;

import java.util.Calendar;

public class Empenho {

	private int idEmpenho;
	private Calendar dataEmpenho;
	private String numeroEmpenho;
	private Empresa empresa;
	private String destino;
	private double valorTotal;
	private String EmpenhoDigitalizado; // MUDAR CONFORME APRENDER
	
	public int getIdEmpenho() {
		return idEmpenho;
	}
	public void setIdEmpenho(int idEmpenho) {
		this.idEmpenho = idEmpenho;
	}
	public Calendar getDataEmpenho() {
		return dataEmpenho;
	}
	public void setDataEmpenho(Calendar dataEmpenho) {
		this.dataEmpenho = dataEmpenho;
	}
	public String getNumeroEmpenho() {
		return numeroEmpenho;
	}
	public void setNumeroEmpenho(String numeroEmpenho) {
		this.numeroEmpenho = numeroEmpenho;
	}
	public Empresa getEmpresa() {
		return empresa;
	}
	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}
	public String getDestino() {
		return destino;
	}
	public void setDestino(String destino) {
		this.destino = destino;
	}
	public double getValorTotal() {
		return valorTotal;
	}
	public void setValorTotal(double valorTotal) {
		this.valorTotal = valorTotal;
	}
	public String getEmpenhoDigitalizado() {
		return EmpenhoDigitalizado;
	}
	public void setEmpenhoDigitalizado(String empenhoDigitalizado) {
		EmpenhoDigitalizado = empenhoDigitalizado;
	}
}
