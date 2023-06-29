

import java.util.ArrayList;
import java.util.Random;

public class Frota {
	final int code;
	ArrayList<Veiculo> listaVeiculos;
	Random rand = new Random();
	
	public Frota() {
		this.listaVeiculos = new ArrayList<Veiculo>();
		this.code = (int) (rand.nextFloat()*Math.pow(10, 6));
	}
	
	public boolean cadastrarVeiculo(Veiculo veiculo) {
		for(int i=0; i<listaVeiculos.size(); i++) {
			if(listaVeiculos.get(i).equals(veiculo)) {
				return false;
			}
		}
		
		listaVeiculos.add(veiculo);
		return true;
	}
	
	public boolean removerVeiculo(Veiculo veiculo) {
		for(int i=0; i<listaVeiculos.size(); i++) {
			if(listaVeiculos.get(i).equals(veiculo)) {
				listaVeiculos.remove(i);
				return true;
			}
		}
		
		return false;
	}
	
	public int getCode() {
		return this.code;
	}
	
	public ArrayList<Veiculo> getListaVeiculos() {
		return listaVeiculos;
	}

	@Override
	public String toString() {
		return "Frota [code=" + code + ", listaVeiculos=" + listaVeiculos + "]";
	}
}
