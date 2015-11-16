import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class will hold all categories. *
 */
public class Control {
	/**
	 * Array holds categories
	 */	
	private ArrayList<Category> cat;
	
	/**
	 * Empty constructor which create an array which holds category objects.
	 */
	Control(){
		this.cat = new ArrayList<Category>();		
	}
	
	/**
	 * Add new category object with given name.
	 * @param st is the name of category.
	 */
	public void addCategory(String st){
		this.cat.add(new Category(st));
	}
	
	/**
	 * Rename the category.
	 * @param index is the category which wants to rename.
	 * @param st is a new name.
	 */
	public void renameCategory(int index, String st){
		this.cat.get(index).setName(st);
	}
	
	/**
	 * Delete the category by its location.
	 * @param index is the location of category.
	 */
	public void deleteCategory(int index){
		this.cat.remove(index);
	}
	
	/**
	 * Delete the category by its name.
	 * @param name is the category's name.
	 */
	public void deleteCategory(String name){
		//There is at least one category in the array.
		if(this.cat.size() != 0){
			for(int i = 0; i < this.cat.size(); i++){
				if(name.equalsIgnoreCase(this.cat.get(i).getName())){
					this.cat.remove(i);
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
			
			//All ready get all clause information
			if(line.equals("End-Clause:")){
				temp.get(index).modifyClauseInfor(temp.get(index).size() - 1, st.toString());
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
				temp.get(index).add(new Data(line, "", temp.get(index).size()));
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
		this.cat = temp;
		input.close();
	}
//	
//	public static void main(String[] args){
//		Control c = new Control();
//		try {
//			c.readFile("data.txt");
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		System.out.println(c.cat.toString());
//	}
}
