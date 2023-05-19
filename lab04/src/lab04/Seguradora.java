package lab04;
import java.util.*;

public class Seguradora {
	/*
	A classe Seguradora tem como objetivo ter as informacoes da seguradora. Os atributos 
	dessa classe sao: nome, telefone, email e endereco. Todos os atributos sao do tipo String.
	*/
	
	String nome;
	String telefone;
	String email;
	String endereco;
	ArrayList <Sinistro> listaSinistros = new ArrayList <Sinistro>();
	ArrayList <Cliente> listaClientes = new ArrayList <Cliente>();
	
	public Seguradora(String nome, String telefone, String email, String endereco) {
		this.nome = nome;
		this.telefone = telefone;
		this.email = email;
		this.endereco = endereco;
	}

	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	
	public boolean cadastrarCliente(Cliente cliente) {
		/*
		 * Retorna False se o cliente ja esta cadastrado; do contrario, cadastra o cliente e retorna True.
		 */
		for(int i=0; i<listaClientes.size(); i++) {
			if(cliente.equals(listaClientes.get(i))) {
				return false;
			}
		}
		listaClientes.add(cliente);
		return true;
	}
	
	public boolean removerCliente(String cliente) {
		// Remove o cliente com nome "cliente"; retorna False se o cliente não existe; do contrario, remove o cliente e retorna True.
		
		/*
		 * x eh uma variavel auxiliar, utilizada aqui para marcar a posicao em que se encontra o cliente na lista.
		 * por padrao, supoe-se que cliente nao esta na lista.
		 */
		int x = -1;
		
		for(int i=0; i<listaClientes.size(); i++) {
			if(cliente.equals(listaClientes.get(i).nome)) {
				listaClientes.remove(i);
				x = i;
				break;
			}
		}
		
		if(x==-1) {
			return false; // O cliente nao esta na lista;
		}
		
		// Remove todos os sinistros gerados pelo cliente
		ArrayList <Sinistro> listaSinistros_ = new ArrayList <Sinistro>();
		/*
		 * Note que esta sendo criada uma nova listaSinistros da qual nao possui nenhum dos
		 * sinistros gerado pelo cliente com nome "cliente".
		 */
		
		for(int i=0; i<listaSinistros.size(); i++) {
			if(!cliente.equals(listaSinistros.get(i).cliente.nome)) {	
				listaSinistros_.add(listaSinistros.get(i));
			}
		}
		
		for(int i=0; i<listaSinistros_.size(); i++) {			
			System.out.println(i+": "+listaSinistros_.get(i));
		}
		
		this.listaSinistros = listaSinistros_;
		return true;
	}
	
	public ArrayList <Cliente> getListaClientesTipo(String tipoCliente) {
		ArrayList <Cliente> listaRetornada = new ArrayList <Cliente>();
		if(tipoCliente.equals("pf")) {			
			for(int i=0; i<listaClientes.size(); i++) {
				if(listaClientes.get(i) instanceof ClientePF) {
					listaRetornada.add(listaClientes.get(i));
				}
			}
		}
		
		else if(tipoCliente.equals("pj")){
			for(int i=0; i<listaClientes.size(); i++) {
				if(listaClientes.get(i) instanceof ClientePJ) {
					listaRetornada.add(listaClientes.get(i));
				}
			}
		}
		return listaRetornada;
	}
	
	public ArrayList<Cliente> getListaClientes(){
		return this.listaClientes;
	}
	
	public Sinistro gerarSinistro(String data, String endereco, Seguradora seguradora, Veiculo veiculo, Cliente cliente) {
		Sinistro newSinistro = new Sinistro(data, endereco, seguradora, veiculo, cliente);
		this.listaSinistros.add(newSinistro);
		return newSinistro;
	}
	 
	public ArrayList <Sinistro> visualizarSinistro(String cliente) {
		ArrayList <Sinistro> clienteSinistros = new ArrayList <Sinistro>();
		for(int i=0; i<this.listaSinistros.size(); i++) {
			if(this.listaSinistros.get(i).cliente.nome.equals(cliente)) {	
				clienteSinistros.add(this.listaSinistros.get(i));
			}
		}
		return clienteSinistros;
	}
	
	public ArrayList <Sinistro> getListaSinistros(){
		return this.listaSinistros;
	}
	
	public ArrayList<Sinistro> getListaSegurosCliente(Cliente cliente){
		ArrayList<Sinistro> sinistrosCliente = new ArrayList<Sinistro> ();
		for(int i=0; i<this.listaSinistros.size(); i++) {
			if(this.listaSinistros.get(i).cliente.equals(cliente)) {
				sinistrosCliente.add(this.listaSinistros.get(i));
			}
		}
		return sinistrosCliente;
	}
	
	public Cliente searchClienteSeguradora(String nome) {
		for(int i=0; i<this.listaClientes.size(); i++) {
			if(this.listaClientes.get(i).nome.equals(nome)) {
				return this.listaClientes.get(i);
			}
		}
		return null;
	}

	public double calcularPrecoSeguroCliente(Cliente cliente) {
		int clienteExiste = 0;
		for(int i=0; i<this.listaClientes.size(); i++) {
			if(this.listaClientes.get(i).equals(cliente)) {
				clienteExiste = 1;
				break;
			}
		}
		
		if(clienteExiste==0) {
			//Cliente nao se encontra na lista de clientes cadastrados na seguradora
			return -1;
		}
		
		double preco = cliente.calculaScore()*(1+getListaSegurosCliente(cliente).size());
		
		return preco;
	}
	
	@Override
	public String toString() {
		return "Seguradora [nome=" + nome + ", telefone=" + telefone + ", email=" + email + ", endereco=" + endereco
				+ ", listaSinistros=" + listaSinistros + ", listaClientes=" + listaClientes + "]";
	}
}
