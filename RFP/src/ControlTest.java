import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

@SuppressWarnings("serial")
public class ControlTest extends Control {
	/**
	 * 
	 */
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



}
