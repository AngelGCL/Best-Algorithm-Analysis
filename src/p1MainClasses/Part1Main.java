package p1MainClasses;

import java.io.FileNotFoundException;

import dataGenerator.DataReader;
import solutions.File2Process;
import solutions.Solution_3;
import solutions.Solution_4;
import solutions.Solutions_1_and_2;

/**
 * 
 * <p>
 * This is the main class for the first part of this project. Here we try each
 * of the solutions given by all the P programmers: P1, P2, P3, P4
 * 
 * <p>
 * The arguments to use the strategies:
 * <ol>
 * <li>Write 1, 2, 3 or 4. The method only accepts the first argument and has to
 * be written as such for it to run a test
 * <li>Write a number that is not 1-4 and the code will run all tests
 * <li>If there are no arguments then the code will run every strategy.
 * </ol>
 * 
 * 
 * @throws FileNotFoundException
 *             If the file that the DataReader instance does not exist in a
 *             directory, child of the program's directory
 * 
 * @author Angel G. Carrillo Laguna 801141100 CIIC4020-030
 * @email angel.carrillo1@upr.edu
 * 
 */
public class Part1Main {

	/**
	 * 
	 * @param args
	 *            Accepts up to one integer argument.
	 * 
	 * @throws FileNotFoundException
	 */
	public static void main(String[] args) throws FileNotFoundException {
		Solutions_1_and_2 p1 = new Solutions_1_and_2("1");
		Solutions_1_and_2 p2 = new Solutions_1_and_2("2");
		Solution_3 p3 = new Solution_3("3");
		Solution_4 p4 = new Solution_4("4");
		DataReader dr = new DataReader();

		File2Process data = new File2Process(dr.readDataFiles());
		if (args.length > 1) {
			System.out.println("Invalid arguments: Must be length <= 1");
		} else if (args.length == 1)
			switch (args[0]) {
			case "1":
				System.out.println("Final Set by P1: " + p1.intersectSets(data.dataPreProcess(1)));
				break;
			case "2":
				System.out.println("Final Set by P2: " + p2.intersectSets(data.dataPreProcess(2)));
				break;
			case "3":
				System.out.println("Final Set by P3: " + p3.intersectSets(data.dataPreProcess(2)));
				break;
			case "4":
				System.out.println("Final Set by P4: " + p4.intersectSets(data.dataPreProcess(2)));
				break;

			}

		else {
			System.out.println("Final Set by P1: " + p1.intersectSets(data.dataPreProcess(1)));

			System.out.println("Final Set by P2: " + p2.intersectSets(data.dataPreProcess(2)));

			System.out.println("Final Set by P3: " + p3.intersectSets(data.dataPreProcess(2)));

			System.out.println("Final Set by P4: " + p4.intersectSets(data.dataPreProcess(2)));
		}

	}

}
