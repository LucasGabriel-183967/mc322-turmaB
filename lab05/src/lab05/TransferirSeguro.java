package lab05;
import java.util.ArrayList;
import java.util.Scanner;

public class TransferirSeguro {
	public static void transferirSeguro(Scanner input) {
		System.out.println("Selecione uma seguradora: ");
		for(int i=0; i<MenuOperacoes.getListaSeguradoras().size(); i++) {
			System.out.println(i+1+": "+MenuOperacoes.getListaSeguradoras().get(i).getNome());
		}
		int opcao = Integer.parseInt(input.nextLine());
		while(opcao-1<0 || opcao-1>=MenuOperacoes.getListaSeguradoras().size()) {
			System.out.println("Selecione uma opcao valida!");
			opcao = Integer.parseInt(input.nextLine());
		}
		Seguradora seguradora = MenuOperacoes.getListaSeguradoras().get(opcao-1);
		
		System.out.println("Qual o nome do cliente?");
		Cliente clienteDoador = null;
		String nome = input.nextLine();
		clienteDoador = seguradora.procurarCliente(nome);
		while(clienteDoador == null) {
			System.out.println("Cliente inexistente na seguradora "+seguradora.getNome()+"! Tente novamente.");
			nome = input.nextLine();
			clienteDoador = seguradora.procurarCliente(nome);
		}			
		
		ArrayList<Seguro> listaSegurosCliente = seguradora.getSegurosPorCliente(clienteDoador);
		if(listaSegurosCliente.size()==0) {
			System.out.println("O cliente "+clienteDoador.getNome()+" nao possui nenhum seguro.\n");
			return;
		}
		
		System.out.println("Selecione um seguro gerado pelo cliente: ");
		for(int i=0; i<listaSegurosCliente.size(); i++) {
			System.out.println(i+1+": "+listaSegurosCliente.get(i).getId());
		}
		
		opcao = Integer.parseInt(input.nextLine());
		while(opcao-1<0 || opcao-1>=listaSegurosCliente.size()) {
			System.out.println("Selecione uma opcao valida!");
			opcao = Integer.parseInt(input.nextLine());
		}
		
		Seguro seguro = listaSegurosCliente.get(opcao-1);
		
		System.out.println("Qual o nome do cliente que recebera o seguro?");
		Cliente clienteRecebedor = null;
		String nomeRecebedor = input.nextLine();
		clienteRecebedor = seguradora.procurarCliente(nomeRecebedor);
		while(clienteRecebedor == null) {
			System.out.println("Cliente inexistente na seguradora "+seguradora.getNome()+"! Tente novamente.");
			nomeRecebedor = input.nextLine();
			clienteRecebedor = seguradora.procurarCliente(nomeRecebedor);
		}
		
		// Note que o veiculo/frota referente ao seguro e passado ao clienteRecebedor
		if(clienteDoador instanceof ClientePF && clienteRecebedor instanceof ClientePF) {
			seguro.setCliente(clienteRecebedor);
			// Downcasting
			SeguroPF seguroPF = (SeguroPF) seguro;
			Veiculo veiculo = seguroPF.getVeiculo();
			ClientePF clienteDoadorPF = (ClientePF) clienteDoador;
			ClientePF clienteRecebedorPF = (ClientePF) clienteRecebedor;
			clienteDoadorPF.removerVeiculo(veiculo);
			clienteRecebedorPF.cadastrarVeiculo(veiculo);
		}
		else if(clienteDoador instanceof ClientePJ && clienteRecebedor instanceof ClientePJ) {
			seguro.setCliente(clienteRecebedor);
			// Downcasting
			SeguroPJ seguroPJ = (SeguroPJ) seguro;
			Frota frota = seguroPJ.getFrota();
			ClientePJ clienteDoadorPJ = (ClientePJ) clienteDoador;
			ClientePJ clienteRecebedorPJ = (ClientePJ) clienteRecebedor;
			clienteDoadorPJ.removerFrota(frota);
			clienteRecebedorPJ.cadastrarFrota(frota);
		}
		else {
			System.out.println("Os clientes devem ser do mesmo tipo!");
		}
	}
}
