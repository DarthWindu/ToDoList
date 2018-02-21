package backend.config;

public class GlobalConfigManager {
	
	public static boolean TESTING_MODE_ENABLED = false;
		
	private static String APP_VERSION;
	private static ReleaseType RELEASE_TYPE;
	
	public static void setGlobalTestingMode(boolean enableTesting) {
		TESTING_MODE_ENABLED = enableTesting;
	}

	public static ReleaseType getReleaseType() {
		return RELEASE_TYPE;
	}

	public static void setReleaseType(ReleaseType releaseType) {
		RELEASE_TYPE = releaseType;
	}
}
