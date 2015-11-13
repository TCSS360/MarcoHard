import java.util.ArrayList;

public class Category {
	/**
	 * name is the name of Category.
	 */
	private String name;
	/**
	 * id is used to distinguish to another Category.
	 */
	private String id;
	/**
	 * An array to hold every clauses in same category.
	 */
	private ArrayList<Data> clause = new ArrayList<>();
	
	/**
	 * The empty class constructor.
	 */
	Category(){
		this.name = "";
		this.id = "";
	}

	/**
	 * Construct the object with inputs.	 
	 * @param name is the name of category.
	 * @param id is the category id.
	 */
	public Category(String name, String id) {		
		this.name = name;
		this.id = id;
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
	 * Modify the clause in the category.
	 * @param index is the clause which want to modify.
	 * @param cl is a new clause.
	 */
	public void modify(int index, Data cl){
		clause.set(index, cl);
	}
	
	/**
	 * Convert the category and its clause into one String.
	 * @return a string.
	 */
	public String toString(){
		StringBuilder st = new StringBuilder();
		st.append(this.name);
		st.append("/n");
		st.append(this.id);
		st.append("/n");
		for(int i = 0; i < this.clause.size(); i++){
			st.append(this.clause.get(i).toString());
			st.append("/n");
		}
		return st.toString();
	}
}
