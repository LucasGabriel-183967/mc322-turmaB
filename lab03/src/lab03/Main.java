package lab03;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
		ClientePF cesarLattes = new ClientePF("Cesar Lattes", "Rua da Ciência, 123", "514-816-328-33", "Masculino", 
				LocalDate.of(1950, 6, 20), "Doutorado em Física", LocalDate.of(1924, 7, 11), "Classe A");
		Veiculo porcheTaycan = new Veiculo("XYZ-1234", "Porsche", "Taycan", 2021);
		cesarLattes.adicionarVeiculo(porcheTaycan);
		
		ClientePJ Unicamp = new ClientePJ("Universidade Estadual de Campinas", 
				"Cidade Universitária Zeferino Vaz, Barão Geraldo, Campinas, SP, Brasil", "46.068.425/0001-33", LocalDate.of(1966, 10, 5));
		Veiculo modelS = new Veiculo("ABC1234", "Tesla", "Model S", 2022);
		Unicamp.adicionarVeiculo(modelS);
		
		Seguradora portoSeguro = new Seguradora("Porto Seguro", "(11) 3366-3000", "atendimento@portoseguro.com.br", 
				"Alameda Barão de Limeira, 539 - Campos Elíseos, São Paulo - SP, 01202-001");
		
		portoSeguro.cadastrarCliente(cesarLattes);
		portoSeguro.cadastrarCliente(Unicamp);
		
		portoSeguro.gerarSinistro("18/04/2023", "Rua Francisco Silveira Campos, 123 - Barao Geraldo, Campinas - sp", portoSeguro, modelS, Unicamp);
		
		System.out.println(cesarLattes.toString());
		System.out.println(porcheTaycan.toString());
		System.out.println(Unicamp.toString());
		System.out.println(modelS.toString());
		System.out.println(portoSeguro.toString());
		
		System.out.println(portoSeguro.listarClientes("pf"));
		System.out.println(portoSeguro.listarClientes("pj"));
		portoSeguro.removerCliente("Cesar Lattes");
		System.out.println(portoSeguro.listarClientes("pf"));
		
		System.out.println(portoSeguro.visualizarSinistro("Universidade Estadual de Campinas"));
		System.out.println(portoSeguro.listarSinistros());
		
		System.out.println(cesarLattes.validarCPF("51481632833"));
		System.out.println(Unicamp.validarCNPJ("46.068.425/0001-33"));
		
		boolean x = true;
		Scanner scanner = new Scanner(System.in);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		while(x==true) {
			System.out.println("Voce quer gerar um cliente PF ou PJ? Caso você queira sair, digite 'Sair'");
			String pessoa = scanner.nextLine();
			if(pessoa.equals("PF") || pessoa.equals("pf")) {
				System.out.println("Digite os seguintes campos para gerar uma PF:"
						+ "\nString nome, String endereco, String cpf, String genero, LocalDate dataLicenca, String educacao,\n"
						+ "LocalDate dataNascimento, String classeEconomica");
				String newNome = scanner.nextLine();
				String newEndereco = scanner.nextLine();
				String newCpf = scanner.nextLine();
				String newGenero = scanner.nextLine();
				String newDataLicencaString = scanner.nextLine();
		        LocalDate newDataLicenca = LocalDate.parse(newDataLicencaString, formatter);
		        String newEducacao = scanner.nextLine();
		        String newDataNascimentoString = scanner.nextLine();
		        LocalDate newDataNascimento = LocalDate.parse(newDataNascimentoString, formatter);
		        String newClasseEconomica = scanner.nextLine();
		        Cliente newClientePF = new ClientePF(newNome, newEndereco, newCpf, newGenero, newDataLicenca, newEducacao, newDataNascimento, newClasseEconomica);
		        portoSeguro.cadastrarCliente(newClientePF);
			}
			else if(pessoa.equals("PJ") || pessoa.equals("pj")) {
				System.out.println("Digite os seguintes campos para gerar uma PJ:"
						+ "String nome, String endereco, String cnpj, LocalDate dataFundacao");
				String newNome = scanner.nextLine();
				String newEndereco = scanner.nextLine();
				String newCnpj = scanner.nextLine();
				String newDataFundacaoString = scanner.nextLine();
		        LocalDate newDataFundacao = LocalDate.parse(newDataFundacaoString, formatter);
		        Cliente newClientePj = new ClientePJ(newNome, newEndereco, newCnpj, newDataFundacao);
		        portoSeguro.cadastrarCliente(newClientePj);
			}
			else if(pessoa.equals("Sair") || pessoa.equals("sair")) {
				x = false;
				scanner.close();
				break;
			}
		}
	}

}
