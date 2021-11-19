package pt.tecsis.es_leti_1sem_2021_grupo15.GUI;

import java.awt.Component;
import java.awt.Container;
import javax.swing.BoxLayout;

import static org.junit.Assert.assertArrayEquals;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.fasterxml.jackson.databind.deser.std.ContainerDeserializerBase;

import javax.swing.JTextArea;
//import javax.swing.plaf.basic.BasicOptionPaneUI.ButtonActionListener;
//import javax.swing.plaf.basic.BasicSliderUI.ComponentHandler;

public class GUI implements ActionListener  {
	   	String token;
	   	private static JTextArea textArea = new JTextArea(5, 40);
	   //	private static JButton button;
	 
	   	
	 
	    private static void addAButton(String text, Container container) {
	        JButton button = new JButton(text);
	        button.setAlignmentX(Component.CENTER_ALIGNMENT);
	        container.add(button);
	        button.addActionListener(new GUI());
	    }
	    private static void addTokenField(String text, Container container) {
	    
	        JTextField tokenBar=new JTextField(text);  
	        tokenBar.setBounds(50,100, 200,30);  
	        container.add(tokenBar);  
	

	    }
	    
	    private static void addTextBox(String text, Container container){
	    	//JTextArea textArea = new JTextArea(5, 40);
		    container.add(textArea);
		    textArea.setText(text);
		    textArea.setLineWrap(true);
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
	        
	        JPanel topJPanel = new JPanel();
	        JPanel pane = new JPanel();
	         
	        
	        topJPanel.setLayout(new FlowLayout());
	        pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));
	        
	        frame.setContentPane(pane);
	        
	        pane.add(topJPanel);
	       
	      
	        addTokenField("Insira o seu Token de  Github", topJPanel);
	        addAButton("Enter", topJPanel);
	        addTextBox("", pane);
	        addAButton("Equipa", pane);
	        addAButton("Sprints", pane);
	        addAButton("Custos", pane);
	        addAButton("Tags", pane);
	        addAButton("Commits", pane);
	        
	 
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
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
		String	buttonName = button.getText();
			//if ("Enter".equals(button.getText())){
			System.out.printf(button.getText());
			//}
		}
	}

