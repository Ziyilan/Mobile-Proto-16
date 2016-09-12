import java.util.*;

public class Cat extends Animal {
	
	public Cat(String name, String color){
		super(4, name, color, "cat", new Random().nextInt(26));
	}
	
	public void grow(){
		weight = weight*3.0;
	}
	
	public boolean isCat(){
		return true;
	}
}
