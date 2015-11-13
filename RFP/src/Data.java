/*
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
	 * @param i is the cluse id.
	 */
	Data(String a, String b, int i){
		setClause(a);
		setInfor(b);
		setId(i);
	}

	/**
	 * @return the clause
	 */
	public String getClause() {
		return clause;
	}

	/**
	 * @param clause the clause to set
	 */
	public void setClause(String clause) {
		this.clause = clause;
	}

	/**
	 * @return the infor
	 */
	public String getInfor() {
		return infor;
	}

	/**
	 * @param infor the infor to set
	 */
	public void setInfor(String infor) {
		this.infor = infor;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	/**
	 * Get the string which include the title and information of the clause.
	 */
	@Override
	public String toString () {
		return this.clause + "/n" + this.infor;
	}
}
