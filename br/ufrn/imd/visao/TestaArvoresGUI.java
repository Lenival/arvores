package br.ufrn.imd.visao;


import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import br.ufrn.imd.controle.ArvoreBinariaBusca;
import br.ufrn.imd.modelo.NoBinario;


public class TestaArvoresGUI extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	public static JFrame frame;
	static JOptionPane message = new JOptionPane();

	private ArvoreBinariaBusca<Integer> arvoreQualquer = null;

	// Nós para impressão com seus respectivos círculos e posições
	private HashMap<NoBinario<Integer>, Rectangle> posicaoNo = null;
	private HashMap<NoBinario<Integer>, Dimension> dimensoesArvores = null;

	// Atributos auxiliares para impressão
	private boolean modificado = true;
	private int distanciaV = 10, distanciaIrmaos = 20;
	private Dimension vazio = new Dimension(0, 0);
	private FontMetrics fm = null;


	//private Font f	= new Font("Courier", Font.PLAIN, 12);

	// campos
	JTextField dado   = new JTextField();

	// botões
	JButton adicionar = new JButton("Adicionar");
	JButton remover = new JButton("Remover");
	JButton abrir = new JButton("Abrir...");
	JButton salvar = new JButton("Salvar...");
	JButton procurar = new JButton("Procurar");

	public TestaArvoresGUI(ArvoreBinariaBusca<Integer> arvore){
		System.out.println(arvore.getClass());
		this.arvoreQualquer = arvore;
		System.out.println(arvoreQualquer.getClass());
		posicaoNo = new HashMap<NoBinario<Integer>, Rectangle>();
		dimensoesArvores = new HashMap<NoBinario<Integer>, Dimension>();

		Container ct = this.getContentPane();
		ct.setLayout(null);

		ct.add(adicionar);
		ct.add(remover);
		ct.add(abrir);
		ct.add(salvar);
		ct.add(procurar);

		// Dimensão e posições dos componentes
		adicionar.setBounds(10,5,100,20);
		remover.setBounds(10,25,100,20);
		abrir.setBounds(10,45,100,20);
		salvar.setBounds(10,65,100,20);
		procurar.setBounds(10,85,100,20);

		// adicionando componentes
		ct.add(adicionar);
		ct.add(remover);
		ct.add(abrir);
		ct.add(salvar);
		ct.add(procurar);

		// evento dos botões
		adicionar.addActionListener(this);
		remover.addActionListener(this);
		abrir.addActionListener(this);
		salvar.addActionListener(this);
		procurar.addActionListener(this);
	}

	public void actionPerformed(ActionEvent e) {
		String input;
		int dado;

		if (e.getSource() == adicionar){
			System.out.println("Botão adicionar pressionado");
			input = JOptionPane.showInputDialog("Insira um dado inteiro");
			try{	// Tenta inserir um inteiro
				dado = Integer.parseInt(input);
				arvoreQualquer.inserirDado(dado);
				modificado = true;
				repaint();
			}catch(NumberFormatException eNum){
				JOptionPane.showMessageDialog(frame, "O dado precisa ser inteiro");
			}
		}

		if (e.getSource() == remover){
			System.out.println("Botão remover pressionado por tipo: " + this.arvoreQualquer.getClass() + " com nó " + this.arvoreQualquer.getRaiz().getClass());
			input = JOptionPane.showInputDialog("Remova um dado inteiro");
			try{	// Tenta remover um inteiro
				dado = Integer.parseInt(input);
				//((ArvoreAVL<Integer>) arvoreQualquer).removerNo((NoBinarioAVL<Integer>)arvoreQualquer.getRaiz(), dado);
				arvoreQualquer.setRaiz(arvoreQualquer.removerNo(arvoreQualquer.getRaiz(), dado));
				modificado = true;
				repaint();
			}
			catch(NumberFormatException eNum){
				JOptionPane.showMessageDialog(frame, "O dado precisa ser inteiro");
			}
		}

		if (e.getSource() == abrir){
			System.out.println("Botão adicionarArquivo pressionado");
			JFileChooser fileChooser = new JFileChooser();
			fileChooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
			int result = fileChooser.showOpenDialog(this);
			File selectedFile = null;
			if (result == JFileChooser.APPROVE_OPTION) {
				selectedFile = fileChooser.getSelectedFile();
				System.out.println("Selected file: " + selectedFile.getAbsolutePath());
			}

			BufferedReader buffer = null;
			try {
				buffer = new BufferedReader(new FileReader(selectedFile));
				String line;
				// Lendo em cada linha do arquivo um dado a ser inserido
				while ((line = buffer.readLine()) != null) {
					input = line;
					arvoreQualquer.inserirDado(Integer.parseInt(input));
					modificado = true;
					repaint();
				}
			}
			catch (IOException z) 
			{
				//The user doesn´t know exactly where is the file text
				z.printStackTrace();
			}
			//Always this is executed...
			finally {
				try{
					//Stop the actions that perform import.java.io.Filereader
					buffer.close();
				}
				//"Try" needs a catch, because of syntax. But, in the program is neve executed. 
				catch (IOException z) {
					z.printStackTrace();
				}
			}
		}

		if (e.getSource() == procurar){
			input = JOptionPane.showInputDialog("Search an integer number:");
			try{	// Tenta encontrar o número
				int f[] = {0};
				dado =Integer.parseInt(input);
				NoBinario<Integer> aux = arvoreQualquer.buscarDado(arvoreQualquer.getRaiz(), dado, f);
				if (aux == null)
					JOptionPane.showMessageDialog(frame, "O elemento " + dado + " não está presente");
				else
					JOptionPane.showMessageDialog(frame, "O elemento " + dado + " foi encontrado");
				modificado = true;
				repaint();
			}catch(NumberFormatException z){
				JOptionPane.showMessageDialog(frame, "O dado precisa ser inteiro");
			}
		}

	}

	private void calcularLocalizacoes() {
		posicaoNo.clear();
		dimensoesArvores.clear();
		NoBinario<Integer> root = arvoreQualquer.getRaiz();
		if (root != null) {
			calcularDimesoes(root);
			calcularLocalizacoes(root, Integer.MAX_VALUE, Integer.MAX_VALUE, 20);
		}
	}

	// Dimensões de uma sub-árvore n
	private Dimension calcularDimesoes(NoBinario<Integer> n) {
		if (n == null) return new Dimension(0, 0);
		Dimension ld = calcularDimesoes(n.getEsq());
		Dimension rd = calcularDimesoes(n.getDir());
		int h = fm.getHeight() + distanciaV + Math.max(ld.height, rd.height);
		int w = ld.width + distanciaIrmaos + rd.width;
		Dimension d = new Dimension(w, h);
		dimensoesArvores.put(n, d);
		return d;
	}

	// Localização dos nós nas sub-árvores de raiz n
	private void calcularLocalizacoes(NoBinario<Integer> n, int left, int right, int top) {
		if (n == null) return;
		Dimension ld = (Dimension) dimensoesArvores.get(n.getEsq());
		if (ld == null) ld = vazio;
		Dimension rd = (Dimension) dimensoesArvores.get(n.getDir());
		if (rd == null) rd = vazio;
		int center = 0;
		if (right != Integer.MAX_VALUE)
			center = right - rd.width - distanciaIrmaos/2;
		else if (left != Integer.MAX_VALUE)
			center = left + ld.width + distanciaIrmaos/2;
		int width = fm.stringWidth((n.getDado()).toString());
		Rectangle r = new Rectangle(center - width/2 - 3, top, width + 6, fm.getHeight());
		posicaoNo.put(n, r);
		calcularLocalizacoes(n.getEsq(), Integer.MAX_VALUE, center - distanciaIrmaos/2, top + fm.getHeight() + distanciaV);
		calcularLocalizacoes(n.getDir(), center + distanciaIrmaos/2, Integer.MAX_VALUE, top + fm.getHeight() + distanciaV);
	}

	// Desenha arvoreQualquer a partir de suas localizações
	private void desenhaArvore(Graphics2D g, NoBinario<Integer> n, int px, int py, int yoffs) {
		if (n == null) return;
		Rectangle r = (Rectangle) posicaoNo.get(n);
		g.draw(r);
		g.drawString((n.getDado()).toString(), r.x + 3, r.y + yoffs);
		if (px != Integer.MAX_VALUE)
			g.drawLine(px, py, r.x + r.width/2, r.y);
		desenhaArvore(g, n.getEsq(), r.x + r.width/2, r.y + r.height, yoffs);
		desenhaArvore(g, n.getDir(), r.x + r.width/2, r.y + r.height, yoffs);
	}

	// Desenha arvoreQualquer no g (Graphics) passado como parâmetro 
	public void paint(Graphics g) {
		super.paint(g);
		fm = g.getFontMetrics();
		if (modificado) {		// Recalcula se houve modificação antes da chamada
			calcularLocalizacoes();
			modificado = false;
		}
		Graphics2D g2d = (Graphics2D) g;
		g2d.translate(getWidth() / 2, distanciaV); // Aponta posição horizontal e vertical onde g2d desenhará
		desenhaArvore(g2d, arvoreQualquer.getRaiz(), Integer.MAX_VALUE, Integer.MAX_VALUE, fm.getLeading() + fm.getAscent());
		fm = null;
	}

}
