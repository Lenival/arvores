package br.ufrn.imd.visao;

import java.util.ArrayList;
import java.util.Arrays;

import br.ufrn.imd.controle.ArvoreAVL;
import br.ufrn.imd.controle.ArvoreBinariaBusca;
import br.ufrn.imd.modelo.NoBinario;

public class TestaArvores {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArvoreBinariaBusca<Integer> a1 = new ArvoreBinariaBusca<Integer>();
		
		a1.inserirDado(10);
		a1.inserirDado(5);
		a1.inserirDado(13);
		a1.inserirDado(18);
		a1.inserirDado(7);
		
		System.out.println("Testando busca na árvore");
		NoBinario<Integer> noTemp = new NoBinario<>();
		noTemp = a1.getRaiz();
		int f[] = {0};
		noTemp = a1.buscarDado(noTemp, 5, f);
		System.out.println("f: "+f[0]+" para noTemp.getDado(): "+noTemp.getDado());

		noTemp = a1.getRaiz();
		f[0] = 0;
		noTemp = a1.buscarDado(noTemp, 7, f);
		System.out.println("f: "+f[0]+" para noTemp.getDado(): "+noTemp.getDado());

		noTemp = a1.getRaiz();
		f[0] = 0;
		noTemp = a1.buscarDado(noTemp, 18, f);
		System.out.println("f: "+f[0]+" para noTemp.getDado(): "+noTemp.getDado());

		noTemp = a1.getRaiz();
		f[0] = 0;
		noTemp = a1.buscarDado(noTemp, 19, f);
		System.out.println("f: "+f[0]+" para noTemp.getDado(): "+noTemp.getDado());

		noTemp = a1.getRaiz();
		f[0] = 0;
		noTemp = a1.buscarDado(noTemp, 11, f);
		System.out.println("f: "+f[0]+" para noTemp.getDado(): "+noTemp.getDado());
		
		System.out.println("Percorrendo árvore em pré-ordem");
		ArrayList<Integer> arrayDados = new ArrayList<>();
		a1.percorrerPreOrdem(a1.getRaiz(), arrayDados);
		System.out.println(arrayDados);
		
		System.out.println("O elemento máximo é "+a1.buscarMaximo(a1.getRaiz()).getDado()+" e o mínimo é "+a1.buscarMinimo(a1.getRaiz()).getDado());

		a1.inserirDado(1);
		a1.inserirDado(6);
		a1.inserirDado(2);
		a1.inserirDado(20);
		a1.inserirDado(9);
		a1.inserirDado(11);
		a1.inserirDado(19);

		//a1.setRaiz(a1.removerNo(a1.getRaiz(), 20));
		
		System.out.println("Percorrendo árvore em ordem");
		//a1.setRaiz(a1.removerNo(a1.getRaiz(), 10));   // Remoção
		arrayDados = new ArrayList<>();
		a1.percorrerEmOrdem(a1.getRaiz(), arrayDados);
		System.out.println(arrayDados);
		System.out.println("Percorrendo árvore em pré-ordem");
		//a1.setRaiz(a1.removerNo(a1.getRaiz(), 18));   // Remoção
		arrayDados = new ArrayList<>();
		a1.percorrerPreOrdem(a1.getRaiz(), arrayDados);
		System.out.println(arrayDados);
		
		System.out.println("Percorrendo árvore em nível");
		ArrayList<NoBinario<Integer>> arrayNos = new ArrayList<NoBinario<Integer>>();
		a1.percorrerEmNivel(a1.getRaiz(), arrayNos);
		System.out.println(arrayDados);
		
		System.out.println("Percorrendo árvore em pós-ordem");
		//a1.setRaiz(a1.removerNo(a1.getRaiz(), 7));   // Remoção
		arrayDados = new ArrayList<>();
		a1.percorrerPosOrdem(a1.getRaiz(), arrayDados);
		System.out.println(arrayDados);

		System.out.println("O elemento máximo é "+a1.buscarMaximo(a1.getRaiz()).getDado()+" e o mínimo é "+a1.buscarMinimo(a1.getRaiz()).getDado());
		
		//a1.percorrerEmNivel(a1.getRaiz());
		System.out.println("O enésimo elemento é: "+ a1.getEnesimoElemento(10));
		int elemento = 7;
		System.out.println("A posição do elemento "+ elemento +" é: "+ a1.getPosicao(elemento));
		System.out.println("O elemento mediano é: "+ a1.getMediana());
		a1.removerNo(a1.getRaiz(), 5);
		arrayDados = new ArrayList<>();
		a1.percorrerEmOrdem(a1.getRaiz(), arrayDados);
		System.out.println(arrayDados);
		System.out.println("O elemento mediano é: "+ a1.getMediana());
		a1.inserirDado(21);
		arrayDados = new ArrayList<>();
		a1.percorrerEmOrdem(a1.getRaiz(), arrayDados);
		System.out.println(arrayDados);
		System.out.println("O elemento mediano é: "+ a1.getMediana());
		
		ArvoreBinariaBusca<Integer> a2 = new ArvoreBinariaBusca<>();
		ArrayList<Integer> v = new ArrayList<>(Arrays.asList(61,89,66,43,51,16,55,11,79,77,82,32));
		for(Integer i : v)
			a2.inserirDado(i);
		arrayNos.clear();
		a2.percorrerEmNivel(a2.getRaiz(), arrayNos);
		System.out.println("Percorrendo árvore em nível");
		System.out.println(arrayNos);
		
		ArvoreAVL<Integer> avl1 = new ArvoreAVL<Integer>();
		arrayNos.clear();
		System.out.println(v);
		for(Integer i : v) {
			System.out.println("Inserindo o elemento " + i);
			avl1.inserirDado(i);
			/*avl1.percorrerEmNivel(avl1.getRaiz(), arrayNos);
			System.out.println("Percorrendo árvore AVL em nível");
			System.out.println(arrayNos);
			arrayNos.clear();*/
		}
		//avl1.inserirDado(5);
		avl1.percorrerEmNivel(avl1.getRaiz(), arrayNos);
		System.out.println("Percorrendo árvore AVL em nível");
		System.out.println(arrayNos);
		
	}

}
