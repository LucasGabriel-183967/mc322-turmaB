package lab04;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public enum Cadastrar {
	CADASTRAR_CLIENTE(1), CADASTRAR_VEICULO(2), CADASTRAR_SEGURADORA(3), VOLTAR(4);

	public final int operacao;
	Cadastrar(int operacao){
		this.operacao = operacao;
	}
	
	public int getOperacao() {
		return this.operacao;
	}
	
	static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	public static void cadastrar(Scanner input) {
		System.out.println("O que voce quer fazer?");
		for(int i=0; i<Cadastrar.values().length; i++) {
			System.out.println(i+1+": "+Cadastrar.values()[i]);
		}
		int operacao = Integer.parseInt(input.nextLine());
		operacao--;
		while(operacao<0 || operacao>=Cadastrar.values().length) {
			System.out.println("Selecione uma opcao valida!");
			operacao = Integer.parseInt(input.nextLine());
			operacao--;
		}
		
		switch(Cadastrar.values()[operacao]) {
			case CADASTRAR_CLIENTE:
				{
					System.out.println("Em qual seguradora voce quer cadastrar o cliente?");
					if(MenuOperacoes.listaSeguradoras.size()==0) {
						System.out.println("Nenhuma seguradora disponível!");
						System.out.println("\n");
						break;
						
					}
					else {
						for(int i=0; i<MenuOperacoes.listaSeguradoras.size(); i++) {
							int j = i+1;
							System.out.println(j+": "+MenuOperacoes.listaSeguradoras.get(i).getNome());
						}
	
						int indiceSeguradora = Integer.parseInt(input.nextLine());
						indiceSeguradora--;
						while(indiceSeguradora>=MenuOperacoes.listaSeguradoras.size()) {
							System.out.println("Selecione uma opcao valida!");
							indiceSeguradora = Integer.parseInt(input.nextLine());
							indiceSeguradora--;
						}
						Seguradora opcaoSeguradora = MenuOperacoes.listaSeguradoras.get(indiceSeguradora);
						
						System.out.println("Qual o tipo do cliente? \n1 - Pessoa Fisica\n2 - Pessoa Juridica");
						int tipoCliente;
						tipoCliente = Integer.parseInt(input.nextLine());
						while(tipoCliente<1 || tipoCliente >2) {
							System.out.println("Selecione uma opcao valida!");
							tipoCliente = Integer.parseInt(input.nextLine());
						}
						if(tipoCliente == 1) {
							// ClientePF
							System.out.println("Nome: ");
							String nome = input.nextLine();
							while(Validacao.validarNome(nome)==false) {
								System.out.println("Nome invalido! Tente novamente.");
								nome = input.nextLine();
							}
							
							System.out.println("Endereco: ");
							String endereco = input.nextLine();
							
							System.out.println("CPF: ");
							String cpf = input.nextLine();
							while(Validacao.validarCPF(cpf)==false) {
								System.out.println("Digite um CPF valido!");
								cpf = input.nextLine();
							}
							
							System.out.println("Genero: ");
							String genero = input.nextLine();
							
							System.out.println("Data de Licenca (formato dd/mm/yyyy): ");
							String dataLicencaString = input.nextLine();
							LocalDate dataLicenca = LocalDate.parse(dataLicencaString, formatter);
							
							System.out.println("Educacao: ");
							String educacao = input.nextLine();
							
							System.out.println("Data de Nascimento (formato dd/mm/yyyy): ");
							String dataNascimentoString = input.nextLine();
							LocalDate dataNascimento = LocalDate.parse(dataNascimentoString, formatter);
							
							System.out.println("Classe Economica: ");
							String classeEconomica = input.nextLine();
							
							ClientePF newCliente = new ClientePF(nome, endereco, cpf, genero, dataLicenca, educacao, dataNascimento, classeEconomica);
							
							opcaoSeguradora.cadastrarCliente(newCliente);
							MenuOperacoes.listaClientes.add(newCliente);
							
							System.out.println("Cliente cadastrado com sucesso!\n");
						}
						else if(tipoCliente == 2) {
							// ClientePJ
							System.out.println("Nome: ");
							String nome = input.nextLine();
							while(Validacao.validarNome(nome)==false) {
								System.out.println("Nome invalido! Tente novamente.");
								nome = input.nextLine();
							}
							
							System.out.println("Endereco: ");
							String endereco = input.nextLine();
							
							System.out.println("CNPJ: ");
							String cnpj = input.nextLine();
							while(Validacao.validarCNPJ(cnpj)==false) {
								System.out.println("Digite um CPF valido!");
								cnpj = input.nextLine();
							}
							
							System.out.println("Data de Fundacao (formato dd/mm/yyyy): ");
							String dataFundacaoString = input.nextLine();
							LocalDate dataFundacao = LocalDate.parse(dataFundacaoString, formatter);
							
							System.out.println("Quantidade de funcionarios: ");
							int quantFuncionarios = Integer.parseInt(input.nextLine());
							
							ClientePJ newCliente = new ClientePJ(nome, endereco, cnpj, dataFundacao, quantFuncionarios);
							
							opcaoSeguradora.cadastrarCliente(newCliente);
							MenuOperacoes.listaClientes.add(newCliente);
							
							System.out.println("Cliente cadastrado com sucesso!\n");
						}
						System.out.println("\n");
					}
				}
				break;
				
			case CADASTRAR_VEICULO:
				{
					
					System.out.println("Nome do cliente (PF ou PJ):");
					Cliente cliente = null;
					String nome = input.nextLine();
					cliente = MenuOperacoes.searchCliente(nome);
					while(cliente == null) {
						System.out.println("Cliente inexistente. Tente novamente!");
						nome = input.nextLine();
						cliente = MenuOperacoes.searchCliente(nome);
					}
					
					System.out.println("Placa: ");
					String placa = input.nextLine();
					
					System.out.println("Marca: ");
					String marca = input.nextLine();
					
					System.out.println("Modelo: ");
					String modelo = input.nextLine();
					
					System.out.println("Ano de Fabricacao: ");
					int anoFabricacao = Integer.parseInt(input.nextLine());
					
					Veiculo newVeiculo = new Veiculo(placa, marca, modelo, anoFabricacao);
					cliente.adicionarVeiculo(newVeiculo);
					
					System.out.println("\n");
				}
				break;
				
			case CADASTRAR_SEGURADORA:
				{
					System.out.println("Nome: ");
					String nome = input.nextLine();
					while(Validacao.validarNome(nome)==false) {
						System.out.println("Nome invalido! Tente novamente.");
						nome = input.nextLine();
					}
					
					System.out.println("Telefone: ");
					String telefone = input.nextLine();
					
					System.out.println("Email: ");
					String email = input.nextLine();
					
					System.out.println("Endereco: ");
					String endereco = input.nextLine();
					
					Seguradora newSeguradora = new Seguradora(nome, telefone, email, endereco);
					MenuOperacoes.listaSeguradoras.add(newSeguradora);
					
					System.out.println("\n");					
				}
				break;
				
			case VOLTAR:
				break;
		}
	}
}
