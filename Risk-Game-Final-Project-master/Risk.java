import java.util.*;
import javax.xml.transform.*;

class Risk {	
		
			
	public static void attackPlace(Territory o1, Territory o2){
		Territory place1 = o1;
		Territory place2 = o2;
	
		/*
		check to make sure spaces are next to eachother.
		*/
	
	//See how many armies are in each territory and detirmine if the attack can happen.
		if(place1.getOwnership() != place2.getOwnership()){
			while(place1.getUnitCount() > 1 == true){
				int attackDice = 1;
				int defenceDice = 1;
			//attack	
				if(place1.getUnitCount() > 3){
					attackDice = 3;
				}else if(place1.getUnitCount() == 3){
					attackDice = 2;
				}else{
					attackDice = 1;
				}
			//defence
				if (place2.getUnitCount() > 1){
					defenceDice = 2;
				}//end unit count if
				
			//Roll dice and stoor the results in an ArrayList.
				ArrayList<Integer> atkDiceRoll = new ArrayList<Integer>();
				ArrayList<Integer> defDiceRoll = new ArrayList<Integer>();
				atkDiceRoll = rollDice(attackDice);
				defDiceRoll = rollDice(defenceDice);
				
			//kill an army
				Integer atkHigh = maxDie(atkDiceRoll);
				Integer defHigh = maxDie(defDiceRoll);
				if(skirmish(atkHigh, defHigh) == true){		
					place2.setUnitCount(place2.getUnitCount() -1);	
				}else {
					place1.setUnitCount(place1.getUnitCount() -1);
				}//end if else
			
			//repeat if there is another army.
				if(attackDice > 1 && defenceDice > 1){
					atkHigh = maxDie(atkDiceRoll);
					defHigh = maxDie(defDiceRoll);
					if(skirmish(atkHigh, defHigh) == true){
						place2.setUnitCount(place2.getUnitCount() -1);
					}else {
						place1.setUnitCount(place1.getUnitCount() -1);
					}//end if else
				}//end if
			//if place two runs out of troops place1 wins. move units and change ownership.
				if(place2.getUnitCount() == 0){
					place2.setOwnership(place1.getOwnership());
					place2.setUnitCount(place1.getUnitCount() -1);
					place1.setUnitCount(1);
				}//end if for control switch
				
			//checks to see what happened
				System.out.print("Territory is owned by: " + place2.getOwnership() + "\n With " + place2.getUnitCount() + " troops. \n");
				System.out.print("Place1 has " + place1.getUnitCount() + " armies. \n");
				
			//clear dice rolls
				atkDiceRoll.removeAll(atkDiceRoll);
				defDiceRoll.removeAll(defDiceRoll);
				
			}//end while loop for place1 unit count check
		}//end ownership check to see if attack can even happen.
	}//end attack place method
	
	
//maxDie method
	public static int maxDie(ArrayList<Integer> o1){
		int max = o1.get(0);
		for(int i = 0; i < o1.size(); i++){
			if(o1.get(i) > max){
				max = o1.get(i);
				o1.remove(i);
			}//end if
		}//end for
		return max;
	}//end method	
		
		
//single battle method
	public static boolean skirmish(int o1, int o2){
		if(o1 > o2){
			return true;
		}else{
			return false;
		}//end if/else
	}//end method	
		
		
//Rolldice method
	public static ArrayList<Integer> rollDice(int num){
		ArrayList<Integer> result = new ArrayList<Integer>();
		for(int x = 0; x < num; x++){
			double temp = Math.random() * 6 +1;
			Integer temp2 = (int) temp;
			result.add(temp2);
		}//end forloop
		return result;
	}//end method		
}//end Risk Class