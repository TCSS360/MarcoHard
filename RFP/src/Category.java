import java.util.ArrayList;

/**
 * This class represents a category that contains clauses.
 */
public class Category {
	/**
	 * The name of Category.
	 */
	private String name;	
	/**
	 * The clauses of this category.
	 */
	private ArrayList<Clause> clauses;
	
	/**
	 * The empty class constructor.
	 */
	Category(){
		this.name = "";
		this.clauses = new ArrayList<>();
	}

	/**
	 * Construct the object with inputs.	 
	 * @param name is the name of category.
	 * @param id is the category id.
	 */
	public Category(String name) {		
		this.name = name;
		this.clauses = new ArrayList<>();
	}

	/**
	 * Adds a data to the category.
	 *@param clause the new clause.
	 */
	public void add(Clause clause){
		this.clauses.add(clause);
	}
	
	/**
	 * Removing the clause from the category.
	 * @param index
	 */
	public void delete(int index) {
		this.clauses.remove(index);
	}
	
	/**
	 * Removes the given clause from the category.
	 * 
	 * @param clause the clause
	 */
	public void delete(Clause clause) {
		if (!clauses.remove(clause)) {
			throw new IllegalArgumentException("Does not contain the given clause.");
		}
	}
	
	/**
	 * @return the size of array that holds clauses.
	 */
	public int size(){
		return this.clauses.size();
	}
	
	/**
	 * Modify the clause in the category.
	 * @param index is the clause which want to modify.
	 * @param cl is a new clause.
	 */
	public void modifyData(int index, Clause clause){
		this.clauses.set(index, clause);
	}
	
	/**
	 * Modify the title of clause in the category.
	 * @param index is the clause which want to modify.
	 * @param title is a new title of clause.
	 */
	public void modifyClauseTitle(int index, String title){
		clauses.get(index).setTitle(title);
	}
	
	/**
	 * Modify the information of clause in the category.
	 * @param index is the clause which want to modify.
	 * @param infor is a new information of clause.
	 */
	public void modifyClauseInfo(int index, String infor){
		clauses.get(index).setInfo(infor);
	}
	
	/**
	 * @return the name of category.
	 */
	public String getName(){
		return this.name;
	}
	
	/**
	 * Rename the category. 
	 * @param st is the new name of category.
	 */
	public void setName(String name){
		this.name = name;
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
	 * @return a String representation of this clause.
	 */
	public String toString(){
		StringBuilder st = new StringBuilder();
		st.append("\n" + this.name);		
		st.append("\nThere are " + this.clauses.size() + " clauses in this category.\n");
		
		for(int i = 0; i < this.clauses.size(); i++){
			st.append(this.clauses.get(i).toString());
			if(i != this.clauses.size() - 1) 
				st.append("\n");
		}
		return st.toString();
	}
}
