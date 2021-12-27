package br.ufrn.imd.modelo;

public class NoBinarioAVL<T extends Comparable <T>> extends NoBinario<T> {
	private int altura;

	public NoBinarioAVL(T dado) {
		super(dado);
		this.altura = 0;
	}

	public NoBinarioAVL() {
		super();
		// TODO Auto-generated constructor stub
		this.altura = 0;
	}

	public int getAltura() {
		return altura;
	}

	public void setAltura(int altura) {
		this.altura = altura;
	}
	
}
