import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class DataTest {
	Data test1;
	Data test2;
	@Before
	public void setUp() throws Exception {
		test1 = new Data("test1");
		test2 = new Data("test2", "the Info");
		test1.setId(1);
		test2.setId(2);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testDataString() {
		assertEquals("test1",test1.getTitle());
		assertEquals("",test1.getInfo());
	}

	@Test
	public void testDataStringString() {
		assertEquals("test2", test2.getTitle());
		assertEquals("the Info", test2.getInfo());
	}

	@Test
	public void testGetTitle() {
		assertEquals("test1", test1.getTitle());
		assertEquals("test2", test2.getTitle());
	}

	@Test
	public void testSetTitle() {
		test1.setTitle("test3");
		assertEquals("test3",test1.getTitle());
	}

	@Test
	public void testGetInfo() {
		assertEquals("", test1.getInfo());
		assertEquals("the Info", test2.getInfo());
	}

	@Test
	public void testSetInfo() {
		test2.setInfo("Changed Info");
		assertEquals("Changed Info", test2.getInfo());
	}

	@Test
	public void testGetID() {
		assertEquals(1, test1.getID());
		assertEquals(2,test2.getID());
	}

	@Test
	public void testSetId() {
		test1.setId(6);
		assertEquals(6,test1.getID());
	}

	@Test
	public void testToString() {
		assertEquals("test2" + "\n" + "the Info" + "\n", test2.toString());
	}

}
