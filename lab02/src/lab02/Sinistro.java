package lab02;
import java.util.Random;

public class Sinistro {
	/*
	A Classe Sinistro corresponde em armazenar as informacoes relacionadas um evento em que o
	seguro foi acionado (acidente, furto, etc). Para isso, a Classe Sinistro possui tres atributos 
	(id, data e endereco). Data e endereco sao do tipo String. Ao passo que o atributo id  ́e um 
	int que dever ́a ser gerado por uma funcao geradora de identificadores  ́unicos.
	*/
	
	/*
	Note que o atributo id foi definido a partir de um método que retorna um valor randômico do
	tipo float (no intervalo (0, 1)), assim é possível, embora em geral improvável, que hajam
	colisões (i.e, o atributo id de diferentes objetos do tipo Sinistro pode ser igual).
	*/
	
	int id;
	String data;
	String endereco;
	Random rand = new Random();
	
	public Sinistro(int id, String data, String endereco) { 
		this.id = (int) (rand.nextFloat()*Math.pow(10, 6));
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
