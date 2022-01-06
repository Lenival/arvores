package br.ufrn.imd.modelo;

public class NoBinarioAVL<T extends Comparable <T>> extends NoBinario<T> {
	private int altura;

	public NoBinarioAVL(T dado) {
		super(dado);
		this.altura = 1;
	}

	public NoBinarioAVL() {
		super();
		// TODO Auto-generated constructor stub
		this.altura = 1;
	}

	
	
	@Override
	public NoBinarioAVL<T> getEsq() {
		// TODO Auto-generated method stub
		return (NoBinarioAVL<T>) this.esq;
	}

	//@Override
	public void setEsq(NoBinarioAVL<T> esq) {
		// TODO Auto-generated method stub
		this.esq = esq;
	}

	@Override
	public NoBinarioAVL<T> getDir() {
		// TODO Auto-generated method stub
		return (NoBinarioAVL<T>) this.dir;
	}

	//@Override
	public void setDir(NoBinarioAVL<T> dir) {
		// TODO Auto-generated method stub
		this.dir = dir;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "NoBinarioAVL [dado=" + dado + " altura=" + altura + "]";
	}

	public int getAltura() {
		return altura;
	}

	public void setAltura(int altura) {
		this.altura = altura;
	}
	
	public void copiarNo(NoBinarioAVL<T> no) {
		if (no == null) {
			this.altura = 0;
			this.dado = null;
			this.dir = null;
			this.esq = null;
		} else {
			this.altura = no.getAltura();
			this.dado = no.getDado();
			this.dir = no.getDir();
			this.esq = no.getEsq();
		}
	}
	
}
