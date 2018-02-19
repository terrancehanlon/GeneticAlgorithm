import javax.print.attribute.standard.NumberOfInterveningJobs;
import javax.script.ScriptEngineManager;
import javax.script.ScriptEngine;
import javax.script.ScriptException;
import javax.script.SimpleBindings;

import java.util.Random;
import java.util.HashMap;
import java.util.Map;

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

	public void checkPopulationFitness(String[][] chromosomes){
		//returns highest value and mean?
		
		int[] fitnesses = new int[numberIndiv];
		
		//Decimal values
		String[] arrayToCheck = decodeChrom(chromosomes);
		
		for(int i = 0; i < arrayToCheck.length; i++)
		{
			fitnesses[i] = getFitness(arrayToCheck[i]);
		}
		
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
		
		return (Integer)engine.eval(newObjFun.toString());
		
		
	}
}	
