package pt.tecsis.es_leti_1sem_2021_grupo15.GUI;

		
import java.awt.Color;

import java.util.HashMap;
import java.util.Map.Entry;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


import pt.tecsis.es_leti_1sem_2021_grupo15.trello_api.TrelloAcoes;
import pt.tecsis.es_leti_1sem_2021_grupo15.trello_api.TrelloDatas;
import pt.tecsis.es_leti_1sem_2021_grupo15.trello_api.TrelloGitTempos;


/**
 * @author Gonçalo Benido
 *
 */
public class GUITabelas {
		
		

		public class Tabela {
			
			public JFrame frame;
			public DefaultTableModel model;
			public JScrollPane pane;
			public JTable table;
			
			public Tabela(JFrame frame2, DefaultTableModel model2, JScrollPane pane2, JTable table2) {
				this.frame=frame2;
				this.model=model2;
				this.pane=pane2;
				this.table=table2;
			}
		}
		
		
		/**
		 * 
		 * @param TituloDasColunas - nome das colunas da tabela ({@link String[]})
		 * @param NomeDaTabela - o titulo da tabela ({@link String})
		 * @return Cria uma tabela generica ({@link Tabela})
		 */
		private static Tabela contrutorDeTabelas(String[] TituloDasColunas, String NomeDaTabela){
			
			GUITabelas construtor = new GUITabelas();
			
			
			JTable table = new JTable();
			
			Object[] columns = TituloDasColunas;
			
			
			DefaultTableModel model = new DefaultTableModel();
			
			JFrame frame = new JFrame(NomeDaTabela);
			frame.getContentPane().setBackground(new Color(0,0,0));
			frame.getContentPane().setForeground(Color.WHITE);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.getContentPane().setLayout(null);
			frame.setLocationRelativeTo(null);
			
			model.setColumnIdentifiers(columns);
			table.setModel(model);
			
			table.setBackground(Color.WHITE);
			table.setForeground(Color.BLACK);
			table.setSelectionBackground(Color.BLACK);
			table.setGridColor(Color.BLACK);
			table.setSelectionForeground(Color.WHITE);

			table.setRowHeight(30);
			table.setAutoCreateRowSorter(true);
			
			JScrollPane pane = new JScrollPane(table);
			table.setForeground(Color.BLACK);
			pane.setBackground(Color.WHITE);
			frame.getContentPane().add(pane);
			
			Tabela tabela = construtor.new Tabela(frame,model,pane,table); 
			
			return tabela;
		}

		

		
		/**
		 * Cria a tabela Total, uma tabela que contem todas as informacoes do projeto
		 * @param idDoQuadro - ID do quadro que pretende criar a tabela ({@link String})
		 * @param Custo - Valor do custo por hora ({@link String})
		 * @return Vai criar duas tabelas Tabela[0] = Contem_as_informacoes_gerais_por_membro_no_projeto; Tabela[1] = Contem_a_informacao_geral_do_projeto ({@link Tabela[]})
		 */
		public static Tabela[] tabelaTotal(String idDoQuadro, String Custo){
	
			Tabela[] tabelas = new Tabela[2];
			
			String[] titulosDasColunas = {"Sprints","Nome do Membro", "Horas Previstas","Horas Gastas", "Custo Por Membro"};
			
			Tabela tabela =  contrutorDeTabelas(titulosDasColunas, "Projeto");
			
			
			tabela.frame.setBounds(100,100,957,550);
			tabela.pane.setBounds(10,10,924,550);
			
			Object[]  row = new Object[5];
			
			int custo = Integer.parseInt(Custo);
			
			int numeroDeSprints = TrelloAcoes.getNumeroDeSprints(idDoQuadro);
			
			
			for(int i = 0; i!=numeroDeSprints+1;i++){
			
				String sprint = "S" + i;
			
			
				HashMap<String,Double[]> temposPorMembro =  TrelloAcoes.getTempoPorSprintPorMembro(idDoQuadro, sprint );
							
				
				for(Entry<String,Double[]> entry: temposPorMembro.entrySet()){
					
					row[0] = "Sprint" + i;
					row[1] = entry.getKey();
					row[2] = entry.getValue()[1];
					row[3] = entry.getValue()[0];
					row[4] = entry.getValue()[0] * custo;
					
						
					tabela.model.addRow(row);

					
				}

			}
			
			tabela.frame.setVisible(true);
			
			
			String[] titulosDasColunas2 = {"Horas totais do Projeto", "Custo Total Do Projeto"};
			
			Tabela tabela2 =  contrutorDeTabelas(titulosDasColunas2, "Tempo e Custo total do Projeto");
			
			
			Double[] tempoTotal = TrelloAcoes.getTempoPorQuadro(idDoQuadro);
			
			tabela2.frame.setBounds(100,100,357, 100);
			tabela2.pane.setBounds(10,10,324,100);
			
			Object[]  row2 = new Object[2];
			
			
			row2[0] = tempoTotal[0];
			row2[1] = tempoTotal[0] * custo;
			
			tabela2.model.addRow(row2);
			
			
			tabela2.frame.setVisible(true);
			
			tabelas[0] = tabela;
			tabelas[1] = tabela2;
			
			return tabelas;
			
		}
		
		

