import javax.print.attribute.standard.NumberOfInterveningJobs;
import java.util.Random;

public class GACompents {
	
	 int objFunction;
	 int numberIndiv;
	 int numberOfGenes;
	 double[][] chromosomes;
	
	
	
	public GACompents(){
		int objFunction;
		int numberIndiv = 3;
		int numberOfGenes = 3;
		chromosomes = new double[numberIndiv][numberOfGenes];
	}
	
	public void setObjFun(int objFun){
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

}	
