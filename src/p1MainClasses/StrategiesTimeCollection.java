package p1MainClasses;

import java.util.AbstractMap;
import java.util.ArrayList;

import interfaces.IntersectionFinder;
import solutions.File2Process;

/**
 * 
 * A collection that extends the {@link ArrayList} and the {@link AbstractMap}
 * classes. This collection can use the methods of an ArrayList with AbstractMap
 * elements. It can be used to evaluate the time that a strategy takes to run
 * and recollect the average time for different size of data.
 * 
 * @author Angel G. Carrillo Laguna 801141100 CIIC4020-030
 * @email angel.carrillo1@upr.edu
 * 
 * @param <Integer>
 *            All instances of this collection are of {@linkplain Integer}.
 */
public class StrategiesTimeCollection<Integer> extends ArrayList<AbstractMap.SimpleEntry<Integer, Float>> {

	/**
	 * serialVersion is default. Added for commentation purposes
	 */
	private static final long serialVersionUID = 1L;
	private int sum;// sum of the times
	File2Process processor;// processes data
	private IntersectionFinder strategy;// strategy to be instantiated

	/**
	 * Constructor for this type of object.
	 * 
	 * @param strategy
	 *            Receives a {@link IntersectionFinder} which will be one of the
	 *            strategies given.
	 */
	public StrategiesTimeCollection(IntersectionFinder strategy) {
		this.sum = 0;
		this.strategy = strategy;
	}

	/**
	 * 
	 * @return Returns the name given to identify the strategy.
	 */
	public String getStratName() {
		return strategy.getName();
	}

	public void resetSum() {
		this.sum = 0;
	}

	/**
	 * Runs the corresponding strategy with the dataset it is given.
	 * 
	 * @param dataset
	 *            Receives the raw data which will be pre-processed before it is
	 *            filtered by the corresponding strategy.
	 */
	public void runTrial(Integer[][][] dataset) {
		processor = new File2Process(dataset);
		switch (getStratName()) {// it will run the strategy given by P1
		case "1":
			strategy.intersectSets(processor.dataPreProcess(1));
			break;

		default:// it will run the corresponding strategy: P2, P3 or P4
			strategy.intersectSets(processor.dataPreProcess(2));
			break;
		}
	}

	/**
	 * Adds the estimated time to the sum.
	 * 
	 * @param estimatedTime
	 *            Integer value to be added.
	 */
	public void incSum(int estimatedTime) {
		this.sum += estimatedTime;
	}

	public float getSum() {
		return sum;
	}

}
