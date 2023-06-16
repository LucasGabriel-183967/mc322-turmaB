package lab05;
import java.util.ArrayList;
import java.util.Scanner;

public enum Listar {
	LISTAR_CLIENTE_POR_SEG(1), LISTAR_SEGURO_POR_CLIENTE(2), LISTAR_SINISTRO_POR_CLIENTE(3), LISTAR_VEICULO_POR_CLIENTE(4), LISTAR_CONDUTORES_POR_SEGURO(5), VOLTAR(6);
	
	public final int operacao;
	
	Listar(int operacao){
		this.operacao = operacao;
	}
	
	public int getOperacao() {
		return this.operacao;
	}
	
	public static void listar(Scanner input) {
		System.out.println("O que voce quer fazer?");
		for(int i=0; i<Listar.values().length; i++) {
			System.out.println(i+1+": "+Listar.values()[i]);
		}
		int operacao = Integer.parseInt(input.nextLine());
		operacao--;
		while(operacao<0 || operacao>=Listar.values().length) {
			System.out.println("Selecione uma opcao valida!");
			operacao = Integer.parseInt(input.nextLine());
			operacao--;
		}
		
		switch(Listar.values()[operacao]) {
			case LISTAR_CLIENTE_POR_SEG:
				{
					
					Seguradora seguradora = Utils.inputSeguradora(input);
					if(seguradora == null) {
						System.out.println("Nenhuma seguradora disponivel!");
						return;
					}						
					System.out.println("Clientes na seguradora "+seguradora.getNome()+":");
					for(int i=0; i<seguradora.listaClientes.size(); i++) {
						System.out.println(seguradora.getListaClientes().get(i).toString());
					}
					System.out.println("\n");
				}
				break;
				
			case LISTAR_SEGURO_POR_CLIENTE:
				{
					Seguradora seguradora = Utils.inputSeguradora(input);
					
					if(seguradora == null) {
						System.out.println("Nenhuma seguradora disponível!");
						return;	
					}
					
					System.out.print("Nome do cliente: ");
					String nome = input.nextLine();
					Cliente cliente = seguradora.procurarCliente(nome);
					while(cliente == null) {
						System.out.println("Cliente inexistente. Tente novamente!");
						nome = input.nextLine();
						cliente = seguradora.procurarCliente(nome);
					}
					
					ArrayList<Seguro> listaSegurosCliente = seguradora.getSegurosPorCliente(cliente);
					
					for(int i=0; i<listaSegurosCliente.size(); i++) {
						System.out.println(listaSegurosCliente.get(i).getId());
					}
					
					System.out.println("\n");
				}
				break;
				
			case LISTAR_SINISTRO_POR_CLIENTE:
				{					
					Seguradora seguradora = Utils.inputSeguradora(input);
					
					if(seguradora == null) {
						System.out.println("Nenhuma seguradora disponível!");
						return;	
					}
					
					System.out.print("Nome do cliente: ");
					String nome = input.nextLine();
					Cliente cliente = seguradora.procurarCliente(nome);
					while(cliente == null) {
						System.out.println("Cliente inexistente. Tente novamente!");
						nome = input.nextLine();
						cliente = seguradora.procurarCliente(nome);
					}
					
					ArrayList<Sinistro> listaSinistrosCliente = seguradora.getSinistrosPorCliente(cliente);
					
					for(int i=0; i<listaSinistrosCliente.size(); i++) {
						System.out.println(listaSinistrosCliente.get(i).getId());
					}
					
					System.out.println("\n");
				}
				break;
				
			case LISTAR_VEICULO_POR_CLIENTE:
				{
					System.out.println("Digite o nome do cliente: ");
					Cliente cliente;
					String nome = input.nextLine();
					cliente = MenuOperacoes.searchCliente(nome);
					while(cliente == null) {
						System.out.println("Cliente inexistente! Tente novamente.");
						nome = input.nextLine();
						cliente = MenuOperacoes.searchCliente(nome);
					}
					
					if(cliente instanceof ClientePF) {
						// Downcast
						ClientePF clientePF = (ClientePF) cliente;
						for(int i=0; i<clientePF.getListaVeiculos().size(); i++) {
							System.out.println(clientePF.getListaVeiculos().get(i));
						}
					}
					else if(cliente instanceof ClientePJ) {
						// Downcast
						ClientePJ clientePJ = (ClientePJ) cliente;
						System.out.println("Selecione uma frota:");
						for(int i=0; i<clientePJ.getListaFrotas().size(); i++) {
							System.out.println(clientePJ.getListaFrotas().get(i).getCode());
						}
						int opcao = Integer.parseInt(input.nextLine());
						while(opcao-1<0 || opcao-1>=clientePJ.getListaFrotas().size()) {
							System.out.println("Digite uma opcao valida!");
							opcao = Integer.parseInt(input.nextLine());
						}
						Frota frota = clientePJ.getListaFrotas().get(opcao-1);
						
						for(int i=0; i<frota.getListaVeiculos().size(); i++) {
							System.out.println("Marca: "+frota.getListaVeiculos().get(i).getMarca()+"; Modelo: "+frota.getListaVeiculos().get(i).getModelo()+"; Fabricacao"+frota.getListaVeiculos().get(i).getAnoFabricacao());
						}
					}
					
					System.out.println("\n");					
				}
				break;
			
			case LISTAR_CONDUTORES_POR_SEGURO:
				{
					Seguradora seguradora = Utils.inputSeguradora(input);
					
					if(seguradora == null) {
						System.out.println("Nenhuma seguradora disponível!");
						return;	
					}
					
					System.out.print("Nome do cliente: ");
					String nome = input.nextLine();
					Cliente cliente = seguradora.procurarCliente(nome);
					while(cliente == null) {
						System.out.println("Cliente inexistente. Tente novamente!");
						nome = input.nextLine();
						cliente = seguradora.procurarCliente(nome);
					}
					
					ArrayList<Seguro> listaSegurosCliente = seguradora.getSegurosPorCliente(cliente);
					
					if(listaSegurosCliente.size()==0) {
						System.out.println("O cliente "+cliente.getNome()+" nao possui nenhum seguro.");
						return;
					}
					
					System.out.println("Selecione um seguro:");
					for(int i=0; i<listaSegurosCliente.size(); i++) {
						System.out.println(i+1+": "+listaSegurosCliente.get(i).getId());
					}
					int opcao = Integer.parseInt(input.nextLine());
					while(opcao-1<0 || opcao-1>=listaSegurosCliente.size()) {
						System.out.println("Selecione uma opcao valida!");
						opcao = Integer.parseInt(input.nextLine());
					}
					Seguro seguro = listaSegurosCliente.get(opcao-1);
					
					for(int i=0; i<seguro.getListaCondutores().size(); i++) {
						System.out.println("Nome: "+seguro.getListaCondutores().get(i).getNome()+"; cpf: "+seguro.getListaCondutores().get(i).getCpf());
					}
					
					System.out.println("\n");
				}
				break;
				
			case VOLTAR:
				break;
		}
	}
}
