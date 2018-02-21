package backend.config;

public enum AppConfigLoadState {
	NORMAL,
	JSON_SYNTAX_ERROR,
	JSON_IO_ERROR,
	FILE_NOT_FOUND,
	UNKNOWN_ERROR
}
