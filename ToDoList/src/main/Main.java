package main;
import java.io.File;
import java.io.FileReader;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import backend.*;

public class Main extends Application{
	public static ToDoList todoList = new ToDoList();
	protected static Stage primStage = null;
	//public static File originOfToDoList;
	
	public static void main(String[] args) {
        Application.launch(Main.class, (java.lang.String[])null);
    }

	
	@Override
	public void start(Stage primaryStage) throws Exception {
		boolean testingMode = false;//Change to FALSE When Delivering
		primStage = primaryStage;
		primaryStage.getIcons().add(new Image(Main.class.getResourceAsStream( "/images/todolistIcon.png" )));
		primaryStage.setResizable(false);
		//import code the saved to do list is saved in todoList
		try {
	        JAXBContext context = JAXBContext.newInstance(ToDoList.class);
	        Unmarshaller unmarshaller = context.createUnmarshaller();
	        todoList = (ToDoList)unmarshaller.unmarshal(new FileReader("toDoList.xml"));
	        todoList.setTestingFlag(testingMode);

		} catch (Exception e1) {
			//MainMenu a = new MainMenu(null);
			e1.printStackTrace();
		} 
		
		if (todoList.getActiveTasks() == null) {
			//ToDoList could not be de-serialized
			todoList = new ToDoList();
		}
		
		//Show Gui
		//MainController.TDL_CHANGE_STATUS = MainController.TDL_UNCHANGED;
		try {
		
            ScrollPane page = (ScrollPane) FXMLLoader.load(getClass().getResource("test1.fxml"));
            Scene scene = new Scene(page);
            primaryStage.setScene(scene);
            primaryStage.setTitle("ToDoList - Student Edition");
            primaryStage.show();
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }

	}
	
	@Override
	public void stop(){
	    System.out.println("Stage is closing");
	    for (Task task : todoList.getActiveTasks()) {
	    	System.out.println(task.getName());
	    }
	  //export code
	    try
        {
	        JAXBContext context = JAXBContext.newInstance(ToDoList.class);
	        Marshaller m = context.createMarshaller();
	        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

	        // Write to File
	        m.marshal(todoList, new File("toDoList.xml"));

        }catch(Exception i)
        {
            i.printStackTrace();
        }
	    System.exit(0);
	}
}
