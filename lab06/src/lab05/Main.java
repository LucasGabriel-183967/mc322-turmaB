package lab05;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		
		// Instanciando clientes e seus veiculos/frotas
		ClientePF alanTuring = new ClientePF(
                "Alan Turing",
                "123456789",
                "Bletchley Park",
                "alan.turing@example.com",
                "123.456.789-10",
                "Masculino",
                "Doutorado",
                LocalDate.of(1912, 6, 23),
                LocalDate.of(1954, 6, 7),
                "Classe Media"
        );
		Veiculo porcheTaycan = new Veiculo("XYZ-1234", "Porsche", "Taycan", 2021);
		alanTuring.cadastrarVeiculo(porcheTaycan);
		
		ClientePJ google = new ClientePJ(
                "Google",
                "0800-724-8149",
                "1600 Amphitheatre Parkway, Mountain View, California",
                "contact@google.com",
                "12.345.678/0001-90",
                LocalDate.of(1998, 9, 4),
                139995
        );
		Veiculo modelS = new Veiculo("ABC1234", "Tesla", "Model S", 2022);
		Veiculo bikezona = new Veiculo("XYZ987", "Caloi", "Explorer", 1940);
		Frota frota = new Frota();
		frota.cadastrarVeiculo(modelS);
		frota.cadastrarVeiculo(bikezona);
		google.cadastrarFrota(frota);		
		
		// instanciando um condutor de velozes e furiosos
		Condutor dwayneJohnson = new Condutor(
                "Dwayne Johnson",
                "(00) 9876-5432",
                "Rua Velocidade, 456",
                "therock@example.com",
                "987.654.321-00",
                LocalDate.of(1972, 5, 2),
                LocalDate.of(1998, 10, 1)
        );
		
		// instanciando uma seguradora confiavel
		Seguradora poucoSegura = new Seguradora(
                " 03.800.799/0001-35",
                "PoucoSegura",
                "(00) 1234-5678",
                "Rua Segura, 123",
                "contato@poucosegura.com"
        );
			
		// cadastrando os clientes e gerando um seguro
		poucoSegura.cadastrarCliente(google);
		poucoSegura.cadastrarCliente(alanTuring);
		
		poucoSegura.gerarSeguro(alanTuring, LocalDate.of(1990, 10, 1), LocalDate.of(2000, 10, 1), poucoSegura, porcheTaycan);
		poucoSegura.gerarSeguro(google, LocalDate.of(2000, 10, 1), LocalDate.of(2020, 10, 1), poucoSegura, frota);
		
		// colocando o condutor em cada seguro e gerando um sinistro
		ArrayList<Seguro> listaSegurosCliente = poucoSegura.getSegurosPorCliente(alanTuring);
		for(int i=0; i<listaSegurosCliente.size(); i++) {
			listaSegurosCliente.get(i).autorizarCondutor(dwayneJohnson);
			Sinistro sinistro = new Sinistro(LocalDate.of(2004, 12, 1), "Rua nenhumPouco Segura", dwayneJohnson, listaSegurosCliente.get(i));
			listaSegurosCliente.get(i).gerarSinistro(sinistro);
		}
		
		listaSegurosCliente = poucoSegura.getSegurosPorCliente(google);
		for(int i=0; i<listaSegurosCliente.size(); i++) {
			listaSegurosCliente.get(i).autorizarCondutor(dwayneJohnson);
		}
		
		// adicionando os novos clientes e a nova seguradora as listas globais do programa
		MenuOperacoes.getListaSeguradoras().add(poucoSegura);
		MenuOperacoes.getListaClientesPF().add(alanTuring);
		MenuOperacoes.getListaClientesPJ().add(google);
		
		// Menu Inicial
		MenuOperacoes.menuInicial(input);
		
		input.close();
	}
}
