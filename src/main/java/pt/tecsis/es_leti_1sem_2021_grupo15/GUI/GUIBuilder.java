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

import pt.tecsis.es_leti_1sem_2021_grupo15.github_api.GitHubAPI;

import pt.tecsis.es_leti_1sem_2021_grupo15.github_api.GitHubBranch;
import pt.tecsis.es_leti_1sem_2021_grupo15.github_api.GitHubCommit;
import pt.tecsis.es_leti_1sem_2021_grupo15.github_api.GitHubRepository;
import pt.tecsis.es_leti_1sem_2021_grupo15.github_api.GitHubTag;
import pt.tecsis.es_leti_1sem_2021_grupo15.github_api.GitHubUser;
import pt.tecsis.es_leti_1sem_2021_grupo15.github_api.auth.GitHubCredentials;
import pt.tecsis.es_leti_1sem_2021_grupo15.trello_api.TrelloAcoes;
import pt.tecsis.es_leti_1sem_2021_grupo15.trello_api.TrelloApiMain;
import pt.tecsis.es_leti_1sem_2021_grupo15.trello_api.TrelloMembros;
import pt.tecsis.es_leti_1sem_2021_grupo15.trello_api.TrelloQuadros;


import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JToolBar;
import javax.swing.JToggleButton;

public class GUIBuilder extends JFrame {

	private JPanel contentPane;
	private static GitHubUser githubuser;
	private GitHubCredentials credentials;
	private JTextField tfCusto;
	private static JTextArea textArea;
	private JTextField tfQuadroID;
	private String m = "";
	private String branch;
	private int custo=20;

	public static String githubAccessToken;
	public static String trelloKey;
	public static String trelloAccessToken;

	// variaveis para o menu
	private static JMenuBar mb;
	private static JMenu menu;
	private static JMenuItem s1, s2, s3, s4, s5;
	
	private static JMenu menuc;
	private static JMenu subMenucsv;
	private static JMenuItem c1,c2,c3,c4, c5;
	private static JMenuItem cs1,cs2,cs3,cs4,cs5,cs6;

	private String quadroId;
	private JTextField tfRepositorio;
	private String nomeRepositorio;
	private GitHubRepository repositorio;
	private JTextField tfBranch;
	


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
		setResizable(false);
		
