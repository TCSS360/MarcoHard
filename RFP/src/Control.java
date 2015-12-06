import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * This class represents a control to all categories.
 */
public class Control implements Serializable {
	
	/** The serial version UID. */
	private static final long serialVersionUID = -1865987846022596672L;

	private static final String password = "MACROHARD123";
	
	/**
	 * The hash map holds key as words and value as category name and clause ID
	 */
//	private MyHashMap<String, Value> myHash;
	
	/** All categories */
	private List<Category> cats;
	
	/**
	 * Initializes a control for storing all categories.
	 */
	Control() {
		this.cats = new ArrayList<Category>();
//		this.myHash = new MyHashMap<String, Value>();
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
	 * Returns the category.
	 * 
	 * @param index the index of the category. 
	 * @return the category.
	 */
	public Category getCategory(int index){
		return cats.get(index);
	}
	
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
				if(name.equalsIgnoreCase(this.cats.get(i).getName())){
					this.cats.remove(i);
					break;
				}
			}
		}
	}
	
//	/**
//	 * Deletes the category that has the given name.
//	 * 
//	 * @param name category's name.
//	 * @exception IllegalArgumentException if the control doesn't contain such category
//	 */
//	public void deleteCategory(String name){
//		int i = -1;
//		do {
//			i++;
//		} while (i <= cats.size() || !cats.get(i).getName().equals(name));
//	
//		if (i > cats.size()) {
//			throw new IllegalArgumentException("Does not contain such category.");
//		} else {
//			cats.remove(i);
//		}
//	}
	
	public boolean passwordMatch(String input) {
		return password.equals(input);
	}
	
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
	 * Loading categories and clauses from the text file. 
	 * @param name is file's name.
	 * @throws FileNotFoundException
	 */
	public void readFile(String name) throws FileNotFoundException {
		File file = new File(name);
		Scanner input = new Scanner(file);
		ArrayList<Category> temp = new ArrayList<Category>();
		int index = 0;
		Boolean catFlag = false;
		Boolean clFlag = false;
		Boolean infoFlag = false;
		StringBuilder st = new StringBuilder();
		
		//Loop until the end of file		
		while(input.hasNextLine()){
			String line = input.nextLine();
			if(line.equals("Category:")){
				catFlag = true;
				continue;
			}			
			//Create new category.
			if(catFlag){ 
				temp.add(new Category(line));
				index = temp.size() - 1;
				catFlag = false;
				continue;
			}			
			//Create new clause.
			if(line.equals("Clause:")){
				clFlag = true;
				continue;
			}
			//Get the clause title then create new clause without clause body.
			if(clFlag){
				temp.get(index).add(new Clause(line, "", temp.get(index).size()));
				clFlag = false;
				infoFlag = true;
				continue;
			}			
			//Get the clause's information.
			if(infoFlag && !line.equals("End-Clause:")){
				st.append(line + "\n");
			}			
			//Got all clause information
			if(line.equals("End-Clause:")){
				st.deleteCharAt(st.length()-1);	//Remove the last new line
				temp.get(index).modifyClauseInfo(temp.get(index).size() - 1, st.toString());
				st = new StringBuilder();
				infoFlag = false;
				continue;
			}
			//End of current category.
			if(line.equals("End-Category"))
				continue;						
		}
		this.cats = temp;
		input.close();
	}
	
	/**
	 * Fill out the hash table with clauses.
	 * In each category, 
	 */
//	public void fillHashMap(){
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
		return toReturn;
	}
	
	/**
	 * Find the clauses which match to the input string.
	 * @param st is the searching String.
	 * @param myHash is the hash table
	 */
	public ArrayList<Value> search(String st, MyHashMap<String,Value> myHash){		 	
		ArrayList<String> words = split(st);	//Splitting the string into individual words
		
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
		if(result.size()>2){
			for(int i = 0; i < result.size()-1; i++) {
				boolean swap = false;
				for(int j = 0; j < result.size()-1; j++){
					if(result.get(j).getCount() > result.get(j+1).getCount()) {
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
	
//	public static void main(String[] args) throws FileNotFoundException{
//		Control c = new Control();
////		Category cat1 = new Category("Category1");
////		cat1.add(new Clause("title 1", "info 1"));
////		cat1.add(new Clause("TITLE  1", "INFO   1"));
////		Category cat2 = new Category("Category22222");
////		cat2.add(new Clause("TITLE222222", "INFOOOO22222"));
////		Category cat3 = new Category("Category 3!");
////		c.addCategory(cat1);
////		c.addCategory(cat2);
////		c.addCategory(cat3);		
//		
////		try {
////			ObjectOutputStream save = new ObjectOutputStream(new FileOutputStream("default.ser"));
////			save.writeObject(c);
////			save.flush();
////			save.close();
////		} catch (Exception e) {
////			e.printStackTrace();
////		}
//		
//		try {
//			ObjectInputStream load = new ObjectInputStream(new FileInputStream("default.ser"));
//			c = (Control) load.readObject();
//			load.close();
////			System.out.println(restore.toString());
//		} catch (Exception e) {
//			e.printStackTrace();			
//		}
//		
//		MyHashMap<String,Value> hash = c.fillHashMap();
//		
//		System.out.println(c.search("cost", hash).size());
//		System.out.println(c.search("cost", hash).toString());
//		
////		System.out.println(c.toString());
//	}
}
