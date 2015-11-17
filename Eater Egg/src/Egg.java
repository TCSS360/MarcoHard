import java.util.ArrayList;

/**
 * The egg has Macrohard member's name.
 */
public class Egg {
	/**
	 * team is the name of the team.
	 */
	private String team;
	
	/**
	 * member is the team members' name.
	 */
	private ArrayList<String> member;
	
	/**
	 * Constructor with input.
	 * @param st 
	 */
	public Egg(String st){
		this.team = st;
		member = new ArrayList<String>();
	}	
	
	/**
	 * 
	 * @return the team name.
	 */
	public String getTeam(){
		return this.team;
	}
	/**
	 * Adding new team member name.
	 * @param name is the member name. 
	 */
	public void setMember(String name) {
		this.member.add(name);
	}
	/**
	 * Get the member name
	 * @param index of the member
	 * @return the name of member at that index
	 */
	public String getMember(int index){
		return this.member.get(index);
	}
}
