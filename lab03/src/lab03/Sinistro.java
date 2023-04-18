package lab03;

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
	
	final int ID;
	String data;
	String endereco;
	Seguradora seguradora;
	Veiculo veiculo;
	Cliente cliente;

	public Sinistro(String data, String endereco, Seguradora seguradora, Veiculo veiculo, Cliente cliente) {
		super();
		this.data = data;
		this.endereco = endereco;
		this.seguradora = seguradora;
		this.veiculo = veiculo;
		this.cliente = cliente;
		Random rand = new Random();
		this.ID = (int) (rand.nextFloat()*Math.pow(10, 6));
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

	public Seguradora getSeguradora() {
		return seguradora;
	}

	public void setSeguradora(Seguradora seguradora) {
		this.seguradora = seguradora;
	}

	public Veiculo getVeiculo() {
		return veiculo;
	}

	public void setVeiculo(Veiculo veiculo) {
		this.veiculo = veiculo;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	@Override
	public String toString() {
		return "Sinistro [ID=" + ID + ", data=" + data + ", endereco=" + endereco + ", seguradora=" + seguradora
				+ ", veiculo=" + veiculo + ", cliente=" + cliente + ", rand=" + "]";
	}
}
