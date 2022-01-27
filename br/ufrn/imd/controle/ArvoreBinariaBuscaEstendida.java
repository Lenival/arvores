package br.ufrn.imd.controle;

import java.util.ArrayList;

import br.ufrn.imd.modelo.NoBinarioEstendido;

public class ArvoreBinariaBuscaEstendida <T extends Comparable <T>> extends ArvoreBinariaBusca<T> {
	
/*
 *	TODO Implementar ArrayList<NoBinarioEstendido<T>> que guardará o nós mais à esquerda de cada nível
 *	TODO Implementar funções de inserção e remoção que contemplem a atualização
 * */
	
	//private ArrayList<NoBinarioEstendido<T>> nosEsq;
	private ArrayList<NoBinarioEstendido<T>> nosExtremaEsq;
	
	public ArvoreBinariaBuscaEstendida() {
		super();
		// TODO Auto-generated constructor stub
		nosExtremaEsq = new ArrayList<NoBinarioEstendido<T>>();
	}

	public void adicionarNosExtremaEsq(NoBinarioEstendido<T> a) {
		this.nosExtremaEsq.add(a);
	}

	public void removerNosExtremaEsq(NoBinarioEstendido<T> a) {
		this.nosExtremaEsq.remove(nosExtremaEsq.indexOf(a));
	}

	public void exibirNosExtremaEsq() {
		System.out.println("Os elementos cadastrados são:");
		for (NoBinarioEstendido<T> a : nosExtremaEsq)
			System.out.println(a.toString());
	}

	@Override
	public void inserirDado(T dado) {
		if (this.raiz == null) {
			NoBinarioEstendido<T> no = new NoBinarioEstendido<T>();
			no.setDado(dado);
			this.raiz = no;
			nosExtremaEsq.add(no);
		}else {
			inserirNo((NoBinarioEstendido<T>)raiz, dado, 1);
		}
	}

	public void inserirNo(NoBinarioEstendido<T> raizSubArvore,T dado, Integer profundidade) {
		if (dado.compareTo(raizSubArvore.getDado()) < 0 ) {
			if(raizSubArvore.getEsq() == null) {
				NoBinarioEstendido<T>  novo_no = new NoBinarioEstendido<T>(dado);
				raizSubArvore.setEsq(novo_no);
			}else {
				inserirNo((NoBinarioEstendido<T>)raizSubArvore.getEsq(), dado);
			}
		}
		if (raizSubArvore.getDado().compareTo(dado) < 0 ) {
			if(raizSubArvore.getDir() == null) {
				NoBinarioEstendido<T>  novo_no = new NoBinarioEstendido<T>(dado);
				raizSubArvore.setDir(novo_no);
			}else {
				inserirNo((NoBinarioEstendido<T>)raizSubArvore.getDir(), dado);
			}
		}
	}

	//@Override
	public NoBinarioEstendido<T> removerNo(NoBinarioEstendido<T> referenciaRaiz, T dado) {
		return (NoBinarioEstendido<T>)super.removerNo(referenciaRaiz, dado);
	}
	
	
	
	//@Override
	/*
	public void inserirNo(NoBinarioEstendido<T> raizSubArvore, T dado, Integer profundidade) {
		if (dado.compareTo(raizSubArvore.getDado()) < 0 ) {
			if(raizSubArvore.getEsq() == null) {
				NoBinarioEstendido<T> novoNoEsq = new NoBinarioEstendido<T>(dado);
				if(raizSubArvore.getDir() != null) { // Se tem o nó direito, ele será o irmão direito do novo nó
					novoNoEsq.setIrmaoDireito((NoBinarioEstendido<T>)raizSubArvore.getDir());
					novoNoEsq.setIrmaoEsquedo(((NoBinarioEstendido<T>)raizSubArvore.getDir()).getIrmaoEsquedo());
					((NoBinarioEstendido<T>)raizSubArvore.getDir()).setIrmaoEsquedo(novoNoEsq);
				}else {	// Se não tem o nó direito...

					NoBinarioEstendido<T> noTemp = raizSubArvore;
					if (nosExtremaEsq.size()>profundidade) {	// Verifica se nosExtremaEsq já tem elementos nessa profundidade
						if (noTemp.getIrmaoDireito() != null) {	// Se o nó pai tem irmão direito, procura-se o primo à direita
							noTemp = noTemp.getIrmaoDireito();	// Referencia o tio para tentar encontrar um primo à direita 
							while(noTemp.getEsq() == null) {	// 
								if (noTemp.getDir() == null) {
									noTemp = noTemp.getIrmaoDireito();
								}else {
									break;
								}
							}
							if (noTemp.getEsq() != null) {
								novoNoEsq.setIrmaoDireito((NoBinarioEstendido<T>)noTemp.getEsq());
								novoNoEsq.setIrmaoEsquedo(((NoBinarioEstendido<T>)noTemp.getEsq()).getIrmaoEsquedo());
								((NoBinarioEstendido<T>)noTemp.getEsq()).setIrmaoEsquedo(novoNoEsq);
							} else {

							}
							
						} else {	// Se não tem irmão direito, vai ter que ter esquerdo, pois nosExtremaEsq já tem essa profundidade

						}
					}else {	// Se não alcançou esta profundidade, basta inserir o novo nó
						nosExtremaEsq.add(novoNoEsq);
					}
					
					while (noTemp != null) {
						if (noTemp.getEsq() != null) {
							novoNoEsq.setIrmaoDireito((NoBinarioEstendido<T>)noTemp.getEsq());
							break;
						}
						else if (noTemp.getDir() != null) {
							novoNoEsq.setIrmaoDireito((NoBinarioEstendido<T>)noTemp.getDir());
							break;
							}
						else
							noTemp = noTemp.getIrmaoDireito();
					}
				}
				raizSubArvore.setEsq(novoNoEsq);
			}else {
				inserirNo(raizSubArvore.getEsq(), dado);
			}
		}
		if (raizSubArvore.getDado().compareTo(dado) < 0 ) {
			if(raizSubArvore.getDir() == null) {
				NoBinarioEstendido<T> novoNoDir = new NoBinarioEstendido<T>(dado);
				NoBinarioEstendido<T> noTemp = raizSubArvore.getIrmaoDireito();
				while (noTemp != null) {
					if (noTemp.getEsq() != null) {
						novoNoDir.setIrmaoDireito((NoBinarioEstendido<T>)noTemp.getEsq());
						break;
					}
					else if (noTemp.getDir() != null) {
						novoNoDir.setIrmaoDireito((NoBinarioEstendido<T>)noTemp.getDir());
						break;
						}
					else
						noTemp = noTemp.getIrmaoDireito();
				}
				raizSubArvore.setDir(novoNoDir);
			}else {
				inserirNo(raizSubArvore.getDir(), dado);
			}
		}
	}
	*/
	
	
	

}
