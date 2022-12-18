package codegen;

import java.util.ArrayList;
import resolution.*;
import view.Interface;

public class Eliminate {
	//une case qui reste dans une ligne ou colonne ou case
	public static boolean  une_case_reste(Indice ind,int t[][]){
		int tab[][]=BoardServer.clone(t);
		int res1=0;int res2=0;int res3=0;
		tab[ind.getI()][ind.getJ()]=0;//initialise la case a tester  0

		//balayage de la colonne
		for(int f=0;f<Interface.cell_number;f++)
			if((tab[ind.getI()][f]==0))res1=res1+1;
		if (res1==1)return true;//seule la case a tester est vide dans les 9 colonnes

		//balayage de la ligne
		for(int f=0;f<Interface.cell_number;f++)
			if((tab[f][ind.getJ()]==0))res2=res2+1;
		if (res2==1)return true;//seule la case a tester est vide dans les 9 lignes

		int reste1=ind.getI()%((int)Math.sqrt(Interface.cell_number));
		int ligne_min=ind.getI()-reste1;int ligne_max=ligne_min+(int)Math.sqrt(Interface.cell_number)-1;
		int reste2=ind.getJ()%((int)Math.sqrt(Interface.cell_number));
		int colonne_min=ind.getJ()-reste2;int colonne_max=colonne_min+(int)Math.sqrt(Interface.cell_number)-1;
		for(int w=ligne_min;w<=ligne_max;w++)
			for(int y=colonne_min;y<=colonne_max;y++)  
				if (tab[w][y] == 0)
					res3=res3+1;			
		if (res3==1) return true; //seule la case a tester est vide dans la region

		return false;
	}

	//tester si tous les 8 chiffres sont dans le champ de l'indice, l'autre doit etre ds la case a tester
	public static boolean contrainte_de_placement(Indice ind, int[][] tab) {
		int res=0;boolean res2=false;
		int t[][]=BoardServer.clone(tab);
		ArrayList<Indice> champ_de_ind=new ArrayList<Indice>();

		for(int f=0;f<Interface.cell_number;f++)//column changes
			if(f!=ind.getJ())
				champ_de_ind.add(new Indice(ind.getI(),f));

		for(int f=0;f<Interface.cell_number;f++)//column changes
			if(f!=ind.getI())
				champ_de_ind.add(new Indice(f,ind.getJ()));

		int reste1=ind.getI()%((int)Math.sqrt(Interface.cell_number));
		int ligne_min=ind.getI()-reste1;int ligne_max=ligne_min+(int)Math.sqrt(Interface.cell_number)-1;
		int reste2=ind.getJ()%((int)Math.sqrt(Interface.cell_number));
		int colonne_min=ind.getJ()-reste2;int colonne_max=colonne_min+(int)Math.sqrt(Interface.cell_number)-1;
		for(int w=ligne_min;w<=ligne_max;w++)		
			for(int y=colonne_min;y<=colonne_max;y++)
				if (w!=ind.getI()&&y!=ind.getJ())champ_de_ind.add(new Indice(w,y));	
		for(int i=1;i<10;i++)		
			for(int j=0;j<champ_de_ind.size();j++)			
				if (t[champ_de_ind.get(j).getI()][champ_de_ind.get(j).getJ()]==i)
				{
					res=res+1;break;
				}	

		if (res==Interface.cell_number-1)res2=true;
		return res2;
	}

	public static boolean contraintes_distantes(Indice ind, int[][] t) {
		//on ne peut pas mettre un chiffre dans tous les cases vides 
		int tab[][]=BoardServer.clone(t);
		tab[ind.getI()][ind.getJ()]=123;
		boolean res1=true;
		boolean res2=true;
		boolean res3=true;
		boolean res=false;

		//cases vides de la colonne 
		for(int f=0;f<Interface.cell_number;f++)						
			if (tab[ind.getI()][f]==0)	
				if(Sudoku.verify(new Indice(ind.getI(),f),t[ind.getI()][ind.getJ()],tab)
						&&GenerateGrid.contrainte_distante(new Indice(ind.getI(),f), tab)==false){
					res1=false;break;
				}	

		//cases vides de la ligne
		for(int f=0;f<Interface.cell_number;f++)
			if (tab[f][ind.getJ()]==0)
				if(Sudoku.verify(new Indice(f,ind.getJ()),t[ind.getI()][ind.getJ()],tab)
						&&GenerateGrid.contrainte_distante(new Indice(f,ind.getJ()), tab)==false){
					res2=false;break;
				}	

		//cases vides de la region		
		int reste1=ind.getI()%((int)Math.sqrt(Interface.cell_number));
		int ligne_min=ind.getI()-reste1;int ligne_max=ligne_min+(int)Math.sqrt(Interface.cell_number)-1;
		int reste2=ind.getJ()%((int)Math.sqrt(Interface.cell_number));
		int colonne_min=ind.getJ()-reste2;int colonne_max=colonne_min+(int)Math.sqrt(Interface.cell_number)-1;
		for(int w=ligne_min;w<=ligne_max;w++)
			for(int y=colonne_min;y<=colonne_max;y++)  			  
				if (tab[w][y] == 0)
					if (Sudoku.verify(new Indice(w,y),t[ind.getI()][ind.getJ()],tab)
							&&GenerateGrid.contrainte_distante(new Indice(w,y), tab)==false)
						res3 = false;							
		if (res1||res2||res3) res=true;	   
		return res;
	}
}
