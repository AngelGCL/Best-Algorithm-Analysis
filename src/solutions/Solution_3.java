package solutions;

import java.util.ArrayList;

import interfaces.MySet;
import mySetImplementations.Set2;
import setIntersectionFinders.AbstractIntersectionFinder;

/**
 * A sub-class of the {@link AbstractIntersectionFinder} which uses the strategy
 * given by P3.
 * 
 * <p>
 * P3 uses a list with all the elements in the dataset to find only those
 * elements that appear in every single set of data and fills an empty set with
 * such elements.
 * 
 * @author Angel G. Carrillo Laguna 801141100 CIIC4020-030
 * @email angel.carrillo1@upr.edu
 * 
 */
public class Solution_3 extends AbstractIntersectionFinder {

	/**
	 * Constructor for the instance of this type of object.
	 * 
	 * @param name
	 *            to identify the strategy, it can be used when selecting a strategy
	 *            to use.
	 */
	public Solution_3(String name) {
		super(name);

	}

	/**
	 * @return the final set {@code f} containing only the elements that appear in
	 *         every single set in the {@code t}.
	 */
	@Override
	public MySet intersectSets(MySet[] t) {

		ArrayList<Integer> allElements = new ArrayList<>();

		// An ArrayList that contains all the elements of every set from the dataset.
		// As the specifications it is instanciated as an Integer array list.
		for (int i = 0; i < t.length; i++) {
			for (Object o : t[i]) {
				allElements.add((Integer) o);
			}
		}
		allElements.sort(null);

		// P3 uses a Set2 type of MySet
		// this is the empty set to be filled with the elements in every set.
		Set2 f = new Set2();
		Integer e = allElements.get(0);// take the first element to start the counting.
		Integer c = 1;// counter variable.
		for (int i = 1; i < allElements.size(); i++) {
			if (allElements.get(i).equals(e)) {
				c++;// every time the element appears in the allElements array list, we count one
					// more repetition.
			} else {
				if (c == t.length) { // if the counter for the element is equal to the amount of sets in the array t
					f.add(e); // we add the element e to the MySet f.
				}
				e = allElements.get(i);
				c = 1;
			}
		}

		return f;
	}

}
