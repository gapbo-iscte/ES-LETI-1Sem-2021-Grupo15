package pt.tecsis.es_leti_1sem_2021_grupo15.GUI;

import java.awt.BorderLayout;


import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;
import java.awt.FlowLayout;
import java.awt.CardLayout;

import javax.naming.AuthenticationException;
import javax.swing.BoxLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import net.miginfocom.swing.MigLayout;
import pt.tecsis.es_leti_1sem_2021_grupo15.github_api.GitHubAPI;
import pt.tecsis.es_leti_1sem_2021_grupo15.github_api.GitHubUser;
import pt.tecsis.es_leti_1sem_2021_grupo15.github_api.auth.GitHubCredentials;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JToolBar;

public class GUIBuilder extends JFrame {

	private JPanel contentPane;
	private JTextField tfGitHubTokenBar;
	private static GitHubUser githubuser;
	private GitHubCredentials credentials;
	private JTextField tfTrelloKey;
	private JTextField tfTrelloTokenBar;
	private static JTextArea textArea;
	private JTextField tfQuadroID;
	
	//variaveis para o menu 
			private static JMenuBar mb;
			private static JMenu menu;
			private static JMenuItem s1, s2;

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
		setBounds(100, 100, 856, 792);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		mb= new JMenuBar();
		mb.setBounds(0, 0, 840, 23);
		menu= new JMenu("Graficos de tempo");
		mb.add(menu);
		s1= new JMenuItem("primeiro sprint backlog");
		s1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				DefaultPieDataset sprint1= new DefaultPieDataset();
				sprint1.setValue("Rodrigo", new Integer(200));
				JFreeChart chart= ChartFactory.createPieChart("sprint backlog 1",sprint1,true, true,true);
				PiePlot P = (PiePlot)chart.getPlot();
				ChartFrame frame= new ChartFrame("Sprint backlog 1", chart);
				frame.setVisible(true);
				frame.setSize(450,500);
			}
		});
		s2 = new JMenuItem("segundo sprint backlog");
		menu.add(s1);
		menu.add(s2);
		contentPane.add(mb);
		
		JButton btnOK = new JButton("Enter");
		btnOK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				credentials = new GitHubCredentials(tfGitHubTokenBar.getText());
				
				try {
					githubuser= GitHubAPI.getSelf(credentials);
				} catch (AuthenticationException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				textArea.setText("Bem Vindo " + githubuser.name);
				
			}
		});
		btnOK.setBounds(764, 56, 66, 23);
		contentPane.add(btnOK);

		JLabel lblGitHubToken = new JLabel("GitHubToken:");
		lblGitHubToken.setBounds(10, 32, 74, 14);
		lblGitHubToken.setBackground(Color.WHITE);
		contentPane.add(lblGitHubToken);
		
		JLabel lblTrelloKey = new JLabel("Trello Key:");
		lblTrelloKey.setBackground(Color.WHITE);
		lblTrelloKey.setBounds(10, 60, 61, 14);
		contentPane.add(lblTrelloKey);
		
		JLabel lblTrelloToken = new JLabel("Trello Token:");
		lblTrelloToken.setBackground(Color.WHITE);
		lblTrelloToken.setBounds(393, 60, 69, 14);
		contentPane.add(lblTrelloToken);

		tfGitHubTokenBar = new JTextField();
		tfGitHubTokenBar.setBounds(94, 29, 736, 20);
		contentPane.add(tfGitHubTokenBar);
		tfGitHubTokenBar.setColumns(10);
		
		tfTrelloKey = new JTextField();
		tfTrelloKey.setColumns(10);
		tfTrelloKey.setBounds(81, 56, 302, 20);
		contentPane.add(tfTrelloKey);
		
		tfTrelloTokenBar = new JTextField();
		tfTrelloTokenBar.setColumns(10);
		tfTrelloTokenBar.setBounds(465, 56, 289, 20);
		contentPane.add(tfTrelloTokenBar);

		JButton btnEquipa = new JButton("Equipa");
		btnEquipa.setBounds(10, 87, 89, 23);
		contentPane.add(btnEquipa);

		JButton btnSprints = new JButton("Sprints");
		btnSprints.setBounds(109, 87, 89, 23);
		contentPane.add(btnSprints);

		JButton btnCustos = new JButton("Custos");
		btnCustos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnCustos.setBounds(203, 87, 89, 23);
		contentPane.add(btnCustos);

		JButton btnTags = new JButton("Tags");
		btnTags.setBounds(294, 87, 89, 23);
		contentPane.add(btnTags);

		JButton btnCommits = new JButton("Commits");
		btnCommits.setBounds(386, 87, 89, 23);
		contentPane.add(btnCommits);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 217, 820, 525);
		contentPane.add(scrollPane);
		
		textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		
		tfQuadroID = new JTextField();
		tfQuadroID.setBounds(68, 189, 136, 20);
		contentPane.add(tfQuadroID);
		tfQuadroID.setColumns(10);
		
		JLabel lblQuadroID = new JLabel("QuadroID:");
		lblQuadroID.setBounds(10, 192, 89, 14);
		contentPane.add(lblQuadroID);
		
		JButton btnQuadroID = new JButton("Enter");
		btnQuadroID.setBounds(210, 188, 66, 23);
		contentPane.add(btnQuadroID);
		
	
	}
}

