
import java.io.*;
import java.util.*;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.util.ArrayList;
//import java.util.List;
//import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import javax.script.ScriptException;


public class GA extends GACompents{

	public static void runGA() throws ScriptException{
		Random ran = new Random();
		GACompents ga = new GACompents();
		
		//basic initialization
		int numberOfGenerations = 0;
		ArrayList<Double> listOfHighestFitnesses = new ArrayList<Double>();
		
		
		ga.setNumberIndiv(10);
		ga.setNumberGenes(3);
		ga.setObjFun("15*x -x*x");
		int x = ga.numberIndiv;
		int y = ga.numberOfGenes;
		
		String[][] chrom = new String[x][y];
		ga.generatePopulation(chrom);
		String[] decimalVersions = ga.decodeChrom(chrom);
		System.out.println("Population size : " + x);
		System.out.println("Gene Size per chromosome: " + y);
		
		
		
		System.out.println("Binary representations of initial chromosomes : ");
		for(int i = 0; i < x; i++){
			for(int j = 0; j < ga.numberOfGenes; j++){
				System.out.print(chrom[i][j]);
			}
			System.out.println();
		}
		System.out.println("---------");
		System.out.println("Decimal representations of initial chromosomes : ");
		
		for(int i = 0; i < decimalVersions.length; i++){
		System.out.println(decimalVersions[i]);
		}
		
		
		System.out.println("---------");
		System.out.println("Fitness levels: ");
		double[] initialFitnessLevels = ga.checkPopulationFitness(decimalVersions);
		
		System.out.println("---------");
		System.out.println("Most fit: ");
		double mostFit = ga.getMostFit(decimalVersions);
		listOfHighestFitnesses.add(mostFit);
		System.out.print(mostFit + " ,");
		double fitnessOfMostFit = ga.getMostFitFitness(initialFitnessLevels);
		System.out.println(" " + fitnessOfMostFit);
		
		
		//Main GA loop
		while(ga.mainLoopCheck(listOfHighestFitnesses)){
			
		}

		
	}
	
	public static void main(String[] args) throws ScriptException{
		System.out.println("Maximize this function");
		runGA();
	}
}

