package lab06;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public enum Cadastrar {
	CADASTRAR_CLIENTE(1), CADASTRAR_VEICULO(2), CADASTRAR_FROTA(3), CADASTRAR_SEGURADORA(4), CADASTRAR_CONDUTOR(5), VOLTAR(6);

	public final int operacao;
	Cadastrar(int operacao){
		this.operacao = operacao;
	}
	
	public int getOperacao() {
		return this.operacao;
	}
	
	protected static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
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
							String nome = Utils.inputNome(input);
							
							System.out.println("Telefone: ");
							String telefone = input.nextLine();
							
							System.out.println("Endereco: ");
							String endereco = input.nextLine();
							
							System.out.println("Email: ");
							String email = input.nextLine();
							
							String cpf = Utils.inputCPF(input);
							
							System.out.println("Genero: ");
							String genero = input.nextLine();
							
							System.out.println("Educacao: ");
							String educacao = input.nextLine();

							System.out.println("Data de Nascimento (formato dd/mm/yyyy): ");
							String dataNascimentoString = input.nextLine();
							LocalDate dataNascimento = LocalDate.parse(dataNascimentoString, formatter);
							
							System.out.println("Data de Licenca (formato dd/mm/yyyy): ");
							String dataLicencaString = input.nextLine();
							LocalDate dataLicenca = LocalDate.parse(dataLicencaString, formatter);
							
							System.out.println("Classe Economica: ");
							String classeEconomica = input.nextLine();
							
							ClientePF newCliente = new ClientePF(nome, telefone, endereco, email, cpf, genero, educacao, dataNascimento, dataLicenca, classeEconomica);
							
							opcaoSeguradora.cadastrarCliente(newCliente);
							MenuOperacoes.listaClientesPF.add(newCliente);
							
							System.out.println("Cliente cadastrado com sucesso!\n");
						}
						else if(tipoCliente == 2) {
							// ClientePJ
							System.out.println("Nome: ");
							String nome = input.nextLine();
							
							System.out.println("Telefone: ");
							String telefone = input.nextLine();
							
							System.out.println("Endereco: ");
							String endereco = input.nextLine();
							
							System.out.println("Email: ");
							String email = input.nextLine();
							
							String cnpj = Utils.inputCNPJ(input);
							
							System.out.println("Data de Fundacao (formato dd/mm/yyyy): ");
							String dataFundacaoString = input.nextLine();
							LocalDate dataFundacao = LocalDate.parse(dataFundacaoString, formatter);
							
							System.out.println("Quantidade de funcionarios: ");
							int quantFuncionarios = Integer.parseInt(input.nextLine());
							
							ClientePJ newCliente = new ClientePJ(nome, telefone, endereco, email, cnpj, dataFundacao, quantFuncionarios);
							
							opcaoSeguradora.cadastrarCliente(newCliente);
							MenuOperacoes.listaClientesPJ.add(newCliente);
							
							System.out.println("Cliente cadastrado com sucesso!\n");
						}
						System.out.println("\n");
					}
				}
				break;
				
			case CADASTRAR_VEICULO:
				{
					System.out.println("Qual o tipo do cliente? \n1 - Pessoa Fisica \n2 - Pessoa Jurídica");
					int tipoCliente = Integer.parseInt(input.nextLine());
					while(tipoCliente<1 || tipoCliente>2) {
						System.out.println("Selecione uma opcao valida!");
						tipoCliente = Integer.parseInt(input.nextLine());
					}
					
					if(tipoCliente == 1) {
						String nome = Utils.inputNome(input);
						ClientePF cliente = null;
						cliente = MenuOperacoes.searchClientePF(nome);
						while(cliente == null) {
							System.out.println("Cliente inexistente. Tente novamente!");
							nome = input.nextLine();
							cliente = MenuOperacoes.searchClientePF(nome);
						}
						
						String placa = Utils.inputPlaca(input);
						
						System.out.println("Marca: ");
						String marca = input.nextLine();
						
						System.out.println("Modelo: ");
						String modelo = input.nextLine();
						
						System.out.println("Ano de Fabricacao: ");
						int anoFabricacao = Integer.parseInt(input.nextLine());
						
						Veiculo newVeiculo = new Veiculo(placa, marca, modelo, anoFabricacao);
						cliente.cadastrarVeiculo(newVeiculo);
					}
					
					else if(tipoCliente == 2) {
						System.out.println("Nome: ");
						String nome = input.nextLine();
						ClientePJ cliente = null;
						cliente = MenuOperacoes.searchClientePJ(nome);
						while(cliente == null) {
							System.out.println("Cliente inexistente. Tente novamente!");
							nome = input.nextLine();
							cliente = MenuOperacoes.searchClientePJ(nome);
						}
						
						System.out.println("Selecione uma frota:");
						for(int i=0; i<cliente.getListaFrotas().size(); i++) {
							System.out.println(i+1+" - "+cliente.getListaFrotas().get(i).getCode());
						}
						int frotaIndice = Integer.parseInt(input.nextLine());
						while(frotaIndice-1<0 || frotaIndice-1>=cliente.getListaFrotas().size()) {
							System.out.println("Selecione uma opcao valida!");
							frotaIndice = Integer.parseInt(input.nextLine());
						}
						
						Frota frota = cliente.getListaFrotas().get(frotaIndice-1);
						
						String placa = Utils.inputPlaca(input);
						
						System.out.println("Marca: ");
						String marca = input.nextLine();
						
						System.out.println("Modelo: ");
						String modelo = input.nextLine();
						
						System.out.println("Ano de Fabricacao: ");
						int anoFabricacao = Integer.parseInt(input.nextLine());
						
						Veiculo newVeiculo = new Veiculo(placa, marca, modelo, anoFabricacao);
						frota.cadastrarVeiculo(newVeiculo);
					}
					
					System.out.println("\n");
				}
				break;
				
			case CADASTRAR_FROTA:
				{
					System.out.println("Nome do cliente (Pessoa Juridica):");
					ClientePJ cliente = null;
					String nome = input.nextLine();
					cliente = MenuOperacoes.searchClientePJ(nome);
					while(cliente == null) {
						System.out.println("Cliente inexistente. Tente novamente!");
						nome = input.nextLine();
						cliente = MenuOperacoes.searchClientePJ(nome);
					}
					
					Frota newFrota = new Frota();
					cliente.cadastrarFrota(newFrota);
					
					System.out.println("\n");
				}
				break;
				
			case CADASTRAR_CONDUTOR:
				{
					System.out.println("Selecione uma seguradora:");
					for(int i=0; i<MenuOperacoes.listaSeguradoras.size(); i++){
						System.out.println(i+1+" - "+MenuOperacoes.listaSeguradoras.get(i));
					}
					int seguradoraIndice = Integer.parseInt(input.nextLine());
					while(seguradoraIndice-1<0 || seguradoraIndice-1>=MenuOperacoes.listaSeguradoras.size()) {
						System.out.println("Selecione uma opcao valida!");
						seguradoraIndice = Integer.parseInt(input.nextLine());
					}
					
					Seguradora seguradora = MenuOperacoes.listaSeguradoras.get(seguradoraIndice-1);
				
					System.out.println("Selecione um seguro:");
					for(int i=0; i<seguradora.listaSeguros.size(); i++) {
						System.out.println(i+1+" - "+seguradora.getListaSeguros().get(i));
					}
					int seguroIndice = Integer.parseInt(input.nextLine());
					while(seguroIndice-1<0 || seguroIndice>=seguradora.getListaSeguros().size()) {
						System.out.println("Selecione uma opcao valida!");
						seguroIndice = Integer.parseInt(input.nextLine());
					}
					
					Seguro seguro = seguradora.getListaSeguros().get(seguroIndice-1);
					
					String nome = Utils.inputNome(input);
					
					System.out.println("Telefone: ");
					String telefone = input.nextLine();
					
					System.out.println("Endereco: ");
					String endereco = input.nextLine();
					
					System.out.println("Email: ");
					String email = input.nextLine();
					
					String cpf = Utils.inputCPF(input);

					System.out.println("Data de Nascimento (formato dd/mm/yyyy): ");
					String dataNascimentoString = input.nextLine();
					LocalDate dataNascimento = LocalDate.parse(dataNascimentoString, formatter);
					
					System.out.println("Data de Licenca (formato dd/mm/yyyy): ");
					String dataLicencaString = input.nextLine();
					LocalDate dataLicenca = LocalDate.parse(dataLicencaString, formatter);
					
					Condutor condutor = new Condutor(nome, telefone, endereco, email, cpf, dataNascimento, dataLicenca);
					
					seguro.autorizarCondutor(condutor);
				}
				break;
			
			case CADASTRAR_SEGURADORA:
				{
					String cnpj = Utils.inputCNPJ(input);
					
					System.out.println("Nome: ");
					String nome = input.nextLine();
					
					System.out.println("Telefone: ");
					String telefone = input.nextLine();
					
					System.out.println("Endereco: ");
					String endereco = input.nextLine();

					System.out.println("Email: ");
					String email = input.nextLine();
					
					Seguradora newSeguradora = new Seguradora(cnpj, nome, telefone, endereco, email);
					MenuOperacoes.listaSeguradoras.add(newSeguradora);
					
					System.out.println("\n");					
				}
				break;
				
			case VOLTAR:
				break;
		}
	}
}
