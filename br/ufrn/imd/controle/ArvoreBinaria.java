package br.ufrn.imd.controle;

import java.util.ArrayList;

import br.ufrn.imd.modelo.NoBinario;

public abstract class ArvoreBinaria <T extends Comparable <T>> {
	protected NoBinario<T> raiz;

	public ArvoreBinaria(NoBinario<T> raiz) {
		this.raiz = raiz;
	}
	
	public ArvoreBinaria() {
		this.raiz = null;
	}

	public NoBinario<T> getRaiz() {
		return raiz;
	}

	public void setRaiz(NoBinario<T> raiz) {
		this.raiz = raiz;
	}

	public abstract void inserirDado(T dado);
	public abstract void inserirNo(NoBinario<T> No, T dado);
	public abstract void removerDado(T dado);

	public void percorrerPreOrdem(NoBinario<T> raiz) {
		if (raiz != null) {
			System.out.println(raiz.toString());
			percorrerPreOrdem(raiz.getEsc());
			percorrerPreOrdem(raiz.getDir());
		}			
	}
	
	public void percorrerPreOrdem(NoBinario<T> raiz, ArrayList<T> a) {
		if (raiz != null) {
			a.add(raiz.getDado());
			percorrerPreOrdem(raiz.getEsc(), a);
			percorrerPreOrdem(raiz.getDir(), a);
		}			
	}

	public void percorrerEmOrdem(NoBinario<T> raiz) {
		if (raiz != null) {
			percorrerEmOrdem(raiz.getEsc());
			System.out.println(raiz.toString());
			percorrerEmOrdem(raiz.getDir());
		}			
	}

	public void percorrerEmOrdem(NoBinario<T> raiz, ArrayList<T> a) {
		if (raiz != null) {
			percorrerEmOrdem(raiz.getEsc(), a);
			a.add(raiz.getDado());
			percorrerEmOrdem(raiz.getDir(), a);
		}			
	}

	public void percorrerPosOrdem(NoBinario<T> raiz) {
		if (raiz != null) {
			percorrerEmOrdem(raiz.getEsc());
			percorrerEmOrdem(raiz.getDir());
			System.out.println(raiz.toString());
		}		
	}
	public void percorrerPosOrdem(NoBinario<T> raiz, ArrayList<T> a) {
		if (raiz != null) {
			percorrerEmOrdem(raiz.getEsc(), a);
			percorrerEmOrdem(raiz.getDir(), a);
			a.add(raiz.getDado());
		}		
	}
}
