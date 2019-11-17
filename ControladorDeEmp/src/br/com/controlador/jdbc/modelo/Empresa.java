package br.com.controlador.jdbc.modelo;

public class Empresa {

	private int idEmpresa;
	private String Nome;
	private String Email;
	private String Contato;
	
	
	public int getIdEmpresa() {
		return idEmpresa;
	}
	public void setIdEmpresa(int idEmpresa) {
		this.idEmpresa = idEmpresa;
	}
	public String getNome() {
		return Nome;
	}
	public void setNome(String nome) {
		Nome = nome;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public String getContato() {
		return Contato;
	}
	public void setContato(String contato) {
		Contato = contato;
	}
}
