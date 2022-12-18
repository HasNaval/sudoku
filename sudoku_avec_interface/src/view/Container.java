package view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import codegen.*;
import resolution.BoardServer;
import resolution.Sudoku;

public class Container extends JPanel{
	private static final long serialVersionUID = 1L;
	private JTextField  cells[][];
	private JPanel rows[];
	private JPanel grid;    
	private JPanel MainPanel;
	private JPanel Buttons1;
	private JPanel conteneur1;
	private JPanel conteneur2;
	private JTextArea state; 
	private JScrollPane statescroll; 
	private JButton Clear_Log;


	public Container(Interface in,int ordre)
	{	
		cells=new JTextField[ordre][ordre];
		for(int i=0;i<ordre;i++){
			for(int j=0;j<ordre;j++){
				int leftb,rightb,topb,botb;
				cells[i][j]=new JTextField();
//				cells[i][j].setColumns(1);
				topb=rightb=leftb=botb=1;
				int x=(int)Math.sqrt(ordre);                
				if(i%x==0)topb=4;if(i%x==x-1)botb=4;                
				if(i==0) topb*=2;if(i==ordre-1) botb*=2;                 
				if(j%x==0)leftb=4;if(j%x==x-1)rightb=4;                  
				if(j==0) leftb*=2;if(j==ordre-1) rightb*=2;
				Color c=getForeground();
				cells[i][j].setBorder(BorderFactory.createMatteBorder(topb,leftb,botb,rightb,c));
			}
		}
		
		
		grid=new JPanel();//Conteneur du grille Sudoku(suivant la ligne)
		grid.setLayout(new BoxLayout(grid, BoxLayout.Y_AXIS));
		rows=new JPanel[ordre];//Conteneur de chaque ligne(suivant la colonne)
		for(int i=0;i<ordre;i++)
		{
			rows[i]=new JPanel();
			rows[i].setLayout(new BoxLayout(rows[i], BoxLayout.X_AXIS));
			for(int j=0;j<ordre;j++)
			{
				rows[i].add(cells[i][j]);
				cells[i][j].setHorizontalAlignment(JTextField.CENTER);
			}
			grid.add(rows[i]);
		}
		//Conteneur des boutons solve et reset plac�es en ligne
		Buttons1=new JPanel();Buttons1.setLayout(new BoxLayout(Buttons1, BoxLayout.X_AXIS));
		JButton play=new JButton("Play");Buttons1.add(play); 
		JButton solve=new JButton("solve");Buttons1.add(solve);        
		JButton reset=new JButton("reset");Buttons1.add(reset);                  

		state=new JTextArea();state.setRows(5);state.setEditable(false);statescroll=new JScrollPane(state);  

		Clear_Log=new JButton("Clear Log");

		conteneur1=new JPanel();//Conteneur du bouton Clear_Log
		conteneur1.setLayout(new BoxLayout(conteneur1, BoxLayout.Y_AXIS));
		conteneur1.add(Clear_Log);

		conteneur2=new JPanel();//Conteneur du bouton Clear_Log et versionscroll suivant la ligne
		conteneur2.setLayout(new BoxLayout(conteneur2, BoxLayout.X_AXIS));
		conteneur2.add(conteneur1);

		MainPanel=new JPanel();
		MainPanel.setLayout(new BoxLayout(MainPanel, BoxLayout.Y_AXIS));repaint();
		MainPanel.add(grid);repaint();
		MainPanel.add(Buttons1);repaint();
		MainPanel.add(statescroll);repaint();
		MainPanel.add(conteneur2);repaint(); 
		in.add(MainPanel);   
		MainPanel.repaint();

		solve.addActionListener(
				new java.awt.event.ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try{
							solve();}
						catch(Exception f){
							setStatus("This sudoku has no solution!");
						}
					}
				}
				);

		reset.addActionListener(
				new java.awt.event.ActionListener() {
					public void actionPerformed(ActionEvent e) {
						resetpuzzle();  			         
					}
				}
				);

		play.addActionListener(
				new java.awt.event.ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(Interface.cell_number==16 || Interface.cell_number==4){
							setStatus("Function still unavailable, try playing 9x9 sudoku instead, thanks");return;
						}
						setStatus("It's an easy-level sudoku, have fun!");
						play();		         
					}
				}
				);

		Clear_Log.addActionListener(
				new java.awt.event.ActionListener() {
					public void actionPerformed(ActionEvent e) {
						state.setText("");		         
					}
				}
				);
	}


	private void setStatus(String string){
		state.append(string+"\n");
	}

	public  int[][] getsud(){
		int sudoku[][];
		sudoku=new int[Interface.cell_number][Interface.cell_number];
		for(int i=0;i<Interface.cell_number;i++)
			for(int j=0;j<Interface.cell_number;j++){
				String s=cells[i][j].getText().trim();
				char c;
				if(!s.equals("")) c=s.charAt(0);
				else 
					c=' ';
				if(Character.isDigit(c))  sudoku[i][j]=Character.digit(c,10);               
				else sudoku[i][j]=0;               
			}	      
		return sudoku;
	}

	public void solve(){
		if(BoardServer.resolvable(getsud())==false){
			setStatus("Sudoku without solution ici");
			return;
		}
		int t[][]=getsud();
		int answer[][]=new Sudoku(t).resoudre();
		for(int i=0;i<Interface.cell_number;i++)	    
			for(int j=0;j<Interface.cell_number;j++)	         
				cells[i][j].setText(""+answer[i][j]);           	     
	}

	public void resetpuzzle(){
		for(int i=0;i<Interface.cell_number;i++)
		{
			for(int j=0;j<Interface.cell_number;j++)    
			{
				cells[i][j].setText("");   
				cells[i][j].setEditable(true);	
			}
		}
	}

	public void play(){
		int sudoku[][]=High_Beginner.getcode4();resetpuzzle();
		for(int i=0;i<Interface.cell_number;i++){
			for(int j=0;j<Interface.cell_number;j++){
				if(sudoku[i][j]==0)cells[i][j].setText(""); 
				else{ 
					cells[i][j].setText(""+sudoku[i][j]);  
				    cells[i][j].setEditable(false);
				}
			}
		}
	}
}
