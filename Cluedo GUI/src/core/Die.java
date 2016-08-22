package core;


import java.util.Random;
/**
 * this class represents a single die object which rolls and random number between 1 and 6
 * the object stores the value of the roll and the path to the image of the corresponding 
 * roll value
 * @author kirita escott and patrick ryan
 * */
public class Die {
	private int roll;

	public Die(){
		roll = new Random().nextInt(6)+1;
	}
	/**
	 * returns the int value of the roll
	 * @return roll
	 * */
	public int getRoll(){
		return roll;
	}
	/**
	 * returns the string path of the location of the image corresponding to 
	 * the roll
	 * */
	public String getDieFile(){
		return System.getProperty("user.dir")+"/Die Faces/"+roll+".jpg";
	}
}
