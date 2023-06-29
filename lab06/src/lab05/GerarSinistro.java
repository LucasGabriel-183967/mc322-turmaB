package lab05;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class GerarSinistro {
	protected static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	public static void gerarSinistro(Scanner input) {
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
		
		ArrayList<Seguro> listaSegurosCliente = seguradora.getSegurosPorCliente(cliente);
		System.out.println("Selecione um seguro: ");
		for(int i=0; i<listaSegurosCliente.size(); i++) {
			System.out.println(i+1+": "+listaSegurosCliente.get(i).getId());
		}
		opcao = Integer.parseInt(input.nextLine());
		while(opcao-1<0 || opcao-1>=listaSegurosCliente.size()) {
			System.out.println("Selecione uma opcao valida!");
			opcao = Integer.parseInt(input.nextLine());
		}
		Seguro seguroCliente = listaSegurosCliente.get(opcao-1);
		
		System.out.println("Data (formato dd/mm/yyyy): ");
		String dataString = input.nextLine();
		LocalDate data = LocalDate.parse(dataString, formatter);
		
		System.out.println("Endereco: ");
		String endereco = input.nextLine();
		
		System.out.println("Selecione um condutor: ");
		for(int i=0; i<seguroCliente.getListaCondutores().size(); i++) {
			System.out.println(i+1+": "+seguroCliente.getListaCondutores().get(i));
		}
		opcao = Integer.parseInt(input.nextLine());
		while(opcao-1<0 || opcao-1>=seguroCliente.getListaCondutores().size()){
			System.out.println("Selecione uma opcao valida!");
			opcao = Integer.parseInt(input.nextLine());
		}
		Condutor condutor = seguroCliente.getListaCondutores().get(opcao-1);
		
		Sinistro newSinistro = new Sinistro(data, endereco, condutor, seguroCliente);
		seguroCliente.gerarSinistro(newSinistro);
		
		System.out.println("Sinsitro gerado!\n");
	}
}