		/**
		 * @param idDoQuadro - idDoQuadro - ID do quadro que pretende criar a tabela ({@link String})
		 * @param NomeDoSprint - NOME_DO_SPRINT = "S1", S1 representa Sprint 1 ({@link String})
		 * @return Vai criar uma tabela com os tempos de um Sprint específico
		 */
		public static Tabela tabelaPorSprint(String idDoQuadro, String NomeDoSprint){
			
			String[] titulosDasColunas = {"Nome do Membro", "Horas Previstas","Horas Gastas"};
			
			Tabela tabela =  contrutorDeTabelas(titulosDasColunas, NomeDoSprint + " - Horas");
			
			
			tabela.frame.setBounds(100,100,557,410);
			tabela.pane.setBounds(10,10,524,410);
			
			
			Object[]  row = new Object[3];
			
			
			HashMap<String,Double[]> temposPorMembro =  TrelloAcoes.getTempoPorSprintPorMembro(idDoQuadro, NomeDoSprint );
			
			System.out.println(temposPorMembro.size());
			
			
			for(Entry<String,Double[]> entry: temposPorMembro.entrySet()){
				
				row[0] = entry.getKey();
				row[1] = entry.getValue()[1];
				row[2] = entry.getValue()[0];
				
					
				tabela.model.addRow(row);
				
			}
			
			
			tabela.frame.setVisible(true);
			
			return tabela;
			
		}

		

		/**
		 * 
		 * @param idDoQuadro - ID do quadro que pretende criar a tabela ({@link String})
		 * @param NomeDoSprint - NOME_DO_SPRINT = "S1", S1 representa Sprint 1 ({@link String})
		 * @param Custo - Valor do custo por hora ({@link String})
		 * @return Vai devolver uma tabela com os Custos de um Sprint específico
		 */
		public static Tabela tabelaCustoPorSprint(String idDoQuadro, String NomeDoSprint, String Custo) {
			
			String[] titulosDasColunas = {"Nome do Membro", "Horas Previstas","Horas Gastas", "Custo"};
			
			Tabela tabela =  contrutorDeTabelas(titulosDasColunas,  NomeDoSprint + " - Custos");
			
			
			tabela.frame.setBounds(100,100,557,410);
			tabela.pane.setBounds(10,10,524,410);
			
			
			
			Object[]  row = new Object[4];
			
			
			int custo = Integer.parseInt(Custo);
			
			
			
			HashMap<String,Double[]> temposPorMembro =  TrelloAcoes.getTempoPorSprintPorMembro(idDoQuadro, NomeDoSprint );
			
			System.out.println(temposPorMembro.size());
			
			
			for(Entry<String,Double[]> entry: temposPorMembro.entrySet()){
				
				row[0] = entry.getKey();
				row[1] = entry.getValue()[1];
				row[2] = entry.getValue()[0];
				row[3] = entry.getValue()[0] * custo;
					
				tabela.model.addRow(row);
				
			}
			
			
			tabela.frame.setVisible(true);
			
			return tabela;
			
		}	
			
			
		
