package view;

import javax.swing.*;

import com.formdev.flatlaf.FlatLightLaf;

import java.awt.event.*;

public class Interface extends JFrame implements ActionListener{
	private static final long serialVersionUID = 1L;
	private JMenu Jmenu;
	private JMenuBar JMenubar;
	private JMenuItem JMenuItem1;
	private JMenuItem JMenuItem2;
	private JMenuItem JMenuItem3;
	public static int cell_number=9;
	public static int X_MAX=700;
	public static int Y_MAX=700;

	public Interface() { 
		super("Sudoku by Tnk");
		setBounds(300,0,X_MAX,Y_MAX);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JMenubar=new JMenuBar();
		Jmenu=new JMenu("Options");
		JMenuItem1=new JMenuItem("Ordre 2");
		JMenuItem2=new JMenuItem("Ordre 3");
		JMenuItem3=new JMenuItem("Ordre 4");
		Jmenu.add(JMenuItem1);
		Jmenu.add(JMenuItem2);
		Jmenu.add(JMenuItem3);
		JMenubar.add(Jmenu);
		setJMenuBar(JMenubar);
		JMenuItem1.addActionListener(this);   
		JMenuItem2.addActionListener(this);
		JMenuItem3.addActionListener(this);
		validate();
		repaint();
	}
	public void actionPerformed(ActionEvent e)
	{
		if("Ordre 2".equals(e.getActionCommand()))
		{
			cell_number=4;getContentPane().removeAll();
			new Container(this,cell_number);
			validate();
			repaint();
		}
		if("Ordre 3".equals(e.getActionCommand()))
		{
			repaint();
			cell_number=9;getContentPane().removeAll();
			new Container(this,cell_number);
			validate();
			this.repaint();
		}
		if("Ordre 4".equals(e.getActionCommand()))
		{
			repaint();
			cell_number=16;getContentPane().removeAll();
			new Container(this,cell_number);
			validate();
			this.repaint();
		}
	}
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(new FlatLightLaf());
		}catch(Exception ex){
			System.err.println("Failed to initialize Laf");
		}
		new Interface();

	}
}