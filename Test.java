class Untitled {
	public static void main(String[] args) {
		Territory space1 = new Territory("Me", 5, "space1");
		Territory space2 = new Territory("Them", 10, "space2");
		
		Risk.attackPlace(space1, space2);
		
	}
}