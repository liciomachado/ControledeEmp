package br.com.controlador.jdbc.modelo;

import java.util.Calendar;

public class NotaFiscal {

	private int idNotaFiscal;
	private int NumNota;
	private String ChaveAcesso;
	private double ValorTotal;
	private String ImagemNota; //MUDAR CONFORME APRENDER
	private Empresa empresa;
	private String idEmpresa;
	private Empenho empenho;
	private String idEmpenho;
	private Calendar dataEmissao;
	private Calendar dataRecebido;
	private Calendar dataProtocolado;
	private Usuario usuario;
	
	public Calendar getDataProtocolado() {
		return dataProtocolado;
	}
	public void setDataProtocolado(Calendar dataProtocolado) {
		this.dataProtocolado = dataProtocolado;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public Empresa getEmpresa() {
		return empresa;
	}
	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}
	public int getIdNotaFiscal() {
		return idNotaFiscal;
	}
	public void setIdNotaFiscal(int idNotaFiscal) {
		this.idNotaFiscal = idNotaFiscal;
	}
	public Empenho getEmpenho() {
		return empenho;
	}
	public void setEmpenho(Empenho empenho) {
		this.empenho = empenho;
	}
	public int getNumNota() {
		return NumNota;
	}
	public void setNumNota(int numNota) {
		NumNota = numNota;
	}
	public String getChaveAcesso() {
		return ChaveAcesso;
	}
	public void setChaveAcesso(String chaveAcesso) {
		ChaveAcesso = chaveAcesso;
	}
	public double getValorTotal() {
		return ValorTotal;
	}
	public void setValorTotal(double valorTotal) {
		ValorTotal = valorTotal;
	}
	public String getImagemNota() {
		return ImagemNota;
	}
	public void setImagemNota(String imagemNota) {
		ImagemNota = imagemNota;
	}
	public String getIdEmpresa() {
		return idEmpresa;
	}
	public void setIdEmpresa(String idEmpresa) {
		this.idEmpresa = idEmpresa;
	}
	public String getIdEmpenho() {
		return idEmpenho;
	}
	public void setIdEmpenho(String idEmpenho) {
		this.idEmpenho = idEmpenho;
	}
	public Calendar getDataEmissao() {
		return dataEmissao;
	}
	public void setDataEmissao(Calendar dataEmissao) {
		this.dataEmissao = dataEmissao;
	}
	public Calendar getDataRecebido() {
		return dataRecebido;
	}
	public void setDataRecebido(Calendar dataRecebido) {
		this.dataRecebido = dataRecebido;
	}
	@Override
	public String toString() {
		return "NotaFiscal [idNotaFiscal=" + idNotaFiscal + ", NumNota=" + NumNota + ", ChaveAcesso=" + ChaveAcesso
				+ ", ValorTotal=" + ValorTotal + ", ImagemNota=" + ImagemNota + ", empresa=" + empresa + ", idEmpresa="
				+ idEmpresa + ", empenho=" + empenho + ", idEmpenho=" + idEmpenho + ", dataEmissao=" + dataEmissao
				+ ", dataRecebido=" + dataRecebido + "]";
	}
	
}
