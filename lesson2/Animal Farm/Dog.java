import java.util.*;

public class Dog extends Animal{
	
	public Dog(String name, String color){
		super(4, name, color, "dog", new Random().nextInt(26));
	}
	
	public void grow(){
		weight = weight*1.5;
	}
	
	public boolean isCat(){
		return false;
	}
	
}
