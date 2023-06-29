
import java.time.LocalDate;

public class SeguroPF extends Seguro {
	ClientePF clientePF;
	Veiculo veiculo;
	
	public SeguroPF(ClientePF clientePF, LocalDate dataInicio, LocalDate dataFim, Seguradora seguradora,
			Veiculo veiculo) {
		super(dataInicio, dataFim, seguradora);
		this.clientePF = clientePF;
		this.veiculo = veiculo;
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
	
	
	protected int quantidadeSinistrosCliente() {
		return super.getListaSinistros().size();
	}
	
	protected int quantidadeSinistrosCondutores() {
		int sinistros = 0;
		for(int i=0; i<super.getListaCondutores().size(); i++) {
			sinistros += super.getListaCondutores().get(i).getListaSinistros().size();
		}
		return sinistros;
	}
	
	public double calcularValor() {
		double valor = super.calcularValor();
		if(this.clientePF.getIdade()<30) {
			valor *= CalcSeguro.FATOR_18_30.getValor();
		}
		else if(this.clientePF.getIdade()>=30 && this.clientePF.getIdade()<60) {
			valor *= CalcSeguro.FATOR_30_60.getValor();
		}
		else if(this.clientePF.getIdade()>=60) {
			valor *= CalcSeguro.FATOR_60_90.getValor();
		}
		
		valor *= (1+(1/(this.clientePF.getListaVeiculos().size()+2)));
		valor *= (2+(quantidadeSinistrosCliente()/10));
		valor *= (5+quantidadeSinistrosCondutores());
		
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

	public Veiculo getVeiculo() {
		return veiculo;
	}

	public void setVeiculo(Veiculo veiculo) {
		this.veiculo = veiculo;
	}
	
	public ClientePF getCliente() {
		return clientePF;
	}

	public void setCliente(Cliente cliente) {
		if(cliente instanceof ClientePF) {
			this.clientePF = (ClientePF) cliente;			
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
		return "SeguroPF [veiculo=" + veiculo + ", id=" + id + ", cliente=" + clientePF + ", dataInicio=" + dataInicio
				+ ", dataFim=" + dataFim + ", seguradora=" + seguradora + ", listaSinistros=" + listaSinistros
				+ ", listaCondutores=" + listaCondutores + ", valorMensal=" + valorMensal + ", rand=" + rand + "]";
	}
}
