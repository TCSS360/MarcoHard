/**
 * @author Abdalla Ahmed
 * 
 * This class is stored the category name and its clause id.
 * we use this class to retrieve the an array and use this array
 * to pull out the clauses which contain the words that we are searching.
 */
public class Value {
	private String categoryName;
	private int clauseID;
	private int counter;
	
	public Value(String name, int id){
		this.categoryName = name;
		this.clauseID = id;
		counter = 1;
	}

	/**
	 * @return the clauseID
	 */
	public int getClauseID() {
		return this.clauseID;
	}

	/**
	 * @return the categoryName
	 */
	public String getCategoryName() {
		return this.categoryName;
	}
	/** 
	 * @return the counter
	 */
	public int getCount(){
		return this.counter;
	}
	/**
	 * Increasing the counter.
	 */
	public void IncreaseCount(){
		this.counter++;
	}	
}
