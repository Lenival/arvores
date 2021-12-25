package br.ufrn.imd.modelo;

public class NoBinario <T extends Comparable <T>>  {
	
	private NoBinario<T> esc;
	private NoBinario<T> dir;
	private T dado;

	public NoBinario(T dado) {
		super();
		this.esc = null;
		this.dir = null;
		this.dado = dado;
	}

	public NoBinario() {
		this.esc = null;
		this.dir = null;
		this.dado = null;
	}

	public NoBinario<T> getEsc() {
		return esc;
	}

	public void setEsc(NoBinario<T> esc) {
		this.esc = esc;
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
