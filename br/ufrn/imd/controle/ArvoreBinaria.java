package br.ufrn.imd.controle;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

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
	public abstract void inserirNo(NoBinario<T> raizSubArvore, T dado);
	public abstract NoBinario<T> removerNo(NoBinario<T> raizSubArvore, T dado);

	public void percorrerPreOrdem(NoBinario<T> raiz) {
		if (raiz != null) {
			System.out.println(raiz.toString());
			percorrerPreOrdem(raiz.getEsq());
			percorrerPreOrdem(raiz.getDir());
		}			
	}
	
	public void percorrerPreOrdem(NoBinario<T> raiz, ArrayList<T> a) {
		if (raiz != null) {
			a.add(raiz.getDado());
			percorrerPreOrdem(raiz.getEsq(), a);
			percorrerPreOrdem(raiz.getDir(), a);
		}			
	}

	public void percorrerEmOrdem(NoBinario<T> raiz) {
		if (raiz != null) {
			percorrerEmOrdem(raiz.getEsq());
			System.out.println(raiz.toString());
			percorrerEmOrdem(raiz.getDir());
		}			
	}

	public void percorrerEmOrdem(NoBinario<T> raiz, ArrayList<T> a) {
		if (raiz != null) {
			percorrerEmOrdem(raiz.getEsq(), a);
			a.add(raiz.getDado());
			percorrerEmOrdem(raiz.getDir(), a);
		}			
	}

	public void percorrerPosOrdem(NoBinario<T> raiz) {
		if (raiz != null) {
			percorrerEmOrdem(raiz.getEsq());
			percorrerEmOrdem(raiz.getDir());
			System.out.println(raiz.toString());
		}		
	}
	public void percorrerPosOrdem(NoBinario<T> raiz, ArrayList<T> a) {
		if (raiz != null) {
			percorrerEmOrdem(raiz.getEsq(), a);
			percorrerEmOrdem(raiz.getDir(), a);
			a.add(raiz.getDado());
		}		
	}

	public void percorrerEmNivel(NoBinario<T> raiz) {
		Queue<NoBinario<T>> fila = new LinkedList<NoBinario<T>>();
		fila.add(raiz);
		while(!fila.isEmpty()){
			raiz = fila.remove();
			System.out.println(raiz.getDado().toString());
			if(raiz.getEsq() != null) {
				fila.add(raiz.getEsq());
			}
			if(raiz.getDir() != null) {
				fila.add(raiz.getDir());
			}
		}
	}
	public void percorrerEmNivel(NoBinario<T> raiz, ArrayList<T> a) {
		Queue<NoBinario<T>> fila = new LinkedList<NoBinario<T>>();
		fila.add(raiz);
		while(!fila.isEmpty()){
			raiz = fila.remove();
			a.add(raiz.getDado());
			if(raiz.getEsq() != null) {
				fila.add(raiz.getEsq());
			}
			if(raiz.getDir() != null) {
				fila.add(raiz.getDir());
			}
		}
	}
	
	public T getEnesimoElemento(int n) {
		if (this.raiz == null)
			return null;
		else {
			ArrayList<T> elementos = new ArrayList<T>();
			percorrerEmOrdem(this.raiz, elementos);
			return elementos.get(n-1);
		}
			
	}
	
	public Integer getPosicao(T elemento) {
		if (this.raiz == null)
			return null;
		else {
			ArrayList<T> elementos = new ArrayList<T>();
			percorrerEmOrdem(this.raiz, elementos);
			return elementos.indexOf(elemento)+1;
		}
			
	}

	public T getMediana() {
		if (this.raiz == null)
			return null;
		else {
			ArrayList<T> elementos = new ArrayList<T>();
			percorrerEmOrdem(this.raiz, elementos);
			return elementos.get((int) Math.round((elementos.size()-1)/2));
		}
			
	}

}





