import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javax.swing.*;
import java.io.*;
import java.util.Scanner;

/**
 * This class controls most of the
 * functionality of the text editor
 */
public class TextEditorController
{
    @FXML
    private final Menu fileMenu;

    @FXML
    private final Menu fontMenu;

    @FXML
    private MenuItem onNew;

    @FXML
    private MenuItem onOpen;

    @FXML
    private MenuItem onSave;

    @FXML
    private MenuItem onSaveAs;

    @FXML
    private MenuItem onExit;

    private final ToggleGroup fontSwitcherToggleGroup;

    @FXML
    private final RadioMenuItem onMonospaced;

    @FXML
    private final RadioMenuItem onSerif;

    @FXML
    private final RadioMenuItem onSansSerif;

    @FXML
    private final CheckMenuItem onItalic;

    @FXML
    private final CheckMenuItem onBold;

    private String fileName;

    @FXML
    private TextArea areaText;

    //create the constructor
    public TextEditorController(Menu fileMenu, Menu fontMenu,
                                ToggleGroup fontSwitcherToggleGroup,
                                RadioMenuItem onMonospaced, RadioMenuItem onSerif,
                                RadioMenuItem onSansSerif, CheckMenuItem onItalic,
                                CheckMenuItem onBold)
    {
        this.fileMenu = fileMenu;
        this.fontMenu = fontMenu;
        this.fontSwitcherToggleGroup = fontSwitcherToggleGroup;
        this.onMonospaced = onMonospaced;
        this.onSerif = onSerif;
        this.onSansSerif = onSansSerif;
        this.onItalic = onItalic;
        this.onBold = onBold;
    }

    @FXML
    private void initialize()
    {
        try
        {

            areaText.setWrapText(true);
            areaText.setFont(new Font("Monospaced", 10));
        }
        catch (Exception e)
            {
                e.printStackTrace();
            }
    }

    public void onOpen (ActionEvent ae)
    {
            String words = "";

            JFileChooser fileChooser = new JFileChooser();

            int condition = fileChooser.showOpenDialog(null);

            if (condition == JFileChooser.APPROVE_OPTION)
            {
                try
                {
                    File selectedFile = fileChooser.getSelectedFile();

                    fileName = selectedFile.getPath();
                    try (Scanner scanner = new Scanner(selectedFile))
                    {
                        do
                        {
                            words += scanner.nextLine();
                        }
                        while (scanner.hasNext());
                        areaText.setText(words);
                    }


                }
                catch (FileNotFoundException e)
                {
                    e.printStackTrace();
                }
            }
        }

        public void onSave (ActionEvent actionEvent)
        {
        if (fileName != null)
        {
            Save();
        }
        else
        {
            SaveAs();
        }

        }

        public void onSaveAs (ActionEvent actionEvent)
        {
            SaveAs();
        }
        public void SaveAs()
        {
            JFileChooser fileChooser = new JFileChooser();

            int condition = fileChooser.showSaveDialog(null);

            if (condition == JFileChooser.APPROVE_OPTION) {

                fileName = fileChooser.getSelectedFile().toString();

                Save();
            }
        }

        public void Save()
        {
            File savedFile = new File(fileName);

            try (FileWriter fileWriter = new FileWriter(savedFile))
            {
                fileWriter.write(areaText.getText());

                fileWriter.close();
            }
            catch (IOException ex)
            {
                ex.printStackTrace();
            }
        }
        public void onExit (ActionEvent actionEvent)
        {
            System.exit(0);
        }

        public void onNew (ActionEvent event)
        {
            areaText.setText(null);
            fileName = null;
        }
        public void onSerif (ActionEvent actionEvent)
        {
            areaText.setFont(Font.font("Serif"));
        }
        public void onSansSerif (ActionEvent actionEvent)
        {
            areaText.setFont(Font.font("SanSerif"));
        }
        public void onMonospaced (ActionEvent actionEvent)
        {
            areaText.setFont(Font.font("Monospaced"));
        }

        public void onItalic (ActionEvent actionEvent)
        {
            areaText.setFont(Font.font("Italic"));
        }

        public void onBold (ActionEvent actionEvent)
        {

            areaText.setFont(Font.font(String.valueOf(FontWeight.EXTRA_BOLD)));
        }

    }
