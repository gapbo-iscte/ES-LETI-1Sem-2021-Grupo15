package pt.tecsis.es_leti_1sem_2021_grupo15.GUI;

import java.awt.Component;
import java.awt.Container;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
	 
public class GUI {
	    public static void addComponentsToPane(Container pane) {
	        pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));
	 
	        addAButton("Equipa", pane);
	        addAButton("Sprints", pane);
	        addAButton("Custos", pane);
	        addAButton("Tags", pane);
	        addAButton("Commits", pane);
	    }
	 
	    private static void addAButton(String text, Container container) {
	        JButton button = new JButton(text);
	        button.setAlignmentX(Component.CENTER_ALIGNMENT);
	        container.add(button);
	    }
	 
	    /**
	     * Create the GUI and show it.  For thread safety,
	     * this method should be invoked from the
	     * event-dispatching thread.
	     */
	    private static void createAndShowGUI() {
	        //Create and set up the window.
	        JFrame frame = new JFrame("Projeto Engenharia de Software");
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	 
	        //Set up the content pane.
	        addComponentsToPane(frame.getContentPane());
	 
	        //Display the window.
	        frame.pack();
	        frame.setVisible(true);
	    }
	 
	    public static void main(String[] args) {
	        //Schedule a job for the event-dispatching thread:
	        //creating and showing this application's GUI.
	        javax.swing.SwingUtilities.invokeLater(new Runnable() {
	            public void run() {
	                createAndShowGUI();
	            }
	        });
	    }
	}

