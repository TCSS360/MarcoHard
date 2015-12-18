import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * @author Ian Henderson and Ken Chan
 */
public class ClauseTest {
	
	Clause test;
	Clause test2;
	
	@Before
	public void setUp() {
		test = new Clause("Clause 1", "Info");
		test2 = new Clause("Clause 2");
	}

	@Test
	public void testClauseString() {
		test2 = new Clause("Clause 2");
	}

	@Test
	public void testClauseStringString() {
		test = new Clause("Clause 1", "Info");
	}

	@Test
	public void testClauseStringStringInt() {
		test = new Clause("Clause 1", "Info", 1);
	}
	
	/**
	 * Test method for {@link Clause#getTitle()}.
	 */
	@Test
	public void testGetTitle() {
		assertEquals("Clause 1", test.getTitle());
		assertEquals("Clause 2", test2.getTitle());
//		
//		test.setTitle("Clause 2");
//		assertEquals("Clause 2", test.getTitle());
//		test2.setTitle("Clause2");
//		assertEquals("Clause2", test2.getTitle());
	}
	
	/**
	 * Test method for {@link Clause#setTitle()}.
	 */
	@Test
	public void testSetTitle() {
		test.setTitle("Clause 2");
		assertEquals("Clause 2", test.getTitle());
		test2.setTitle("Clause2");
		assertEquals("Clause2", test2.getTitle());
	}
	
	/**
	 * Test method for {@link Clause#getInfo()}.
	 */
	@Test
	public void testGetInfo() {
		assertEquals("Info", test.getInfo());
		assertEquals("", test2.getInfo());
		
//		test.setInfo("Edited info.");
//		assertEquals("Edited info.", test.getInfo());
//		test2.setInfo("Info.");
//		assertEquals("Info.", test2.getInfo());
		
	}
	
	/**
	 * Test method for {@link Clause#setInfo()}.
	 */
	@Test
	public void testSetInfo(){
		test.setInfo("Edited info.");
		assertEquals("Edited info.", test.getInfo());
		test2.setInfo("Info.");
		assertEquals("Info.", test2.getInfo());
	}
	
	/**
	 * Test method for {@link Clause#getID()}.
	 */
	@Test
	public void testGetID() {
		test.setID(12345);
		assertEquals(12345, test.getID());
		test2.setID(123456);
		assertEquals(123456, test2.getID());
	}
	
	/**
	 * Test method for {@link Clause#setID()}.
	 */
	@Test
	public void testSetID() {
		test.setID(12345);
		assertEquals(12345, test.getID());
		test2.setID(123456);
		assertEquals(123456, test2.getID());
	}
	
	/**
	 * Test method for {@link Clause#toString()}.
	 */
	@Test
	public void testToString() {
		System.out.println(test.toString());
		System.out.println(test2.toString());
		assertEquals("Clause 1\n--------\nInfo\n", test.toString());
		assertEquals("Clause 2\n--------\n<EMPTY>\n", test2.toString());
		
	}
}
