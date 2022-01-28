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
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.GridLayout;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.KeyStroke;

import br.ufrn.imd.controle.ArvoreBinaria;
import br.ufrn.imd.controle.ArvoreBinariaBusca;
import br.ufrn.imd.controle.ArvoreBinariaBuscaEstendida;
import br.ufrn.imd.modelo.NoBinario;


public class TestaArvoresGUI extends JFrame implements ActionListener {
	
	private static final long serialVersionUID = 1L;
	public static JFrame frame;
    static JOptionPane message = new JOptionPane();
	
    private ArvoreBinariaBusca<Integer> arvoreQualquer = null;
	
	// Nós para impressão com seus respectivos círculos e posições
    private HashMap<NoBinario<Integer>, Rectangle> nodeLocations = null;
    private HashMap<NoBinario<Integer>, Dimension> subtreeSizes = null;
	
	// Atributos auxiliares para impressão
    private boolean modificado = true;
    private int distanciaV = 10, distanciaIrmaos = 20;
    private Dimension vazio = new Dimension(0, 0);
	private FontMetrics fm = null;
	
	
	private Font f	= new Font("Courier", Font.PLAIN, 12);
			
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
		nodeLocations = new HashMap<NoBinario<Integer>, Rectangle>();
		subtreeSizes = new HashMap<NoBinario<Integer>, Dimension>();
		
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
    	  System.out.println("Botão remover pressionado");
          input = JOptionPane.showInputDialog("Remova um dado inteiro");
          try{	// Tenta remover um inteiro
                  dado = Integer.parseInt(input);
                  arvoreQualquer.removerNo(arvoreQualquer.getRaiz(), dado);
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
    	  fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
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

    private void calculateLocations() {
      nodeLocations.clear();
      subtreeSizes.clear();
      NoBinario<Integer> root = arvoreQualquer.getRaiz();
      if (root != null) {
        calculateSubtreeSize(root);
        calculateLocation(root, Integer.MAX_VALUE, Integer.MAX_VALUE, 20);
      }
    }
    
    // This method calculates the size of a subtree rooted at n
    private Dimension calculateSubtreeSize(NoBinario<Integer> n) {
      if (n == null) return new Dimension(0, 0);
      Dimension ld = calculateSubtreeSize(n.getEsq());
      Dimension rd = calculateSubtreeSize(n.getDir());
      int h = fm.getHeight() + distanciaV + Math.max(ld.height, rd.height);
      int w = ld.width + distanciaIrmaos + rd.width;
      Dimension d = new Dimension(w, h);
      subtreeSizes.put(n, d);
      return d;
    }
    
    // This method calculates the location of the nodes in the subtree rooted at n
    private void calculateLocation(NoBinario<Integer> n, int left, int right, int top) {
      if (n == null) return;
      Dimension ld = (Dimension) subtreeSizes.get(n.getEsq());
      if (ld == null) ld = vazio;
      Dimension rd = (Dimension) subtreeSizes.get(n.getDir());
      if (rd == null) rd = vazio;
      int center = 0;
      if (right != Integer.MAX_VALUE)
        center = right - rd.width - distanciaIrmaos/2;
      else if (left != Integer.MAX_VALUE)
        center = left + ld.width + distanciaIrmaos/2;
      int width = fm.stringWidth((n.getDado()).toString());
      Rectangle r = new Rectangle(center - width/2 - 3, top, width + 6, fm.getHeight());
      nodeLocations.put(n, r);
      calculateLocation(n.getEsq(), Integer.MAX_VALUE, center - distanciaIrmaos/2, top + fm.getHeight() + distanciaV);
      calculateLocation(n.getDir(), center + distanciaIrmaos/2, Integer.MAX_VALUE, top + fm.getHeight() + distanciaV);
    }
    
    // This method draws the arvoreQualquer using the pre-calculated locations. We need necessary a graphic
    private void drawTree(Graphics2D g, NoBinario<Integer> n, int px, int py, int yoffs) {
      if (n == null) return;
      Rectangle r = (Rectangle) nodeLocations.get(n);
      g.draw(r);
      g.drawString((n.getDado()).toString(), r.x + 3, r.y + yoffs);
     if (px != Integer.MAX_VALUE)
       g.drawLine(px, py, r.x + r.width/2, r.y);
     drawTree(g, n.getEsq(), r.x + r.width/2, r.y + r.height, yoffs);
     drawTree(g, n.getDir(), r.x + r.width/2, r.y + r.height, yoffs);
   }
   
    //This method will draw our arvoreQualquer, this receives a graphic called "g" 
    public void paint(Graphics g) {
     super.paint(g);
     fm = g.getFontMetrics();
     // if node locations not calculated
     if (modificado) {
       calculateLocations();
       modificado = false;
     }
     Graphics2D g2d = (Graphics2D) g;
     g2d.translate(getWidth() / 2, distanciaV);
     drawTree(g2d, arvoreQualquer.getRaiz(), Integer.MAX_VALUE, Integer.MAX_VALUE, fm.getLeading() + fm.getAscent());
     fm = null;
   }
   
   /*At the start of the program will show a messagebox with all the commands that 
    can be used to work this program correctly,also set the dimension of the principal 
    window */
   @SuppressWarnings("deprecation")

	public static void main(String[] args) {
		// TODO Auto-generated method stub
       ArvoreBinariaBuscaEstendida<Integer> tree = new ArvoreBinariaBuscaEstendida<Integer>();
       JFrame f = new JFrame("Binary Tree");
       JOptionPane.showMessageDialog(frame, "Welcome"
               + "\n\nThis program works typing some letters from your keyboard"
               + "\nSo, the operations you can use are:"
               + "\n a --- Add an integer number"
               + "\n f --- Add from file"
               + "\n s --- Search an integer number"
               + "\n d --- Delete an integer number"
               + "\n h --- Help (if you forgot this");
        f.getContentPane().add(new TestaArvoresGUI(tree));
        // create and add an event handler for window closing event
        f.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
            //System.exit(0);
        }
     });
     f.setBounds(50, 50, 700, 700);
     f.show();
	}

}
