package pt.tecsis.es_leti_1sem_2021_grupo15.GUI;

import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

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
import javax.swing.text.StyledEditorKit.ForegroundAction;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.trello4j.model.Board;
import org.trello4j.model.Member;

import java.awt.FlowLayout;
import java.awt.CardLayout;

import javax.naming.AuthenticationException;
import javax.swing.BoxLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import net.miginfocom.swing.MigLayout;
import pt.tecsis.es_leti_1sem_2021_grupo15.github_api.GitHubAPI;
import pt.tecsis.es_leti_1sem_2021_grupo15.github_api.GitHubTag;
import pt.tecsis.es_leti_1sem_2021_grupo15.github_api.GitHubUser;
import pt.tecsis.es_leti_1sem_2021_grupo15.github_api.auth.GitHubCredentials;
import pt.tecsis.es_leti_1sem_2021_grupo15.trello_api.TrelloAcoes;
import pt.tecsis.es_leti_1sem_2021_grupo15.trello_api.TrelloMembros;
import pt.tecsis.es_leti_1sem_2021_grupo15.trello_api.TrelloQuadros;

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

	private static String trelloKey;
	private static String trelloAccessToken;

	// variaveis para o menu
	private static JMenuBar mb;
	private static JMenu menu;
	private static JMenuItem s1, s2, s3, s4;

	private String quadroId;
	private JTextField tfRepositorio;
	private String nomeRepositorio;
			

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

		mb = new JMenuBar();
		mb.setBounds(0, 0, 840, 23);
		menu = new JMenu("Graficos de tempo");
		mb.add(menu);
		s1 = new JMenuItem("Tempo gasto por membro");
		s1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				List<Board> quadros = TrelloQuadros
						.BuscarQuadros(TrelloMembros.getMembroDoQuadro(trelloAccessToken).getUsername());

				String qu = quadros.get(0).getId();

				HashMap<String, Double[]> tempoPorMembro = TrelloAcoes.getTempoPorMembro(qu);

				DefaultPieDataset sprint1 = new DefaultPieDataset();


				int i = 0;
				for (Entry<String, Double[]> entry : tempoPorMembro.entrySet()) {
				

					sprint1.insertValue(i, entry.getKey(), entry.getValue()[0]);
					i++;
				}
				;
				JFreeChart chart = ChartFactory.createPieChart("Tempo gast por membro", sprint1, true, true, true);				
				PiePlot P = (PiePlot) chart.getPlot();
				ChartFrame frame = new ChartFrame("Tempo gasto por membro", chart);
				frame.setVisible(true);
				frame.setSize(450, 500);
			}
		});
		
		s2 = new JMenuItem("Primeiro sprint backlog");
		s2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				List<Board> quadros = TrelloQuadros
						.BuscarQuadros(TrelloMembros.getMembroDoQuadro(trelloAccessToken).getUsername());

				String qu = quadros.get(0).getId();

				HashMap<String, Double[]> tempoPorMembro = TrelloAcoes.getTempoPorSprintPorMembro(qu, "S1");

				DefaultPieDataset sprint1 = new DefaultPieDataset();


				int i = 0;
				for (Entry<String, Double[]> entry : tempoPorMembro.entrySet()) {
				

					sprint1.insertValue(i, entry.getKey(), entry.getValue()[0]);
					i++;
				}
				;
				JFreeChart chart = ChartFactory.createPieChart("sprint backlog 1", sprint1, true, true, true);				
				PiePlot P = (PiePlot) chart.getPlot();
				ChartFrame frame = new ChartFrame("Sprint backlog 1", chart);
				frame.setVisible(true);
				frame.setSize(450, 500);
				
			}
			});
		s3= new JMenuItem("Segundo sprint backlog");
		s3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				List<Board> quadros = TrelloQuadros
						.BuscarQuadros(TrelloMembros.getMembroDoQuadro(trelloAccessToken).getUsername());

				String qu = quadros.get(0).getId();

				HashMap<String, Double[]> tempoPorMembro = TrelloAcoes.getTempoPorSprintPorMembro(qu, "S2");

				DefaultPieDataset sprint1 = new DefaultPieDataset();


				int i = 0;
				for (Entry<String, Double[]> entry : tempoPorMembro.entrySet()) {
				

					sprint1.insertValue(i, entry.getKey(), entry.getValue()[0]);
					i++;
				}
				;
				JFreeChart chart = ChartFactory.createPieChart("sprint backlog 2", sprint1, true, true, true);				
				PiePlot P = (PiePlot) chart.getPlot();
				ChartFrame frame = new ChartFrame("Sprint backlog 2", chart);
				frame.setVisible(true);
				frame.setSize(450, 500);
				
			}
			});
		s4= new JMenuItem("Tempo por sprint");
		s4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				List<Board> quadros = TrelloQuadros
						.BuscarQuadros(TrelloMembros.getMembroDoQuadro(trelloAccessToken).getUsername());

				String qu = quadros.get(0).getId();

				HashMap<String, Double[]> tempoPorSprint = TrelloAcoes.getTempoPorSprint(qu);

				DefaultPieDataset sprint1 = new DefaultPieDataset();


				int i = 0;
				for (Entry<String, Double[]> entry : tempoPorSprint.entrySet()) {
				

					sprint1.insertValue(i, entry.getKey(), entry.getValue()[0]);
					i++;
				}
				;
				JFreeChart chart = ChartFactory.createPieChart("Tempo por sprint", sprint1, true, true, true);				
				PiePlot P = (PiePlot) chart.getPlot();
				ChartFrame frame = new ChartFrame("Tempo por sprint", chart);
				frame.setVisible(true);
				frame.setSize(450, 500);
				
			}
			});
		
		menu.add(s1);
		menu.add(s2);
		menu.add(s3);
		menu.add(s4);
		contentPane.add(mb);
		
		JButton btnOK = new JButton("Enter");
		btnOK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				credentials = new GitHubCredentials(tfGitHubTokenBar.getText());

				trelloKey = tfTrelloKey.getText();
				trelloAccessToken = tfTrelloTokenBar.getText();
				TrelloQuadros.Inicializar(trelloKey, trelloAccessToken);

				try {
					githubuser = GitHubAPI.getSelf(credentials);
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
		btnOK.setBounds(764, 56, 59, 23);
		contentPane.add(btnOK);

		JLabel lblGitHubToken = new JLabel("GitHubToken:");
		lblGitHubToken.setBounds(10, 29, 66, 14);
		lblGitHubToken.setBackground(Color.WHITE);
		contentPane.add(lblGitHubToken);

		JLabel lblTrelloKey = new JLabel("Trello Key:");
		lblTrelloKey.setBackground(Color.WHITE);
		lblTrelloKey.setBounds(24, 60, 52, 14);
		contentPane.add(lblTrelloKey);

		JLabel lblTrelloToken = new JLabel("Trello Token:");
		lblTrelloToken.setBackground(Color.WHITE);
		lblTrelloToken.setBounds(393, 60, 64, 14);
		contentPane.add(lblTrelloToken);

		tfGitHubTokenBar = new JTextField();
		tfGitHubTokenBar.setBounds(81, 29, 749, 20);
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
		btnEquipa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Enviar com Esta
				//List<Member> membros = TrelloMembros.getMembrosDoQuadro(quadroId);
				
				//SO PARA TESTES APAGAR DEPOIS
				List<Member> membros = TrelloMembros.getMembrosDoQuadro("615c89b6a359063061e30315");
				//
				String m = "";
				for(Member membro : membros){
					m = m + membro.getFullName()+"\n";
				}
				textArea.setText(m);
			}
		});
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
		btnTags.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//APAGAR DEPOIS SO PARA TESTES
				nomeRepositorio = "ES-LETI-1Sem-2021-Grupo15"; 
				//
				GitHubTag[] tags= null;
				try {
					tags = GitHubAPI.getRepositoryTags("gapbo-iscte", nomeRepositorio, credentials);
				} catch (AuthenticationException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				String tagString = "";
				for(int i = 0; i < tags.length; i++){
					tagString = tagString + tags[i].name;
				}
				textArea.setText(tagString);
			}
		});
		btnTags.setBounds(294, 87, 89, 23);
		contentPane.add(btnTags);

		JButton btnCommits = new JButton("Commits");
		btnCommits.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
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
		lblQuadroID.setBounds(10, 192, 51, 14);
		contentPane.add(lblQuadroID);

		JButton btnQuadroID = new JButton("Enter");
		btnQuadroID.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				quadroId = tfQuadroID.getText();
			}
		});
		btnQuadroID.setBounds(210, 188, 66, 23);
		contentPane.add(btnQuadroID);
		
		JLabel lblRepositorio = new JLabel("Repositorio:");
		lblRepositorio.setBounds(294, 193, 58, 14);
		contentPane.add(lblRepositorio);
		
		tfRepositorio = new JTextField();
		tfRepositorio.setColumns(10);
		tfRepositorio.setBounds(358, 189, 148, 20);
		contentPane.add(tfRepositorio);
		
		JButton btnRepositorio = new JButton("Enter");
		btnRepositorio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nomeRepositorio = tfRepositorio.getText();
			}
		});
		btnRepositorio.setBounds(510, 188, 66, 23);
		contentPane.add(btnRepositorio);
	}
}
