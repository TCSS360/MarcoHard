import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Navy Nguyen
 * @co-author Ken Chan designed save and load functions
 * 
 * This class represents a control to all categories.
 */
public class Control implements Serializable {
	
	/** The serial version UID. */
	private static final long serialVersionUID = -1865987846022596672L;
	
	/** Password to login as Admin */
	private static final String password = "macrohard";
		
	/** All categories */
	private List<Category> cats;
	
	/**
	 * Initializes a control for storing all categories.
	 */
	Control() {
		this.cats = new ArrayList<Category>();
	}
	
	/**
	 * Adds the given category to the control.
	 * 
	 * @param cat the category
	 * @exception IllegalArgumentException if the control contains a category with the same name
	 */
	public void addCategory(Category cat) {
		for (int i = 0; i < cats.size(); i++) {
			if (cats.get(i).getName().equals(cat.getName())) {
				throw new IllegalArgumentException("The control contains a category with the same name.");
			}
		}
		cats.add(cat);
	}
	
	/**
	 * Renames the category.
	 * @param index is the category which wants to rename.
	 * @param st is a new name.
	 */
	public void renameCategory(int index, String st){
		this.cats.get(index).setName(st);
	}
	
	/**	 
	 * @param index the index of the category. 
	 * @return the category.
	 */
	public Category getCategory(int index){
		return cats.get(index);
	}
	
