import java.io.Serializable;

/**
 * @author Mubarek Shafi
 * 
 * This class represents a clause. 
 */
public class Clause implements Serializable {

	/** The serial version UID. */
	private static final long serialVersionUID = -9077504892430983770L;

	/** The title of this clause. */	
	private String title;
	
	/** The information of this clause. */
	private String info;
	
	/** The clause ID */
	private int ID;
	
	/**
	 * Constructs a clause with the given title.
	 * 
	 * @param title the title
	 */
	Clause(String title){
		this(title, "");
	}
	
	/**
	 * Constructs a clause with the given title and information.
	 * 
	 * @param title the title
	 * @param information the information
	 */
	Clause(String title, String info){
		this.title = title;
		this.info = info;
	}
	
	/**
	 * Constructs a clause with the given title and information.
	 * 
	 * @param title the title
	 * @param information the information
	 */
	Clause(String title, String info, int id){
		this.title = title;
		this.info = info;
		this.ID = id;
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
	public void setID(int ID) {
		this.ID = ID;
	}
	
	/**
	 * @return a String representation of this clause.
	 */
	@Override
	public String toString () {
		StringBuilder sb = new StringBuilder();
		sb.append(title + "\n");
		for (int i = 0; i < title.length(); i++) {
			sb.append("-");
		}
		String infoString = info;
		if (info.length() == 0) {
			infoString = "<EMPTY>";
		}
		sb.append("\n" + infoString + "\n");
		return sb.toString();
	}
}
