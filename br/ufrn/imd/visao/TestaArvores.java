package br.ufrn.imd.visao;

import java.util.ArrayList;

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
		
		System.out.println("Testando busca na �rvore");
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
		
		System.out.println("Percorrendo �rvore em pr�-ordem");
		ArrayList<Integer> arrayDados = new ArrayList<>();
		a1.percorrerPreOrdem(a1.getRaiz(), arrayDados);
		System.out.println(arrayDados);
		
		System.out.println("O elemento m�ximo � "+a1.buscarMaximo(a1.getRaiz()).getDado()+" e o m�nimo � "+a1.buscarMinimo(a1.getRaiz()).getDado());

		a1.inserirDado(1);
		a1.inserirDado(6);
		a1.inserirDado(2);
		a1.inserirDado(20);
		a1.inserirDado(9);
		a1.inserirDado(11);
		a1.inserirDado(19);

		//a1.setRaiz(a1.removerNo(a1.getRaiz(), 20));
		
		System.out.println("Percorrendo �rvore em ordem");
		//a1.setRaiz(a1.removerNo(a1.getRaiz(), 10));   // Remo��o
		arrayDados = new ArrayList<>();
		a1.percorrerEmOrdem(a1.getRaiz(), arrayDados);
		System.out.println(arrayDados);
		System.out.println("Percorrendo �rvore em pr�-ordem");
		a1.setRaiz(a1.removerNo(a1.getRaiz(), 18));   // Remo��o
		arrayDados = new ArrayList<>();
		a1.percorrerPreOrdem(a1.getRaiz(), arrayDados);
		System.out.println(arrayDados);
		System.out.println("Percorrendo �rvore em p�s-ordem");
		//a1.setRaiz(a1.removerNo(a1.getRaiz(), 7));   // Remo��o
		arrayDados = new ArrayList<>();
		a1.percorrerPosOrdem(a1.getRaiz(), arrayDados);
		System.out.println(arrayDados);

		System.out.println("O elemento m�ximo � "+a1.buscarMaximo(a1.getRaiz()).getDado()+" e o m�nimo � "+a1.buscarMinimo(a1.getRaiz()).getDado());
		

	}

}
