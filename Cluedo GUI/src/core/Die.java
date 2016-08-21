package core;


import java.util.Random;

public class Die {
	private int roll;
	

	public Die(){
		roll = new Random().nextInt(6)+1;
	}
	
	public int getRoll(){
		return roll;
	}
	
	public String getDieFile(){
		return System.getProperty("user.dir")+"/Die Faces/"+roll+".jpg";
	}
}
