package lab04;
import java.time.LocalDate;
import java.util.Scanner;

/*

• Cadastrar e remover pelo menos 1 Cliente (ClientePF ou ClientePJ);

• Chamar os m etodos validarCPF(cpf: String) (ClientePF) e validarCNPJ(cnpj: String) (ClientePJ);

• Adicionar pelo menos 1 Veiculo em cada Cliente instanciado;

• Instanciar pelo menos 1 objeto de Seguradora;

• Cadastrar pelo menos 2 clientes em Seguradora (sem remover), sendo 1 do tipo ClientePF e 1 do tipo
ClientePJ;

• Gerar pelo menos 1 Sinistro;

• Chamar o m ́etodo toString() de cada classe;

• Chamar os m ́etodos listarClientes (tipoCliente: String), visualizarSinistro(cliente: String) e listarSi-
nistros() da classe Seguradora;

• Implementar e chamar um m ́etodo que fa ̧ca leitura de dados usando a fun ̧c ̃ao System.In.

*/

public class Main {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		
		ClientePF cesarLattes = new ClientePF("Cesar Lattes", "Rua da Ciência, 123", "514-816-328-33", "Masculino",
				LocalDate.of(1950, 6, 20), "Doutorado em Física", LocalDate.of(1924, 7, 11), "Classe A");
		Veiculo porcheTaycan = new Veiculo("XYZ-1234", "Porsche", "Taycan", 2021);
		cesarLattes.adicionarVeiculo(porcheTaycan);
		
		ClientePJ Unicamp = new ClientePJ("Universidade Estadual de Campinas", 
				"Cidade Universitária Zeferino Vaz, Barão Geraldo, Campinas, SP, Brasil", "46.068.425/0001-33", LocalDate.of(1966, 10, 5), 10);
		Veiculo modelS = new Veiculo("ABC1234", "Tesla", "Model S", 2022);
		Unicamp.adicionarVeiculo(modelS);
		
		Seguradora portoSeguro = new Seguradora("Porto Seguro", "(11) 3366-3000", "atendimento@portoseguro.com.br", 
				"Alameda Barão de Limeira, 539 - Campos Elíseos, São Paulo - SP, 01202-001");
		
		portoSeguro.cadastrarCliente(cesarLattes);
		portoSeguro.cadastrarCliente(Unicamp);
		
		portoSeguro.gerarSinistro("18/04/2023", "Rua Francisco Silveira Campos, 123 - Barao Geraldo, Campinas - sp", portoSeguro, modelS, Unicamp);
		
		MenuOperacoes.listaSeguradoras.add(portoSeguro);
		MenuOperacoes.listaClientes.add(Unicamp);
		
		// Menu Inicial
		MenuOperacoes.menuInicial(input);
		
		input.close();
	}
}
