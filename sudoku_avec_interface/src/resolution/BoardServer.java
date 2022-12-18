package resolution;

import java.util.ArrayList;

import view.Interface;

public class BoardServer 
{

	//obtenir le nombre de cases rempli dans un sudoku
	public static int getnbr(int t[][]){
		int res=0;
		for(int i=0;i<9;i++)	    
			for(int j=0;j<9;j++)		    
				if(t[i][j]!=0)res=res+1;
		return res;
	}

	//cloner un tableau
	public static int[][]clone(int t[][]){
		int res[][]=new int[t.length][t[0].length];
		for(int i=0;i<res.length;i++)	    
			for(int j=0;j<res[0].length;j++)
				res[i][j]=t[i][j]; 
		return res;
	}

	public static void afficher(int t[][]){
		for(int i=0;i<t.length;i++)
		{
			for(int j=0;j<t.length;j++){
				if(j==2||j==5)
					System.out.print(t[i][j]+"      ,");
				else System.out.print(t[i][j]+" ,");
			}
			if(i==2||i==5)
			{
				System.out.println();System.out.println();}
			else  System.out.println();
		}
	}

	//verifier d'avance que si il y a deja deux valeurs identiques dans une ligne ou colonne ou case
	// le sudoku obtenu a partir de ce tableau n'a pas de solution
	public static boolean resolvable(int board[][])
	{
		ArrayList<Indice> l=new ArrayList<Indice>();
		int board1[][]=clone(board);
		int board2[][]=clone(board);
		for(int a=0;a<board[0].length;a++)
			for(int b=0;b<board[0].length;b++)
				if(board[a][b]!=0)l.add(new Indice(a,b));
		for(int i=0;i<l.size();i++)
		{
			if( board1[l.get(i).getI()][l.get(i).getJ()]>Interface.cell_number)
				return false;
		}
		for(int i=0;i<l.size();i++)
		{
			board1[l.get(i).getI()][l.get(i).getJ()]=0;
			if(Sudoku.verify(l.get(i),board2[l.get(i).getI()][l.get(i).getJ()],board1)==false)
				return false;
		}
		return true;
	}
}		   
