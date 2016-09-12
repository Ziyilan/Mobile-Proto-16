public abstract class Animal{
	
	protected int legs;
	protected String name;
	protected String color;
	protected String species;
	protected double weight;
	
	public Animal(int legs, String name, String color, String species, int weight){
		this.legs = legs;
		this.name = name;
		this.color = color;
		this.species = species;
		this.weight = weight;
	}
	
	public int getLegs(){
		return legs;
	}
	
	public void setLeg(int legs){
		this.legs = legs;
	}
	
	public String getName(){
		return name;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public String getColor(){
		return color;
	}
	
	public void setColor(String color){
		this.color = color;
	}
	
	public String getSpecies(){
		return species;
	}
	
	public void setSpecies(String species){
		this.species = species;
	}
	
	public double getWeight(){
		return weight;
	}
	
	public void setWeight(int weight){
		this.weight = weight;
	}
	
	public abstract void grow();
	public abstract boolean isCat();
	
}
