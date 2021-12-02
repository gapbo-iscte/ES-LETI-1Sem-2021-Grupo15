package pt.tecsis.es_leti_1sem_2021_grupo15.GUI;

import java.awt.BorderLayout;


import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
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
import javax.swing.BoxLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import net.miginfocom.swing.MigLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

public class GUIBuilder extends JFrame {

	private JPanel contentPane;
	private JTextField tfTokenBar;
	
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
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		mb= new JMenuBar();
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
		contentPane.setLayout(new BorderLayout());
		contentPane.add(mb,BorderLayout.NORTH);

		JLabel lbltoken = new JLabel("Token ID:");
		lbltoken.setBounds(10,29, 47, 14);
		lbltoken.setBackground(Color.WHITE);
		contentPane.add(lbltoken);

		tfTokenBar = new JTextField();
		tfTokenBar.setBounds(67, 26, 288, 20);
		contentPane.add(tfTokenBar);
		tfTokenBar.setColumns(10);


		JButton btnOK = new JButton("Enter");
		btnOK.setBounds(365, 25, 59, 23);
		contentPane.add(btnOK);

		JButton btnEquipa = new JButton("Equipa");
		btnEquipa.setBounds(10, 54, 89, 23);
		contentPane.add(btnEquipa);

		JButton btnSprints = new JButton("Sprints");

		btnSprints.setBounds(100, 54, 89, 23);
		contentPane.add(btnSprints);

		JButton btnCustos = new JButton("Custos");
		btnCustos.setBounds(190, 54, 89, 23);
		contentPane.add(btnCustos);

		JButton btnTags = new JButton("Tags");
		btnTags.setBounds(280, 54, 89, 23);
		contentPane.add(btnTags);

		JButton btnCommits = new JButton("Commits");
		btnCommits.setBounds(10, 80, 89, 23);
		contentPane.add(btnCommits);

		JTextArea textArea = new JTextArea();
		textArea.setBounds(10, 160, 414, 123);
		contentPane.add(textArea);
		
	
	}
}
