package app;

public class SimpleFileHashUtilityLoader {
	public static void main(String[] args) {
		try {
			SimpleFileHashUtility.run(args);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}