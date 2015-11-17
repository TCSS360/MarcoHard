import static org.junit.Assert.*;

import org.junit.Test;

public class EggTest {
	private Egg egg;

	@Test
	public void testEgg() {	
		egg = new Egg("Macrohard");
	}

	@Test
	public void testGetTeam() {
		egg = new Egg("Macrohard");
		assertTrue(egg.getTeam().equals("Macrohard"));
	}

	@Test
	public void testSetMember() {
		egg = new Egg("Macrohard");
		egg.setMember("test");
		assertTrue(egg.getMember(0).equals("test"));
	}

	@Test
	public void testGetMember() {
		egg = new Egg("Macrohard");
		egg.setMember("test");
		assertTrue(egg.getMember(0).equals("test"));
	}

}
