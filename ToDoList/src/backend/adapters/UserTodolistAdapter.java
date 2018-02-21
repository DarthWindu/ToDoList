package backend.adapters;

public class UserTodolistAdapter {
	private static TodolistLoadState LOAD_STATE;

	public static TodolistLoadState getLoadState() {
		return LOAD_STATE;
	}

	public static void setLoadState(TodolistLoadState lOAD_STATE) {
		LOAD_STATE = lOAD_STATE;
	}
}
