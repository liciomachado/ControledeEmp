package br.com.controlador.jdbc.modelo;

import java.util.Arrays;
import java.util.Calendar;

import com.sun.prism.Image;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class Empenho {

	private int idEmpenho;
	private Calendar dataEmpenho;
	private String numeroEmpenho;
	private Empresa empresa;
	private NotaFiscal notaFiscal;
	private String destino;
	private double valorTotal;
	private byte[] EmpenhoDigitalizado; // MUDAR CONFORME APRENDER
	
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
