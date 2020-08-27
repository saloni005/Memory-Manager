package memory;

import java.util.HashMap;
import java.util.Map;

public class Memory {

	public static int MAX_MEMORY;
	public static int THRESHOLD;
	public static Block[] mem_allocated;
	public int mem_occupied = 0;
	public HashMap<String, Process> processes;

	public Memory(int max_blocks) {
		Memory.MAX_MEMORY = max_blocks;
		THRESHOLD = Memory.MAX_MEMORY / 4;
		mem_allocated = new Block[MAX_MEMORY];
		this.processes = new HashMap<>();
	}

	private static BlockRange getAllocatableBlock(String process_name, String variable, int mem_size) {
		int block_count = 0, start = 0, end = 0;

		for (int i = 0; i < MAX_MEMORY;) {

			while (i < MAX_MEMORY && !isBlockAvailable(mem_allocated[i], process_name, variable)) {
				i++;
				continue;
			}

			if (isBlockAvailable(mem_allocated[i], process_name, variable)) {
				start = i;
				block_count++;

				while (i < MAX_MEMORY && isBlockAvailable(mem_allocated[i], process_name, variable)) {
					i++;
					block_count++;

					if (block_count == mem_size) {
						end = i;
						return new BlockRange(start, end);
					}
				}
			}
		}
		return null;
	}

	private static boolean isBlockAvailable(Block block, String process_name, String variable) {
		return (null == block
				|| (process_name.equals(block.getProcess_name()) && variable.equals(block.getVariable_name())));
	}

	public void execAllocate(String process_name, String variable, int mem_demand) {

		if (mem_demand > MAX_MEMORY) {
			printMessage("1error");
			return;
		}

		if (!this.processes.containsKey(process_name)) {
			Process process = new Process();
			BlockRange mem_block = Memory.getAllocatableBlock(process_name, variable, mem_demand);

			if (null == mem_block) {
				printMessage("2error");
				return;
			}

			allocateMemory(process_name, variable, mem_block);
			process.getAllocation().put(variable, mem_block);
			this.processes.put(process_name, process);
			mem_occupied += mem_demand;
			printMessage("success");
		} else {
			Process process = this.processes.get(process_name);
			System.out.println(THRESHOLD);
			if (process.getMem_occupied() + mem_demand < THRESHOLD) {
				BlockRange mem_block = Memory.getAllocatableBlock(process_name, variable, mem_demand);

				if (null == mem_block) {
					System.out.println("3error");
					return;
				}

				process.getAllocation().put(variable, mem_block);
				mem_occupied += mem_demand;
				printMessage("success");
			} else {
				System.out.println("4error");
				return;
			}
		}
	}

	private void allocateMemory(String process_name, String variable, BlockRange mem_block) {
		for (int i = mem_block.start; i <= mem_block.end; i++) {
			mem_allocated[i] = new Block(process_name, variable);
		}
	}

	public void execFree(String process_name, String variable) {
		if (processes.containsKey(process_name)) {
			Map<String, BlockRange> var_allocated = processes.get(process_name).getAllocation();

			if (var_allocated.containsKey(variable)) {
				var_allocated.remove(variable);
				freeVariableMemory(process_name, variable);
				printMessage("success");
			} else {
				printMessage("error");
			}
		} else {
			printMessage("error");
		}
	}

	private void freeVariableMemory(String process_name, String variable) {
		for (int i = 0; i < MAX_MEMORY; i++) {
			Block block = mem_allocated[i];
			if (null != block && process_name.equals(block.getProcess_name())
					&& variable.equals(block.getVariable_name())) {
				mem_allocated[i] = null;
				mem_occupied--;
			}
		}
	}

	public void execKill(String process_name) {
		if (processes.containsKey(process_name)) {
			processes.get(process_name).getAllocation().keySet()
					.forEach(variable -> freeVariableMemory(process_name, variable));
			processes.remove(process_name);
			printMessage("success");
		} else {
			printMessage("error");
		}

	}

	public void execInspect(String process_name) {

//		for (Block block : mem_allocated) {
//			if (null != block)
//				System.out.println(block.toString());
//		}
//
//		System.out.println(processes);

		if (processes.containsKey(process_name)) {
			processes.get(process_name).getAllocation()
					.forEach((k, v) -> System.out.println(k + " " + v.start + "-" + v.end));
		} else {
			printMessage("error");
		}
	}

	private void printMessage(String status) {
		System.out.println(status + " " + mem_occupied + "/" + (MAX_MEMORY - mem_occupied));
	}

}
