
public class COVIDTracker {

	public static boolean addTest(String[] pos, String[] neg, String id, boolean isPos) {
		/*
		 * adds a test to an array
		 */
		if (isPos) {
			return loop(pos, id);
		}
			
		else {
			return loop(neg, id);
		}	
	}
	private static boolean loop(String[] ar, String id) {
		/*
		 helper function for addTest
		 loops through an array to check for first null, then replaces null with given id and then breaks
		 * 
		 */
		boolean flag = false;
		for (int index = 0; index < ar.length; index++) {
			if (ar[index] == null) {
				ar[index] = id;
				flag = true;
				break;
			}
		}
		return flag;
	}
	public static boolean removeIndividual(String[] pos, String[] neg, String id) {
		/*
		 * returns true or false if an element was deleted from on of the arrays
		 */
		return (delete(pos, id) || delete(neg, id)); 
			
	}
	private static boolean delete(String[] ar, String id) {
		/*
		 helper function for removeIndividual
		 loops through ar and checks for id
		 if true then it replaces it with null and returns true after traversing through the rest of the ar
		 * 
		 */
		boolean flag = false;
		for (int index = 0; index < ar.length; index++) {
			if (ar[index] == id) {
				ar[index] = null;
				flag = true;
			}
		}
		return flag;
	}
	
	public static String getPopStats(String[] pos, String[] neg) {
		/*
		 * returns a String with population stats
		 */
		int numPos = 0;
		int numNeg = 0;
		numPos = counter(pos);
		numNeg = counter(neg);
		int total = numPos + numNeg;
		int ind = unique(pos, neg);
		double posTests = ((double)numPos / (double)total) * 100;
		for (int x = 0; x < pos.length; x++) {
			for (int y = x + 1; y < pos.length; y++) {
				if(pos[x] == pos[y] && pos[x] != null) {
					pos[y] = null;
				}
			}
		}
		int count = counter(pos);
		double prop = ((double) count / (double) ind) * 100;
		int temp = 0;
		for (int x = 0; x < pos.length; x++) {
			if(pos[x] == null || pos[x] == "") {
				temp++;
			}
		}
		if(temp == pos.length) {
			prop = 0.0;
			posTests = 0.0;
		}
		return ("Total tests: " + total + "\n"
				+ "Total individuals tested: " + ind + "\n"
						+ "Percent positive tests: " + posTests + "%\n"
								+ "Percent positive individuals: " + prop + "%");
	}
	private static int counter(String[] ar) {
		/*
		 * helper function for getPopStats
		 * returns number of non-null elements in an array
		 */
		int count = 0;
		for (int index = 0; index < ar.length; index++) {
			if (ar[index] != null && ar[index] != "") {
				count++;
			}
		}
		return count;
	}
	private static int unique(String[] ar, String[] list) {
		/*
		 * helper function for getPopStats
		 * returns the number unique elements between two arrays
		 */
		int count = 0;
		String[] finList = new String[ar.length + list.length];
		for(int index = 0; index < ar.length; index++) {
			finList[index] = ar[index];
		}
		for (int z = 0; z < list.length; z++) {
			finList[z + (ar.length)] = list[z];
		}
		
		for (int x = 0; x < finList.length; x++) {
			for (int y = x + 1; y < finList.length; y++) {
				if (finList[x] == finList[y] && finList[x] != null) {
					finList[y] = null;
				}
			}
		}
		count = counter(finList);
		return count;
		
	}
	public static String getIndividualStats(String[] pos, String[] neg, String id) {
		int posTests = totalTests(pos, id);
		int negTests = totalTests(neg, id);
		int totalTests = posTests + negTests;
		return "Total tests: " + totalTests + "\n"
				+ "Positive: " + posTests + "\n"
						+ "Negative: " + negTests;
	}
	private static int totalTests(String[] ar, String id) {
		/*
		 * helper function for getIndividualStats
		 * records the number of tests a certain individual has taken
		 */
		int count = 0;
		for (int index = 0; index < ar.length; index++) {
			if (ar[index] == id) {
				count++;
			}
		}
		return count;
	}

}
