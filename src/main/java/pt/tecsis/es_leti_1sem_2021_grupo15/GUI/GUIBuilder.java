package pt.tecsis.es_leti_1sem_2021_grupo15.GUI;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextArea;

public class GUIBuilder extends JFrame {

	private JPanel contentPane;
	private JTextField tfTokenBar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUIBuilder frame = new GUIBuilder();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GUIBuilder() {
		setTitle("Projeto Engenharia de Software");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lbltoken = new JLabel("Token ID:");
		lbltoken.setBounds(10, 14, 47, 14);
		lbltoken.setBackground(Color.WHITE);
		contentPane.add(lbltoken);
		
		tfTokenBar = new JTextField();
		tfTokenBar.setBounds(67, 11, 288, 20);
		contentPane.add(tfTokenBar);
		tfTokenBar.setColumns(10);
		
		
		JButton btnOK = new JButton("Enter");
		btnOK.setBounds(365, 10, 59, 23);
		contentPane.add(btnOK);
		
		JButton btnEquipa = new JButton("Equipa");
		btnEquipa.setBounds(10, 39, 89, 23);
		contentPane.add(btnEquipa);
		
		JButton btnSprints = new JButton("Sprints");
		
		btnSprints.setBounds(100, 39, 89, 23);
		contentPane.add(btnSprints);
		
		JButton btnCustos = new JButton("Custos");
		btnCustos.setBounds(190, 39, 89, 23);
		contentPane.add(btnCustos);
		
		JButton btnTags = new JButton("Tags");
		btnTags.setBounds(280, 39, 89, 23);
		contentPane.add(btnTags);
		
		JButton btnCommits = new JButton("Commits");
		btnCommits.setBounds(10, 65, 89, 23);
		contentPane.add(btnCommits);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(10, 127, 414, 123);
		contentPane.add(textArea);
	}
}
