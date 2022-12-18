package codegen;

import java.util.ArrayList;
import resolution.Indice;

public class High_Beginner {
	public static int[][] getLevel1()
	{
		int t[][]=Resolved.getsudoku();
		ArrayList<Indice> al=new ArrayList<Indice>();
		for(int i=0;i<t.length;i++)
			for(int j=0;j<t.length;j++)
				al.add(new Indice(i,j));
		for(int i=0,sizeInit=al.size();i<sizeInit;i++){
			int idx=(int)(Math.random()*al.size());
			Indice ind=al.get(idx);			
			if(Eliminate.une_case_reste(ind,t))
				t[ind.getI()][ind.getJ()]=0;	
			al.remove(idx);
		}
		return t;
	}
	public static int[][] getLevel2()
	{
		int t[][]=getLevel1();
		ArrayList<Indice> al=new ArrayList<Indice>();
		for(int i=0;i<t.length;i++)
			for(int j=0;j<t.length;j++) 			
				if (t[i][j]!=0)al.add(new Indice(i, j));							
		for(int i=0,sizeInit=al.size();i<sizeInit;i++){
			int idx=(int)(Math.random()*al.size());
			Indice ind=al.get(idx);		
			if(Eliminate.contrainte_de_placement(ind,t)||GenerateGrid.contrainte_distante(ind, t))
				t[ind.getI()][ind.getJ()]=0;	
			al.remove(idx);
		}
		return t;
	}
	public static int[][] getLevel3()
	{
		int t[][]=getLevel2();
		ArrayList<Indice> al=new ArrayList<Indice>();
		for(int i=0;i<t.length;i++)
			for(int j=0;j<t.length;j++) 			
				if (t[i][j]!=0)al.add(new Indice(i, j));							
		for(int i=0,sizeInit=al.size();i<sizeInit;i++){
			int idx=(int)(Math.random()*al.size());
			Indice ind=al.get(idx);		
			if(Eliminate.contraintes_distantes(ind,t)||GenerateGrid.contrainte_distante(ind, t))
				t[ind.getI()][ind.getJ()]=0;	
			al.remove(idx);
		}
		return t;
	}
	public static int[][]getcode4()
	{
		int t[][]=getLevel3();
		ArrayList<Indice> al=new ArrayList<Indice>();
		for(int i=0;i<t.length;i++)
			for(int j=0;j<t.length;j++) 			
				if (t[i][j]!=0)al.add(new Indice(i, j));							
		for(int i=0,sizeInit=al.size();i<sizeInit;i++){
			int idx=(int)(Math.random()*al.size());
			Indice ind=al.get(idx);		
			if(GenerateGrid.contrainte_distante(ind, t))
				t[ind.getI()][ind.getJ()]=0;	
			al.remove(idx);
		}
		return t;
	}
}
