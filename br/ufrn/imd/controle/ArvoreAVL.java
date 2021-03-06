package br.ufrn.imd.controle;

import br.ufrn.imd.modelo.NoBinario;
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
			this.inserirNo((NoBinarioAVL<T>)raiz, dado);
		}
	}


	public void inserirNo(NoBinarioAVL<T> raizSubArvore, T dado) {
		if (dado.compareTo(raizSubArvore.getDado()) < 0 ) { // A inser??o ser? feita do lado esquerdo
			if(raizSubArvore.getEsq() == null) {
				NoBinarioAVL<T> novo_no = new NoBinarioAVL<T>(dado);
				raizSubArvore.setEsq(novo_no);
			}else {
				this.inserirNo(raizSubArvore.getEsq(), dado);
			}

			if (alturaNo(raizSubArvore)<(alturaNo(raizSubArvore.getEsq())+1)) {
				raizSubArvore.setAltura(maior(alturaNo(raizSubArvore.getEsq()),alturaNo(raizSubArvore.getDir()))+1);
			}
			
			if (fatorBalanceamento(raizSubArvore) >= 2) {
				if (dado.compareTo(raizSubArvore.getEsq().getDado()) < 0) {
					rotacionarEsq(raizSubArvore);
				}else {
					rotacionarEsqDir(raizSubArvore);
				}
			}
			
		}else if (raizSubArvore.getDado().compareTo(dado) < 0 ) { // A inser??o ser? feita do lado direito
			if(raizSubArvore.getDir() == null) {
				NoBinarioAVL<T> novo_no = new NoBinarioAVL<T>(dado);
				raizSubArvore.setDir(novo_no);
			}else {
				this.inserirNo(raizSubArvore.getDir(), dado);
			}


			if (alturaNo(raizSubArvore)<(alturaNo(raizSubArvore.getDir())+1)) {
				raizSubArvore.setAltura(maior(alturaNo(raizSubArvore.getEsq()),alturaNo(raizSubArvore.getDir()))+1);
			}
			
			// TODO (resolvido, mas n?o testado) Resolver problema de estar perdendo a refer?ncia quando entra aqui
			if (fatorBalanceamento(raizSubArvore) >= 2) {
				if (dado.compareTo(raizSubArvore.getDir().getDado()) > 0) {
					rotacionarDir(raizSubArvore);
				}else {
					rotacionarDirEsq(raizSubArvore);
				}
			}
			
		} else 
			System.out.println("Valor duplicado!");
	}

	public void rotacionarEsq(NoBinarioAVL<T> raizSubArvore) {
		NoBinarioAVL<T> no = new NoBinarioAVL<T>();
		no.copiarNo(raizSubArvore.getEsq());
		raizSubArvore.setEsq(no.getDir());
		raizSubArvore.setAltura(maior(alturaNo(raizSubArvore.getEsq()),alturaNo(raizSubArvore.getDir()))+1);
		no.setDir(new NoBinarioAVL<T>());
		no.getDir().copiarNo(raizSubArvore);
		//raizSubArvore.setAltura(maior(alturaNo(raizSubArvore.getEsq()),alturaNo(raizSubArvore.getDir()))+1);
		no.setAltura(maior(alturaNo(no.getEsq()),alturaNo(raizSubArvore))+1);
		raizSubArvore.copiarNo(no);
	}
	public void rotacionarDir(NoBinarioAVL<T> raizSubArvore) {
		NoBinarioAVL<T> no = new NoBinarioAVL<T>();
		no.copiarNo(raizSubArvore.getDir());
		raizSubArvore.setDir(no.getEsq());
		// Atualiza??o da altura necess?ria porque n?o foi feita anteriormente
		raizSubArvore.setAltura(maior(alturaNo(raizSubArvore.getEsq()),alturaNo(raizSubArvore.getDir()))+1);
		no.setEsq(new NoBinarioAVL<T>());
		no.getEsq().copiarNo(raizSubArvore);
		//raizSubArvore.setAltura(maior(alturaNo(raizSubArvore.getEsq()),alturaNo(raizSubArvore.getDir()))+1);
		no.setAltura(maior(alturaNo(no.getDir()),alturaNo(raizSubArvore))+1);
		raizSubArvore.copiarNo(no);
	}
	public void rotacionarDirEsq(NoBinarioAVL<T> raizSubArvore) {
		rotacionarEsq(raizSubArvore.getDir());
		rotacionarDir(raizSubArvore);
	}
	public void rotacionarEsqDir(NoBinarioAVL<T> raizSubArvore) {
		rotacionarDir(raizSubArvore.getEsq());
		rotacionarEsq(raizSubArvore);
	}


	//public NoBinarioAVL<T> removerNo(NoBinarioAVL<T> referenciaRaiz, T dado) {
	@Override
	public NoBinario<T> removerNo(NoBinario<T> referenciaRaiz, T dado) {
		// TODO Auto-generated method stub
		if(referenciaRaiz == null)
			return referenciaRaiz;
		if (dado.compareTo(referenciaRaiz.getDado()) < 0) {
			referenciaRaiz.setEsq(this.removerNo(referenciaRaiz.getEsq(), dado));
			// Verificar problema
			if (fatorBalanceamento((NoBinarioAVL<T>)referenciaRaiz) >= 2) {
				//if (dado.compareTo(referenciaRaiz.getDir().getDado()) > 0) {
				if (alturaNo((NoBinarioAVL<T>)referenciaRaiz.getDir().getEsq()) <= alturaNo((NoBinarioAVL<T>)referenciaRaiz.getDir().getDir())) {
					rotacionarDir((NoBinarioAVL<T>)referenciaRaiz);
					//rotacionarDirEsq((NoBinarioAVL<T>)referenciaRaiz);
				}else {
					//rotacionarEsqDir((NoBinarioAVL<T>)referenciaRaiz);
					rotacionarDirEsq((NoBinarioAVL<T>)referenciaRaiz);
					//rotacionarDir((NoBinarioAVL<T>)referenciaRaiz);
				}
			}
			
		}else {
			if (dado.compareTo(referenciaRaiz.getDado()) > 0) {
				referenciaRaiz.setDir(this.removerNo(referenciaRaiz.getDir(), dado));

				// Verificar problema
				if (fatorBalanceamento((NoBinarioAVL<T>)referenciaRaiz) >= 2) {
					//if (dado.compareTo(referenciaRaiz.getEsq().getDado()) < 0) {
					if (alturaNo((NoBinarioAVL<T>)(referenciaRaiz.getEsq().getDir())) <= alturaNo((NoBinarioAVL<T>)referenciaRaiz.getEsq().getEsq())) {
						rotacionarEsq((NoBinarioAVL<T>)referenciaRaiz);
						//rotacionarEsqDir((NoBinarioAVL<T>)referenciaRaiz);
					}else {
						//rotacionarDirEsq((NoBinarioAVL<T>)referenciaRaiz);
						rotacionarEsqDir((NoBinarioAVL<T>)referenciaRaiz);
						//rotacionarEsq((NoBinarioAVL<T>)referenciaRaiz);
					}
				}
				
			}else {
				// Tratando os 3 poss?veis casos de remo??o
				if ((referenciaRaiz.getEsq() == null) || (referenciaRaiz.getDir() == null) ) {
					if (referenciaRaiz.getEsq() != null) // Tem 1 filho ? esquerda
						((NoBinarioAVL<T>)referenciaRaiz).copiarNo((NoBinarioAVL<T>)referenciaRaiz.getEsq()); 
					else if (referenciaRaiz.getDir() != null)// Tem 1 filho ? direita ou nenhum filho (null)
						((NoBinarioAVL<T>)referenciaRaiz).copiarNo((NoBinarioAVL<T>)referenciaRaiz.getDir());
					else
						return null;
						

				} else { // Tem 2 filhos!!
					T dadoAux = buscarMinimo(referenciaRaiz.getDir()).getDado();	// Encontra o menor dado da sub-?rvore direita
					referenciaRaiz.setDir(this.removerNo(referenciaRaiz.getDir(), dadoAux));	// Remove o n? encontrado antes
					referenciaRaiz.setDado(dadoAux);	// "Remove" o n? da chamada original substituindo seu dado 

					if (fatorBalanceamento((NoBinarioAVL<T>)referenciaRaiz) >= 2) {
						if (alturaNo((NoBinarioAVL<T>)(referenciaRaiz.getEsq().getDir())) <= alturaNo((NoBinarioAVL<T>)referenciaRaiz.getEsq().getEsq())) {
							rotacionarEsq((NoBinarioAVL<T>)referenciaRaiz);
							//rotacionarEsqDir((NoBinarioAVL<T>)referenciaRaiz);
						}else {
							rotacionarEsqDir((NoBinarioAVL<T>)referenciaRaiz);
							//rotacionarEsq((NoBinarioAVL<T>)referenciaRaiz);
						}
					}
					
				}
				//return referenciaRaiz;	// Retorna a ?rvore ap?s a remo??o
			}
		}
		if (referenciaRaiz != null)
			((NoBinarioAVL<T>)referenciaRaiz).setAltura(maior(alturaNo((NoBinarioAVL<T>)referenciaRaiz.getEsq()),alturaNo((NoBinarioAVL<T>)referenciaRaiz.getDir()))+1);
		
		return referenciaRaiz;
	}
	
	public int fatorBalanceamento(NoBinarioAVL<T> no) {
		int altEsq = 0, altDir = 0;
		if(no.getEsq() != null)
			altEsq = no.getEsq().getAltura();
		if(no.getDir() != null)
			altDir = no.getDir().getAltura();
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
