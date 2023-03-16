package lab02;

public class Cliente {
	/*
	Essa classe compreende as informacoes do cliente segurado. Ela  ́e composta de cinco 
	atributos (nome, cpf, dataNascimento, idade e endereco). O atributo idade  ́e do tipo inteiro
	(int), ao passo que os demais sao todos do tipo String.
	*/
	
	String nome;
	String cpf;
	String dataNascimento;
	int idade;
	String endereco;
	
	public Cliente(String nome, String cpf, String dataNascimento, int idade, String endereco) {
		this.nome = nome;
		this.cpf = cpf;
		this.dataNascimento = dataNascimento;
		this.idade = idade;
		this.endereco = endereco;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	public String getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(String dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	
	public int getIdade() {
		return idade;
	}
	public void setIdade(int idade) {
		this.idade = idade;
	}
	
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	
	private boolean equalDigits(String cpf) {
		/*
		Returns true if digits are equal 
		*/
		
		char a = cpf.charAt(0);
		for(int i=1; i<cpf.length(); i++) {
			if(cpf.charAt(i)!=a) {
				return false;
			}
		}
		return true;
	}
	
	private boolean verifyingDigits(String cpf) {
		int d1=0, d2=0;
		String digits;
		for(int i=0; i<9; i++) {
			d1+=Character.getNumericValue(cpf.charAt(i))*(10-i);
			d2+=Character.getNumericValue(cpf.charAt(i+1))*(10-i);
		}
		d1 = d1%11; d2 = d2%11;
		if(d1==0 || d1==1) {
			d1 = 0;
		}
		else {
			d1 = 11-d1;
		}
		
		if(d2==0 || d2==1) {
			d2 = 0;
		}
		else {
			d2 = 11-d2;
		}
		
		digits = Integer.toString(d1)+Integer.toString(d2);
		return digits.equals(this.cpf.substring(9)); 
	}
	
	public boolean validarCPF(String cpf) {
		/*
		This method does the following:
		-> Removes all non-digit characters from the cpf string (the result is 
		refered here as "normalized cpf")
		-> Verify is the normalized cpf has 11 digits
		-> Verify if the cpf is all made by equal digits
		*/
		String normalizedCpf = cpf.replaceAll("\\D", "");
		if(normalizedCpf.length()!=11 | !normalizedCpf.equals(this.cpf) | equalDigits(normalizedCpf) | !verifyingDigits(normalizedCpf)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "Cliente [nome=" + nome + ", cpf=" + 
				cpf + ", dataNascimento=" + dataNascimento + ", idade=" + idade
				+ ", endereco=" + endereco + "]";
	}
	
	
}
