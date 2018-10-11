package solutions;

import interfaces.MySet;
import mySetImplementations.Set1;
import mySetImplementations.Set2;

/**
 * 
 * Objects of this type can process the dataset they receive and create a
 * {@link MySet} array containing all the family of sets that will be
 * intersected.
 * 
 * @author Angel G. Carrillo Laguna 801141100 CIIC4020-030
 * @email angel.carrillo1@upr.edu
 * 
 */
public class File2Process {

	private Object[][][] dataMultiSet;

	/**
	 * Constructor for an instance of this type of object.
	 * 
	 * @param daTa
	 *            raw data which is a three-dimensional {@link Object} array
	 */
	public File2Process(Object[][][] daTa) {
		this.dataMultiSet = daTa;
	}

	/**
	 * Converts the raw data into an array containing all the family of sets.
	 * 
	 * @param typeOfSet
	 *            an {@link Integer} that will determine if the sets inside the
	 *            array are {@link Set1} or {@link Set2}
	 * 
	 * @return returns an {@link MySet} type array containing all the family of
	 *         sets.
	 * 
	 */
	public MySet[] dataPreProcess(Integer typeOfSet) {

		MySet[] t = new MySet[this.dataMultiSet.length];

		for (int i = 0; i < this.dataMultiSet.length; i++) {
			if (typeOfSet == 1)// this should depend on the strategy that will use the array t.
				t[i] = new Set1();
			else {
				t[i] = new Set2();
			}

			for (int j = 0; j < this.dataMultiSet[i].length; j++) {
				for (int k = 0; k < this.dataMultiSet[i][j].length; k++) {
					t[i].add(this.dataMultiSet[i][j][k]);
					;
				}
			}
		}
		return t;
	}
}
