package lab05;

import java.time.LocalDate;

public class SeguroPJ extends Seguro {
	ClientePJ clientePJ;
	Frota frota;
	
	public SeguroPJ(ClientePJ clientePJ, LocalDate dataInicio, LocalDate dataFim, Seguradora seguradora,
			Frota frota) {
		super(dataInicio, dataFim, seguradora);
		this.clientePJ = clientePJ;
		this.frota = frota;
		super.valorMensal = calcularValor();
	}
	
	public boolean autorizarCondutor(Condutor condutor) {
		for(int i=0; i<listaCondutores.size(); i++) {
			if(listaCondutores.get(i).equals(condutor)) {
				return false;
			}
		}
		
		listaCondutores.add(condutor);
		return true;
	}
	
	public boolean desautorizarCondutor(Condutor condutor) {
		for(int i=0; i<listaCondutores.size(); i++) {
			if(listaCondutores.get(i).equals(condutor)) {
				listaCondutores.remove(i);
				return true;
			}
		}
		
		return false;
	}
	
	protected int quantidadeVeiculos() {
		return this.frota.getListaVeiculos().size();
	}
	
	protected int quantidadeSinistrosCliente(){
		return super.getListaSinistros().size();
	}
	
	protected int quantidadeSinistrosCondutores(){
		int sinistros = 0;
		for(int i=0; i<super.getListaCondutores().size(); i++) {
			sinistros += super.getListaCondutores().get(i).getListaSinistros().size();
		}
		
		return sinistros;
	}
	
	public double calcularValor() {
		double valor = super.calcularValor();
		valor *= 10+(this.clientePJ.getQuantFuncionarios()/10);
		valor *= 1+(1/(quantidadeVeiculos()/2));
		valor *= 1+(1/(this.clientePJ.getIdade()+2));
		valor *= 2+(quantidadeSinistrosCliente()/10);
		valor *= 5+(quantidadeSinistrosCondutores()/10);
		return valor;
	}
	
	public boolean gerarSinistro(Sinistro sinistro) {
		for(int i=0; i<listaSinistros.size(); i++) {
			if(listaSinistros.get(i).equals(sinistro)) {
				return false;
			}
		}
		
		listaSinistros.add(sinistro);
		return true;
	}

	public Frota getFrota() {
		return frota;
	}

	public void setFrota(Frota frota) {
		this.frota = frota;
	}
	
	public ClientePJ getCliente() {
		return this.clientePJ;
	}
	
	public void setCliente(Cliente cliente) {
		if(cliente instanceof ClientePJ) {
			this.clientePJ = (ClientePJ) cliente;			
		}
		else {
			throw new IllegalArgumentException("Tipo de cliente invalido!");
		}
	}
	
	public double getValorMensal() {
		// Atualiza e retorna o alor mensal
		this.valorMensal = calcularValor();
		return this.valorMensal;
	}

	@Override
	public String toString() {
		return "SeguroPJ [frota=" + frota + ", id=" + id + ", cliente=" + clientePJ + ", dataInicio=" + dataInicio
				+ ", dataFim=" + dataFim + ", seguradora=" + seguradora + ", listaSinistros=" + listaSinistros
				+ ", listaCondutores=" + listaCondutores + ", valorMensal=" + valorMensal + ", rand=" + rand + "]";
	}
	
}
