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
					}
				}else {
					if (referenciaRaiz.getDir() == null) {
						f[0] = 3;
					}else {
						referenciaRaiz = referenciaRaiz.getDir();
					}
				}
				if (f[0] < 1) {
					return buscarDado(referenciaRaiz, dado, f);
				}
			}
		}
		return referenciaRaiz;
	}

	public NoBinario<T> buscarMaximo(NoBinario<T> referenciaRaiz) {
		NoBinario<T> noTemp = new NoBinario<>();
		noTemp = referenciaRaiz;
		if (noTemp != null)
			while (noTemp.getDir() != null)
				noTemp = noTemp.getDir();
		return noTemp;
	}
	
	public NoBinario<T> buscarMinimo(NoBinario<T> referenciaRaiz) {
		NoBinario<T> noTemp = new NoBinario<>();
		noTemp = referenciaRaiz;
		if (noTemp != null)
			while (noTemp.getEsq() != null)
				noTemp = noTemp.getEsq();
		return noTemp;
	}
	
	@Override
	public NoBinario<T> removerNo(NoBinario<T> referenciaRaiz, T dado) {
		if(referenciaRaiz == null)
			return referenciaRaiz;
		if (dado.compareTo(referenciaRaiz.getDado()) < 0) {
			referenciaRaiz.setEsq(removerNo(referenciaRaiz.getEsq(), dado));
		}else {
			if (dado.compareTo(referenciaRaiz.getDado()) > 0) {
				referenciaRaiz.setDir(removerNo(referenciaRaiz.getDir(), dado));
			}else {
				// Tratando os 3 possíveis casos de remoção
				if ((referenciaRaiz.getEsq() == null) && (referenciaRaiz.getDir() == null) ) {
					return null;
				} else if ((referenciaRaiz.getEsq() == null) && (referenciaRaiz.getDir() != null)) { // Tem 1 filho à direita
					return referenciaRaiz.getDir();
				} else if ((referenciaRaiz.getEsq() != null) && (referenciaRaiz.getDir() == null)) { // Tem 1 filho à esquerda
						return referenciaRaiz.getEsq();
				} else { // Tem 2 filhos!!
					T dadoAux = buscarMinimo(referenciaRaiz.getDir()).getDado();	// Encontra o menor dado da sub-árvore direita
					referenciaRaiz.setDir(removerNo(referenciaRaiz.getDir(), dadoAux));	// Remove o nó encontrado antes
					referenciaRaiz.setDado(dadoAux);	// "Remove" o nó da chamada original substituindo seu dado 
					return referenciaRaiz;	// Retorna a árvore após a remoção
				}
			}
		}
		
		return referenciaRaiz;
	}

}
