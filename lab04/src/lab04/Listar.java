package lab04;
import java.util.Scanner;

public enum Listar {
	LISTAR_CLIENTE_POR_SEG(1), LISTAR_SINISTROS_POR_SEG(2), LISTAR_SINISTRO_POR_CLIENTE(3), LISTAR_VEICULO_POR_CLIENTE(4), LISTAR_VEICULO_POR_SEGURADORA(5), VOLTAR(6);
	
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
					System.out.println("Selecione uma seguradora: ");
					if(MenuOperacoes.listaSeguradoras.size()==0) {
						System.out.println("Nenhuma seguradora disponível!");
						return;
						
					}
					else {
						for(int i=0; i<MenuOperacoes.listaSeguradoras.size(); i++) {
							System.out.println(i+1+": "+MenuOperacoes.listaSeguradoras.get(i).getNome());
						}
						
						int j = Integer.parseInt(input.nextLine());
						while(j-1<0 || j-1>=MenuOperacoes.listaSeguradoras.size()) {
							System.out.println("Selecione uma opcao valida!");
							j = Integer.parseInt(input.nextLine());
						}
						Seguradora opcaoSeguradora = MenuOperacoes.listaSeguradoras.get(j-1);
						
						System.out.println("Clientes na seguradora "+opcaoSeguradora.getNome()+":");
						for(int i=0; i<opcaoSeguradora.listaClientes.size(); i++) {
							System.out.println(opcaoSeguradora.listaClientes.get(i));
						}
					}
					System.out.println("\n");
				}
				break;
				
			case LISTAR_SINISTROS_POR_SEG:
				{
					System.out.println("Selecione uma seguradora: ");
					if(MenuOperacoes.listaSeguradoras.size()==0) {
						System.out.println("Nenhuma seguradora disponível!");
						return;
						
					}
					else {
						for(int i=0; i<MenuOperacoes.listaSeguradoras.size(); i++) {
							System.out.println(i+1+": "+MenuOperacoes.listaSeguradoras.get(i).getNome());
						}
						
						int j = Integer.parseInt(input.nextLine());
						while(j-1<0 || j-1>=MenuOperacoes.listaSeguradoras.size()) {
							System.out.println("Selecione uma opcao valida!");
							j = Integer.parseInt(input.nextLine());
						}
						Seguradora opcaoSeguradora = MenuOperacoes.listaSeguradoras.get(j-1);
						
						System.out.println("Lista de sinistros na seguradora "+opcaoSeguradora.getNome()+": ");
						for(int i=0; i<opcaoSeguradora.getListaSinistros().size(); i++) {
							System.out.println("Cliente "+opcaoSeguradora.listaSinistros.get(i).getCliente().getNome()+", "+opcaoSeguradora.listaSinistros.get(i).getVeiculo()+", data "+opcaoSeguradora.listaSinistros.get(i).getData());
						}
					}
					System.out.println("\n");
				}
				break;
				
			case LISTAR_SINISTRO_POR_CLIENTE:
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
					
					System.out.println("Lista de sinistros do cliente "+cliente.getNome()+":");
					for(int i=0; i<cliente.getListaSinsitro().size(); i++) {
						System.out.println(cliente.getListaSinsitro().get(i));
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
					
					System.out.println("Lista de veiculos do cliente "+cliente.getNome()+":");
					for(int i=0; i<cliente.getListaVeiculos().size(); i++) {
						System.out.println(cliente.getListaVeiculos().get(i));
					}
					
					System.out.println("\n");					
				}
				break;
				
			case LISTAR_VEICULO_POR_SEGURADORA:
				{					
					for(int i=0; i<MenuOperacoes.listaSeguradoras.size(); i++) {
						System.out.println(i+1+": "+MenuOperacoes.listaSeguradoras.get(i).getNome());
					}
					
					int j = Integer.parseInt(input.nextLine());
					while(j-1<0 || j-1>=MenuOperacoes.listaSeguradoras.size()) {
						System.out.println("Selecione uma opcao valida!");
						j = Integer.parseInt(input.nextLine());
					}
					Seguradora opcaoSeguradora = MenuOperacoes.listaSeguradoras.get(j-1);
					
					for(int i=0; i<opcaoSeguradora.getListaClientes().size(); i++) {
						Cliente cliente = opcaoSeguradora.listaClientes.get(i);
						System.out.println("Cliente "+cliente.getNome()+": ");
						if(cliente.getListaVeiculos().size()==0) {
							continue;
						}
						else {
							for(int k=0; j<cliente.getListaVeiculos().size(); k++) {
								System.out.println(cliente.getListaVeiculos().get(k));
							}					
						}
					}
					
					System.out.println("\n");
				}
				break;
				
			case VOLTAR:
				break;
		}
	}
}
