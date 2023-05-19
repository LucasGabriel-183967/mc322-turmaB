package lab04;
import java.util.Scanner;

public enum Excluir {
	EXCLUIR_CLIENTE(1), EXCLUIR_VEICULO(2), EXCLUIR_SINISTRO(3), VOLTAR(4);
	
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
					System.out.println("Digite o nome do cliente: ");
					String nome = input.nextLine();
					for(int i=0; i<MenuOperacoes.listaSeguradoras.size(); i++) {
						// Remove o cliente de todas as seguradoras (conjuntamente com seus sinistros gerados)
						MenuOperacoes.listaSeguradoras.get(i).removerCliente(nome);
					}
					for(int i=0; i<MenuOperacoes.listaClientes.size(); i++) {
						if(MenuOperacoes.listaClientes.get(i).getNome().equals(nome)) {
							// Remove o cliente da lista de clientes
							MenuOperacoes.listaClientes.remove(i);
							System.out.println("\n");
						}
					}
					System.out.println("\n");
				}
				break;
			
			case EXCLUIR_VEICULO:
				{
					System.out.println("Qual o nome do cliente?");
					Cliente cliente = null;
					String nome = input.nextLine();
					cliente = MenuOperacoes.searchCliente(nome);
					while(cliente == null) {
						System.out.println("Cliente inexistente! Tente novamente.");
						nome = input.nextLine();
						cliente = MenuOperacoes.searchCliente(nome);
					}
					
					System.out.println("Qual a placa do veiculo?");
					// note que podem haver conflitos (carro com mesmo nome e marca, porem data de fabricacao distinto)
					System.out.println("Placa: ");
					String placa = input.nextLine();
					
					for(int i=0; i<cliente.getListaVeiculos().size(); i++) {
						if(cliente.getListaVeiculos().get(i).getPlaca().equals(placa)) {
							cliente.getListaVeiculos().remove(i);
							System.out.println("Veiculo removido.\n");
						}
					}
					
					System.out.println("Veiculo nao encontrado.\n");
				}
				break;
			
			case EXCLUIR_SINISTRO:
				{
					System.out.println("Qual o nome do cliente?");
					Cliente cliente = null;
					String nome = input.nextLine();
					cliente = MenuOperacoes.searchCliente(nome);
					while(cliente == null) {
						System.out.println("Cliente inexistente! Tente novamente.");
						nome = input.nextLine();
						cliente = MenuOperacoes.searchCliente(nome);
					}			
					
					if(cliente.getListaSinsitro().size()==0) {
						System.out.println("O cliente "+cliente.getNome()+" nao possui nenhum sinistro gerado.\n");
					}
					
					System.out.println("Selecione um sinistro gerado pelo cliente: ");
					for(int i=0; i<cliente.getListaSinsitro().size(); i++) {
						System.out.println("Data: "+cliente.getListaSinsitro().get(i).getData()+" Veiculo: "+cliente.getListaSinsitro().get(i).getVeiculo()+" Seguradora: "+cliente.getListaSinsitro().get(i).getSeguradora());
					}
					
					int j = Integer.parseInt(input.nextLine());
					while(j<0 || j>=cliente.getListaSinsitro().size()) {
						System.out.println("Selecione uma opcao valida!");
						j = Integer.parseInt(input.nextLine());
					}
					
					Sinistro sinistro = cliente.getListaSinsitro().get(j);
					Seguradora seguradora = sinistro.getSeguradora();
					cliente.getListaSinsitro().remove(j);
					for(int i=0; i<seguradora.getListaSinistros().size(); i++) {
						if(seguradora.getListaSinistros().get(i).equals(sinistro)) {
							seguradora.getListaSinistros().remove(i);
							System.out.println("\n");
						}
					}
				}
				break;
			
			case VOLTAR:
				break;
		}
	}
}