	/**	 
	 * @return the list of category name
	 */
	public String[] getCategoryName(){
		String[] toReturn = new String[cats.size()];
		for(int i = 0; i < cats.size(); i++){
			toReturn[i] = cats.get(i).getName();
		}
		return toReturn;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < cats.size(); i++) {
			sb.append(cats.get(i).toString() + "\n\n");
		}
		return sb.toString();
	}
	
	/**
	 * Delete the category by its name.
	 * @param name is the category's name.
	 */
	public void deleteCategory(String name){
		//There is at least one category in the array.
		if(this.cats.size() != 0){
			for(int i = 0; i < this.cats.size(); i++){
				if(name.equals(this.cats.get(i).getName())){
					this.cats.remove(i);
					break;
				}
			}
		}
	}
	
	/**
	 * Delete the category by its name.
	 * @param name is the category's name.
	 */
	public void deleteCategory(int index){
		this.cats.remove(index);
	}
	
	/**
	 * Check entered password is match with stored password
	 * @param input is a entered password
	 * @return true or flase
	 */
	public boolean passwordMatch(char[] input) {
		boolean match = true;
		if(password.length() != input.length){
			match = false;
		} else {
			for(int i = 0; i < password.length(); i++){
				if(password.charAt(i) != input[i]){
					match = false;
					break;
				}
			}
		}
		return match;
	}
	
	/**
	 * @author Ken Chan
	 * 
	 * Save the data into file .ser
	 * @param c is the Control object
	 * @param fileName is the name of file
	 */
	public void save(Control c, String fileName) {
		try {
			ObjectOutputStream save = new ObjectOutputStream(new FileOutputStream(fileName));
			save.writeObject(c);
			save.flush();
			save.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @author Ken Chan
	 * 
	 * Loading the data into object
	 * @param fileName is the name of file which you want to load data.
	 * @return the object with all data.
	 */
	public Control load(String fileName) {
		Control c = null;
		try {
			ObjectInputStream load = new ObjectInputStream(new FileInputStream(fileName));
			c = (Control) load.readObject();
			load.close();
		} catch (Exception e) {
			e.printStackTrace();			
		}
		return c;
	}	
	
	/**
	 * Fill out the hash table with clauses.
	 * In each category, 
	 */
	public MyHashMap<String,Value> fillHashMap(){
		MyHashMap<String,Value> myHash = new MyHashMap<String,Value>();
		//Go through all the category
		for(int i = 0; i < this.cats.size(); i++){
			Category cate = this.cats.get(i);
			//Go through all the clauses
			for(int j = 0; j < cate.size(); j++){
				String cl = cate.getClause(j).getTitle();
				ArrayList<String> sentence = split(cl);
				for(int k = 0; k < sentence.size(); k++){
//					Value v = new Value(cate.getName(), cate.getClause(j).getID());
//					System.out.println(sentence.get(k)+ 
//							" " + cate.getName() + " " + cate.getClause(j).getID());
					myHash.put(sentence.get(k), new Value(cate.getName(), cate.getClause(j).getID()));
				}
			}
		}
		return myHash;
	}
	
	/**
	 * This function will split the string into the individual words
	 * which do not have any punctuations.
	 * @param st be a string
	 * @return An arraylist of string
	 */
	private ArrayList<String> split(String st){
		st = st.toLowerCase();
		ArrayList<String> toReturn = new ArrayList<String>();
		StringBuilder temp = new StringBuilder();		
		for(int i = 0; i < st.length(); i++){
			//Eliminate all the punctuations.
			if(st.charAt(i)<48 || (st.charAt(i)>57 && st.charAt(i)<97) || st.charAt(i)>122){				
				//Eliminat the empty string.
				if(!temp.toString().equals("")) 
					toReturn.add(temp.toString());
				temp = new StringBuilder();
			} else {
				temp.append(st.charAt(i));
			}
			if(i == st.length()-1) toReturn.add(temp.toString());
		}
		
		for(int i = 0; i < toReturn.size(); i++){
			if(toReturn.get(i).equals("")){
				toReturn.remove(i);
			}
		}
		
		return toReturn;
	}
	
	/**
	 * Find the clauses which match to the input string.
	 * @param st is the searching String.
	 * @param myHash is the hash table
	 */
	public ArrayList<Value> search(String st, MyHashMap<String,Value> myHash){		 	
		ArrayList<String> words = split(st);	//Splitting the string into individual words
//		System.out.println(words.toString());
		//Get the clauses which contains the words in the input string		
		ArrayList<Value> result = new ArrayList<Value>();		
		for(int i = 0; i < words.size(); i++){
			ArrayList<Value> temp = myHash.getValue(words.get(i));
			//Check the new list of clauses that were either found or not
			for(int j = 0; j < temp.size(); j++){
				boolean flag = false;
				for(int k = 0; k < result.size(); k++){			
					//If the clause was found then increase the count #
					if(result.get(k).getCategoryName().equals(temp.get(j).getCategoryName()) && 
							result.get(k).getClauseID() == temp.get(j).getClauseID()){
						result.get(k).IncreaseCount();
						flag = true;
						break;
					}
				}
				//If the clause was not found, then add it into result array.
				if(!flag)
					result.add(temp.get(j));
			}
		}
						
		//Using bubble sort to sort the result array to descending 
		if(result.size()>=2){
			for(int i = 0; i < result.size()-1; i++) {
				boolean swap = false;
				for(int j = 0; j < result.size()-1; j++){
					if(result.get(j).getCount() < result.get(j+1).getCount()) {
						Value temp = result.get(j+1);
						result.set(j+1, result.get(j));
						result.set(j, temp);
						swap = true;
					}
				}
				if(!swap) break;
			}	
		}
		return result;		
	}
	
	/**
	 * Looking for the clauses that match with the list of value objects just found.
	 * The value object contains the category name and clause ID which is the location
	 * of the clause in the category.
	 * @param result is a list of value objects
	 * @return the list of clauses that match to the string input
	 */
	public ArrayList<Clause> getClauseFromSearch(ArrayList<Value> result){
		ArrayList<Clause> toReturn = new ArrayList<Clause>();
		for(int i = 0; i < result.size(); i++){
			for(int j = 0; j < this.cats.size(); j++){
				if(result.get(i).getCategoryName().equals(this.cats.get(j).getName())){
					toReturn.add(this.cats.get(j).getClause(result.get(i).getClauseID()));
					break;
				}
			}
		}
		
		return toReturn;
	}
}
