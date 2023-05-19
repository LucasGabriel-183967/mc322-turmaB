package lab04;
import java.util.*;

public enum MenuOperacoes {
	CADASTRAR(1), LISTAR(2), EXCLUIR(3), GERAR_SINISTRO(4), TRANSFERIR_SEGURO(5), CALCULAR_RECEITA_SEGURADORA(6), SAIR(7);
	static ArrayList<Seguradora> listaSeguradoras = new ArrayList<Seguradora>();
	static ArrayList<Cliente> listaClientes = new ArrayList<Cliente>();
	
	public final int operacao;
	MenuOperacoes(int operacao){
		this.operacao = operacao;
	}
	
	public int getOperacao() {
		return this.operacao;
	}
	
	public static Cliente searchCliente(String nome) {
		if(listaClientes.size()==0) {
			return null;
		}
		for(int i=0; i<listaClientes.size(); i++) {
			if(nome.equals(listaClientes.get(i).nome)) {
				return listaClientes.get(i);
			}
		}
		return null;
	}
	
	public static void exibirMenuInicial() {
		System.out.println("Escolha uma opcao: ");			
		for(int i=0; i<MenuOperacoes.values().length; i++) {
			System.out.println(i+1+": "+MenuOperacoes.values()[i]);
		}
	}
	
	public static void menuInicial(Scanner input) {
		int opcaoEscolhida;
		do {
			MenuOperacoes.exibirMenuInicial();
			opcaoEscolhida = Integer.parseInt(input.nextLine());
			while(opcaoEscolhida-1<0 || opcaoEscolhida-1>=MenuOperacoes.values().length) {
				System.out.println("Selecione uma opcao valida!");
				opcaoEscolhida = Integer.parseInt(input.nextLine());
			}
			
			opcaoEscolhida--;
			
			switch(MenuOperacoes.values()[opcaoEscolhida]) {
				case CADASTRAR:
					Cadastrar.cadastrar(input);
					break;
				case LISTAR:
					Listar.listar(input);
					break;
				case EXCLUIR:
					Excluir.excluir(input);
					break;
				case GERAR_SINISTRO:
					GerarSinistro.gerarSinistro(input);
					break;
				case TRANSFERIR_SEGURO:
					TransferirSeguro.transferirSeguro(input);
					break;
				case CALCULAR_RECEITA_SEGURADORA:
					System.out.println("Receita: "+CalcularReceitaSeguradora.calcularReceitaSeguradora(input)+"\n");
					break;
				case SAIR:
					break;
			}
		} while(opcaoEscolhida != MenuOperacoes.SAIR.getOperacao()-1);
	}
	
}	
