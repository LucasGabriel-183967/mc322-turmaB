

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validacao {
	
	// Global methods
	private static boolean equalDigits(String document) {
		/*
		Funcao necessaria tanto para ClientePF quando ClientePJ, sendo portanto
		particularmente pratico deixa-lo como metodo comum para as classes que herdam de Cliente
		*/
		
		char a = document.charAt(0);
		for(int i=1; i<document.length(); i++) {
			if(document.charAt(i)!=a) {
				return false;
			}
		}
		return true;
	}
	
	
	// CPF methods
	private static boolean verifyingDigitsCPF(String cpf) {
		/*
		Source: https://www.macoratti.net/alg_cpf.htm 
		*/
		int d1=0, d2=0;
		String digits;
		for(int i=0; i<9; i++) {
			d1+=Character.getNumericValue(cpf.charAt(i))*(10-i);
		}
		for(int i=0; i<10; i++) {
			d2+=Character.getNumericValue(cpf.charAt(i))*(11-i);
		}
		d1 = d1%11; d2 = d2%11;
		if(d1<2) {
			d1 = 0;
		}
		else {
			d1 = 11-d1;
		}
		
		if(d2<2) {
			d2 = 0;
		}
		else {
			d2 = 11-d2;
		}
		
		digits = Integer.toString(d1)+Integer.toString(d2);
		return digits.equals(cpf.substring(9)); 
	}
	
	public static boolean validarCPF(String cpf) {
		/*
		-> Removes all non-digit characters from the cpf string (the result is 
		refered here as "normalized cpf")
		-> Verify is the normalized cpf has 11 digits
		-> Verify if the cpf is all made by equal digits
		*/
		String normalizedCpf = cpf.replaceAll("\\D", "");
		if(normalizedCpf.length()!=11 || !normalizedCpf.equals(cpf) || equalDigits(normalizedCpf) || !verifyingDigitsCPF(normalizedCpf)) {
			return false;
		}
		return true;
	}
	
	
	// CNPJ methods
	private static boolean verifyingDigitsCNPJ(String cnpj) {
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
		return digits.equals(cnpj.substring(12));
	}
	
	public static boolean validarCNPJ(String cnpj) {
		String normalizedCnpj = cnpj.replaceAll("\\D", "");
		if(normalizedCnpj.length()!=14 || !normalizedCnpj.equals(cnpj) || equalDigits(normalizedCnpj) || !verifyingDigitsCNPJ(normalizedCnpj)) {
			return false;
		}
		return true;
	}
	
	
	// Validacao do nome
	public static boolean validarNome(String nome) {
		String normalizedNome = nome.replaceAll("\\d", "");
		if(normalizedNome.equals(nome) && normalizedNome.length()>0) {
			return true;
		}
		return false;
	}
	
	// Validacao da opcao escolhida no menu inicial
	public static boolean validarOpcao(int opcao) {
		if(opcao<0 || opcao>MenuOperacoes.values().length) {
			return false;
		}
		return true;
	}
	
	// Validacao da placa do carro
	public static boolean validarPlaca(String placa) {
		Pattern pattern = Pattern.compile("[A-Z]{3}-[0-9]{4}");
	    Matcher matcher = pattern.matcher(placa);
	    
	    if(matcher.matches()) {
	    	return true;
	    }
	    
	    return false;
	}
}
