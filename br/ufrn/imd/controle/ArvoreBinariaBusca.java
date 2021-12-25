package br.ufrn.imd.controle;

import br.ufrn.imd.modelo.NoBinario;

public class ArvoreBinariaBusca<T extends Comparable <T>> extends ArvoreBinaria<T> {

	@Override
	public void inserirDado(T dado) {
		// TODO Auto-generated method stub
		if (this.raiz == null) {
			NoBinario<T> no = new NoBinario<>();
			no.setDado(dado);
			this.raiz = no;
		}else {
			inserirNo(raiz, dado);
		}
	}
	

	@Override
	public void inserirNo(NoBinario<T> raizSubArvore,T dado) {
		// TODO Auto-generated method stub
		if (dado.compareTo(raizSubArvore.getDado()) < 0 ) {
			if(raizSubArvore.getEsc() == null) {
				NoBinario<T> novo_no = new NoBinario<>(dado);
				raizSubArvore.setEsc(novo_no);
			}else {
				inserirNo(raizSubArvore.getEsc(), dado);
			}
		}
		if (raizSubArvore.getDado().compareTo(dado) < 0 ) {
			if(raizSubArvore.getDir() == null) {
				NoBinario<T> novo_no = new NoBinario<>(dado);
				raizSubArvore.setDir(novo_no);
			}else {
				inserirNo(raizSubArvore.getDir(), dado);
			}
		}
	}
	
	
	@Override
	public void removerDado(T dado) {
		// TODO Auto-generated method stub

	}

}
