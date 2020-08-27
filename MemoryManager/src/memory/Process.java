package memory;

import java.util.HashMap;

public class Process{
	private HashMap<String, BlockRange> allocation;
	private int mem_occupied = 0;
	
	public Process() {
		this.allocation  = new HashMap<>();
	}

	/**
	 * @return the allocation
	 */
	public HashMap<String, BlockRange> getAllocation() {
		return allocation;
	}

	/**
	 * @param allocation the allocation to set
	 */
	public void setAllocation(HashMap<String, BlockRange> allocation) {
		this.allocation = allocation;
	}

	/**
	 * @return the mem_occupied
	 */
	public int getMem_occupied() {
		return mem_occupied;
	}

	/**
	 * @param mem_occupied the mem_occupied to set
	 */
	public void setMem_occupied(int mem_occupied) {
		this.mem_occupied = mem_occupied;
	}

	@Override
	public String toString() {
		return "Process [allocation=" + allocation + ", mem_occupied=" + mem_occupied + "]";
	}
	
	
}
