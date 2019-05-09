import java.util.*;
import javax.xml.transform.*;

class Risk {
	
	Territory space1 = new Territory("Me", 3, "Place 1");
	Territory space2 = new Territory("Not Me", 1, "Place 2");
	Territory space3 = new Territory("Me", 1, "Place 3");
	Territory space4 = new Territory("Me", 1, "Place 4");
	Territory space5 = new Territory("Me", 1, "Place 5");
	
	Territory[] vertices = {space1, space2, space3, space4, space5};
	int[][] edges = {{ 0, 1 }, {0, 3}, {0, 4},
				{1, 2}, {1, 3},{2, 3}};
				
						
	public void attackPlace(Territory o1, Territory o2){
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
				if(place1.getUnitCount() > 4){
					attackDice = 3;
				}else if(place1.getUnitCount() == 3){
					attackDice = 2;
				}else{
					attackDice = 1;
				}
			//defence
				if (place2.getUnitCount() > 1){
					defenceDice = 2;
				}//end unit if
				
			//Roll dice and stoor them in an ArrayList.
				ArrayList<Integer> atkDiceRoll = new ArrayList<Integer>();
				ArrayList<Integer> defDiceRoll = new ArrayList<Integer>();
				atkDiceRoll = rollDice(attackDice);
				defDiceRoll = rollDice(defenceDice);
				System.out.print(atkDiceRoll);
				System.out.print(defDiceRoll);
			
			//get highest die
				Integer atkHigh = maxDie(atkDiceRoll);
				Integer defHigh = maxDie(defDiceRoll);
			
			//kill an army
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
				}
				
			//clear dice rolls
				atkDiceRoll.removeAll(atkDiceRoll);
				defDiceRoll.removeAll(defDiceRoll);
				
			}//end while loop
		}//end ownership check
	}//end attack place method
	
	
	//maxDie method
		public int maxDie(ArrayList<Integer> o1){
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
		public boolean skirmish(int o1, int o2){
			if(o1 > o2){
				return true;
			}else{
				return false;
			}//end if/else
		}//end method	
		
		
	//Rolldice method
		public ArrayList<Integer> rollDice(int num){
			ArrayList<Integer> result = new ArrayList<Integer>();
			for(int x = 0; x < num; x++){
				result.add((int) Math.random() * 6 +1);
			}//end forloop
			return result;
		}//end method
				
}