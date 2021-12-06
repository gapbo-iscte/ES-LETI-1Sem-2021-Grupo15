package pt.tecsis.es_leti_1sem_2021_grupo15.GUI;

		
import java.awt.Color;

import java.util.HashMap;
import java.util.Map.Entry;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


import pt.tecsis.es_leti_1sem_2021_grupo15.trello_api.TrelloAcoes;


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
		 *  Caso queira exportar multiplas Tabelas
		 * @param tabelas - tabelas que pretente exportar ({@link Tabela[]})
		 *  @param pathToExportTo - path onde pretende guardar o ficheiro ({@link String})
		 * @return um ficheiro csv com os conteudos das tabelas ({@link .csv})
		 */
		public static boolean exportTabela(Tabela[] tabelas, String pathToExportTo) {		
			
			boolean deu = false;

			for(int i=0; i != tabelas.length; i++){
				
				String pathCorrigido = GUICsv.pathCorrection(tabelas[i].frame.getAccessibleContext().getAccessibleName());
				
				deu = GUICsv.exportToCSV(tabelas[i].table, pathToExportTo + pathCorrigido +".csv");
				
			}
			
			return deu;
			
			
		}
		
		
		
	
		/**
		 * Caso queira exportar somente uma tabela
		 * @param tabela - tabela que pretente exportar ({@link Tabela})
		 * @param pathToExportTo - path onde pretende guardar o ficheiro ({@link String})
		 * @return um ficheiro csv com os conteudos da tabela ({@link .csv})
		 */
		public static boolean exportTabela(Tabela tabela, String pathToExportTo) {	
			
			String pathCorrigido = GUICsv.pathCorrection(tabela.frame.getAccessibleContext().getAccessibleName());
				
			boolean deu = GUICsv.exportToCSV(tabela.table,pathToExportTo + pathCorrigido +".csv");
			
			return deu;
			
			
		}
			
			
			
			
			
			
			
			
			
			
			
			



}
