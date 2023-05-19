package lab04;
import java.util.Scanner;

public class GerarSinistro {
	public static void gerarSinistro(Scanner input) {
		System.out.println("Data (formato dd/mm/yyyy): ");
		String data = input.nextLine();
		
		System.out.println("Endereco: ");
		String endereco = input.nextLine();
		
		System.out.println("Selecione uma seguradora: ");
		for(int i=0; i<MenuOperacoes.listaSeguradoras.size(); i++) {
			System.out.println((i+1)+": "+MenuOperacoes.listaSeguradoras.get(i).getNome());
		}
		
		int j = Integer.parseInt(input.nextLine());
		j--;
		while(j<0 || j>=MenuOperacoes.listaSeguradoras.size()) {
			System.out.println("Selecione uma opcao valida!");
			for(int i=0; i<MenuOperacoes.listaSeguradoras.size(); i++) {
				System.out.println((i+1)+": "+MenuOperacoes.listaSeguradoras.get(i).getNome());
			}
			j = Integer.parseInt(input.nextLine());
		}
		Seguradora seguradora = MenuOperacoes.listaSeguradoras.get(j);
		
		System.out.println("Nome do cliente (PF ou PJ):");
		Cliente cliente = null;
		String nome = input.nextLine();
		cliente = seguradora.searchClienteSeguradora(nome);
		while(cliente == null) {
			System.out.println("Cliente inexistente. Tente novamente!");
			nome = input.nextLine();
			cliente = seguradora.searchClienteSeguradora(nome);
		}
				
		System.out.println("Selecione um veiculo: ");
		for(int i=0; i<cliente.getListaVeiculos().size(); i++) {
			System.out.println(i+1+": "+cliente.getListaVeiculos().get(i).getMarca()+" "+cliente.getListaVeiculos().get(i).getModelo()+" "+cliente.getListaVeiculos().get(i).getAnoFabricacao());
		}
		j = Integer.parseInt(input.nextLine());
		while(j-1<0 || j-1>=cliente.getListaVeiculos().size()){
			System.out.println("Selecione uma opcao valida!");
			j = Integer.parseInt(input.nextLine());
		}
		Veiculo veiculo = cliente.getListaVeiculos().get(j-1);
		
		Sinistro newSinistro = seguradora.gerarSinistro(data, endereco, seguradora, veiculo, cliente);
		cliente.adicionarSinistro(newSinistro);
		
		System.out.println("Sinsitro gerado!\n");
	}
}
