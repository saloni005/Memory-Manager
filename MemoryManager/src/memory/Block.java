package memory;

public class Block {
	private String process_name;
	private String variable_name;

	/**
	 * @param process_name
	 * @param variable_name
	 */
	public Block(String process_name, String variable_name) {
		super();
		this.process_name = process_name;
		this.variable_name = variable_name;
	}

	/**
	 * @return the process_name
	 */
	public String getProcess_name() {
		return process_name;
	}

	/**
	 * @param process_name the process_name to set
	 */
	public void setProcess_name(String process_name) {
		this.process_name = process_name;
	}

	/**
	 * @return the variable_name
	 */
	public String getVariable_name() {
		return variable_name;
	}

	/**
	 * @param variable_name the variable_name to set
	 */
	public void setVariable_name(String variable_name) {
		this.variable_name = variable_name;
	}

	@Override
	public String toString() {
		return "Block [process_name=" + process_name + ", variable_name=" + variable_name + "]";
	}

}
