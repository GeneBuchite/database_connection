package Abstraction;


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

public class FXComboBoxExample1 extends Application
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
		Label monthsLbl = new Label("Month:");

		// Create the ComboBox
		final ComboBox<String> months = new ComboBox<>();
		// Add the Months to the ComboBox
		months.getItems().addAll("January", "February", "March", "April", "May", "June",
				"July",  "August", "September", "October", "November", "December");
		// Set the Limit of visible months to 5
		months.setVisibleRowCount(5);

		// Update the message Label when the selected item changes
		months.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>()
		{
		    public void changed(ObservableValue<? extends String> ov,
		            final String oldvalue, final String newvalue)
		    {
		    	monthChanged(ov, oldvalue, newvalue);
        }});

		// Update the message Label when the index changes
		months.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>()
		{
		    public void changed(ObservableValue<? extends Number> ov,
		            final Number oldvalue, final Number newvalue)
		    {
		    	indexChanged(ov, oldvalue, newvalue);
		    }
		});

		// Update the message Label when the value changes
		months.setOnAction(new EventHandler<ActionEvent>()
		{
            @Override public void handle(ActionEvent e)
            {
            	valueChanged(months);
            }
        });

		// Create the HBox for the Months
		HBox monthBox = new HBox();
		// add the Label and the ComboBox to the HBox
		monthBox.getChildren().addAll(monthsLbl, months);

		// Create the HBox for the Selection
		HBox selectionBox = new HBox();
		// Add the Labels to the HBox
		selectionBox.getChildren().addAll(userSelectionMsgLbl, userSelectionDataLbl);

		// Create the VBox
		VBox root = new VBox();
		// Add the details to the VBox
		root.getChildren().addAll(monthBox, selectionBox, itemChangeLbl, indexChangeLbl);
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
		Scene scene = new Scene(root,300,200);
		// Add the scene to the Stage
		stage.setScene(scene);
		// Set the title of the Stage
		stage.setTitle("Using the ComboBox Control");
		// Display the Stage
		stage.show();
	}

	// Method to display the selected Month
	public void valueChanged(ComboBox<String> list)
	{
		String month = list.getValue();
		userSelectionDataLbl.setText(month);
	}

	// Method to display the Data, which has been changed
	public void monthChanged(ObservableValue<? extends String> observable,String oldValue,String newValue)
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