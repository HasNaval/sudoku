package codegen;

import java.util.ArrayList;
import resolution.*;
import view.*;

public class GenerateGrid {
	public static boolean contrainte_distante(Indice ind,int t[][])
	{
		//un ne chiffre peut etre place dans cette case si il doit etre dans la ligne ou colonne(autre region que lui) a lakl l apartient   
		int tab[][]=BoardServer.clone(t);
		tab[ind.getI()][ind.getJ()]=26;
		ArrayList<Indice> champ_de_ind=new ArrayList<Indice>();
		int reste1=ind.getI()%((int)Math.sqrt(Interface.cell_number));
		int ligne_min=ind.getI()-reste1;int ligne_max=ligne_min+(int)Math.sqrt(Interface.cell_number)-1;
		int reste2=ind.getJ()%((int)Math.sqrt(Interface.cell_number));
		int colonne_min=ind.getJ()-reste2;int colonne_max=colonne_min+(int)Math.sqrt(Interface.cell_number)-1;
		for(int w=ligne_min;w<=ligne_max;w++)		
			for(int y=colonne_min;y<=colonne_max;y++)
				if (tab[w][y]==0)champ_de_ind.add(new Indice(w,y));	
		boolean a_voir=true;
		boolean a_voir1=true;
		boolean a_voir2=true;
		for(int i=0;i<champ_de_ind.size();i++)
		{
			if(champ_de_ind.get(i).getI()==ind.getI()&&champ_de_ind.get(i).getJ()!=ind.getJ()){	  
				a_voir=tester_colonne(champ_de_ind.get(i), tab, ind,t);
				if(a_voir)return false;
			}
			if(champ_de_ind.get(i).getJ()==ind.getJ()&&champ_de_ind.get(i).getI()!=ind.getI()){			  
				a_voir=tester_ligne(champ_de_ind.get(i), tab, ind,t);
				if(a_voir)return false;
			}
			if(champ_de_ind.get(i).getI()!=ind.getI()&&champ_de_ind.get(i).getJ()!=ind.getJ()){			 
				a_voir1=tester_colonne(champ_de_ind.get(i), tab, ind,t);
				a_voir2=tester_ligne(champ_de_ind.get(i), tab, ind,t);
				if(a_voir1&&a_voir2){ return false;}
			}
		}
		return true;
	}

	public static boolean tester_ligne(Indice indice, int[][] tab,Indice ind2,int b[][]) {
		// TODO Auto-generated method stub
		int reste1=indice.getI()%((int)Math.sqrt(Interface.cell_number));
		int ligne_min=indice.getI()-reste1;int ligne_max=ligne_min+(int)Math.sqrt(Interface.cell_number)-1;
		boolean res1=false,res2=false;
		if(indice.getJ()<3){
			for(int w=ligne_min;w<=ligne_max;w++)		
				for(int y=3;y<=5;y++)
					if(w!=indice.getI()){
						if(tab[w][y]==0){
							if(Sudoku.verify(new Indice(w,y),b[ind2.getI()][ind2.getJ()], tab)==true){
								res1=true;break;
							}
						}
						if(tab[w][y]==b[ind2.getI()][ind2.getJ()])res1=true;
					}
			for(int w=ligne_min;w<=ligne_max;w++)		
				for(int y=6;y<=8;y++)
					if(w!=indice.getI()){
						if(tab[w][y]==0){
							if(Sudoku.verify(new Indice(w,y),b[ind2.getI()][ind2.getJ()], tab)==true){
								res2=true;break;
							}
						}
						if(tab[w][y]==b[ind2.getI()][ind2.getJ()])res2=true;
					}
		}
		if(indice.getJ()>=3&&indice.getJ()<=5){
			for(int w=ligne_min;w<=ligne_max;w++)		
				for(int y=0;y<=2;y++)
					if(w!=indice.getI()){
						if(tab[w][y]==0){
							if(Sudoku.verify(new Indice(w,y),b[ind2.getI()][ind2.getJ()], tab)==true){
								res1=true;break;
							}  
						} 
						if(tab[w][y]==b[ind2.getI()][ind2.getJ()])res1=true;
					}
			for(int w=ligne_min;w<=ligne_max;w++)		
				for(int y=6;y<=8;y++)
					if(w!=indice.getI()){
						if(tab[w][y]==0){
							if(Sudoku.verify(new Indice(w,y),b[ind2.getI()][ind2.getJ()], tab)==true){
								res2=true;break;
							}
						}
						if(tab[w][y]==b[ind2.getI()][ind2.getJ()])res2=true;
					}
		}
		if(indice.getJ()>=6&&indice.getJ()<=8){
			for(int w=ligne_min;w<=ligne_max;w++)		
				for(int y=0;y<=2;y++)
					if(w!=indice.getI()){
						if(tab[w][y]==0){
							if(Sudoku.verify(new Indice(w,y),b[ind2.getI()][ind2.getJ()], tab)==true){
								res1=true;break;
							}
						}
						if(tab[w][y]==b[ind2.getI()][ind2.getJ()])res1=true;
					}
			for(int w=ligne_min;w<=ligne_max;w++)		
				for(int y=3;y<=5;y++)
					if(w!=indice.getI()){
						if(tab[w][y]==0){
							if(Sudoku.verify(new Indice(w,y),b[ind2.getI()][ind2.getJ()], tab)==true){
								res2=true;break;
							}
						}
						if(tab[w][y]==b[ind2.getI()][ind2.getJ()])res2=true;
					}
		}
		if(res1==false||res2==false)
			return false;
		return true;
	}

