import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * This class represents a category that contains clauses.
 */
public class Category implements Serializable {

	/** The serial version UID. */
	private static final long serialVersionUID = 4900133832349049758L;

	/** The name of this category. */
	private String name;
	
	/** The clauses of this category. */
	private List<Clause> clauses;

	/**
	 * Constructs a category with the given name.
	 *  
	 * @param name the name of the category.
	 */
	public Category(String name) {		
		this.name = name;
		this.clauses = new ArrayList<>();
	}

	/**
	 * Adds a data to the category.
	 * 
	 * @param clause the new clause.
	 */
	public void add(Clause clause) {
		clauses.add(clause);
	}
	
	/**
	 * Check there is the clause with same title
	 * @param clause wants to check.
	 * @return true or false
	 */
	public boolean existClauseTitle(String title){
		boolean toReturn = false;
		for (int i = 0; i < this.clauses.size(); i++) {
			if (this.clauses.get(i).getTitle().equals(title)) {
				toReturn = true;
				break;
			}
		}
		return toReturn;
	}
	/**
	 * Removing the clause from the category by using its ID.
	 * @param index
	 */
	public void delete(int index) {
		this.clauses.remove(index);
		//Reset the clause ID after remove clauses
		if(index != 0) 
			index--;
		
		for(int i = index; i < this.clauses.size(); i++){
			this.clauses.get(i).setID(i);
		}
	}

	/**
	 * Removes the given clause from the category.
	 * 
	 * @param clause the clause
	 */
	public void delete(Clause clause) {
		if (!this.clauses.remove(clause)) {
			throw new IllegalArgumentException("Does not contain the given clause.");
		}
	}
	
	/**
	 * Returns the size of the category.
	 * 
	 * @return the size of the category.
	 */
	public int size() {
		return clauses.size();
	}
	
	/**
	 * Modify given clause in the category.
	 * 
	 * @param index is the clause which want to modify.
	 * @param cl a new clause.
	 */
	public void modifyInformation(int index, Clause cl){
		this.clauses.set(index, cl);
	}
	
	/**
	 * Modify the title of clause in the category.
	 * @param index the clause which want to modify.
	 * @param title a new title of clause.
	 */
	public void modifyClauseTitle(int index, String title){
		this.clauses.get(index).setTitle(title);
	}
	
	/**
	 * Modify the information of clause in the category.
	 * @param index is the clause which want to modify.
	 * @param infor is a new information of clause.
	 */
	public void modifyClauseInfo(int index, String infor){
		this.clauses.get(index).setInfo(infor);
	}
	
	/**
	 * Get the clause at the index i
	 * @param index 
	 * @return the clause
	 */
	public Clause getClause(int index){
		return clauses.get(index);
	}
	
	/**
	 * @return the name of the category.
	 */
	public String getName(){
		return name;
	}
	/**
	 * Renames this category.
	 * 
	 * @param name the name of the category.
	 */
	public void setName(String name){
		this.name = name;
	}
		
	/**
	 * @return a String representation of this clause.
	 */
	public String toString(){
		StringBuilder sb = new StringBuilder();
//		String header = "Category: " + name;
//		if (clauses.size() < 2) {
//			header = header + " (" + clauses.size() + " clause)";
//		} else {
//			header = header + " (" + clauses.size() + " clauses)";
//		}
//		sb.append(header + "\n");
//		for (int i = 0; i < header.length(); i++) {
//			sb.append('=');
//		}
//		sb.append("\n");
		if (clauses.size() != 0) {
			sb.append(clauses.get(0).toString());
		}
		for(int i = 1; i < clauses.size(); i++){
			sb.append("\n" + clauses.get(i).toString());
		}
//		for (int i = 0; i < header.length(); i++) {
//			sb.append('=');
//		}
		return sb.toString();
	}
}
