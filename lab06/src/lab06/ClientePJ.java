package lab06;
import java.time.LocalDate;
import java.util.ArrayList;

public class ClientePJ extends Cliente {
	final String cnpj;
	LocalDate dataFundacao;
	int quantFuncionarios;
	ArrayList<Frota> listaFrotas;
	AtualizarFrotaOpcao atualizarF = new AtualizarFrotaOpcao();
	
	protected class AtualizarFrotaOpcao {
		// Classe interna
		public boolean adicionar(Frota frota, Veiculo veiculo) {
			for(int i=0; i<listaFrotas.size(); i++) {
				if(listaFrotas.get(i).equals(frota)) {
					listaFrotas.get(i).cadastrarVeiculo(veiculo);
					return true;
				}
			}
			
			// Frota nao encontrada!
			return false;
		}
		
		public boolean remover(Frota frota, Veiculo veiculo) {
			for(int i=0; i<listaFrotas.size(); i++) {
				if(listaFrotas.get(i).equals(frota)) {
					Frota frota_ = listaFrotas.get(i);
					for(int j=0; j<frota_.getListaVeiculos().size(); j++) {
						if(frota_.listaVeiculos.get(j).equals(veiculo)) {
							frota_.listaVeiculos.remove(j);
							
							if(frota_.listaVeiculos.size()==0) {
								// Nao ha mais nenhum carro na frota_ -> exclui a frota
								listaFrotas.remove(i);
							}
							return true;
						}
					}
				}
			}
			
			return false;
		}
	}

	public ClientePJ(String nome, String telefone, String endereco, String email, String cnpj, LocalDate dataFundacao,
			int quantFuncionarios) {
		super(nome, telefone, endereco, email);
		this.cnpj = cnpj;
		this.dataFundacao = dataFundacao;
		this.quantFuncionarios = quantFuncionarios;
		this.listaFrotas = new ArrayList<Frota>();
	}

	public boolean cadastrarFrota(Frota frota) {
		for(int i=0; i<listaFrotas.size(); i++) {
			if(listaFrotas.get(i).equals(frota)) {
				return false;
			}
		}
		
		listaFrotas.add(frota);
		return true;
	}
	
	public boolean removerFrota(Frota frota) {
		for(int i=0; i<listaFrotas.size(); i++) {
			if(listaFrotas.get(i).equals(frota)) {
				listaFrotas.remove(i);
				return true;
			}
		}
		return false;
	}
	
	public void atualizarFrota(Frota frota, Veiculo veiculo, int opcao) {
		switch(opcao) {
			case 1:
				this.atualizarF.adicionar(frota, veiculo);
			
			case 2:
				this.atualizarF.remover(frota, veiculo);
		}
	}
	
	public ArrayList<Veiculo> getVeiculosPorFrota(Frota frota) {
		for(int i=0; i<listaFrotas.size(); i++) {
			if(listaFrotas.get(i).equals(frota)) {
				return listaFrotas.get(i).getListaVeiculos();
			}
		}
		
		return null;
	}
	
	public LocalDate getDataFundacao() {
		return dataFundacao;
	}
	
	public void setDataFundacao(LocalDate dataFundacao) {
		this.dataFundacao = dataFundacao;
	}
	
	public int getIdade() {
		return this.dataFundacao.getYear();
	}

	public ArrayList<Frota> getListaFrotas() {
		return listaFrotas;
	}
	
	public String getCnpj() {
		return cnpj;
	}

	public int getQuantFuncionarios() {
		return quantFuncionarios;
	}

	public void setQuantFuncionarios(int quantFuncionarios) {
		this.quantFuncionarios = quantFuncionarios;
	}

	@Override
	public String toString() {
		return "ClientePJ [nome=" + nome + ", cnpj=" + cnpj + ", dataFundacao=" + dataFundacao + ", quantFuncionarios=" + quantFuncionarios
				+ ", listaFrotas=" + listaFrotas + ", atualizarF=" + atualizarF + ", telefone="
				+ telefone + ", endereco=" + endereco + ", email=" + email + "]";
	}
}