	public static boolean tester_colonne(Indice indice, int[][] tab,Indice ind2,int b[][]) {
		// TODO Auto-generated method stub
		int reste2=indice.getJ()%((int)Math.sqrt(Interface.cell_number));
		int colonne_min=indice.getJ()-reste2;int colonne_max=colonne_min+(int)Math.sqrt(Interface.cell_number)-1;
		boolean res1=false,res2=false;
		if(indice.getI()<3){
			for(int w=3;w<=5;w++)		
				for(int y=colonne_min;y<=colonne_max;y++)
					if(y!=indice.getJ()){
						if(tab[w][y]==0){
							if(Sudoku.verify(new Indice(w,y),b[ind2.getI()][ind2.getJ()], tab)==true){
								res1=true;break;
							}
						}
						if(tab[w][y]==b[ind2.getI()][ind2.getJ()])res1=true;
					}
			for(int w=6;w<=8;w++)		
				for(int y=colonne_min;y<=colonne_max;y++)
					if(y!=indice.getJ()){
						if(tab[w][y]==0){
							if(Sudoku.verify(new Indice(w,y),b[ind2.getI()][ind2.getJ()], tab)==true){
								res2=true;break;
							}
						}
						if(tab[w][y]==b[ind2.getI()][ind2.getJ()])res2=true;
					}
		}
		if(indice.getI()>=3&&indice.getI()<=5){
			for(int w=0;w<=2;w++)		
				for(int y=colonne_min;y<=colonne_max;y++)
					if(y!=indice.getJ()){
						if(tab[w][y]==0)
						{
							if(Sudoku.verify(new Indice(w,y),b[ind2.getI()][ind2.getJ()], tab)==true){
								res1=true;break;
							}
						}
						if(tab[w][y]==b[ind2.getI()][ind2.getJ()])res1=true;
					}
			for(int w=6;w<=8;w++)		
				for(int y=colonne_min;y<=colonne_max;y++)
					if(y!=indice.getJ()){
						if(tab[w][y]==0){
							if(Sudoku.verify(new Indice(w,y),b[ind2.getI()][ind2.getJ()], tab)==true){
								res2=true;break;
							}
						}
						if(tab[w][y]==b[ind2.getI()][ind2.getJ()])res2=true;
					}
		}
		if(indice.getI()>=6&&indice.getI()<=8){
			for(int w=0;w<=2;w++)		
				for(int y=colonne_min;y<=colonne_max;y++)
					if(y!=indice.getJ()){
						if(tab[w][y]==0){
							if(Sudoku.verify(new Indice(w,y),b[ind2.getI()][ind2.getJ()], tab)==true){
								res1=true;break;
							}
						}
						if(tab[w][y]==b[ind2.getI()][ind2.getJ()])res1=true;
					}
			for(int w=3;w<=5;w++)		
				for(int y=colonne_min;y<=colonne_max;y++)
					if(y!=indice.getJ()){
						if(tab[w][y]==0){
							if(Sudoku.verify(new Indice(w,y),b[ind2.getI()][ind2.getJ()], tab)==true){
								res2=true;break;
							}
						}
						if(tab[w][y]==b[ind2.getI()][ind2.getJ()])res2=true;
					}
		}
		if(res1==false||res2==false)
			return false;
		return true;
	}
}
