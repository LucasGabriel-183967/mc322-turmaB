package lab06;
import java.time.LocalDate;
import java.util.Random;

public class Sinistro {
	final int id;
	LocalDate data;
	String endereco;
	Condutor condutor;
	Seguro seguro;
	Random rand = new Random();
	
	public Sinistro(LocalDate data, String endereco, Condutor condutor, Seguro seguro) {
		super();
		this.id = (int) (rand.nextFloat()*Math.pow(10, 6));
		this.data = data;
		this.endereco = endereco;
		this.condutor = condutor;
		this.seguro = seguro;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public Condutor getCondutor() {
		return condutor;
	}

	public void setCondutor(Condutor condutor) {
		this.condutor = condutor;
	}

	public Seguro getSeguro() {
		return seguro;
	}

	public void setSeguro(Seguro seguro) {
		this.seguro = seguro;
	}

	public int getId() {
		return id;
	}

	@Override
	public String toString() {
		return "Sinistro [id=" + id + ", data=" + data + ", endereco=" + endereco + ", condutor=" + condutor
				+ ", seguro=" + seguro + "]";
	}
}
