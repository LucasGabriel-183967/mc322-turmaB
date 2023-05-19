package lab04;
import java.util.Scanner;

public class TransferirSeguro {
	public static void transferirSeguro(Scanner input) {
		System.out.println("Qual o nome do cliente?");
		Cliente cliente = null;
		String nome = input.nextLine();
		System.out.println("Nome: "+nome);
		cliente = MenuOperacoes.searchCliente(nome);
		while(cliente == null) {
			System.out.println("Cliente inexistente! Tente novamente.");
			nome = input.nextLine();
			cliente = MenuOperacoes.searchCliente(nome);
		}			
		
		if(cliente.getListaSinsitro().size()==0) {
			System.out.println("O cliente "+cliente.getNome()+" nao possui nenhum sinistro gerado.\n");
			return;
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
		
		System.out.println("Qual o nome do cliente que recebera o seguro?");
		Cliente clienteRecebedor = null;
		String nomeRecebedor = input.nextLine();
		clienteRecebedor = MenuOperacoes.searchCliente(nomeRecebedor);
		while(clienteRecebedor == null) {
			System.out.println("Cliente inexistente! Tente novamente.");
			nomeRecebedor = input.nextLine();
			clienteRecebedor = MenuOperacoes.searchCliente(nomeRecebedor);
		}
		
		// Note que ambos o sinistro e o veiculo referente ao sinistro sao passados ao clienteRecebedor
		cliente.removerSinistro(sinistro);
		cliente.removerVeiculo(sinistro.getVeiculo());
		
		clienteRecebedor.adicionarSinistro(sinistro);
		clienteRecebedor.adicionarVeiculo(sinistro.getVeiculo());
		
		for(int i=0; i<seguradora.getListaSinistros().size(); i++) {
			if(seguradora.getListaSinistros().get(i).equals(sinistro)) {
				seguradora.getListaSinistros().get(i).cliente = clienteRecebedor;
				System.out.println("\n");
				return;
			}
		}
		
	}
}
