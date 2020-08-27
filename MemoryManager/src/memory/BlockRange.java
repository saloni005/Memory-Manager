package memory;

public class BlockRange {
	int start;
	int end;
	
	public BlockRange(int start, int end) {
		this.start = start;
		this.end = end;
	}

	@Override
	public String toString() {
		return "BlockRange [start=" + start + ", end=" + end + "]";
	}
}
