package br.ufrn.imd.modelo;

public class NoBinario <T extends Comparable <T>>  {
	
	private NoBinario<T> esq;
	private NoBinario<T> dir;
	private T dado;

	public NoBinario(T dado) {
		super();
		this.esq = null;
		this.dir = null;
		this.dado = dado;
	}

	public NoBinario() {
		this.esq = null;
		this.dir = null;
		this.dado = null;
	}

	public NoBinario<T> getEsq() {
		return esq;
	}

	public void setEsq(NoBinario<T> esq) {
		this.esq = esq;
	}

	public NoBinario<T> getDir() {
		return dir;
	}

	public void setDir(NoBinario<T> dir) {
		this.dir = dir;
	}

	public T getDado() {
		return dado;
	}

	public void setDado(T dado) {
		this.dado = dado;
	}

	@Override
	public String toString() {
		return "NoBinario [dado=" + dado + "]";
	}
	
	
		
}
