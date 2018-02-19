
import java.io.*;
import java.util.*;

import javax.script.ScriptException;


public class GA extends GACompents{

	public static void runGA() throws ScriptException{
		Random ran = new Random();
		GACompents ga = new GACompents();
		
		ga.setNumberIndiv(4);
		ga.setNumberGenes(4);
		ga.setObjFun("15*x -x*x");
		int x = ga.numberIndiv;
		int y = ga.numberOfGenes;
		
		String[][] chrom = new String[x][y];
		ga.generatePopulation(chrom);
		String[] a = ga.decodeChrom(chrom);
		System.out.println("Population size : " + x);
		System.out.println("Gene Size per chromosome: " + y);
		
		
		
		System.out.println("Binary representations of chromosomes : ");
		for(int i = 0; i < x; i++){
			for(int j = 0; j < ga.numberOfGenes; j++){
				System.out.print(chrom[i][j]);
			}
			System.out.println();
		}
		System.out.println("---------");
		System.out.println("Decimal representations of chromosomes : ");
		
		for(int i = 0; i < a.length; i++){
		System.out.println(a[i]);
		}
		
		ga.checkFitness("3");
		
		
	}
	public static void main(String[] args) throws ScriptException{
		
		runGA();
	}
}

