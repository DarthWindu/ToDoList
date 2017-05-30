package main;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.bind.JAXB;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.UnmarshalException;
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
		primaryStage.setResizable(true);
		//import code the saved to do list is saved in todoList
		try {
	        JAXBContext context = JAXBContext.newInstance(ToDoList.class);
	        Unmarshaller unmarshaller = context.createUnmarshaller();
	        todoList = (ToDoList)unmarshaller.unmarshal(new FileReader("toDoList.xml"));
	        todoList.setTestingFlag(testingMode);
	        /*
			FileInputStream fileIn = new FileInputStream("./" + "todolist" + ".java");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			todoList = (ToDoList) in.readObject();
			todoList.setTestingFlag(testingMode);
			in.close();
			fileIn.close();
			*/
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

			FileOutputStream fileOut = new FileOutputStream("./" + "todolist" + ".java");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(todoList); // change this to the toDoList you are trying to save - Done
			out.close();
			fileOut.close();
        }catch(Exception i)
        {
            i.printStackTrace();
        }
	    System.exit(0);
	}
}
