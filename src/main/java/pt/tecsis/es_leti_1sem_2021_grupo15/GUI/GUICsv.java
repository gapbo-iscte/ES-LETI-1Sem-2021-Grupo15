package pt.tecsis.es_leti_1sem_2021_grupo15.GUI;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JTable;
import javax.swing.table.TableModel;

import pt.tecsis.es_leti_1sem_2021_grupo15.GUI.GUITabelas.Tabela;

/**
 * @author Gonçalo Benido
 *
 */
public class GUICsv {
	

	/**
	 * @param tableToExport - Tabela que pretende exportar par CSV ({@link JTable})
	 * @param pathToExportTo - Path para onde o ficheiro CSV vai ser guardado ({@link String})
	 * @return Um boolena para confirmar se deu certo a exportaçao do fucheiro ({@link boolean})
	 */
	public static boolean exportToCSV(JTable tableToExport, String pathToExportTo) {

	    try {

	        TableModel model = tableToExport.getModel();
	        FileWriter csv = new FileWriter(new File(pathToExportTo),true);

	        for (int i = 0; i < model.getColumnCount(); i++) {
	            csv.write(model.getColumnName(i) + ",");
	        }

	        csv.write("\n");

	        for (int i = 0; i < model.getRowCount(); i++) {
	            for (int j = 0; j < model.getColumnCount(); j++) {
	                csv.write(model.getValueAt(i, j).toString() + ",");
	                System.out.println(model.getValueAt(i, j).toString() + ",");
	            }
	            csv.write("\n");
	        }

	        csv.close();
	        return true;
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	    return false;
	}
	

	
	
	/**
	 * @param pathToExportTo - path onde vai ser guardado o ficheiro ({@link String})
	 * @return retorna uma String onde contem o nome do path onde as palvaras sao aseparadas por "_" ({@link String})
	 */
	public static String pathCorrection(String pathToExportTo){
		
			String[] partes = pathToExportTo.split(" ");
			
			String pathCompleto = "Tabela_";
			
			for(int i = 0; i != partes.length; i++){
				
				pathCompleto = pathCompleto + partes[i] + "_";				
				
			}

		return pathCompleto;
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
				
				String pathCorrigido = pathCorrection(tabelas[i].frame.getAccessibleContext().getAccessibleName());
				
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
			
			String pathCorrigido = pathCorrection(tabela.frame.getAccessibleContext().getAccessibleName());
				
			boolean deu = GUICsv.exportToCSV(tabela.table,pathToExportTo + pathCorrigido +".csv");
			
			return deu;
			
			
		}
		
	
	

}
