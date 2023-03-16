package lab02;

public class Main {

	public static void main(String[] args) {
		Cliente alguem = new Cliente("a", "51481632833", "c", 123, "d");
		System.out.println(alguem.toString());
		System.out.println(alguem.validarCPF("5sdf148163sdf28ssfdfd33yesadadasd"));
	}

}
