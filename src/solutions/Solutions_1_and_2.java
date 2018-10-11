package solutions;

import java.util.Iterator;

import interfaces.MySet;
import mySetImplementations.Set1;
import mySetImplementations.Set2;
import setIntersectionFinders.AbstractIntersectionFinder;

/**
 * 
 * A sub-class of the {@link AbstractIntersectionFinder} which uses the
 * strategies given by P1 and P2.
 * <p>
 * P1 and P2 create a {@link Set1} or a {@link Set2} respectively, which will
 * have the elements of the first set in the family of sets {@code t} and then
 * remove any element that does not appear in any other set in the family.
 * 
 * @author Angel G. Carrillo Laguna 801141100 CIIC4020-030
 * @email angel.carrillo1@upr.edu
 * 
 */
public class Solutions_1_and_2 extends AbstractIntersectionFinder {

	/**
	 * Constructor for the object of this type
	 * 
	 * @param name
	 *            Identifies the strategy. Can be used when selecting a strategy to
	 *            use.
	 */
	public Solutions_1_and_2(String name) {
		super(name);
	}

	/**
	 * @return Returns a set containing only the elements that appear in all of the
	 *         sets in the array {@code t}
	 */
	@Override
	public MySet intersectSets(MySet[] t) {
		MySet f;
		// Uses the strategy by P1
		if (getName() == "1") {
			f = new Set1();
			f = t[0];// the set will have the elements of the first set in t.
		}

		// Uses the strategy by P2
		else {
			f = new Set2();
			f = t[0];// set receives the elements of the first set in t
		}

		for (int i = 1; i < t.length; i++) {
			Iterator it = f.iterator();
			while (it.hasNext()) {
				if (!t[i].contains(it.next())) {
					it.remove();
				}
			}
		}

		return f;
	}

}
