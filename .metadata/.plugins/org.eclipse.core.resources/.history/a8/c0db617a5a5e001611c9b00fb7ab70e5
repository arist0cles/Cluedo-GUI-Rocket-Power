package squares;

import java.util.Iterator;

import core.Location;

public abstract class Square {
	
	private String name;
	private Location location;
	
	public Square(String name, Location location){
		this.name=name; this.location=location;
	}
	
	public String getName(){
		return name;
	}
	
	public Location getLoc(){
		return location;
	}
	
	public void draw(){
		System.out.println(this.name);
	}
	
	
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((location == null) ? 0 : location.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Square other = (Square) obj;
		if (location == null) {
			if (other.location != null)
				return false;
		} else if (!location.equals(other.location))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

//	public boolean equals(Square square){
//		return this.name.equals(square.getName()) && this.location.equals(square.getLoc());
//	}

}
