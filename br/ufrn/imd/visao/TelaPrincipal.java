package br.ufrn.imd.visao;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import br.ufrn.imd.controle.ArvoreAVL;
import br.ufrn.imd.controle.ArvoreBinariaBuscaEstendida;

public class TelaPrincipal extends JFrame implements ActionListener{
	
	private static final long serialVersionUID = 1L;
	public static JFrame frame;
	
	JMenuBar mnbar = new JMenuBar();
	JMenu menuArv = new JMenu("Árvores");
	JMenu menuAjud = new JMenu("Ajuda");
	
	JMenuItem mItem1 = new JMenuItem("Árvore Binária de Busca");
	JMenuItem mItem2 = new JMenuItem("Árvore AVL");
	
	JMenuItem mItem7 = new JMenuItem("Sobre");
	JMenuItem mItem8 = new JMenuItem("Sair");
		 		
	public TelaPrincipal(){
		Container ct = this.getContentPane();
		ct.setLayout(new BorderLayout());
		
		setJMenuBar(mnbar);
		mnbar.add(menuArv);
		mnbar.add(menuAjud);
		

		menuArv.add(mItem1);
		menuArv.add(mItem2);
				
		menuAjud.add(mItem7);
		menuAjud.addSeparator();
		menuAjud.add(mItem8);
				
		setSize(800,600);
		setResizable(false);
		setTitle("Teste de implementações de árvores");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		// eventos
		mItem1.addActionListener(this);	
		mItem2.addActionListener(this);

		mItem7.addActionListener(this);
		mItem8.addActionListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == mItem1){
			ArvoreBinariaBuscaEstendida<Integer> tree = new ArvoreBinariaBuscaEstendida<Integer>();
			TestaArvoresGUI f = new TestaArvoresGUI(tree);
			f.addWindowListener(new WindowAdapter() {
			    public void windowClosing(WindowEvent e) {
			    //System.exit(0);
			    }
			 });
			 f.setBounds(50, 50, 1200, 500);
			 f.setVisible(true);
		}
		if (e.getSource() == mItem2){
			ArvoreAVL<Integer> tree = new ArvoreAVL<Integer>();
			TestaArvoresGUI f = new TestaArvoresGUI(tree);
	        f.addWindowListener(new WindowAdapter() {
	            public void windowClosing(WindowEvent e) {
	            //System.exit(0);
		        }
		     });
	        f.setBounds(50, 50, 1200, 500);
	        f.setVisible(true);
		}
		if (e.getSource() == mItem7) {
			TelaSobre tlSobre = new TelaSobre("Sobre");
			tlSobre.setVisible(true);
		}
		if (e.getSource() == mItem8) {
			System.out.println("Fechando o sistema!!!");
			System.exit(0);
		}
	}
	
	public static void main(String[] args) {
		TelaPrincipal telaPai = new TelaPrincipal();
		telaPai.setVisible(true);
	}
}
