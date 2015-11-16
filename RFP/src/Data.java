/**
 * The Data class will store the clause and information about that clause
 */
public class Data {
	/**
	 * Store the title of clause.
	 */	
	private String clause;
	
	/**
	 * Store the information of the clause.
	 */
	private String infor;
	
	/**
	 * ID is used to
	 */
	private int id;
	
	/**
	 * Construct the empty class.
	 */
	Data(){
		setClause("");
		setInfor("");
		setId(0);		
	}
	
	/**
	 * Construct the class with inputs.
	 * @param a is the clause name.
	 * @param b is the clause information.
	 * @param i is the clause id.
	 */
	Data(String a, String b, int i){
		setClause(a);
		setInfor(b);
		setId(i);
	}

	/**
	 * @return the clause's title.
	 */
	public String getClause() {
		return this.clause;
	}

	/**
	 * @param clause is a new title of clause.
	 */
	public void setClause(String clause) {
		this.clause = clause;
	}

	/**
	 * @return the information of the clause.
	 */
	public String getInfor() {
		return this.infor;
	}

	/**
	 * @param inf is the new information about the clause.
	 */
	public void setInfor(String inf) {
		this.infor = inf;
	}

	/**
	 * @return the id of clause
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id is new clause id.
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	/**
	 * Get the string which include the title and information of the clause.
	 */
	@Override
	public String toString () {
		return this.clause + "\n" + this.infor;
	}
}
