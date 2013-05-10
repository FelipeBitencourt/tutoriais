package br.bitencourt.amigooculto;

public class Pessoa {

	String nome;
	String email;
	Pessoa amigo;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public boolean setAmigo(Pessoa amigo){
		if (amigo.getNome() == this.nome ||
			amigo.getEmail() == this.email	
		) {
			return false;
		}
		
		this.amigo = amigo;
		
		return true;
	}
	
	
	
	
}
