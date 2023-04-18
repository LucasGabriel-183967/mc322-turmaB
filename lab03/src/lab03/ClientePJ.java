package lab03;
import java.time.LocalDate;

public class ClientePJ extends Cliente {
	String cnpj;
	LocalDate dataFundacao;
	
	
	
	public ClientePJ(String nome, String endereco, String cnpj, LocalDate dataFundacao) {
		super(nome, endereco); // Note a ausencia de listaVeiculos (a lista eh sempre inicializada como vazia por padrao)
		// Normaliza o cnpj.
		cnpj = cnpj.replaceAll("\\D", "");
		this.cnpj = cnpj;
		this.dataFundacao = dataFundacao;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public LocalDate getDataFundacao() {
		return dataFundacao;
	}

	public void setDataFundacao(LocalDate dataFundacao) {
		this.dataFundacao = dataFundacao;
	}

	private boolean verifyingDigitsCNPJ(String cnpj) {
		/*
		Source: https://www.macoratti.net/alg_cnpj.htm
		*/
		int d1=0, d2=0;
		String digits;
		
		int x = 5;
		for(int i=0; i<12; i++) {
			if(x<2) {
				x = 9;
			}
			d1+=Character.getNumericValue(cnpj.charAt(i))*x;
			x--;
		}
		
		x = 6;
		for(int i=0; i<13; i++) {
			if(x<2) {
				x=9;
			}
			d2+=Character.getNumericValue(cnpj.charAt(i))*x;
			x--;
		}
		
		d1 = d1%11; d2 = d2%11;
		if(d1<2) {
			d1 = 0;
		}
		else if(d1>=2) {
			d1 = 11-d1;
		}
		
		if(d2<2) {
			d2 = 0;
		}
		else if(d2>=2) {
			d2 = 11-d2;
		}
		
		
		digits = Integer.toString(d1)+Integer.toString(d2);
		return digits.equals(this.cnpj.substring(12));
	}
	
	public boolean validarCNPJ(String cnpj) {
		String normalizedCnpj = cnpj.replaceAll("\\D", "");
		if(normalizedCnpj.length()!=14 | !normalizedCnpj.equals(this.cnpj) | equalDigits(normalizedCnpj) | !verifyingDigitsCNPJ(normalizedCnpj)) {
			return false;
		}
		return true;
	}
	
	public static String getClienteClass() {
		return "PJ";
	}

	@Override
	public String toString() {
		return "ClientePJ [cnpj=" + cnpj + ", dataFundacao=" + dataFundacao + ", nome=" + nome + ", endereco="
				+ endereco + ", listaVeiculos=" + listaVeiculos + "]";
	}
	
}
