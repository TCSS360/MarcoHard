/**
 * The Data class will store the title and information of a clause.
 */
public class Data {
	
	/** The title of this clause. */	
	private String title;
	
	/** The information of this clause. */
	private String info;
	
	/** The clause ID */
	private int ID;
	
//	/** Constructs the empty class. */
//	Data() {
//		setClause("");
//		setInfor("");
//		setId(0);		
//	}
	
	/**
	 * Constructs a clause with the given title.
	 * 
	 * @param title the title
	 */
	Data(String title){
		this(title, "");
	}
	
	/**
	 * Constructs a clause with the given title and information.
	 * 
	 * @param title the title
	 * @param information the information
	 */
	Data(String title, String info){
		this.title = title;
		this.info = info;
	}

	/**
	 * @return the clause's title.
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Sets the given title to this clause.
	 * 
	 * @param title title of the clause.
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the information of the clause.
	 */
	public String getInfo() {
		return info;
	}

	/**
	 * Sets the given information to this clause
	 *
	 * @param info information of the clause.
	 */
	public void setInfo(String info) {
		this.info = info;
	}

	/**
	 * @return the ID of this clause
	 */
	public int getID() {
		return ID;
	}

	/**
	 * Sets the given ID to this clause
	 * 
	 * @param ID is new clause id.
	 */
	public void setId(int ID) {
		this.ID = ID;
	}
	
	/**
	 * @return a String representation of this clause.
	 */
	@Override
	public String toString () {
		return title + "\n" + info + "\n";
	}
}
