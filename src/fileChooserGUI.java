/*
 * Resources used:
 * https://docs.oracle.com/javase/tutorial/displayCode.html?code=https://docs.oracle.com/javase/tutorial/uiswing/examples/components/FileChooserDemoProject/src/components/FileChooserDemo.java
 * https://docs.oracle.com/javase/tutorial/uiswing/examples/components/FileChooserDemo2Project/src/components/FileChooserDemo2.java
 */

import javafx.scene.Node;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.SwingUtilities;

public class fileChooserGUI extends JPanel implements ActionListener {

    File selectedFile;
    static private String newline = "\n";
    private JTextArea log;
    private JFileChooser fc;
    private JViewport view;
    private JButton splitButton;

    public fileChooserGUI() {
        //super(new BorderLayout());
        BorderPane b = new BorderPane();

        //Create the log first, because the action listener
        //needs to refer to it.
        log = new JTextArea(5,20);
        log.setMargin(new Insets(5,5,5,5));
        log.setEditable(false);
        JScrollPane logScrollPane = new JScrollPane(log);

        JButton uploadButton = new JButton("Upload File");
        uploadButton.addActionListener(this);

        splitButton = new JButton("PNG Split");
        splitButton.addActionListener(this);

        //view = new JViewport();

        add(uploadButton, BorderLayout.PAGE_START);
        add(splitButton, BorderLayout.CENTER);
        add(logScrollPane, BorderLayout.CENTER);
    }

    public HBox addHBox() {
        HBox hbox = new HBox();
        hbox.setSpacing(10);
        hbox.setStyle("-fx-background-color: #336699;");
        TextField field = new TextField();
        //hbox.getChildren().addAll(new Label("Search:"), field, new Button("Go"));

        return hbox;
    }


    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == splitButton && fc != null) {
            int returnVal = fc.showSaveDialog(fileChooserGUI.this);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = fc.getSelectedFile();
                try {
                    new convertImage(file);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                log.append("Converting: " + file.getName() + "." + newline);
            } else {
                log.append("Converting cancelled by user." + newline);
            }
            log.setCaretPosition(log.getDocument().getLength());
        }
        if (e.getSource() == splitButton && fc == null) {
            log.append("You must attach an image first.");
        }
        //Set up the file chooser.
        if (fc == null) {
            fc = new JFileChooser();
            fc.setCurrentDirectory(new File(System.getProperty("user.dir")));

            //Add a custom file filter and disable the default
            //(Accept All) file filter.
            fc.addChoosableFileFilter(new ImageFilter());
            fc.setAcceptAllFileFilterUsed(false);

            //Add custom icons for file types.
            fc.setFileView(new ImageFileView());

            //Add the preview pane.
            fc.setAccessory(new ImagePreview(fc));
        }

        //Show it.
        int returnVal = fc.showDialog(fileChooserGUI.this,
                "Attach");

        //Process the results.
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            selectedFile = fc.getSelectedFile();
            log.append("Attaching file: " + selectedFile.getName()
                    + "." + newline);
        } else {
            log.append("Attachment cancelled by user." + newline);
        }
        log.setCaretPosition(log.getDocument().getLength());

        //Reset the file chooser for the next time it's shown.
        fc.setSelectedFile(null);
    }

    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event dispatch thread.
     */
    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("PNG Split Tool");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Add content to the window.
        frame.add(new fileChooserGUI());

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        //Schedule a job for the event dispatch thread:
        //creating and showing this application's GUI.
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                //Turn off metal's use of bold fonts
                UIManager.put("swing.boldMetal", Boolean.FALSE);
                createAndShowGUI();
            }
        });
    }
}