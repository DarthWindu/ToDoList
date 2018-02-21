package backend.config;

public class AppConfigBuilder{
	
	public AbstractAppConfigAdapter create() {
		return new AppConfigAdapter();
	}
	
	private class AppConfigAdapter extends AbstractAppConfigAdapter {
		
		AppConfigAdapter() {
			initialize();
		}
		
		@Override
		protected void initialize() {
			// TODO Auto-generated method stub
			loadAppConfig();
		}

		@Override
		public String getErrorMessage() {
			// TODO Auto-generated method stub
			
			String 
				errorMessageFNF = "App Configuration File Not Found",
				errorMessageIO = "IO Error",
				errorMessageSyntax = "Config Syntax Corrupted",
				normalMessage = "All operations are normal",
				unknownerrorMessage = "An unknown error occured. Yikes!";
			
			switch (getLoadState()) {
			case FILE_NOT_FOUND:
				return errorMessageFNF;
			case JSON_IO_ERROR:
				return errorMessageIO;
			case JSON_SYNTAX_ERROR:
				return errorMessageSyntax;
			case NORMAL:
				return normalMessage;
			case UNKNOWN_ERROR:
				return unknownerrorMessage;
			default:
				return "I am confuuusiooonnnn! The load state is not recognized!"; 
			
			}
		}
		
	}

}
