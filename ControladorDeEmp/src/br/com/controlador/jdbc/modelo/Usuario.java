package br.com.controlador.jdbc.modelo;

public class Usuario {
	
	private int idUsuario;
	private String Nome;
	private String Senha;
	private String email;
	private String tipoUser;
	private String graduacao;
	private String senhaGmail;
	
	
	public String getGraduacao() {
		return graduacao;
	}
	public void setGraduacao(String graduacao) {
		this.graduacao = graduacao;
	}
	public String getSenhaGmail() {
		return senhaGmail;
	}
	public void setSenhaGmail(String senhaGmail) {
		this.senhaGmail = senhaGmail;
	}
	public int getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}
	public String getNome() {
		return Nome;
	}
	public void setNome(String nome) {
		Nome = nome;
	}
	public String getSenha() {
		return Senha;
	}
	public void setSenha(String senha) {
		Senha = senha;
	}
	public String getEmail() {	
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTipoUser() {
		return tipoUser;
	}
	public void setTipoUser(String tipoUser) {
		this.tipoUser = tipoUser;
	}
	
	
}
