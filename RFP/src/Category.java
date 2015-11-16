import java.util.ArrayList;

/**
 * This class is a group of clauses. Clauses are stored in an arraylist.
 *
 */
public class Category {
	/**
	 * name is the name of Category.
	 */
	private String name;	
	/**
	 * An array to hold every clauses in same category.
	 */
	private ArrayList<Data> clause;
	
	/**
	 * The empty class constructor.
	 */
	Category(){
		this.name = "";
		this.clause = new ArrayList<>();
	}

	/**
	 * Construct the object with inputs.	 
	 * @param name is the name of category.
	 * @param id is the category id.
	 */
	public Category(String name) {		
		this.name = name;
		this.clause = new ArrayList<>();
	}

	/**
	 * Add new data into this category.
	 * @param cl is the new clause.
	 */
	public void add(Data cl){
		clause.add(cl);
	}
	
	/**
	 * Removing the clause from the category.
	 * @param index
	 */
	public void delete(int index) {
		clause.remove(index);
	}
	
	/**
	 * @return the size of array that holds clauses.
	 */
	public int size(){
		return this.clause.size();
	}
	
	/**
	 * Modify the clause in the category.
	 * @param index is the clause which want to modify.
	 * @param cl is a new clause.
	 */
	public void modifyData(int index, Data cl){
		clause.set(index, cl);
	}
	
	/**
	 * Modify the title of clause in the category.
	 * @param index is the clause which want to modify.
	 * @param title is a new title of clause.
	 */
	public void modifyClauseTitle(int index, String title){
		clause.get(index).setClause(title);
	}
	
	/**
	 * Modify the information of clause in the category.
	 * @param index is the clause which want to modify.
	 * @param infor is a new information of clause.
	 */
	public void modifyClauseInfor(int index, String infor){
		clause.get(index).setInfor(infor);
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
	public void setName(String st){
		this.name = st;
	}
		
	/**
	 * Convert the category and its clause into one String.
	 * @return a string.
	 */
	public String toString(){
		StringBuilder st = new StringBuilder();
		st.append("\n" + this.name);		
		st.append("\nThere are " + this.clause.size() + " clauses in this category.\n");
		
		for(int i = 0; i < this.clause.size(); i++){
			st.append(this.clause.get(i).toString());
			st.append("\n");
		}
		return st.toString();
	}
}
