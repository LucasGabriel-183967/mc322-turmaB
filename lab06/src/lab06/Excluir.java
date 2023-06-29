package lab06;
import java.util.ArrayList;
import java.util.Scanner;

public enum Excluir {
	EXCLUIR_CLIENTE(1), EXCLUIR_VEICULO(2), EXCLUIR_FROTA(4), EXCLUIR_SINISTRO(5), VOLTAR(6);
	
	public final int operacao;
	Excluir(int operacao){
		this.operacao = operacao;
	}
	
	public int getOperacao() {
		return this.operacao;
	}
	
	public static void excluir(Scanner input) {
		System.out.println("O que voce quer fazer?");
		for(int i=0; i<Excluir.values().length; i++) {
			System.out.println(i+1+": "+Excluir.values()[i]);
		}
		int operacao = Integer.parseInt(input.nextLine());
		operacao--;
		while(operacao<0 || operacao>=Excluir.values().length) {
			System.out.println("Selecione uma opcao valida!");
			operacao = Integer.parseInt(input.nextLine());
			operacao--;
		}
		
		switch(Excluir.values()[operacao]) {
			case EXCLUIR_CLIENTE:
				{
					System.out.println("Selecione uma seguradora: ");
					for(int i=0; i<MenuOperacoes.listaSeguradoras.size(); i++) {
						System.out.println((i+1)+": "+MenuOperacoes.listaSeguradoras.get(i).getNome());
					}
					int opcao = Integer.parseInt(input.nextLine());
					while(opcao-1<0 || opcao-1>=MenuOperacoes.listaSeguradoras.size()) {
						System.out.println("Selecione uma opcao valida!");
						opcao = Integer.parseInt(input.nextLine());
					}
					Seguradora seguradora = MenuOperacoes.listaSeguradoras.get(opcao-1);
					
					System.out.println("Nome do cliente:");
					Cliente cliente = null;
					String nome = input.nextLine();
					cliente = seguradora.procurarCliente(nome);
					while(cliente == null) {
						System.out.println("Cliente inexistente. Tente novamente!");
						nome = input.nextLine();
						cliente = seguradora.procurarCliente(nome);
					}
					
					seguradora.removerCliente(cliente);
					
					System.out.println("\n");
				}
				break;
			
			case EXCLUIR_VEICULO:
				{
					System.out.println("Nome do cliente:");
					ClientePF cliente = null;
					String nome = input.nextLine();
					cliente = MenuOperacoes.searchClientePF(nome);
					while(cliente == null) {
						System.out.println("Cliente inexistente. Tente novamente!");
						nome = input.nextLine();
						cliente = MenuOperacoes.searchClientePF(nome);
					}
					
					if(cliente.getListaVeiculos().size()==0) {
						System.out.println("O cliente nao possui nenhum veiculo cadastrado!");
						break;
					}
						
					System.out.println("Selecione um veiculo: ");
					for(int i=0; i<cliente.getListaVeiculos().size(); i++) {
						System.out.println(i+1+": "+cliente.getListaVeiculos().get(i));
					}
					int opcao = Integer.parseInt(input.nextLine());
					while(opcao-1<0 || opcao-1>=cliente.getListaVeiculos().size()) {
						System.out.println("Selecione uma opcao valida!");
						opcao = Integer.parseInt(input.nextLine());
					}
					cliente.getListaVeiculos().remove(opcao-1);
					
					for(int i=0; i<MenuOperacoes.listaSeguradoras.size(); i++) {
						// Atualiza a receita de cada seguradora (e consequentemente o valor dos seguros dos quais o cliente possuia)
						MenuOperacoes.listaSeguradoras.get(i).calcularReceita();
					}
				}
				break;
			
			case EXCLUIR_FROTA:
				{
					System.out.println("Nome do cliente:");
					ClientePJ cliente = null;
					String nome = input.nextLine();
					cliente = MenuOperacoes.searchClientePJ(nome);
					while(cliente == null) {
						System.out.println("Cliente inexistente. Tente novamente!");
						nome = input.nextLine();
						cliente = MenuOperacoes.searchClientePJ(nome);
					}
					
					if(cliente.getListaFrotas().size()==0) {
						System.out.println("O cliente nao possui nenhuma frota cadastrada!");
						break;
					}
					
					System.out.println("Selecione uma frota: ");
					for(int i=0; i<cliente.getListaFrotas().size(); i++) {
						System.out.println(i+1+": "+cliente.getListaFrotas().get(i));
					}
					int opcao = Integer.parseInt(input.nextLine());
					while(opcao-1<0 || opcao-1>=cliente.getListaFrotas().size()) {
						System.out.println("Selecione uma opcao valida!");
						opcao = Integer.parseInt(input.nextLine());
					}
					
					cliente.getListaFrotas().remove(opcao-1);
					for(int i=0; i<MenuOperacoes.listaSeguradoras.size(); i++) {
						// Atualiza a receita de cada seguradora (e consequentemente o valor dos seguros dos quais o cliente possuia)
						MenuOperacoes.listaSeguradoras.get(i).calcularReceita();
					}
				}
				break;
				
			case EXCLUIR_SINISTRO:
				{
					System.out.println("Selecione uma seguradora: ");
					for(int i=0; i<MenuOperacoes.listaSeguradoras.size(); i++) {
						System.out.println((i+1)+": "+MenuOperacoes.listaSeguradoras.get(i).getNome());
					}
					int opcao = Integer.parseInt(input.nextLine());
					while(opcao-1<0 || opcao-1>=MenuOperacoes.listaSeguradoras.size()) {
						System.out.println("Selecione uma opcao valida!");
						opcao = Integer.parseInt(input.nextLine());
					}
					Seguradora seguradora = MenuOperacoes.listaSeguradoras.get(opcao-1);
					
					System.out.println("Qual o nome do cliente?");
					Cliente cliente = null;
					String nome = input.nextLine();
					cliente = seguradora.procurarCliente(nome);
					while(cliente == null) {
						System.out.println("Cliente inexistente! Tente novamente.");
						nome = input.nextLine();
						cliente = seguradora.procurarCliente(nome);
					}
					
					ArrayList<Seguro> segurosCliente = seguradora.getSegurosPorCliente(cliente);
					System.out.println("Selecione um seguro:");
					for(int i=0; i<segurosCliente.size(); i++) {
						System.out.println(i+1+": "+segurosCliente.get(i).getId());
					}
					opcao = Integer.parseInt(input.nextLine());
					while(opcao-1<0 || opcao-1>=segurosCliente.size()) {
						System.out.println("Selecione uma opcao valida!");
						opcao = Integer.parseInt(input.nextLine());
					}
					
					segurosCliente.remove(opcao-1);
				}
				break;
			
			case VOLTAR:
				break;
		}
	}
}
