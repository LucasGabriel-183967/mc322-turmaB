package lab02;

public class Sinistro {
	/*
	A Classe Sinistro corresponde em armazenar as informacoes relacionadas um evento em que o
	seguro foi acionado (acidente, furto, etc). Para isso, a Classe Sinistro possui tres atributos 
	(id, data e endereco). Data e endereco sao do tipo String. Ao passo que o atributo id  ́e um 
	int que dever ́a ser gerado por uma funcao geradora de identificadores  ́unicos.
	*/
	
	int id;
	String data;
	String endereco;
	
	public Sinistro(int id, String data, String endereco) {
		this.id = id;
		this.data = data;
		this.endereco = endereco;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	
	
}
