package br.com.controlador.jdbc.modelo;

import java.util.Arrays;
import java.util.Calendar;

public class Empenho {

	private int idEmpenho;
	private Calendar dataEmpenho;
	private String numeroEmpenho;
	private Empresa empresa;
	private NotaFiscal notaFiscal;
	private Usuario usuario;
	private String destino;
	private double valorTotal;
	private byte[] EmpenhoDigitalizado; 
	private int etapa;
	private double saldoUtilizado;
	private double saldo;
	
	public double getSaldoUtilizado() {
		return saldoUtilizado;
	}
	public void setSaldoUtilizado(double saldoUtilizado) {
		this.saldoUtilizado = saldoUtilizado;
	}
	public double getSaldo() {
		return saldo;
	}
	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public int getEtapa() {
		return etapa;
	}
	public void setEtapa(int etapa) {
		this.etapa = etapa;
	}
	public NotaFiscal getNotaFiscal() {
		return notaFiscal;
	}
	public void setNotaFiscal(NotaFiscal notaFiscal) {
		this.notaFiscal = notaFiscal;
	}
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
	public byte[] getEmpenhoDigitalizado() {
		return EmpenhoDigitalizado;
	}
	public void setEmpenhoDigitalizado(byte[] empenhoDigitalizado) {
		EmpenhoDigitalizado = empenhoDigitalizado;
	}
	@Override
	public String toString() {
		return "Empenho [idEmpenho=" + idEmpenho + ", dataEmpenho=" + dataEmpenho + ", numeroEmpenho=" + numeroEmpenho
				+ ", empresa=" + empresa + ", notaFiscal=" + notaFiscal + ", destino=" + destino + ", valorTotal="
				+ valorTotal + ", EmpenhoDigitalizado=" + Arrays.toString(EmpenhoDigitalizado) + "]";
	}
	
	
}
