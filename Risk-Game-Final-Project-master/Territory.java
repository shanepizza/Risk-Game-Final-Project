
class Territory extends Object{
	String ownership;
	int unitCount;
	String name;

//Constructor
	Territory(String control, int count, String assignName){
		ownership = control;
		unitCount = count;
		name = assignName;
	}

//Setters and getters
	public String getOwnership(){
		return ownership;
	}
	public int getUnitCount(){
		return unitCount;
	}
	public String getName(){
		return name;
	}
	void setOwnership(String o1){
		ownership = o1;
	}
	public void setUnitCount(int o1){
		unitCount = o1;
	}
	public void setName(String o1){
		name = o1;
	}
	
	
	
}//End Class