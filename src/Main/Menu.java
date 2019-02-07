package Main;

import javax.swing.*;
import java.io.IOException;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Parent;
import javafx.scene.Scene;

/**
 * <h1>Calco Java Game</h1>
 * This Java game is being developed by Calco MasterClassers
 * for educational purposes.
 * <p></p>
 *
 * @version 0.0.1
 * @since   07-02-2019
 */
public class Menu{

    /* Fields */
    public JFrame mainFrame;

    /**
     * This is the constructor for the Menu class.
     * This is the part of the code that's being
     * executed when the Menu class has ben initialized.
     * <p>In here a JFrame is created and loaded.</p>
     */

    public Menu(){
        mainFrame = new JFrame("Home");
        final JFXPanel fxPanel = new JFXPanel();

        mainFrame.add(fxPanel);
        mainFrame.setSize(1000, 563);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setVisible(true);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                try {
                    initFX(fxPanel);
                }catch(IOException e){System.out.println("ERROR");}
            }

        });
    }

    /* Initialize JavaFX */

    /**
     * This method is used to load a fxml-file into an
     * JFXPanel. This is also used to load the JFXPanel into
     * the JFrame.
     * @param fxPanel
     * @return Nothing.
     * @throws IOException On load error.
     * @see    IOException
     */

    private void initFX(JFXPanel fxPanel) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/Main/fxml/menu.fxml"));
        Scene scene = new Scene(root, 400, 400);
        fxPanel.setScene(scene);
    }

    /* Methods */

    /**
     * This is the main method which makes use of the game.
     * @param args
     * @return Nothing.
     */

    public static void main(String[] args){
        new Menu();
    }
}