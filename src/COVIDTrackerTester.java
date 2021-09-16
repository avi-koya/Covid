
public class COVIDTrackerTester {

	public static void main(String[] args) {
		System.out.println("testAddtest(): " + testAddTest());
		System.out.println("testRemoveIndividual(): " + testRemoveIndividual());
		System.out.println("testGetPopStats(): " + testGetPopStats());
		System.out.println("testGetIndividualStats(): " + testGetIndividualStats());
	}
	public static boolean testAddTest() {
		String[] pos = new String[2];
		String[] neg = new String[2];
		if (!COVIDTracker.addTest(pos, neg, "AB1234", false) || neg[0] == null) {
			return false;
		}
		if (!COVIDTracker.addTest(pos, neg, "CD2345", true) || pos[0] == null) {
			return false;
		}
		// two arrays with space -> true
		if (!COVIDTracker.addTest(pos, neg, "CD2345", false) || neg[1] == null) {
			return false;
		}
		// one full array but adding to one with space -> true
		if (!COVIDTracker.addTest(pos, neg, "EF3456", true) || pos[1] == null) {
			return false;
		}
		// one array with space but adding to full one -> false
		String[] pos2 = new String[2];
		if (COVIDTracker.addTest(pos2, neg, "EF3456", false)) {
			return false;
		}
		// two full arrays -> false
		if (COVIDTracker.addTest(pos, neg, "EF3456", true)) {
			return false;
		}
		return true;

	}
	public static boolean testRemoveIndividual() {
		String[] pos = new String[2];
		String[] neg = new String[2];
		pos[0] = "AB123";
		pos[1] = "BC234";
		neg[0] = "DC456";
		neg[1] = "AB123";
		String id = "AB222";
		boolean flag = false;
		if(!COVIDTracker.removeIndividual(pos, neg, id)) {
			flag = true;
		}
		id = "BC234";
		if (COVIDTracker.removeIndividual(pos, neg, id)) {
			flag = true;
		}
		id = "EEEE2";
		if(COVIDTracker.removeIndividual(pos, neg, id)) {
			flag = false;
		}
		return flag;
	}
	public static boolean testGetPopStats() {
		String[] pos = {"AB123", "EC567", "CD234", "AB123"};
		String[] neg = {"AB123", "AY221", "GH876", "EC567"};
		String ans = (COVIDTracker.getPopStats(pos, neg));		
		if (ans.equals("Total tests: 8\n"
				+ "Total individuals tested: 5\n"
				+ "Percent positive tests: 50.0%\n"
				+ "Percent positive individuals: 60.0%")) {
			return true;
		}
		return false;
	}
	public static boolean testGetIndividualStats() {
		String[] pos = {"AB123", "EC567", "CD234", "AB123"};
		String[] neg = {"AB123", "AY221", "GH876", "EC567"};
		String id = "AB123";
		String ans = COVIDTracker.getIndividualStats(pos, neg, id);
		if(ans.equals("Total tests: 3\n"
				+ "Positive: 2\n"
				+ "Negative: 1")) {
			return true;
		}
		return false;
	}

	

}
