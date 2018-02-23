import javax.print.attribute.standard.NumberOfInterveningJobs;
import javax.script.ScriptEngineManager;
import javax.script.ScriptEngine;
import javax.script.ScriptException;
import javax.script.SimpleBindings;

import java.util.Random;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;

public class GACompents {
	
	 String objFunction;
	 int numberIndiv;
	 int numberOfGenes;
	 double[][] chromosomes;
	
	
	
	public GACompents(){
		String objFunction = "15*x -x.*x";
		int numberIndiv = 3;
		int numberOfGenes = 3;
		chromosomes = new double[numberIndiv][numberOfGenes];
	}
	
	public void setObjFun(String objFun){
		objFunction = objFun;
	}
	
	public  void setNumberIndiv(int num){
		numberIndiv = num;
	}
	
	public  void setNumberGenes(int num){
		numberOfGenes = num;
	}
	
	public  void generatePopulation(String[][] chromosomes){
		Random rand = new Random();
		for(int i = 0; i < numberIndiv; i++){
			for(int j = 0; j < numberOfGenes; j++){
				chromosomes[i][j] = Integer.toString((int)Math.round(rand.nextDouble()));
			}
		}
	
	}
	

	public String[] decodeChrom(String[][] chromosomes){
		//Decimal values end up here
		String[] decodedValues = new String[numberIndiv];
		for(int i = 0; i < numberIndiv; i++){
			StringBuilder temp = new StringBuilder();
			for(int j = 0; j < numberOfGenes; j++){
				temp.append(chromosomes[i][j]);
			}
			String x = Integer.toString(Integer.parseInt(temp.toString(),2));
			decodedValues[i] = x;
		}
		
		return decodedValues;
	}
	/*
	 * 
	 * returns decimal version of fitness
	 */
	public double[] checkPopulationFitness(String[] chromosomes) throws ScriptException{
		//returns highest value and mean?
		
		double[] fitnesses = new double[numberIndiv];
		
		//Decimal values
		//String[] arrayToCheck = decodeChrom(chromosomes);
		
		for(int i = 0; i < chromosomes.length; i++)
		{
			fitnesses[i] = getFitness(chromosomes[i]);
		}
		
		for(int i = 0; i < fitnesses.length; i++){
			System.out.println(fitnesses[i]);
		}
		return fitnesses;
	}
	public double getMostFit(String[] chromosomes){
		double fittest = Integer.MIN_VALUE;
		for(int i = 0; i < chromosomes.length; i++){
			if(Double.parseDouble(chromosomes[i]) > fittest)
				fittest = Double.parseDouble(chromosomes[i]);
		}
		return fittest;
	}
	//returns the actual fitness value of the most fit value
	public double getMostFitFitness(double[] chromosomes){
		double fittest = -1;
		for(int i = 0; i < chromosomes.length; i++){
			if((chromosomes[i]) > fittest)
				fittest = ((chromosomes[i]));
		}
		return fittest;
	}
	
	public double[] getLeastFit(String[] chromosomesDecimal){
		double[] fitPair = new double[2];
		double fittestDecimal = Integer.MAX_VALUE;

		for(int i = 0; i < chromosomesDecimal.length; i++){
			if(Double.parseDouble(chromosomesDecimal[i]) < fittestDecimal){
				fitPair[0] = i;
				fitPair[1] = Double.parseDouble(chromosomesDecimal[i]);	
			}
		}
		return fitPair;
		//returns array with binary index location, then decimal version 
	}
	
	public int getFitness(String variable) throws ScriptException{
		ScriptEngineManager mgr = new ScriptEngineManager();
		ScriptEngine engine = mgr.getEngineByName("JavaScript");
		Map<String, Object> values = new HashMap<String, Object>();
		String[] objFunArr = objFunction.split("");
		StringBuilder newObjFun = new StringBuilder();
		
		for(int i = 0; i < objFunArr.length; i++){
			if(objFunArr[i].equals("x"))
				objFunArr[i] = variable;
		}
		
		for(int i = 0; i < objFunArr.length; i++)
			newObjFun.append(objFunArr[i]);
		
		//System.out.println(engine.eval(newObjFun.toString()));
		
		return (int)(engine.eval(newObjFun.toString()));
		
		
	}
	
	public String[][] removeLeastFit(String[][] binaryRep, double[] locations){
		//Turns double into string, then string into int for index look ups, re write?
		double indexToRemove =  locations[1];
		
		//removing binary version
		String[][] newBinaryArray = new String[numberIndiv-1][numberOfGenes];
		for(int i = 0; i < numberIndiv - 1; i++){
			if(i == indexToRemove)
				continue;
			for(int j = 0; j < numberOfGenes; j++){
				newBinaryArray[i][j] = binaryRep[i][j];
			}
		}
		return newBinaryArray;
	}
	
	public boolean getDifference(double val1, double val2){
		
		 if(Math.abs(val1 - val2) < 3)
			 return true;
		 else
			 return false;
		
	}
	public boolean mainLoopCheck(ArrayList<Double> list){
		boolean result = true;
		if(list.size() == 1)
			return true;
		for(int i = 0; i < list.size() - 1; i++){
			if(!getDifference(list.get(i), list.get(i+1))){
				return false;
			}
		}
		
		return result;
		
	}
	
	public void printArray(String[][] chrom){
		for(int i = 0; i < chrom.length; i++){
			for(int j = 0; j < numberOfGenes; j++){
				System.out.print(chrom[i][j]);
			}
			System.out.println("");
		}
		
	}
}	
