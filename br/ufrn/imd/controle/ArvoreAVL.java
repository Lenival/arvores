package br.ufrn.imd.controle;

import br.ufrn.imd.modelo.NoBinarioAVL;

public class ArvoreAVL<T extends Comparable <T>> extends ArvoreBinariaBusca<T> {

	public ArvoreAVL() {
		this.raiz = (NoBinarioAVL<T>)null;
	}
	
	

	@Override
	public void inserirDado(T dado) {
		if (this.raiz == null) {
			NoBinarioAVL<T> no = new NoBinarioAVL<>();
			no.setDado(dado);
			no.setAltura(1);
			this.raiz = no;
		}else {
			inserirNo((NoBinarioAVL<T>)raiz, dado);
		}
	}


	public void inserirNo(NoBinarioAVL<T> raizSubArvore, T dado) {
		if (dado.compareTo(raizSubArvore.getDado()) < 0 ) { // A inserção será feita do lado esquerdo
			if(raizSubArvore.getEsq() == null) {
				NoBinarioAVL<T> novo_no = new NoBinarioAVL<T>(dado);
				raizSubArvore.setEsq(novo_no);
			}else {
				inserirNo(raizSubArvore.getEsq(), dado);
			}
			
			if (fatorBalanceamento(raizSubArvore) >= 2) {
				NoBinarioAVL<T> noDes = new NoBinarioAVL<T>(); 
				noDes = raizSubArvore;
				if (dado.compareTo(raizSubArvore.getEsq().getDado()) < 0) {
					rotacionarEsq(noDes);
				}else {
					rotacionarEsqDir(noDes);
				}
				raizSubArvore = noDes;
			}

			if (alturaNo(raizSubArvore)<(alturaNo(raizSubArvore.getEsq())+1)) {
				//raizSubArvore.setAltura(alturaNo(raizSubArvore.getEsq())+1);
				raizSubArvore.setAltura(maior(alturaNo(raizSubArvore.getEsq()),alturaNo(raizSubArvore.getDir()))+1);
			}
		}else if (raizSubArvore.getDado().compareTo(dado) < 0 ) { // A inserção será feita do lado direito
			if(raizSubArvore.getDir() == null) {
				NoBinarioAVL<T> novo_no = new NoBinarioAVL<T>(dado);
				raizSubArvore.setDir(novo_no);
			}else {
				inserirNo(raizSubArvore.getDir(), dado);
			}
			
			// TODO (resolvido, mas não testado) Resolver problema de estar perdendo a referência quando entra aqui
			if (fatorBalanceamento(raizSubArvore) >= 2) {
				NoBinarioAVL<T> noDes = new NoBinarioAVL<T>(); 
				noDes = raizSubArvore;
				if (dado.compareTo(raizSubArvore.getDir().getDado()) > 0) {
					rotacionarDir(noDes);
				}else {
					rotacionarDirEsq(noDes);
				}
				raizSubArvore = noDes;
			}

			if (alturaNo(raizSubArvore)<(alturaNo(raizSubArvore.getDir())+1)) {
				//raizSubArvore.setAltura(alturaNo(raizSubArvore.getDir())+1);
				raizSubArvore.setAltura(maior(alturaNo(raizSubArvore.getEsq()),alturaNo(raizSubArvore.getDir()))+1);
			}
			
		} else 
			System.out.println("Valor duplicado!");
	}

	public void rotacionarDir(NoBinarioAVL<T> raizSubArvore) {
		NoBinarioAVL<T> no = new NoBinarioAVL<T>(); 
		//no = raizSubArvore.getEsq();
		no.copiarNo(raizSubArvore.getEsq());
		raizSubArvore.setEsq(no.getDir());
		raizSubArvore.setAltura(maior(alturaNo(raizSubArvore.getEsq()),alturaNo(raizSubArvore.getDir()))+1);
		//raizSubArvore.getEsq().copiarNo(no.getDir());
		//no.setDir(raizSubArvore);
		no.setDir(new NoBinarioAVL<T>());
		no.getDir().copiarNo(raizSubArvore);
		//raizSubArvore.setAltura(maior(alturaNo(raizSubArvore.getEsq()),alturaNo(raizSubArvore.getDir()))+1);
		no.setAltura(maior(alturaNo(no.getEsq()),alturaNo(raizSubArvore))+1);
		//raizSubArvore = no;
		raizSubArvore.copiarNo(no);
	}
	public void rotacionarEsq(NoBinarioAVL<T> raizSubArvore) {
		NoBinarioAVL<T> no = new NoBinarioAVL<T>();
		//no = raizSubArvore.getDir();
		no.copiarNo(raizSubArvore.getDir());
		raizSubArvore.setDir(no.getEsq());
		raizSubArvore.setAltura(maior(alturaNo(raizSubArvore.getEsq()),alturaNo(raizSubArvore.getDir()))+1);
		//no.setEsq(raizSubArvore);
		no.setEsq(new NoBinarioAVL<T>());
		no.getEsq().copiarNo(raizSubArvore);
		no.setAltura(maior(alturaNo(no.getDir()),alturaNo(raizSubArvore))+1);
		//raizSubArvore = no;
		raizSubArvore.copiarNo(no);
	}
	public void rotacionarDirEsq(NoBinarioAVL<T> raizSubArvore) {
		rotacionarDir(raizSubArvore.getDir());
		rotacionarEsq(raizSubArvore);
	}
	public void rotacionarEsqDir(NoBinarioAVL<T> raizSubArvore) {
		rotacionarEsq(raizSubArvore.getEsq());
		rotacionarDir(raizSubArvore);
	}

	//@Override
	public NoBinarioAVL<T> removerNo(NoBinarioAVL<T> referenciaRaiz, T dado) {
		return referenciaRaiz;
		// TODO Auto-generated method stub
		//return super.removerNo(referenciaRaiz, dado);
	}
	
	public int fatorBalanceamento(NoBinarioAVL<T> no) {
		int altEsq = 0, altDir = 0;
		if(no.getEsq() != null)
			altEsq = no.getEsq().getAltura();
		if(no.getDir() != null)
			altEsq = no.getDir().getAltura();
		return Math.abs(altEsq - altDir);
	}
	
	public int maior(int a, int b) {
		if (b>a) {
			return b;
		}
		return a;
	}
	
	public int alturaNo(NoBinarioAVL<T> no) {
		if (no == null) {
			return 0;
		}
		return no.getAltura();
	}
}
