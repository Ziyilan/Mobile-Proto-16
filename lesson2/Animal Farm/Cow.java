import java.util.*;

public class Cow extends Animal{
	
	public Cow(String name, String color){
		super(4, name, color, "cow", new Random().nextInt(101)+100);
	}
	
	public void grow(){
		weight = weight*5.0;
	}
	
	public boolean isCat(){
		return false;
	}
}