		/**
		 * Os testes tem de estar indentificados num carta no Trello com o Titulo da carta como (Testes)
		 * @param idDoQuadro - ID do quadro que pretende criar a tabela ({@link String})
		 * @param NomeDoSprint - NOME_DO_SPRINT = "S1", S1 representa Sprint 1 ({@link String})
		 * @param Custo - Valor do custo por hora ({@link String})
		 * @return Vai devolver uma tabela com o tempo que cada teste foi realizado
		 */
		public static Tabela tabelaTempoTestes(String idDoQuadro) {
			
			String[] titulosDasColunas = {"Data De ínicio", "Data De Fim","Teste realizado"};
			
			Tabela tabela =  contrutorDeTabelas(titulosDasColunas, "Testes Datas");
			
			
			tabela.frame.setBounds(100,100,957,410);
			tabela.pane.setBounds(10,10,924,610);
			
				
			Object[]  row = new Object[4];

			
			HashMap<String,String[]> NomeDatas =  TrelloDatas.getDataTestes(idDoQuadro);
			
			System.out.println(NomeDatas.size());
			
			
			for(Entry<String,String[]> entry: NomeDatas.entrySet()){
				 
				row[0] = entry.getValue()[0];
				row[1] = entry.getValue()[1];
				row[2] = entry.getKey();
					
				tabela.model.addRow(row);
				
			}
			
			
			tabela.frame.setVisible(true);
			
			return tabela;
			
		}	
		
			
		
		
		/**
		 * @param idDoQuadro - ID do quadro que pretende criar a tabela ({@link String})
		 * @param Custo - Valor do custo por hora ({@link String})
		 * @return Vai criar duas tabelas Tabela[0] = Contem_as_informacoes_das_horas_que_originaram_commits_por_membro; Tabela[1] = Contem_a_informacao_das_horas_que_originaram_commits_no_projeto ({@link Tabela[]})
		 */
		public static Tabela[] tabelaGerouCommits(String idDoQuadro, String Custo){
			
			Double atividades_totais = 0.0;
			Double horas_totais = 0.0; 

			
			Tabela[] tabelas = new Tabela[2];
			
			String[] titulosDasColunas = {"Nome do Membro", "Número De Atividades","Horas Gastas", "Custo Por Membro"};
			
			Tabela tabela =  contrutorDeTabelas(titulosDasColunas, "Origem a artefactos");
			
			
			tabela.frame.setBounds(100,100,957,550);
			tabela.pane.setBounds(10,10,924,550);
			
			Object[]  row = new Object[4];
			
			int custo = Integer.parseInt(Custo);
			
						
			
			HashMap<String,Double[]> horasPorCommit =  TrelloGitTempos.getTempoPorCommitPorMembro(idDoQuadro);

							
				
			for(Entry<String,Double[]> entry: horasPorCommit.entrySet()){
					
				row[0] = entry.getKey();
				row[1] = entry.getValue()[0];
				row[2] = entry.getValue()[1];
				row[3] = entry.getValue()[1] * custo;
					
						
				tabela.model.addRow(row);
				
				
				atividades_totais = atividades_totais + entry.getValue()[0];
				horas_totais = horas_totais + entry.getValue()[1]; 

							
			}
			
			tabela.frame.setVisible(true);
			
			
			String[] titulosDasColunas2 = {"Número De Atividades","Horas totais do Projeto", "Custo Total Do Projeto"};
			
			Tabela tabela2 =  contrutorDeTabelas(titulosDasColunas2, "Tempo e Custo total do Projeto");
			
			
			tabela2.frame.setBounds(100,100,557, 100);
			tabela2.pane.setBounds(10,10,524,100);
			
			Object[]  row2 = new Object[3];

			row2[0] = atividades_totais;
			row2[1] = horas_totais;
			row2[2] = horas_totais * custo;
			
			tabela2.model.addRow(row2);
			
			
			tabela2.frame.setVisible(true);
			
			tabelas[0] = tabela;
			tabelas[1] = tabela2;
			
			return tabelas;
			
		}
		
		
		

		/**
		 * @param idDoQuadro - ID do quadro que pretende criar a tabela ({@link String})
		 * @param Custo - Valor do custo por hora ({@link String})
		 * @return Vai criar duas tabelas Tabela[0] = Contem_as_informacoes_das_horas_que_originaram_commits_por_membro; Tabela[1] = Contem_a_informacao_das_horas_que_originaram_commits_no_projeto ({@link Tabela[]})
		 */
		public static Tabela[] tabelaNaoGerouCommits(String idDoQuadro, String Custo){
			
			Double atividades_totais = 0.0;
			Double horas_totais = 0.0; 
			
			Tabela[] tabelas = new Tabela[2];
			
			String[] titulosDasColunas = {"Nome do Membro", "Número De Atividades","Horas Gastas", "Custo Por Membro"};
			
			Tabela tabela =  contrutorDeTabelas(titulosDasColunas, "Atividades onde não gerou artefactos");
			
			
			tabela.frame.setBounds(100,100,957,550);
			tabela.pane.setBounds(10,10,924,550);
			
			Object[]  row = new Object[4];
			
			int custo = Integer.parseInt(Custo);
			
						
			
			HashMap<String,Double[]> horasPorCommit =  TrelloGitTempos.getTempoPorCommitPorMembro(idDoQuadro);
			
			HashMap<String,Double[]> horasTotaisPorMembro =  TrelloAcoes.getTempoPorMembro(idDoQuadro);
			
							
			for(Entry<String,Double[]> entry: horasPorCommit.entrySet()){	
				
				for(Entry<String,Double[]> entry2: horasTotaisPorMembro.entrySet()){
					
					if(entry.getKey().equalsIgnoreCase(entry2.getKey())){
						
					row[0] = entry.getKey();
					row[1] = entry.getValue()[2];
					row[2] = entry2.getValue()[0] - entry.getValue()[1] ;
					row[3] = (entry2.getValue()[0] - entry.getValue()[1]) * custo;
						
							
					tabela.model.addRow(row);
					
					
					atividades_totais = atividades_totais + entry.getValue()[0];
					horas_totais = horas_totais +(entry2.getValue()[0] - entry.getValue()[1]);
					
					}
	
						
				}
			}
			
			tabela.frame.setVisible(true);
			
			
			String[] titulosDasColunas2 = {"Número De Atividades","Horas totais do Projeto", "Custo Total Do Projeto"};
			
			Tabela tabela2 =  contrutorDeTabelas(titulosDasColunas2, "Tempo e Custo total Sem Commit");
			
			
			tabela2.frame.setBounds(100,100,557, 100);
			tabela2.pane.setBounds(10,10,524,100);
			
			Object[]  row2 = new Object[3];

			row2[0] = atividades_totais;
			row2[1] = horas_totais;
			row2[2] = horas_totais * custo;
			
			tabela2.model.addRow(row2);
			
			
			tabela2.frame.setVisible(true);
			
			tabelas[0] = tabela;
			tabelas[1] = tabela2;
			
			return tabelas;
			
		}
			
			
			
			
			
			
			
			
			



}
