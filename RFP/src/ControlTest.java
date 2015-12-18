import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Ian Henderson
 */
@SuppressWarnings("serial")
public class ControlTest extends Control {
	
	Control controltest;

	@Before
	public void setUp() throws Exception {
		controltest = new Control();
		controltest.addCategory(new Category("test"));
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testControl() {
		assertTrue(!controltest.getCategory(0).equals(null));
	}

	@Test
	public void testAddCategory() {
		assertEquals("test", controltest.getCategory(0).getName());
	}

	@Test
	public void testRenameCategory() {
		controltest.getCategory(0).setName("renamed");
		assertEquals("renamed",controltest.getCategory(0).getName());
	}

	@Test
	public void testGetCategory() {
		assertEquals("test", controltest.getCategory(0).getName());
	}

	@Test
	public void testGetCategoryName() {
		assertEquals("test", controltest.getCategory(0).getName());
	}

	@Test
	public void testToString() {
		assertEquals("" + "\n\n", controltest.getCategory(0).toString() + "\n\n");
	}

	@Test
	public void testDeleteCategoryString() {
		controltest.deleteCategory(0);
		assertTrue(!controltest.getClass().isArray());
	}

	@Test
	public void testDeleteCategoryInt() {
		controltest.deleteCategory(0);
		assertTrue(!controltest.getClass().isArray());
	}

	@Test
	public void testPasswordMatch() {
		char[] input = {'m','a','c','r','o','h','a','r','d'};
		assertTrue(controltest.passwordMatch(input));
	}

	@Test
	public void testSave() {
		controltest.save(controltest, "test");
	}

	@Test
	public void testLoad() {
		controltest.load("test");
	}
	
	@Test
	public void testFillHashMap() {
		MyHashMap<String, Value> myHash = controltest.fillHashMap();
		assertEquals(0,controltest.search("clause", myHash).size());
	}

	@Test
	public void testSearch() {
		MyHashMap<String, Value> myHash = new MyHashMap<String, Value>();
		assertEquals(0,controltest.search("clause", myHash).size());
	}

	@Test
	public void testGetClauseFromSearch() {
		MyHashMap<String, Value> myHash = new MyHashMap<String, Value>();
		ArrayList<Value> result = controltest.search("clause", myHash);
		ArrayList<Clause> test = controltest.getClauseFromSearch(result);
		assertEquals(0, test.size());
	}

}
