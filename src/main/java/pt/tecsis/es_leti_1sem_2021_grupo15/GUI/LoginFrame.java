package pt.tecsis.es_leti_1sem_2021_grupo15.GUI;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SuppressWarnings("serial")
public class LoginFrame extends JFrame {
	
	public final static String WINDOW_TITLE = "Projeto Engenharia de Software";
	
	public LoginFrame() {
		super(WINDOW_TITLE);
		
		JLabel githubTokenLabel = new JLabel("GitHub access token a utilizar: "); 
		JLabel trelloTokenLabel = new JLabel("Trello access token a utilizar: "); 
		JLabel trelloApiKeyLabel = new JLabel("Trello API key a utilizar: "); 
		
		final JTextField githubTokenTextField = new JTextField("", 1);
		final JTextField trelloTokenTextField = new JTextField("", 1);
		final JTextField trelloApiKeyTextField = new JTextField("", 1);
		
		final JButton okButton = new JButton("OK");
		
		Insets insets = new Insets(5, 5, 5, 5);
		
		githubTokenTextField.setMargin(insets);
		githubTokenTextField.setBorder(new EmptyBorder(insets));
		trelloTokenTextField.setMargin(insets);
		trelloTokenTextField.setBorder(new EmptyBorder(insets));
		trelloApiKeyTextField.setMargin(insets);
		trelloApiKeyTextField.setBorder(new EmptyBorder(insets));
		
		JPanel contentPanel = new JPanel();
		JPanel inputsContentPanel = new JPanel();
		JPanel buttonContentPanel = new JPanel();
		
		contentPanel.setLayout(new GridBagLayout());
		inputsContentPanel.setLayout(new GridLayout(3, 2, 5, 5));
		buttonContentPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		inputsContentPanel.add(githubTokenLabel);
		inputsContentPanel.add(githubTokenTextField);
		inputsContentPanel.add(trelloTokenLabel);
		inputsContentPanel.add(trelloTokenTextField);
		inputsContentPanel.add(trelloApiKeyLabel);
		inputsContentPanel.add(trelloApiKeyTextField);
		
		buttonContentPanel.add(okButton);
		
		GridBagConstraints layoutConstraints = new GridBagConstraints();
		layoutConstraints.gridwidth = GridBagConstraints.RELATIVE;
		layoutConstraints.fill = GridBagConstraints.HORIZONTAL;
		
		contentPanel.add(inputsContentPanel, layoutConstraints);
		contentPanel.add(buttonContentPanel, layoutConstraints);
		
		this.add(contentPanel);
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setVisible(true);
		this.pack();
		
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				setVisible(false);
				
				GUIBuilder.githubAccessToken = githubTokenTextField.getText();
				GUIBuilder.trelloAccessToken = trelloTokenTextField.getText();
				GUIBuilder.trelloKey = trelloApiKeyTextField.getText();
				
				new GUIBuilder().setVisible(true);
			}
		});
	}
	
	public static void main(String[] args) {
		new LoginFrame();
	}
}
