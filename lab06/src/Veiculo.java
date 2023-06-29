

public class Veiculo {
	/*
	Classe responsavel por armazenar as informacoes dos ve iculos segurados dos clientes.
	A classe  ́e composta pelos seguintes atributos: placa, marca e modelo. Todos os atributos
	sao do tipo String. 
	*/
	
	String placa;
	String marca;
	String modelo;
	int anoFabricacao;
	
	public Veiculo(String placa, String marca, String modelo, int anoFabricacao) {
		this.placa = placa;
		this.marca = marca;
		this.modelo = modelo;
		this.anoFabricacao = anoFabricacao;
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
	public int getAnoFabricacao() {
		return anoFabricacao;
	}
	public void setAnoFabricacao(int anoFabricacao) {
		this.anoFabricacao = anoFabricacao;
	}

	@Override
	public String toString() {
		return "Veiculo [placa=" + placa + ", marca=" + marca + ", modelo=" + modelo + ", anoFabricacao="
				+ anoFabricacao + "]";
	}
}
