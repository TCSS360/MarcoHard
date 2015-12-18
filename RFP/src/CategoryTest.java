import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


/**
 * @author Ken Chan
 */
public class CategoryTest {
	
	Category test;
	Clause clause1;
	Clause clause3;

	/**
	 * 
	 */
	@Before
	public void setUp() {
		test = new Category("Category 1");
		clause1 = new Clause("clause 1", "this is the info.");
		clause3 = new Clause("clause 3");
	}
	
	@Test
    /**
     * Test method for {@link Category#add(Clause)}
     * when the category contains a clause with the same title.
     */
//    (expected = IllegalArgumentException.class)
//    public void exceptionTestAdd() {
	public void testAdd(){
    	test.add(clause3);
    	test.add(new Clause("clause 3", "info"));
    	
    }
	
	@Test
	public void testExistClauseTitle() {
		test.existClauseTitle("clause 3");
	}
	
    /**
     * Test method for {@link Category#delete(Clause)}
     * when the given clause is not found.
     */
    @Test(expected = IllegalArgumentException.class)
    public void exceptionTestDelete1() {
    	test.delete(clause3);
    }

    /**
     * Test method for {@link Category#delete(Clause)} when the given clause is not
     * found because they are different objects, even though they have the same title.
     */
    @Test(expected = IllegalArgumentException.class)
    public void exceptionTestDelete2() {
    	test.add(new Clause("clause 1"));
    	test.delete(clause1);
    }
    
    @Test
	public void testModifyInformation() {
    	test.add(new Clause("clause 1"));    	
		test.modifyInformation(0, new Clause("clause 2"));
	}
    
    @Test
	public void testGetClause() {
    	test.add(new Clause("clause 1"));
		test.getClause(0);
	}
    
	/**
	 * Test method for {@link Category#size()}.
	 */
	@Test
	public void testSize() {
		assertEquals(0, test.size());
		
		test.add(clause1);
		test.add(new Clause("clause 2"));
		assertEquals(2, test.size());

		test.delete(clause1);
		assertEquals(1, test.size());
	}

	/**
	 * Test method for {@link Category#getName()}.
	 */
	@Test
	public void testGetName() {
		assertEquals("Category 1", test.getName());
		
		test.setName("Category 2");
		assertEquals("Category 2", test.getName());
	}

	/**
	 * Test method for {@link Category#toString()}.
	 */
	@Test
	public void testToString() {
		test.add(clause1);
		test.add(clause3);
		System.out.println(test.toString());
		StringBuilder sb = new StringBuilder();
		sb.append("clause 1\n");
		sb.append("--------\n");
		sb.append("this is the info.\n\n");
		sb.append("clause 3\n");
		sb.append("--------\n");
		sb.append("<EMPTY>\n");		
		assertEquals(sb.toString(), test.toString());
	}
}
