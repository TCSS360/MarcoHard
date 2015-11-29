import java.util.ArrayList;
import java.util.List;

/**
 * This class represents a category that contains clauses.
 */
public class Category {
	
	/** The name of this category. */
	private String name;
	
	/** The clauses of this category. */
	private List<Data> clauses;
	
//	/** The default constructor. */
//	public Category() {
//		this("");
//	}

	/**
	 * Constructs a category with the given name.
	 *  
	 * @param name the name of the category.
	 */
	public Category(String name) {		
		this.name = name;
		clauses = new ArrayList<>();
	}

	/**
	 * Adds a data to the category.
	 * 
	 * @param clause the new clause.
	 */
	public void add(Data clause){
		clauses.add(clause);
	}
	
//	/**
//	 *  Removes the clause from the category.
//	 * 
//	 *  @param index the index
//	 */
//	public void delete(int index) {
//		clauses.remove(index);
//	}

	/**
	 * Removes the given clause from the category.
	 * 
	 * @param clause the clause
	 */
	public void delete(Data clause) {
		if (!clauses.remove(clause)) {
			throw new IllegalArgumentException("Does not contain the given clause.");
		}
	}
	
	/**
	 * @return the size of the category.
	 */
	public int size() {
		return clauses.size();
	}
	
//	/**
//	 * Modify given clause in the category.
//	 * 
//	 * @param index is the clause which want to modify.
//	 * @param cl a new clause.
//	 */
//	public void modifyInformation(int index, Data cl){
//		clause.set(index, cl);
//	}
//	
//	/**
//	 * Modify the title of clause in the category.
//	 * @param index the clause which want to modify.
//	 * @param title a new title of clause.
//	 */
//	public void modifyClauseTitle(int index, String title){
//		clause.get(index).setClause(title);
//	}
//	
//	/**
//	 * Modify the information of clause in the category.
//	 * @param index is the clause which want to modify.
//	 * @param infor is a new information of clause.
//	 */
//	public void modifyClauseInfor(int index, String infor){
//		clause.get(index).setInfor(infor);
//	}
	
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
		sb.append(name);		
		sb.append("\nThere are " + clauses.size() + " clauses in this category:\n\n");
		
		for(int i = 0; i < clauses.size(); i++){
			sb.append(clauses.get(i).toString() + "\n");
		}
		return sb.toString();
	}
}
