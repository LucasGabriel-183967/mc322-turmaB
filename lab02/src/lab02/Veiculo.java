package lab02;

public class Veiculo {
	/*
	Classe respons ́avel por armazenar as informacoes dos ve iculos segurados dos clientes.
	A classe  ́e composta pelos seguintes atributos: placa, marca e modelo. Todos os atributos 
	sao do tipo String. 
	*/
	
	String placa;
	String marca;
	String modelo;
	
	public Veiculo(String placa, String marca, String modelo) {
		this.placa = placa;
		this.marca = marca;
		this.modelo = modelo;
	}
	
	public String getPlaca() {
		return placa;
	}
	public void setPlaca(String placa) {
		this.placa = placa;
	}
	
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	
	public String getModelo() {
		return modelo;
	}
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
}
