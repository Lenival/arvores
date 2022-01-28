package br.ufrn.imd.visao;

import java.awt.Container;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JTextArea;


public class TelaSobre extends JFrame {
	JTextArea sobre = new JTextArea();
	
	private Font f	= new Font("Courier", Font.PLAIN, 12);
	//private JScrollPane scroll;

	public TelaSobre(String str) {
		super(str);

		Container ct = this.getContentPane();
		ct.setLayout(null);

		sobre.setFont(f);
		
		sobre.setLineWrap(true);
		sobre.setWrapStyleWord(true);
		// coordenadas
		sobre.setBounds(10,10,280,200);
		
		// adicionando componentes
		ct.add(sobre);
				
		// especificações do formulário
		setSize(390,210);
		setTitle(str);
		

		//cDAO = ClienteDAO.getInstance();
		sobre.setText("Aqui deve vir informações sobre o programa!");
	}

	
}