import java.util.ArrayList;

/**
 * This class is used to category and its clauses as value.
 *
 * @param <K>
 * @param <V>
 */
public class MyHashMap <K, V>{
	/**
	 * The table hold the Key and Value []
	 */
	private Nodes<K, V>[] bucket;
	/**
	 * HashMap constructor.
	 */
	MyHashMap(){
		bucket = new Nodes[32768];
	}
	
	/**
	 * Put the key and value into the table
	 * @param searchKey
	 * @param value
	 */
	public void put(K searchKey, V value){		
		int index = hashCode(searchKey);
		//If the node is empty then then put a new value and key into this node.
		if(bucket[index] == null){
			bucket[index] = new Nodes<K, V>(searchKey, value);
		} else {
			//If the node is not empty then then put a new value into this node.
			while(bucket[index] != null){
				if(bucket[index].key.equals(searchKey)){
					bucket[index].addValue(value);
					break;
				} else {
					index = (index + 1) % bucket.length;
				}
			}
		}
	}
	
	/**
	 * Get the all the values at this node base on the key.
	 * @param searchKey
	 * @return The array of values.
	 */
	public ArrayList<V> getValue(K searchKey){
		int index = hashCode(searchKey);
		ArrayList<V> toReturn = new ArrayList<V>();
		//Check this not only this not is not empty.
		while(bucket[index] != null){
			if(bucket[index].key.equals(searchKey)){
				toReturn = bucket[index].getValue();
				break;
			} else {
				index = (index + 1) % bucket.length;
			}
		}
		return toReturn;
	}
	
	/**
	 * Check the searchKey is existed
	 * @param searchKey
	 * @return true or false
	 */
	public boolean constainsKey(K searchKey){
		boolean flag = false;
		K search = (K) searchKey.toString().toLowerCase();
		int index = hashCode(search);
		
		while(bucket[index] != null){
			if(bucket[index].key.equals(search)){
				flag = true;
				break;
			} else {
				index = (index + 1) % bucket.length;
			}
		}
		return flag;
	}
	
	/**
	 * Convert the words into #
	 * @param words
	 * @return Integer
	 */
	public int hashCode(K words){
		int sum = ((String)words).length();
		
		for(int j = 0; j < ((String)words).length(); j++){
			sum = (int)(sum + ((String)words).charAt(j)) * 9;
		}	
		return Math.abs(sum % bucket.length);		
	}
	
	/**
	 * The node class is stored the key and value.
	 * @param <K> is the String for searching.
	 * @param <V> is the value object which contains category name and its clauses.
	 */
	private class Nodes<K, V>{
		private K key;
		private ArrayList<V> values = new ArrayList<V>();
		
		public Nodes(K key, V value){
			this.key = key;
			addValue(value);
		}
		
		/**
		 * Add the new value into this node which has similarly key
		 * @param value
		 */
		public void addValue(V value){
			if(this.values.size() != 0){
				boolean exist = false;
				for(int i = 0; i < this.values.size(); i++){
					if(this.values.get(i).equals(value)){
						exist = true;
						break;
					}
				}
				
				if(!exist) this.values.add(value);
			} else {
				this.values.add(value);
			}
		}
		
		/**
		 * Get the array of values as that has same key
		 * @return the value array
		 */
		public ArrayList<V> getValue(){			
			return this.values;
		}
	}
}
