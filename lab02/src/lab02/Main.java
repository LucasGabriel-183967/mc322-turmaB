package lab02;

public class Main {

	public static void main(String[] args) {
		Cliente cliente_example = new Cliente("a", "51481632833", "c", 123, "d");
		Veiculo veiculo_example = new Veiculo("ABC-1234", "Audi", "Sedan");
		Sinistro sinistro_example = new Sinistro(0, "28/03/2023", "Cidade Universitaria");
		Seguradora seguradora_example = new Seguradora("Seguradora", "91234-5678", "email@domain", "Random place");
		
		// Verifications and tests
		System.out.println(cliente_example.toString());
		System.out.println("Is 5sdf148163sdf28ssfdfd33yesadadasd a valid cpf (acording to the pre-specifyed rules) and equal to the clliente_example cpf? "+cliente_example.validarCPF("5sdf148163sdf28ssfdfd33yesadadasd"));
		System.out.println("Car model: " + veiculo_example.getMarca());
		System.out.println("Sinistro submission date: " + sinistro_example.getData());
		System.out.println("Sinistro Id: " + sinistro_example.getId());
		System.out.println("Seguradora email: " + seguradora_example.getEmail());
	}

}
