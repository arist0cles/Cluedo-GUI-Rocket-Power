package core;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Die {

	private BufferedImage p;
	private List<BufferedImage> faces = new ArrayList<>();
	
	public Die(){
		
	}
	
	private void setupDie(){
		
	}
	
	private int roll(){
		Random r = new Random();
		return r.nextInt(6)+1;
	}
}
