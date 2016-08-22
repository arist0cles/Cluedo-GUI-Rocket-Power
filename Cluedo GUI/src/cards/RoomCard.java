package cards;

public class RoomCard implements Card {

	String name;
	
	public RoomCard(String name) {
		this.name=name;
	}

	@Override
	public String getName() {
		return this.name;
	}

	/* *
	 * generate hashcode method, necessary for comparison of card objects
	 * @return result
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	/** 
	 * generated equals method, necessary for comparison of card objects
	 * @param obj
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RoomCard other = (RoomCard) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	


	
}
