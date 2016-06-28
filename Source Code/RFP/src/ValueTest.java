import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Ian Henderson
 */
public class ValueTest {
	Value test;

	@Before
	public void setUp() throws Exception {
		test = new Value("name", 1);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testValue() {
		assertEquals("name", test.getCategoryName());
		assertEquals(1, test.getClauseID());
	}

	@Test
	public void testGetClauseID() {
		assertEquals(1, test.getClauseID());
	}

	@Test
	public void testGetCategoryName() {
		assertEquals("name",test.getCategoryName());
	}

	@Test
	public void testGetCount() {
		assertEquals(1,test.getCount());
	}

	@Test
	public void testIncreaseCount() {
		test.IncreaseCount();
		assertEquals(2,test.getCount());
	}

}
