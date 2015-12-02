import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * This class represents all categories.
 */
public class Control {
	/** A list that holds all categories */	
	private List<Category> cats;
	
	/**
	 * The hash map holds key as words and value as category name and clause ID
	 */
	private MyHashMap<String, Value> myHash;
	
	/**
	 * Initializes a control for storing all categories.
	 */
	public Control(){
		this.cats = new ArrayList<Category>();		
	}
	
	/**
	 * Adds the given category to the control.
	 * 
	 * @param cat the category.
	 */
	public void addCategory(Category cat){
		this.cats.add(cat);
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
		Boolean catFlag = true;
		Boolean clFlag = false;
		Boolean infoFlag = false;
		StringBuilder st = new StringBuilder();
		
		//Loop until the end of file		
		while(input.hasNextLine()){
			String line = input.nextLine();
			
			//Create new category.
			if(catFlag){ 
				temp.add(new Category(line));
				index = temp.size() - 1;
				catFlag = false;
			}			
			
			//Got all clause information
			if(line.equals("End-Clause:")){
				temp.get(index).modifyClauseInfo(temp.get(index).size() - 1, st.toString());
				infoFlag = false;
			}
			
			//Get the clause's information.
			if(infoFlag){
				if(st.length() != 0)
					st.append("\n");
				else
					st.append(line);
			}			
			
			//Get the clause title then create new clause without clause body.
			if(clFlag){
				temp.get(index).add(new Clause(line, "", temp.get(index).size()));
				clFlag = false;
				infoFlag = true;
			}
			
			//End of current category.
			if(line.equals("End-Category"))
				catFlag = true;
			
			//Create new clause.
			if(line.equals("Clause:")){
				clFlag = true;
			}			
		}
		this.cats = temp;
		input.close();
	}
	
	/**
	 * Fill out the hash table with clauses.
	 */
	public void fillHashMap(){
		//Go through all the category
		for(int i = 0; i < this.cats.size(); i++){
			Category c = this.cats.get(i);
			//Go through all the clauses
			for(int j = 0; j < c.size(); j++){
				String cl = c.getClause(j).getTitle().toLowerCase();
				String[] sentence = cl.split("[\\/-_()]");
				for(int k = 0; k < sentence.length; k++){
					Value v = new Value(c.getName(), c.getClause(j).getID());
					myHash.put(sentence[k], v);
				}
			}
		}
	}
	
	
	/**
	 * Find the clauses which contains words in the input string.
	 * @param st
	 */
	public void search(String st){
		String[] words = st.toLowerCase().split("[s+]");
		ArrayList<Value> result = new ArrayList<Value>();
		for(int i = 0; i < words.length; i++){
			result.addAll(myHash.getValue(words.toString()));
			System.out.println(result.size());
		}
	}
	
	public static void main(String[] args){		
//		Control c = new Control();
//		try {
//			c.readFile("data.txt");
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		System.out.println(c.cats.toString());
	}
}
