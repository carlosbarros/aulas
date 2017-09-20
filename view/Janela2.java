package view;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Janela2 extends JFrame implements ActionListener{
	//Vamos construir uma interface com java swing.
	//primeiro passo - declarar os componentes visuais
	private JLabel lbNome;
	private JTextField tfNome;
	private JButton btSalvar;
	//segundo passo - criar o método construtor da classe
	public Janela2(String titulo)
	{
		super(titulo);
		//terceiro passo - instanciar cada componente visual
		lbNome   = new JLabel("Digite seu nome");
		tfNome   = new JTextField(20);
		btSalvar = new JButton("Salvar");
		
		//quarto passo - definir o gerenciador de layout
		setLayout(new FlowLayout(FlowLayout.LEFT));
		
		//quinto passo - adicionar os componentes visuais ao container
		add(lbNome);
		add(tfNome);
		add(btSalvar);
		
		//sexto passo - setar as configurações de janela
		setVisible(true);
		setSize(240, 240);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);

		//setimo passo - tratando eventos
		//vamos implementar outra maneira de tratar
		btSalvar.addActionListener(this);
		//tratadora de eventos.
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == btSalvar)
			JOptionPane.showMessageDialog(this, "Ola, "+tfNome.getText());
		
		
	}
	
	
	
	

}
