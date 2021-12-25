package br.ufrn.imd.visao;

import java.util.ArrayList;

import br.ufrn.imd.controle.ArvoreBinariaBusca;

public class TestaArvores {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArvoreBinariaBusca<Integer> a1 = new ArvoreBinariaBusca<Integer>();
		
		a1.inserirDado(10);
		a1.inserirDado(5);
		a1.inserirDado(13);
		a1.inserirDado(18);
		a1.inserirDado(7);
		
		System.out.println("Percorrendo Árvore em pré-ordem");
		ArrayList<Integer> arrayDados = new ArrayList<>();
		a1.percorrerPreOrdem(a1.getRaiz(), arrayDados);
		System.out.println(arrayDados);

		a1.inserirDado(1);
		a1.inserirDado(6);
		a1.inserirDado(2);
		a1.inserirDado(20);
		a1.inserirDado(9);

		System.out.println("Percorrendo Árvore em ordem");
		arrayDados = new ArrayList<>();
		a1.percorrerEmOrdem(a1.getRaiz(), arrayDados);
		System.out.println(arrayDados);
		System.out.println("Percorrendo Árvore em pré-ordem");
		arrayDados = new ArrayList<>();
		a1.percorrerPreOrdem(a1.getRaiz(), arrayDados);
		System.out.println(arrayDados);
		System.out.println("Percorrendo Árvore em pós-ordem");
		arrayDados = new ArrayList<>();
		a1.percorrerPosOrdem(a1.getRaiz(), arrayDados);
		System.out.println(arrayDados);
		

	}

}
