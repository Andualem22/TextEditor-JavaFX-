import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
/**
 * This is a text editor application
 * for the final project
 *
 */
public class TextEditor  extends Application
{

    private Menu fileMenu;

    private Menu fontMenu;

    private ToggleGroup fontSwitcherToggleGroup;

    private RadioMenuItem onMonospaced;

    private RadioMenuItem onSerif;

    private RadioMenuItem onSansSerif;

    private CheckMenuItem onItalic;

    private CheckMenuItem onBold;

    @Override
    public void start(Stage stage) throws Exception
    {
        //Load the fxml file and set it to the controller factory
        FXMLLoader parent = new FXMLLoader(
                getClass().getResource("TextEditor.fxml"));


        parent.setControllerFactory(t -> new TextEditorController(fileMenu, fontMenu,
                fontSwitcherToggleGroup, onMonospaced, onSerif, onSansSerif,
                onItalic, onBold));
        //Set the title and show it
        stage.setTitle("Text Editor");
        stage.setScene(new Scene(parent.load()));
        stage.show();
    }
    public static void main(String[] args)
    {
        //Launch the application
        launch(args);
    }
}
