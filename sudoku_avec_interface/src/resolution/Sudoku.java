package resolution;
import java.util.ArrayList;

import view.Interface;

public class Sudoku{
	private int tableau[][]=new int [Interface.cell_number][Interface.cell_number];

	public Sudoku(int tab[][]){tableau=tab;}

	//valeur[] contient si on va resoudre le Sudoku par de valeur 1 a 9 ou de 9 a 1
	public int [][]  resoudre(){
		int i,j;int t[][]=BoardServer.clone(tableau);
		ArrayList<Indice> l=new ArrayList<Indice>();
		for(int a=0;a<t.length;a++)
			for(int b=0;b<t.length;b++)
				if(t[a][b]==0)l.add(new Indice(a,b));	

			for (i = 0; i < l.size(); i++) {
				for (j = 1; j < Interface.cell_number + 1; j++) {
					if (verify(l.get(i), j, t)) {
						t[l.get(i).getI()][l.get(i).getJ()] = j;break;			
					}
				}
				if (j == Interface.cell_number + 1) {
					int tab2[] = gettab(l, i - 1, t);
					t[l.get(tab2[0]).getI()][l.get(tab2[0]).getJ()] = tab2[1];
					i = tab2[0];
				}
				
			}
			return t;
	}		   

	public static boolean  verify(Indice ind,int value,int tab[][]){

		//si value appartient deja a la ligne ou colonne engendre par ind
		for(int f=0;f<Interface.cell_number;f++)
			if((tab[ind.getI()][f]==value)||(tab[f][ind.getJ()]==value))return false;


		int reste1=ind.getI()%((int)Math.sqrt(Interface.cell_number));
		int ligne_min=ind.getI()-reste1;int ligne_max=ligne_min+(int)Math.sqrt(Interface.cell_number)-1;
		int reste2=ind.getJ()%((int)Math.sqrt(Interface.cell_number));
		int colonne_min=ind.getJ()-reste2;int colonne_max=colonne_min+(int)Math.sqrt(Interface.cell_number)-1;
		for(int w=ligne_min;w<=ligne_max;w++)
		{
			for(int y=colonne_min;y<=colonne_max;y++)
				if(tab[w][y]==value)return false;
		}
		return true;   
	}

	public static int[] gettab( ArrayList<Indice> l,int ligne_de_retour,int t[][]){
		int g;int res[]=new int[2];
		Indice a=l.get(ligne_de_retour);
		if(t[a.getI()][a.getJ()]!=Interface.cell_number){ 
			for(g=t[a.getI()][a.getJ()]+1;g<Interface.cell_number+1;g++)				
				if(verify(a,g,t)){
					res[0]=ligne_de_retour;res[1]=g;return res;
				}				
			t[a.getI()][a.getJ()]=0;res=gettab(l,ligne_de_retour-1,t);
		}
		else{
			t[a.getI()][a.getJ()]=0;res=gettab(l,ligne_de_retour-1,t);
		}
		return res;
	}
}
