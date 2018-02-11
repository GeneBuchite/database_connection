package Abstraction;


import java.util.ArrayList;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class FXComboBoxExample2 extends Application
{
	// Declaring Labels for messages
	Label userSelectionMsgLbl = new Label("Your selection: ");
	Label userSelectionDataLbl = new Label("");
	Label itemChangeLbl = new Label("Item Changed: ");
	Label indexChangeLbl = new Label("Index Changed: ");

	public static void main(String[] args)
	{
		Application.launch(args);
	}

	@Override
	public void start(Stage stage)
	{
		// Create the Label
		Label personLbl = new Label("Select/Enter Person:");

		// Create the ComboBox
		final ComboBox<Person> persons = new ComboBox<>();
		// Add the Persons to the ComboBox
		persons.getItems().addAll(createPersonList());
		// Set Converter to the ComboBox
		persons.setConverter(new PersonConverter());
                
                persons.setPromptText("Serlerct Sombody Already");
                persons.setEditable(true);
                
		// Update the message Label when the selected item changes
               //  emailComboBox.setPromptText("Email address");
       // emailComboBox.setEditable(true);   
               
                
                
		persons.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Person>()
		{
		    public void changed(ObservableValue<? extends Person> ov,
		            final Person oldvalue, final Person newvalue)
		    {
		    	personChanged(ov, oldvalue, newvalue);
		    }
		});

		// Update the message Label when the index changes
		persons.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>()
		{
		    public void changed(ObservableValue<? extends Number> ov,
		            final Number oldvalue, final Number newvalue)
		    {
		    	indexChanged(ov, oldvalue, newvalue);
        }});

		// Update the message Label when the value changes
		persons.setOnAction(new EventHandler<ActionEvent>()
		{
            @Override public void handle(ActionEvent e)
            {
            	valueChanged(persons);
            }
        });

		// create the HBox for the Persons
		HBox personBox = new HBox();
		// Add the Label and the ComboBox to the HBox
		personBox.getChildren().addAll(personLbl, persons);

		// Create the HBox for User Selection
		HBox selectionBox = new HBox();
		// Add the Labels to the HBox
		selectionBox.getChildren().addAll(userSelectionMsgLbl, userSelectionDataLbl);

		// create the VBox
		VBox root = new VBox();
		// Add the children to the VBox
		root.getChildren().addAll(personBox, selectionBox, itemChangeLbl, indexChangeLbl);
		// Set the vertical spacing between children to 10px
		root.setSpacing(10);

		// Set the Style-properties of the VBox
		root.setStyle("-fx-padding: 10;" +
			"-fx-border-style: solid inside;" +
			"-fx-border-width: 2;" +
			"-fx-border-insets: 5;" +
			"-fx-border-radius: 5;" +
			"-fx-border-color: blue;");

		// Create the Scene
		Scene scene = new Scene(root, 500, 200);
		// Add the scene to the Stage
		stage.setScene(scene);
		// Set the title of the Stage
		stage.setTitle("Using StringConverter in ComboBox");
		// Display the Stage
		stage.show();
	}

	// Helper-Method to create an ArrayList of Persons
	private ArrayList<Person> createPersonList()
	{
		ArrayList<Person> persons = new ArrayList<Person>();

		persons.add(new Person("Donna", "Duncan"));
		persons.add(new Person("Layne", "Estes"));
		persons.add(new Person("John", "Jacobs"));
		persons.add(new Person("Mason", "Boyd"));
		persons.add(new Person("Harry", "Eastwood"));
                persons.add(new Person("Cindy", "Buchite"));
                persons.add(new Person("Gene", "Buchite"));
                persons.add(new Person("Troy", "Buchanan"));

		return persons;
	}

	// Method to display the selected Person
	public void valueChanged(ComboBox<Person> list)
	{
		Person p = list.getValue();
		String name = p.getLastName() + ", " + p.getFirstName();
		userSelectionDataLbl.setText(name);
	}

	// Method to display the Data, which has been changed
	public void personChanged(ObservableValue<? extends Person> observable,Person oldValue,Person newValue)
	{
		String oldText = oldValue == null ? "null" : oldValue.toString();
		String newText = newValue == null ? "null" : newValue.toString();

		itemChangeLbl.setText("Itemchanged: old = " + oldText + ", new = " + newText + "\n");
	}

	// Method to display the Index, which has been changed
	public void indexChanged(ObservableValue<? extends Number> observable,Number oldValue,Number newValue)
	{
		indexChangeLbl.setText( "Indexchanged: old = " + oldValue + ", new = " + newValue + "\n");
	}
}