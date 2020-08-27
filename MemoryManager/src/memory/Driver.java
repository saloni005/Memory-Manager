package memory;

import java.util.Scanner;

public class Driver {
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		Memory memory = new Memory(sc.nextInt());

		int t = 0;

		while (t++ <= (int) Math.pow(10, 6)) {
			String command = sc.next();

			String process_name = sc.next();

			switch (command) {
			case "allocate": {
				memory.execAllocate(process_name, sc.next(), sc.nextInt());
			}
				break;
			case "free": {
				memory.execFree(process_name, sc.next());
			}
				break;
			case "kill": {
				memory.execKill(process_name);
			}
				break;
			case "inspect": {
				memory.execInspect(process_name);
			}
				break;
			case "exit": {
				System.out.println("exiting...");
			}
				break;
			default: {
				System.out.println("Invalid Command!!");
			}
				break;
			}
		}

		sc.close();
	}
}
