import backend.*;
import frontend.*;

public class Main{
	public static void main(String[] args) {
        ToDoList tdl = new ToDoList();
        
        Task t = new Task("t"); 
        
        tdl.add(t);
        
        new MainMenu(tdl);
    }
}