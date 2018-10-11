package solutions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import interfaces.MySet;
import mySetImplementations.Set2;
import setIntersectionFinders.AbstractIntersectionFinder;

/**
 * A sub-class of {@link AbstractIntersectionFinder} that uses the strategy
 * given by P4
 * 
 * <p>
 * P4 uses {@link HashMap} to filter the elements by taking each element from
 * the {@code allElements} putting them inside the hash map as an entry and the
 * counter for this element shall be its value. thus the entry with the value
 * equal to the amount of sets in array {@code t} shall be added to an empty set
 * {@code f} which is the returned set.
 * 
 * @author Angel G. Carrillo Laguna 801141100 CIIC4020-030
 * @email angel.carrillo1@upr.edu
 * 
 */
public class Solution_4 extends AbstractIntersectionFinder {

	/**
	 * Constructor for the instance of this type of object.
	 * 
	 * @param name
	 *            used to identify this strategy. Can be used for when selecting a
	 *            strategy to use.
	 */
	public Solution_4(String name) {
		super(name);

	}

	/**
	 * @return the final set {@code f} which contains only the elements that appear
	 *         in every single set inside {@code t}
	 */
	@Override
	public MySet intersectSets(MySet[] t) {

		ArrayList<Integer> allElements = new ArrayList<>();
		HashMap<Integer, Integer> map = new HashMap<>();

		// Creating the Integer ArrayList with all the elements as per specifications.
		for (int i = 0; i < t.length; i++) {
			for (Object o : t[i]) {
				allElements.add((Integer) o);
			}
		}

		allElements.sort(null);

		// Creating the hash map with which the elements will be filtered
		for (Integer e : allElements) {
			Integer c = map.getOrDefault(e, 0);
			map.put(e, c + 1);
		}
		// final set f initially empty
		Set2 f = new Set2();
		for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
			if (entry.getValue() == t.length)
				f.add(entry.getKey());
		}

		return f;
	}

}
