
import java.io.*;
import java.util.*;


public class GA extends GACompents{

	public static void runGA(){
		Random ran = new Random();
		GACompents ga = new GACompents();
		
		ga.setNumberIndiv(4);
		ga.setNumberGenes(4);
		int x = ga.numberIndiv;
		int y = ga.numberOfGenes;
		
		String[][] chrom = new String[x][y];
		ga.generatePopulation(chrom);
		String[] a = ga.decodeChrom(chrom);
		System.out.println(x);
		
		
		for(int i = 0; i < x; i++){
			for(int j = 0; j < ga.numberOfGenes; j++){
				System.out.print(chrom[i][j]);
			}
			System.out.println();
		}
		System.out.println("---------");
		
		
		for(int i = 0; i < a.length; i++){
		System.out.println(a[i]);
		}
		
		
		
	}
	public static void main(String[] args){
		
		runGA();
	}
}

