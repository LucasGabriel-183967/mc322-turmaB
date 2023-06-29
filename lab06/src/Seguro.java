
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;

public abstract class Seguro {
	final int id;
	LocalDate dataInicio;
	LocalDate dataFim;
	Seguradora seguradora;
	ArrayList<Sinistro> listaSinistros;
	ArrayList<Condutor> listaCondutores;
	double valorMensal;
	Random rand = new Random();
	
	public Seguro(LocalDate dataInicio, LocalDate dataFim, Seguradora seguradora) {
		super();
		this.dataInicio = dataInicio;
		this.dataFim = dataFim;
		this.seguradora = seguradora;
		this.listaSinistros = new ArrayList<Sinistro>();
		this.listaCondutores = new ArrayList<Condutor>();
		this.valorMensal = 0;
		this.id = (int) (rand.nextFloat()*Math.pow(10, 6));
	}
	
	public abstract boolean autorizarCondutor(Condutor condutor);
	
	public abstract boolean desautorizarCondutor(Condutor condutor);	
	
	public double calcularValor() {
		return CalcSeguro.VALOR_BASE.getValor();
	}
	
	public abstract boolean gerarSinistro(Sinistro sinistro);	

	public LocalDate getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(LocalDate dataInicio) {
		this.dataInicio = dataInicio;
	}

	public LocalDate getDataFim() {
		return dataFim;
	}

	public void setDataFim(LocalDate dataFim) {
		this.dataFim = dataFim;
	}

	public Seguradora getSeguradora() {
		return seguradora;
	}

	public void setSeguradora(Seguradora seguradora) {
		this.seguradora = seguradora;
	}

	public ArrayList<Sinistro> getListaSinistros() {
		return listaSinistros;
	}

	public ArrayList<Condutor> getListaCondutores() {
		return listaCondutores;
	}

	public abstract double getValorMensal();

	public void setValorMensal(double valorMensal) {
		this.valorMensal = valorMensal;
	}
	
	public abstract Cliente getCliente();
	
	public abstract void setCliente(Cliente cliente);
	
	public int getId() {
		return id;
	}

	@Override
	public String toString() {
		return "Seguro [id=" + id + ", dataInicio=" + dataInicio + ", dataFim=" + dataFim + ", seguradora=" + seguradora
				+ ", listaSinistros=" + listaSinistros + ", listaCondutores=" + listaCondutores + ", valorMensal="
				+ valorMensal + ", rand=" + rand + "]";
	}
}
