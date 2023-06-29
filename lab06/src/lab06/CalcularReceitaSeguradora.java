package lab06;
import java.util.Scanner;

public class CalcularReceitaSeguradora {
	public static double calcularReceitaSeguradora(Scanner input) {
		System.out.println("Selecione uma seguradora:");
		for(int i=0; i<MenuOperacoes.listaSeguradoras.size(); i++) {
			System.out.println(i+1+": "+MenuOperacoes.listaSeguradoras.get(i).getNome());
		}
		int j = Integer.parseInt(input.nextLine());
		while(j-1<0 || j-1>=MenuOperacoes.listaSeguradoras.size()) {
			System.out.println("Selecione uma opcao valida!");
			j = Integer.parseInt(input.nextLine());
		}
		Seguradora seguradora = MenuOperacoes.listaSeguradoras.get(j-1);
		
		double receita = seguradora.calcularReceita();
			
		return receita;
	}
}
