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
			if(raizSubArvore.getEsq() == null) {
				NoBinario<T> novo_no = new NoBinario<>(dado);
				raizSubArvore.setEsq(novo_no);
			}else {
				inserirNo(raizSubArvore.getEsq(), dado);
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

	public NoBinario<T> buscarDado(NoBinario<T> referenciaRaiz, T dado, int[] f) {
		if (referenciaRaiz != null) {
			if(referenciaRaiz.getDado().equals(dado)) {
				f[0] = 1;
			}else {
				if (dado.compareTo(referenciaRaiz.getDado()) < 0) {
					if (referenciaRaiz.getEsq() == null) {
						f[0] = 2;
					}else {
						referenciaRaiz = referenciaRaiz.getEsq();
						//referenciaRaiz.setDado(referenciaRaiz.getEsq().getDado());
						//referenciaRaiz.setEsq(referenciaRaiz.getEsq().getEsq());
						//referenciaRaiz.setDir(referenciaRaiz.getEsq().getDir());
					}
				}else {
					if (referenciaRaiz.getDir() == null) {
						f[0] = 3;
					}else {
						referenciaRaiz = referenciaRaiz.getDir();
						//referenciaRaiz.setDado(referenciaRaiz.getDir().getDado());
						//referenciaRaiz.setEsq(referenciaRaiz.getDir().getEsq());
						//referenciaRaiz.setDir(referenciaRaiz.getDir().getDir());
					}
				}
				if (f[0] < 1) {
					return buscarDado(referenciaRaiz, dado, f);
				}
			}
		}
		return referenciaRaiz;
	}
	
	@Override
	public void removerDado(T dado) {
		// TODO Auto-generated method stub

	}

}
