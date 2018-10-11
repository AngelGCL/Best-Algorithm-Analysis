package p1MainClasses;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.AbstractMap;
import java.util.ArrayList;

import dataGenerator.DataGenerator;
import solutions.Solution_3;
import solutions.Solution_4;
import solutions.Solutions_1_and_2;

/**
 * <p>
 * Main class for experiments using each strategy. It will evaluate the average
 * time it takes each strategy to finish its procedure for different datasets
 * with different sizes.
 * 
 * @author Angel G. Carrillo Laguna 801141100 CIIC4020-030
 * @email angel.carrillo1@upr.edu
 *
 */
public class Part2Main {

	// default parameters
	/**/
	private static int finalSize = 50000;
	private static int incrementalSizeStep = 1000;
	private static int initialSize = 1000;
	private static int repetitionsPerSize = 200;
	private static int m = 50;
	private static int n = 10;
	/**/
	/**
	 * ArrayList containing all the strategies| used for accessing each strategy.
	 * The elements are of type {@link StrategiesTimeCollection}.
	 */
	private static ArrayList<StrategiesTimeCollection<Integer>> resultsPerStrategy;

	/**
	 * Adds the strategies to the {@code resultsPerStrategy} arraylist.
	 */
	private static void prepStratList() {
		resultsPerStrategy = new ArrayList<>();
		resultsPerStrategy.add(new StrategiesTimeCollection<Integer>(new Solutions_1_and_2("1")));
		resultsPerStrategy.add(new StrategiesTimeCollection<Integer>(new Solutions_1_and_2("2")));
		resultsPerStrategy.add(new StrategiesTimeCollection<Integer>(new Solution_3("3")));
		resultsPerStrategy.add(new StrategiesTimeCollection<Integer>(new Solution_4("4")));
	}

	/**
	 * Saves the results from the experiments.
	 * 
	 * @basedOn {@code saveResults()} from {@linkplain ExperimentController} class
	 *          by Prof. P. Rivera
	 * @throws FileNotFoundException
	 *             If the file does not exists inside the directory of this program
	 */
	private static void saveResults() throws FileNotFoundException {
		PrintWriter out = new PrintWriter(new File("experimentalResults", "allResults.txt"));
		StringBuilder writer = new StringBuilder();
		writer.append("Size\tP1\tP2\tP3\tP4\n");
		int rep = resultsPerStrategy.get(0).size();
		for (int i = 0; i < rep; i++) {
			writer.append(resultsPerStrategy.get(0).get(i).getKey() + "\t");
			writer.append(resultsPerStrategy.get(0).get(i).getValue() + "\t");
			writer.append(resultsPerStrategy.get(1).get(i).getValue() + "\t");
			writer.append(resultsPerStrategy.get(2).get(i).getValue() + "\t");
			writer.append(resultsPerStrategy.get(3).get(i).getValue() + "\n");
		}
		out.println(writer.toString());
		out.close();
	}

	/**
	 * Tests the strategies for different dataset sizes. It will take all six
	 * arguments. wether defaults or user submitted, as the parameters.
	 * 
	 * @basedOn {@code run()} from {@linkplain ExperimentController} by Prof. P.
	 *          Rivera
	 * @param n
	 * @param m
	 * @param initialSize
	 * @param finalSize
	 * @param sizeStep
	 * @param repetitionPerSize
	 */
	private static void doTest(int n, int m, int initialSize, int finalSize, int sizeStep, int repetitionPerSize) {

		for (int size = initialSize; size <= finalSize; size += incrementalSizeStep) {

			for (StrategiesTimeCollection<Integer> strategy : resultsPerStrategy) {
				strategy.resetSum(); // set to 0 an internal instance variable used to accumulate sum of times
			}

			for (int r = 0; r < repetitionsPerSize; r++) {

				Integer[][][] dataset = (Integer[][][]) new DataGenerator(n, m, size).generateData();

				for (StrategiesTimeCollection<Integer> strategy : resultsPerStrategy) {
					long startTime = System.nanoTime();
					strategy.runTrial(dataset);
					long endTime = System.nanoTime();
					strategy.incSum((int) (endTime - startTime));
				}
			}

			// For each strategy, compute the average time for the current size.
			for (StrategiesTimeCollection<Integer> strategy : resultsPerStrategy) {
				strategy.add(
						new AbstractMap.SimpleEntry<Integer, Float>(size, (strategy.getSum() / repetitionsPerSize)));
			}

			/* */

		}

	}

	/**
	 * Main method to test the strategies. After the results have been saved, the
	 * method will print out the address of the file containing the results.
	 * 
	 * @param args
	 *            Up to six integer arguments. In this order:
	 *            <ol>
	 *            <li>n - number of companies
	 *            <li>m - number of events
	 *            <li>iSize - initial size
	 *            <li>fSize - final size
	 *            <li>sizeUp - rate of size incrementation
	 *            <li>rep - repetitions per size </o>
	 * @Warning Integers n and m are bounded as such that n*m*dataset[i][j].length =
	 *          size. Values that do not follow this, will not run properly.
	 * @throws FileNotFoundException
	 */
	public static void main(String[] args) throws FileNotFoundException {
		// array with default parameters
		int[] newArgs = { n, m, initialSize, finalSize, incrementalSizeStep, repetitionsPerSize };
		prepStratList();
		if (args.length > 6)
			System.out.println("Invalid number of arguments: must be <= 6");
		else {
			for (int i = 0; i < args.length; i++)
				newArgs[i] = Integer.parseInt(args[i]);// if the length < 6, the last i-th arguments will stay as
														// default
		}

		doTest(newArgs[0], newArgs[1], newArgs[2], newArgs[3], newArgs[4], newArgs[5]);
		saveResults();

		/*
		 * THIS IS TO HELP FIND THE FILE WITH THE RESULTS AND TO LET THE USER KNOW THAT
		 * THE PROGRAM FINISHED.
		 */
		File dir = new File("./src/experimentalResults/allResults.txt");
		try {
			System.out.println("Done. You can find the results here: " + "\n" + dir.getCanonicalPath());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/**/

	}

}