		setTitle("Projeto Engenharia de Software");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 851, 662);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);


		mb = new JMenuBar();
		mb.setBounds(0, 0, 840, 23);
		menu = new JMenu("Graficos de tempo");
		
		menuc=new JMenu("Graficos de custo");
		
		subMenucsv= new JMenu("Exportar por CSV");

		mb.add(menu);
		mb.add(menuc);
		mb.add(subMenucsv);
		
		cs1= new JMenuItem("Exportar Tabela total");
		cs1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				List<Board> quadros = TrelloQuadros
						.BuscarQuadros(TrelloMembros.getMembroDoQuadro(trelloAccessToken).getUsername());

				String qu = quadros.get(0).getId();
				
				GUICsv.exportTabela(GUITabelas.tabelaTotal(qu,custo), "C:\\Users\\Public\\Downloads\\");
				
			}
		});
		
		cs2= new JMenuItem("Exportar tabela custo por sprint");
		cs2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				List<Board> quadros = TrelloQuadros
						.BuscarQuadros(TrelloMembros.getMembroDoQuadro(trelloAccessToken).getUsername());

				String qu = quadros.get(0).getId();
				
				GUICsv.exportTabela(GUITabelas.tabelaCustoPorSprint(qu, "S3", custo), "C:\\Users\\Public\\Downloads\\");
				
			}
		});
		
		cs3= new JMenuItem("Exportar Tabela por sprint");
		cs3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				List<Board> quadros = TrelloQuadros
						.BuscarQuadros(TrelloMembros.getMembroDoQuadro(trelloAccessToken).getUsername());

				String qu = quadros.get(0).getId();
				
				GUICsv.exportTabela(GUITabelas.tabelaPorSprint(qu, "S3"), "C:\\Users\\Public\\Downloads\\");
				
			}
		});
		
		cs4= new JMenuItem("Exportar Tabela por testes");
		cs4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				List<Board> quadros = TrelloQuadros
						.BuscarQuadros(TrelloMembros.getMembroDoQuadro(trelloAccessToken).getUsername());

				String qu = quadros.get(0).getId();
				
				GUICsv.exportTabela(GUITabelas.tabelaTempoTestes(qu), "C:\\Users\\Public\\Downloads\\");
				
			}
		});
		
		cs5= new JMenuItem("Exportar Tabela gerou commit");
		cs5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				List<Board> quadros = TrelloQuadros
						.BuscarQuadros(TrelloMembros.getMembroDoQuadro(trelloAccessToken).getUsername());

				String qu = quadros.get(0).getId();
				
				GUICsv.exportTabela(GUITabelas.tabelaGerouCommits(qu, custo), "C:\\Users\\Public\\Downloads\\");
				
			}
		});
		
		cs6 = new JMenuItem("Exportar tabela nao gerou commmit");
		cs6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				List<Board> quadros = TrelloQuadros
						.BuscarQuadros(TrelloMembros.getMembroDoQuadro(trelloAccessToken).getUsername());

				String qu = quadros.get(0).getId();
				
				GUICsv.exportTabela(GUITabelas.tabelaNaoGerouCommits(qu, custo), "C:\\Users\\Public\\Downloads\\");				
			}
		});
		
		c1= new JMenuItem("Custo de cada membro");
		c1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				List<Board> quadros = TrelloQuadros
						.BuscarQuadros(TrelloMembros.getMembroDoQuadro(trelloAccessToken).getUsername());

				String qu = quadros.get(0).getId();

				HashMap<String, Double[]> tempoPorMembro = TrelloAcoes.getTempoPorMembro(qu);

				DefaultPieDataset sprint1 = new DefaultPieDataset();
				

				int i = 0;
				for (Entry<String, Double[]> entry : tempoPorMembro.entrySet()) {


					sprint1.insertValue(i, entry.getKey(), custo*(entry.getValue()[0]));
					i++;
				};
				JFreeChart chart = ChartFactory.createPieChart("Tempo gasto por membro", sprint1, true, true, true);
				PiePlot P = (PiePlot) chart.getPlot();
				ChartFrame frame = new ChartFrame("Tempo gasto por membro", chart);
				frame.setVisible(true);
				frame.setSize(450, 500);
			}
		});
		
		c2= new JMenuItem("Custo de cada membro no sprint1");
		c2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				List<Board> quadros = TrelloQuadros
						.BuscarQuadros(TrelloMembros.getMembroDoQuadro(trelloAccessToken).getUsername());

				String qu = quadros.get(0).getId();

				HashMap<String, Double[]> tempoPorMembro = TrelloAcoes.getTempoPorSprintPorMembro(qu,"S1");
				GUITabelas.tabelaCustoPorSprint(qu,"S1",custo);

				DefaultPieDataset sprint1 = new DefaultPieDataset();
				

				int i = 0;
				for (Entry<String, Double[]> entry : tempoPorMembro.entrySet()) {


					sprint1.insertValue(i, entry.getKey(), custo*(entry.getValue()[0]));
					i++;
				};
				JFreeChart chart = ChartFactory.createPieChart("Tempo gasto por membro", sprint1, true, true, true);
				PiePlot P = (PiePlot) chart.getPlot();
				ChartFrame frame = new ChartFrame("Tempo gasto por membro", chart);
				frame.setVisible(true);
				frame.setSize(450, 500);
			}
		});
		
		c3= new JMenuItem("Custo de cada membro no sprint2");
		c3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				List<Board> quadros = TrelloQuadros
						.BuscarQuadros(TrelloMembros.getMembroDoQuadro(trelloAccessToken).getUsername());

				String qu = quadros.get(0).getId();

				HashMap<String, Double[]> tempoPorMembro = TrelloAcoes.getTempoPorSprintPorMembro(qu,"S2");
				GUITabelas.tabelaCustoPorSprint(qu,"S2",custo);


				DefaultPieDataset sprint1 = new DefaultPieDataset();
				

				int i = 0;
				for (Entry<String, Double[]> entry : tempoPorMembro.entrySet()) {


					sprint1.insertValue(i, entry.getKey(), custo*(entry.getValue()[0]));
					i++;
				};
				JFreeChart chart = ChartFactory.createPieChart("Tempo gasto por membro", sprint1, true, true, true);
				PiePlot P = (PiePlot) chart.getPlot();
				ChartFrame frame = new ChartFrame("Tempo gasto por membro", chart);
				frame.setVisible(true);
				frame.setSize(450, 500);
			}
		});
		
		c4= new JMenuItem("Custo de cada membro no sprint3");
		c4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				List<Board> quadros = TrelloQuadros
						.BuscarQuadros(TrelloMembros.getMembroDoQuadro(trelloAccessToken).getUsername());

				String qu = quadros.get(0).getId();

				HashMap<String, Double[]> tempoPorMembro = TrelloAcoes.getTempoPorSprintPorMembro(qu,"S3");
				GUITabelas.tabelaCustoPorSprint(qu,"S3",custo);


				DefaultPieDataset sprint1 = new DefaultPieDataset();
				

				int i = 0;
				for (Entry<String, Double[]> entry : tempoPorMembro.entrySet()) {


					sprint1.insertValue(i, entry.getKey(), custo*(entry.getValue()[0]));
					i++;
				};
				JFreeChart chart = ChartFactory.createPieChart("Tempo gasto por membro", sprint1, true, true, true);
				PiePlot P = (PiePlot) chart.getPlot();
				ChartFrame frame = new ChartFrame("Tempo gasto por membro", chart);
				frame.setVisible(true);
				frame.setSize(450, 500);
			}
		});
		
		c5= new JMenuItem("Tempo dos testes");
		c5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				List<Board> quadros = TrelloQuadros
						.BuscarQuadros(TrelloMembros.getMembroDoQuadro(trelloAccessToken).getUsername());

				String qu = quadros.get(0).getId();
				
				GUITabelas.tabelaTempoTestes(qu);
				
				
				
			}
		});
			
		
		subMenucsv.add(cs1);
		subMenucsv.add(cs2);
		subMenucsv.add(cs3);
		subMenucsv.add(cs4);
		subMenucsv.add(cs5);
		subMenucsv.add(cs6);
		menuc.add(c1);
		menuc.add(c2);
		menuc.add(c3);
		menuc.add(c4);
	
		
		
		s1 = new JMenuItem("Tempo gasto por membro");
		s1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				List<Board> quadros = TrelloQuadros
						.BuscarQuadros(TrelloMembros.getMembroDoQuadro(trelloAccessToken).getUsername());

				String qu = quadros.get(0).getId();

				HashMap<String, Double[]> tempoPorMembro = TrelloAcoes.getTempoPorMembro(qu);

				DefaultPieDataset sprint1 = new DefaultPieDataset();
				DefaultPieDataset estimate = new DefaultPieDataset();


				int i = 0;
				for (Entry<String, Double[]> entry : tempoPorMembro.entrySet()) {
					sprint1.insertValue(i, entry.getKey(), entry.getValue()[0]);
					estimate.insertValue(i, entry.getKey(), entry.getValue()[1]);
					i++;
				}
				
				JFreeChart chart = ChartFactory.createPieChart("Tempo gasto por membro", sprint1, true, true, true);
				JFreeChart chartEstimate = ChartFactory.createPieChart("Tempo estimado por membro", estimate, true, true, true);
				PiePlot P = (PiePlot) chart.getPlot();
				PiePlot PEstimate = (PiePlot) chartEstimate.getPlot();
				ChartFrame frameEstimate= new ChartFrame("Tempo estimado por tempo", chartEstimate);
				ChartFrame frame = new ChartFrame("Tempo gasto por membro", chart);
				
				frameEstimate.setVisible(true);
				frameEstimate.setSize(450,500);
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

				HashMap<String, Double[]> tempoPorMembro = TrelloAcoes.getTempoPorSprintPorMembro(qu,"S1");
				GUITabelas.tabelaPorSprint(qu,"S1");

				DefaultPieDataset sprint1 = new DefaultPieDataset();
				DefaultPieDataset estimate = new DefaultPieDataset();


				int i = 0;
				for (Entry<String, Double[]> entry : tempoPorMembro.entrySet()) {
				

					sprint1.insertValue(i, entry.getKey(), entry.getValue()[0]);
					estimate.insertValue(i, entry.getKey(), entry.getValue()[1]);
					i++;
				}
				;
				JFreeChart chart = ChartFactory.createPieChart("Tempo gasto por membro", sprint1, true, true, true);
				JFreeChart chartEstimate = ChartFactory.createPieChart("Tempo estimado por membro", estimate, true, true, true);
				PiePlot P = (PiePlot) chart.getPlot();
				PiePlot PEstimate = (PiePlot) chartEstimate.getPlot();
				ChartFrame frameEstimate= new ChartFrame("Tempo estimado por tempo", chartEstimate);
				ChartFrame frame = new ChartFrame("Tempo gasto por membro", chart);
				frameEstimate.setVisible(true);
				frameEstimate.setSize(450,500);
				frame.setVisible(true);
				frame.setSize(450, 500);
			}
			});
		s3= new JMenuItem("Segundo sprint backlog");
		s3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				List<Board> quadros = TrelloQuadros.BuscarQuadros(TrelloMembros.getMembroDoQuadro(trelloAccessToken).getUsername());

				String qu = quadros.get(0).getId();

				HashMap<String, Double[]> tempoPorMembro = TrelloAcoes.getTempoPorSprintPorMembro(qu,"S2");
				GUITabelas.tabelaPorSprint(qu,"S2");

				DefaultPieDataset sprint1 = new DefaultPieDataset();
				DefaultPieDataset estimate = new DefaultPieDataset();


				int i = 0;
				for (Entry<String, Double[]> entry : tempoPorMembro.entrySet()) {
				

					sprint1.insertValue(i, entry.getKey(), entry.getValue()[0]);
					estimate.insertValue(i, entry.getKey(), entry.getValue()[1]);
					i++;
				}
				;
				JFreeChart chart = ChartFactory.createPieChart("Tempo gasto por membro", sprint1, true, true, true);
				JFreeChart chartEstimate = ChartFactory.createPieChart("Tempo estimado por membro", estimate, true, true, true);
				PiePlot P = (PiePlot) chart.getPlot();
				PiePlot PEstimate = (PiePlot) chartEstimate.getPlot();
				ChartFrame frameEstimate= new ChartFrame("Tempo estimado por tempo", chartEstimate);
				ChartFrame frame = new ChartFrame("Tempo gasto por membro", chart);
				frameEstimate.setVisible(true);
				frameEstimate.setSize(450,500);
				frame.setVisible(true);
				frame.setSize(450, 500);
				
			}
			});
		s4= new JMenuItem("Terceiro sprint backlog");
		s4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				List<Board> quadros = TrelloQuadros
						.BuscarQuadros(TrelloMembros.getMembroDoQuadro(trelloAccessToken).getUsername());

				String qu = quadros.get(0).getId();

				HashMap<String, Double[]> tempoPorMembro = TrelloAcoes.getTempoPorSprintPorMembro(qu,"S3");
				GUITabelas.tabelaPorSprint(qu,"S3");

				DefaultPieDataset sprint1 = new DefaultPieDataset();
				DefaultPieDataset estimate = new DefaultPieDataset();


				int i = 0;
				for (Entry<String, Double[]> entry : tempoPorMembro.entrySet()) {
				

					sprint1.insertValue(i, entry.getKey(), entry.getValue()[0]);
					estimate.insertValue(i, entry.getKey(), entry.getValue()[1]);
					i++;
				}
				;
				JFreeChart chart = ChartFactory.createPieChart("Tempo gasto por membro", sprint1, true, true, true);
				JFreeChart chartEstimate = ChartFactory.createPieChart("Tempo estimado por membro", estimate, true, true, true);
				PiePlot P = (PiePlot) chart.getPlot();
				PiePlot PEstimate = (PiePlot) chartEstimate.getPlot();
				ChartFrame frameEstimate= new ChartFrame("Tempo estimado por tempo", chartEstimate);
				ChartFrame frame = new ChartFrame("Tempo gasto por membro", chart);
				frameEstimate.setVisible(true);
				frameEstimate.setSize(450,500);
				frame.setVisible(true);
				frame.setSize(450, 500);
			}
			});
		s5 =new JMenuItem("Tempo por sprint");
		s5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				List<Board> quadros = TrelloQuadros
						.BuscarQuadros(TrelloMembros.getMembroDoQuadro(trelloAccessToken).getUsername());

				String qu = quadros.get(0).getId();

				DefaultPieDataset sprint1 = new DefaultPieDataset();
				DefaultPieDataset estimate = new DefaultPieDataset();
				
				HashMap<String, Double[]> tempoPorSprint = TrelloAcoes.getTempoPorSprint(qu);

				int i = 0;
				for (Entry<String, Double[]> entry : tempoPorSprint.entrySet()) {
				

					sprint1.insertValue(i, entry.getKey(), entry.getValue()[0]);
					estimate.insertValue(i, entry.getKey(), entry.getValue()[1]);
					i++;
				}
				;
				JFreeChart chart = ChartFactory.createPieChart("Tempo gasto por sprint", sprint1, true, true, true);
				JFreeChart chartEstimate = ChartFactory.createPieChart("Tempo estimado por sprint", estimate, true, true, true);
				PiePlot P = (PiePlot) chart.getPlot();
				PiePlot PEstimate = (PiePlot) chartEstimate.getPlot();
				ChartFrame frameEstimate= new ChartFrame("Tempo estimado por sprint", chartEstimate);
				ChartFrame frame = new ChartFrame("Tempo gasto por sprint", chart);
				frameEstimate.setVisible(true);
				frameEstimate.setSize(450,500);
				frame.setVisible(true);
				frame.setSize(450, 500);
			}
			});
		
		
		menu.add(s1);
		menu.add(s2);
		menu.add(s3);
		menu.add(s4);
		menu.add(s5);
		menu.add(c5);

		contentPane.add(mb);
		
		JButton btnSprints = new JButton("Tabela");
		btnSprints.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				List<Board> quadros = TrelloQuadros
						.BuscarQuadros(TrelloMembros.getMembroDoQuadro(trelloAccessToken).getUsername());

				String qu = quadros.get(0).getId();
				
				GUITabelas.tabelaTotal(qu, custo);
				
			}
		});
		btnSprints.setBounds(109, 30, 89, 23);
		contentPane.add(btnSprints);
		
		tfCusto = new JTextField();
		tfCusto.setColumns(10);
		tfCusto.setBounds(520, 31, 89, 23);
		contentPane.add(tfCusto);


		JButton btnEquipa = new JButton("Equipa");
		btnEquipa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textArea.setText(m);
			}
		});
		btnEquipa.setBounds(10, 30, 89, 23);
		contentPane.add(btnEquipa);

		

		JButton btnCustos = new JButton("Custos");
		btnCustos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(!tfCusto.getText().isEmpty()) {
					try
					{
						custo= Integer.valueOf(tfCusto.getText());
						
						textArea.setText("Cada hora deste projeto custou "+custo+" unidades monetarias");

					}
					catch(Exception ee) {
						textArea.setText("Digite o custo em digitos por favor");
					}
					
				}else {
					custo=20;
				}
			}
		});
		btnCustos.setBounds(420, 30, 89, 23);
		contentPane.add(btnCustos);

		JButton btnTags = new JButton("Tags");
		btnTags.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
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

		btnTags.setBounds(320, 30, 89, 23);
		contentPane.add(btnTags);

		JButton btnCommits = new JButton("Commits");
		btnCommits.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				GitHubCommit[] commits= null;
				
				try {
					commits = GitHubAPI.getBranchCommits("gabpo-iscte", nomeRepositorio, branch, credentials);
				} catch (AuthenticationException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				String commitsString = "";
				for(int i = 0; i < commits.length; i++){
					commitsString = commitsString + commits[i].message;
				}
				textArea.setText(commitsString);
				
				
			}
		});
		btnCommits.setBounds(208, 30, 102, 23);

		contentPane.add(btnCommits);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 96, 820, 525);
		contentPane.add(scrollPane);


		textArea = new JTextArea();
		scrollPane.setViewportView(textArea);

		tfQuadroID = new JTextField();
		tfQuadroID.setBounds(85, 67, 136, 20);
		contentPane.add(tfQuadroID);
		tfQuadroID.setColumns(10);


		JLabel lblQuadroID = new JLabel("QuadroID:");
		lblQuadroID.setBounds(12, 69, 80, 14);
		contentPane.add(lblQuadroID);

		JButton btnQuadroID = new JButton("Enter");
		btnQuadroID.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				quadroId = tfQuadroID.getText();
			}
		});
		btnQuadroID.setBounds(227, 65, 85, 23);
		contentPane.add(btnQuadroID);
		
		JLabel lblRepositorio = new JLabel("Repositorio:");
		lblRepositorio.setBounds(335, 69, 89, 14);
		contentPane.add(lblRepositorio);
		
		tfRepositorio = new JTextField();
		tfRepositorio.setColumns(10);
		tfRepositorio.setBounds(429, 67, 148, 20);
		contentPane.add(tfRepositorio);
		
		JButton btnRepositorio = new JButton("Enter");
		btnRepositorio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				nomeRepositorio = tfRepositorio.getText();
				List<Member> membros = TrelloMembros.getMembrosDoQuadro(quadroId);
				
				for(Member membro : membros){
					m = m + membro.getFullName()+"\n";
				}
				
				try {
					repositorio = GitHubAPI.getRepository("gapbo-iscte", nomeRepositorio, credentials);
					textArea.setText("Data de Inicio do Projeto: " + repositorio.createdAt.toString() + "\n Criado por: " + repositorio.getOwner().name + 
							"\n Descricao: " + repositorio.descricao +"\n Membros: \n" + m);
				} catch (AuthenticationException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				
			}
		});
		btnRepositorio.setBounds(581, 65, 80, 23);
		contentPane.add(btnRepositorio);
		

		tfBranch = new JTextField();
		tfBranch.setBounds(665, 30, 89, 24);
		contentPane.add(tfBranch);
		tfBranch.setColumns(10);
		
		JLabel lblBranch = new JLabel("Branch:");
		lblBranch.setBounds(619, 34, 37, 14);
		contentPane.add(lblBranch);
		
		JButton btnBranch = new JButton("Enter");
		btnBranch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				branch = tfBranch.getText();
			}
		});
		btnBranch.setBounds(759, 29, 66, 24);
		contentPane.add(btnBranch);

		inicializarAPIs();
	}
	
	
	private void inicializarAPIs() {
		
		credentials = new GitHubCredentials(githubAccessToken);
		TrelloApiMain.Inicializar(trelloKey, trelloAccessToken);
		
		try {
			githubuser = GitHubAPI.getSelf(credentials);
		} catch (AuthenticationException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		textArea.setText("Bem Vindo " + githubuser.name);

	}
}
