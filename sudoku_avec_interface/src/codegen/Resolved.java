package codegen;

import java.util.ArrayList;
import resolution.*;
import view.Interface;

public class Resolved {
	public static boolean pas_de_valeur_possible(int i,int j,int t[][])
	{
		for(int k=1;k<Interface.cell_number+1;k++){
			Indice ind=new Indice(i,j);
			if(Sudoku.verify(ind,k,t)==true)return false;
		}return true;
	}
	public static int[][]getsudoku()
	{
		int a_placer;boolean placable,valeur_possible;
		int tab[][]=new int[Interface.cell_number][Interface.cell_number];
		ArrayList<Indice> lis=new ArrayList<Indice>();
		for(int i=0;i<tab.length;i++)
			for(int j=0;j<tab.length;j++)
				if(tab[i][j]==0)lis.add(new Indice(i,j));
		for(int i=0;i<lis.size();i++){
			a_placer=(int)(1+Math.random()*Interface.cell_number);
			placable=Sudoku.verify(lis.get(i),a_placer,tab);	
			if(placable==true)		        	
				tab[lis.get(i).getI()][lis.get(i).getJ()]=a_placer;	
			if (placable==false) {
				valeur_possible=pas_de_valeur_possible(lis.get(i).getI(),lis.get(i).getJ(),tab);
				if (valeur_possible == false) {
					while (placable == false) {
						a_placer = (int) (1 + Math.random()* Interface.cell_number);
						placable = Sudoku.verify(lis.get(i), a_placer,tab);
					}
					tab[lis.get(i).getI()][lis.get(i).getJ()] = a_placer;
				}
				if (valeur_possible == true) {
					int tab2[] = Sudoku.gettab(lis, i - 1, tab);
					i = tab2[0];
					tab[lis.get(i).getI()][lis.get(i).getJ()] = tab2[1];
				}
			} 	
		}
		return tab;
	}
}
