import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * This class represents a control to all categories.
 */
public class Control implements Serializable {
	
	/** The serial version UID. */
	private static final long serialVersionUID = -1865987846022596672L;

	private static final String password = "MACROHARD123";
	
	/** All categories */
	private List<Category> cats;
	
	/**
	 * Initializes a control for storing all categories.
	 */
	Control() {
		cats = new ArrayList<Category>();
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
	 * Returns the category.
	 * 
	 * @param index the index of the category.
	 * 
	 * @return the category.
	 */
	public Category getCategory(int index){
		return cats.get(index);
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < cats.size(); i++) {
			sb.append(cats.get(i).toString() + "\n\n");
		}
		return sb.toString();
	}

//	/**
//	 * Renames the category.
//	 * 
//	 * @param index is the category which wants to rename.
//	 * @param st is a new name.
//	 */
//	public void renameCategory(int index, String st){
//		this.cats.get(index).setName(st);
//	}

	
//	/**
//	 * Deletes the category.
//	 * 
//	 * @param index is the location of category.
//	 */
//	public void deleteCategory(int index){
//		this.cat.remove(index);
//	}
	
	/**
	 * Deletes the category that has the given name.
	 * 
	 * @param name category's name.
	 * @exception IllegalArgumentException if the control doesn't contain such category
	 */
	public void deleteCategory(String name){
		int i = -1;
		do {
			i++;
		} while (i <= cats.size() || !cats.get(i).getName().equals(name));
	
		if (i > cats.size()) {
			throw new IllegalArgumentException("Does not contain such category.");
		} else {
			cats.remove(i);
		}
	}
	
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
	
//	public static void main(String[] args){
//		Control c = new Control();
//		Category cat1 = new Category("Category1");
//		cat1.add(new Clause("title 1", "info 1"));
//		cat1.add(new Clause("TITLE  1", "INFO   1"));
//		Category cat2 = new Category("Category22222");
//		cat2.add(new Clause("TITLE222222", "INFOOOO22222"));
//		Category cat3 = new Category("Category 3!");
//		c.addCategory(cat1);
//		c.addCategory(cat2);
//		c.addCategory(cat3);
//		
//		System.out.println(c.toString());
//		try {
//			ObjectOutputStream save = new ObjectOutputStream(new FileOutputStream("default.ser"));
//			save.writeObject(c);
//			save.flush();
//			save.close();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		try {
//			ObjectInputStream load = new ObjectInputStream(new FileInputStream("default.ser"));
//			Control restore = (Control) load.readObject();
//			load.close();
//			System.out.println(restore.toString());
//		} catch (Exception e) {
//			e.printStackTrace();			
//		}
//	}
}
