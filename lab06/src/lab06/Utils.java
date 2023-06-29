package lab06;

import java.util.ArrayList;
import java.util.Scanner;

public class Utils {
	// Classe de funcoes do tipo static uteis durante a execucao de todo o codigo
	public static Cliente searchCliente(ArrayList<Cliente> listaClientes, String cliente) {
		for(int i=0; i<listaClientes.size(); i++) {
			if(listaClientes.get(i).nome.equals(cliente)) {
				return listaClientes.get(i);
			}
		}
		
		return null;
	}
	
	public static String inputNome(Scanner input) {
		System.out.println("Nome: ");
		String nome = input.nextLine();
		while(Validacao.validarNome(nome)==false) {
			System.out.println("Nome invalido! Tente novamente.");
			nome = input.nextLine();
		}
		
		return nome;
	}
	
	public static String inputCPF(Scanner input) {
		System.out.println("CPF: ");
		String cpf = input.nextLine();
		while(Validacao.validarCPF(cpf)==false) {
			System.out.println("Digite um CPF valido!");
			cpf = input.nextLine();
		}
		
		return cpf;
	}
	
	public static String inputCNPJ(Scanner input) {
		System.out.println("CNPJ:");
		String cnpj = input.nextLine();
		while(Validacao.validarCNPJ(cnpj)==false) {
			System.out.println("Digite um cnpj valido!");
			cnpj = input.nextLine();
		}
		
		return cnpj;
	}
	
	public static String inputPlaca(Scanner input) {
		System.out.println("Placa: ");
		String placa = input.nextLine();
		while(Validacao.validarPlaca(placa)==false) {
			System.out.println("Digite uma placa valida!");
			placa = input.nextLine();
		}
		
		return placa;
	}
	
	public static Seguradora inputSeguradora(Scanner input) {
		System.out.println("Selecione uma seguradora: ");
		if(MenuOperacoes.getListaSeguradoras().size()==0) {
			return null;
		}
		
		for(int i=0; i<MenuOperacoes.getListaSeguradoras().size(); i++) {
			System.out.println(i+1+": "+MenuOperacoes.getListaSeguradoras().get(i).getNome());
		}
		int opcao = Integer.parseInt(input.nextLine());
		while(opcao-1<0 || opcao-1>=MenuOperacoes.getListaSeguradoras().size()) {
			System.out.println("Selecione uma opcao valida!");
			opcao = Integer.parseInt(input.nextLine());
		}
		return MenuOperacoes.getListaSeguradoras().get(opcao-1);
	}
}
