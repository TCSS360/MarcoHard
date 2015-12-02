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
	private ArrayList<Category> cats;
	
	/**
	 * The hash map holds key as words and value as category name and clause ID
	 */
	private MyHashMap<String, Value> myHash;
	
	/**
	 * Initializes a control for storing all categories.
	 */
	public Control(){
		this.cats = new ArrayList<Category>();
		this.myHash = new MyHashMap<String, Value>();
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
	public void fillHashMap(){
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
					this.myHash.put(sentence.get(k), new Value(cate.getName(), cate.getClause(j).getID()));
				}
			}
		}
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
		}		
		return toReturn;
	}
	
	/**
	 * Find the clauses which match to the input string.
	 * @param st is the searching String.
	 */
	public ArrayList<Clause> search(String st){
		ArrayList<Value> result = new ArrayList<Value>();		
		String[] words = st.split("\\s");	//Splitting the string into individual words
		
		//Get the clauses which contains the words in the input string
		if(words.length != 0){
			result = myHash.getValue(words[0]);			
			for(int i = 1; i < words.length; i++){
				ArrayList<Value> temp = myHash.getValue(words[i]);
				//Check the new list of clauses that were either found or not
				for(int j = 0; j < temp.size(); j++){
					boolean flag = false;
					for(int k = 0; k < result.size(); k++){			
						//If the clause was found then increase the count #
						if(result.get(k).getClauseID() == temp.get(j).getClauseID()){
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
		}
				
		//Using bubble sort to sort the result array to descending 
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
		
		/*
		 * Looking for the clauses that match with the list of value objects.
		 * The value object contains the category name and clause ID which is the location
		 * of the clause in the category.
		 */
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
	
	
	public static void main(String[] args){
//		String st = "COST REPORTS-COST REIMBURSEMENT PURCHASE ORDERS/CONTRACTS (Clause B-4.1) (April 1999)"; 
//		Control c = new Control();
//		ArrayList<String> test = c.split(st);
//		ArrayList<String> t = test;
//		for(int i = 0; i < t.size(); i++){
//			System.out.println(t.get(i));
//		}
		
//		String[] t = st.split("\\s");
//		for(int i = 0; i < t.length; i++){
//			System.out.println(t[i]);
//		}
		
		Control c = new Control();
		try {
			c.readFile("data.txt");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		System.out.println(c.cats.toString());
		c.fillHashMap();
		System.out.println(c.search("ordering cost").toString());
	}
}
