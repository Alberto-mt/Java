package controlador;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.Generated;
import javax.swing.JOptionPane;

import jxl.WorkbookSettings;
import jxl.format.CellFormat;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import modelo.Modelo;

/**
 * GestionaExportarDatos - Clase que permite exportar tablas a documentos: XML,
 * SQL y XSD
 * 
 * @author Alberto
 *
 */
public class GestionaExportarDatos {
	/*
	 * Creo objetos para acceder a sus m�todos
	 */
	GestionaConsultasModelos consul = new GestionaConsultasModelos();
	// Carpeta padre de documentos exportados
	String carpeta = "";

	/**
	 * xmlMarcas(String marca) - M�todo que crea un documento XML de un marca
	 * seleccionada, en orden ascendente
	 * 
	 * @param marca - marca seleccionada
	 */
	public void xmlMarcas(String marca) {
		/* Cargo informaci�n */
		ArrayList<Modelo> arrayConsulta = consul.consultaMarcAsc(marca);
		BufferedWriter bw = null;
		FileWriter fw = null;
		try {
			String fichero = "documentosExportados/marcasXML/" + marca + ".xml";
			File file = new File(fichero);
			// Si el archivo no existe, se crea
			if (!file.exists()) {
				file.createNewFile();
			}
			/* Flag true, indica adjuntar informaci�n al fichero */
			fw = new FileWriter(file.getAbsoluteFile(), true);
			bw = new BufferedWriter(fw);
			bw.write("<Modelos>");
			bw.newLine();
			for (int i = 0; i < arrayConsulta.size(); i++) {
				bw.write("	<ModeloElegivo>");
				bw.newLine();
				bw.write("		<Modelo>" + arrayConsulta.get(i).getModelo() + "</Modelo>");
				bw.newLine();
				bw.write("" + "		<Consumo>" + arrayConsulta.get(i).getConsumo() + "</Consumo>");
				bw.newLine();
				bw.write("" + "		<Emisiones>" + arrayConsulta.get(i).getEmisiones() + "</Emisiones>");
				bw.newLine();
				bw.write("		<C_Energetica>" + arrayConsulta.get(i).getcEnergetica() + "</C_Energetica>");
				bw.newLine();
				bw.write("	</ModeloElegivo>");
				bw.newLine();
			}
			bw.write("</Modelos>");
			bw.newLine();
			// Informaci�n agregada al fichero
			JOptionPane.showMessageDialog(null, "XML CREADO CORRECTAMENTE", "INFORMACION",
					JOptionPane.INFORMATION_MESSAGE);
		} catch (IOException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "ERROR: FICHERO NO CREADO", "ERROR", JOptionPane.ERROR_MESSAGE);
		} finally {
			try {
				/* Cierra instancias de FileWriter y BufferedWriter */
				if (bw != null) {
					bw.close();
				}
				if (fw != null) {
					fw.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, "ERROR: AL CERRRAR FICHERO", "ERROR", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	/**
	 * sqlMarcas(String marca) - M�todo que crea un documento SQL de un marca
	 * seleccionada, en orden ascendente
	 * 
	 * @param marca - marca seleccionada
	 */
	public void sqlMarcas(String marca) {
		/* Cargo informaci�n */
		ArrayList<Modelo> arrayConsulta = consul.consultaMarcAsc(marca);
		BufferedWriter bw = null;
		FileWriter fw = null;
		try {
			String fichero = "documentosExportados/marcasSQL/" + marca + ".sql";
			File file = new File(fichero);
			// Si el archivo no existe, se crea!
			if (!file.exists()) {
				file.createNewFile();
			}
			/* Flag true, indica adjuntar informaci�n al fichero */
			fw = new FileWriter(file.getAbsoluteFile(), true);
			bw = new BufferedWriter(fw);
			// Creo tabla
			bw.write("\nCREATE TABLE " + marca + "(");
			bw.write("\n\t MODELO VARCHAR(200) NOT NULL,");
			bw.write("\n\t CONSUMO FLOAT,");
			bw.write("\n\t EMISIONES FLOAT,");
			bw.write("\n\t C_ENERGETICA VARCHAR(2),");
			bw.write("\n\t CONSTRAINT PK_" + marca + "_MODELO PRIMARY KEY (MODELO),");
			bw.write("\n\t CONSTRAINT FK_" + marca + "_MODELO FOREIGN KEY (MODELO)");
			bw.write("\n\t\t REFERENCES modelos(MODELO),");
			bw.write("\n\t CONSTRAINT FK_" + marca + "_CONSUMO FOREIGN KEY (CONSUMO)");
			bw.write("\n\t\t REFERENCES modelos(CONSUMO),");
			bw.write("\n\t CONSTRAINT FK_" + marca + "_EMISIONES FOREIGN KEY (EMISIONES)");
			bw.write("\n\t\t REFERENCES modelos(EMISIONES),");
			bw.write("\n\t CONSTRAINT FK_" + marca + "_C_ENERGETICA FOREIGN KEY (C_ENERGETICA)");
			bw.write("\n\t\t REFERENCES modelos(C_ENERGETICA)");
			bw.write("\n);\n");
			bw.newLine();
			// Creo inserts
			for (int i = 0; i < arrayConsulta.size(); i++) {
				bw.write("INSERT INTO " + marca + " VALUES ('" + arrayConsulta.get(i).getModelo() + "',"
						+ arrayConsulta.get(i).getConsumo() + "," + arrayConsulta.get(i).getEmisiones() + ",'"
						+ arrayConsulta.get(i).getcEnergetica() + "');");
				bw.newLine();
			}

			// Informaci�n agregada al fichero
			JOptionPane.showMessageDialog(null, "SQL CREADO CORRECTAMENTE", "INFORMACION",
					JOptionPane.INFORMATION_MESSAGE);
		} catch (IOException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "ERROR: FICHERO NO CREADO", "ERROR", JOptionPane.ERROR_MESSAGE);
		} finally {
			try {
				/* Cierra instancias de FileWriter y BufferedWriter */
				if (bw != null) {
					bw.close();
				}
				if (fw != null) {
					fw.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, "ERROR: AL CERRRAR FICHERO", "ERROR", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	/**
	 * xslMarcas(String marca) - M�todo que crea un documento XSL de un marca
	 * seleccionada, en orden ascendente
	 * 
	 * @param marca - marca seleccionada
	 */
	public void xslMarcas(String marca) {
		/* Cargo informaci�n */
		ArrayList<Modelo> arrayConsulta = consul.consultaMarcAsc(marca);
		try {
			/* Creo documento con formato */
			String ruta = ("documentosExportados/marcasXLS/" + marca + ".xls");
			WorkbookSettings conf = new WorkbookSettings();
			conf.setEncoding("ISO-8859-1");
			WritableWorkbook workbook = jxl.Workbook.createWorkbook(new File(ruta), conf);
			WritableSheet sheet = workbook.createSheet("Resultado", 0);

			WritableFont h1 = new WritableFont(WritableFont.ARIAL, 16, WritableFont.BOLD);
			WritableFont h = new WritableFont(WritableFont.ARIAL, 14, WritableFont.NO_BOLD);
			WritableCellFormat hFormat1 = new WritableCellFormat(h1);
			WritableCellFormat hFormat = new WritableCellFormat(h);
			// Escribo encabezado de tabla
			sheet.addCell(new jxl.write.Label(0, 0, "MODELO", hFormat1));
			sheet.addCell(new jxl.write.Label(1, 0, "CONSUMO", hFormat1));
			sheet.addCell(new jxl.write.Label(2, 0, "EMISIONES", hFormat1));
			sheet.addCell(new jxl.write.Label(3, 0, "C_ENERGETICA", hFormat1));
			// Escribo contenido
			for (int i = 1; i < arrayConsulta.size(); i++) {
				sheet.addCell(new jxl.write.Label(0, i, arrayConsulta.get(i).getModelo(), hFormat));
				sheet.addCell(new jxl.write.Number(1, i, arrayConsulta.get(i).getConsumo(), hFormat));
				sheet.addCell(new jxl.write.Number(2, i, arrayConsulta.get(i).getEmisiones(), hFormat));
				sheet.addCell(new jxl.write.Label(3, i, arrayConsulta.get(i).getcEnergetica(), hFormat));
			}
			/*
			 * Cierro e informo al usuario de que se creado correctamente
			 */
			workbook.write();
			workbook.close();
			JOptionPane.showMessageDialog(null, "XLS CREADO CORRECTAMENTE", "INFORMACION",
					JOptionPane.INFORMATION_MESSAGE);
			/* Capturo excepciones */
		} catch (IOException ex) {
			JOptionPane.showMessageDialog(null, "ERROR: EL SISTEMA NO PUEDE ENCONTRAR LA RUTA ESPECIFICADA", "ERROR",
					JOptionPane.ERROR_MESSAGE);
		} catch (WriteException ex) {
			JOptionPane.showMessageDialog(null, "ERROR: AL CREAR DOCUMENTO", "ERROR", JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * xmlConsumos(Float consumo) - M�todo que crea un documento XML de un consumo
	 * menor o igual al seleccionado, en orden descendente
	 * 
	 * @param consumo - consumo elegido
	 */
	public void xmlConsumos(Float consumo) {
		/* Cargo informaci�n */
		ArrayList<Modelo> arrayConsulta = consul.consultaConsDes(consumo);
		BufferedWriter bw = null;
		FileWriter fw = null;
		try {
			String fichero = "documentosExportados/consumosXML/consumoMenorOIgual_" + consumo + ".xml";
			File file = new File(fichero);
			// Si el archivo no existe, se crea
			if (!file.exists()) {
				file.createNewFile();
			}
			/* Flag true, indica adjuntar informaci�n al fichero */
			fw = new FileWriter(file.getAbsoluteFile(), true);
			bw = new BufferedWriter(fw);
			bw.write("<Modelos>");
			bw.newLine();
			for (int i = 0; i < arrayConsulta.size(); i++) {
				bw.write("	<ModeloElegivo>");
				bw.newLine();
				bw.write("		<Modelo>" + arrayConsulta.get(i).getModelo() + "</Modelo>");
				bw.newLine();
				bw.write("" + "		<Consumo>" + arrayConsulta.get(i).getConsumo() + "</Consumo>");
				bw.newLine();
				bw.write("" + "		<Emisiones>" + arrayConsulta.get(i).getEmisiones() + "</Emisiones>");
				bw.newLine();
				bw.write("		<C_Energetica>" + arrayConsulta.get(i).getcEnergetica() + "</C_Energetica>");
				bw.newLine();
				bw.write("	</ModeloElegivo>");
				bw.newLine();
			}
			bw.write("</Modelos>");
			bw.newLine();
			// Informaci�n agregada al fichero
			JOptionPane.showMessageDialog(null, "XML CREADO CORRECTAMENTE", "INFORMACION",
					JOptionPane.INFORMATION_MESSAGE);
		} catch (IOException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "ERROR: FICHERO NO CREADO", "ERROR", JOptionPane.ERROR_MESSAGE);
		} finally {
			try {
				/* Cierra instancias de FileWriter y BufferedWriter */
				if (bw != null) {
					bw.close();
				}
				if (fw != null) {
					fw.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, "ERROR: AL CERRRAR FICHERO", "ERROR", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	/**
	 * sqlConsumo(Float consumo) - M�todo que crea un documento SQL de un consumo
	 * menor o igual al seleccionado, en orden descendente
	 * 
	 * @param consumo - consumo elegido
	 */
	public void sqlConsumo(Float consumo) {
		/* Cargo informaci�n */
		ArrayList<Modelo> arrayConsulta = consul.consultaConsDes(consumo);
		BufferedWriter bw = null;
		FileWriter fw = null;
		try {
			String fichero = "documentosExportados/consumosSQL/consumoMenorOIgual_" + consumo + ".sql";
			File file = new File(fichero);
			// Si el archivo no existe, se crea
			if (!file.exists()) {
				file.createNewFile();
			}
			/* Flag true, indica adjuntar informaci�n al fichero */
			fw = new FileWriter(file.getAbsoluteFile(), true);
			bw = new BufferedWriter(fw);
			// Creo tabla
			bw.write("\nCREATE TABLE ConsultaConsumo(");
			bw.write("\n\t MODELO VARCHAR(200) NOT NULL,");
			bw.write("\n\t CONSUMO FLOAT,");
			bw.write("\n\t EMISIONES FLOAT,");
			bw.write("\n\t C_ENERGETICA VARCHAR(2),");
			bw.write("\n\t CONSTRAINT PK_ConsultaConsumo_MODELO PRIMARY KEY (MODELO),");
			bw.write("\n\t CONSTRAINT FK_ConsultaConsumo_Consumo_MODELO FOREIGN KEY (MODELO)");
			bw.write("\n\t\t REFERENCES modelos(MODELO),");
			bw.write("\n\t CONSTRAINT FK_ConsultaConsumo_CONSUMO FOREIGN KEY (CONSUMO)");
			bw.write("\n\t\t REFERENCES modelos(CONSUMO),");
			bw.write("\n\t CONSTRAINT FK_ConsultaConsumo_EMISIONES FOREIGN KEY (EMISIONES)");
			bw.write("\n\t\t REFERENCES modelos(EMISIONES),");
			bw.write("\n\t CONSTRAINT FK_ConsultaConsumo_C_ENERGETICA FOREIGN KEY (C_ENERGETICA)");
			bw.write("\n\t\t REFERENCES modelos(C_ENERGETICA)");
			bw.write("\n);\n");
			bw.newLine();
			// Creo inserts
			for (int i = 0; i < arrayConsulta.size(); i++) {
				bw.write("INSERT INTO ConsultaConsumo VALUES ('" + arrayConsulta.get(i).getModelo() + "',"
						+ arrayConsulta.get(i).getConsumo() + "," + arrayConsulta.get(i).getEmisiones() + ",'"
						+ arrayConsulta.get(i).getcEnergetica() + "');");
				bw.newLine();
			}

			// Informaci�n agregada al fichero
			JOptionPane.showMessageDialog(null, "SQL CREADO CORRECTAMENTE", "INFORMACION",
					JOptionPane.INFORMATION_MESSAGE);
		} catch (IOException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "ERROR: FICHERO NO CREADO", "ERROR", JOptionPane.ERROR_MESSAGE);
		} finally {
			try {
				/* Cierra instancias de FileWriter y BufferedWriter */
				if (bw != null) {
					bw.close();
				}
				if (fw != null) {
					fw.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, "ERROR: AL CERRRAR FICHERO", "ERROR", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	/**
	 * xslConsumo(Float consumo) - M�todo que crea un documento XSL de un consumo
	 * menor o igual al seleccionado, en orden descendente
	 * 
	 * @param consumo - consumo elegido
	 */
	public void xslConsumo(Float consumo) {
		/* Cargo informaci�n */
		ArrayList<Modelo> arrayConsulta = consul.consultaConsDes(consumo);
		try {
			/* Creo documento con formato */
			String ruta = ("documentosExportados/consumosXLS/consumoMenorOIgual_" + consumo + ".xls");
			WorkbookSettings conf = new WorkbookSettings();
			conf.setEncoding("ISO-8859-1");
			WritableWorkbook workbook = jxl.Workbook.createWorkbook(new File(ruta), conf);
			WritableSheet sheet = workbook.createSheet("Resultado", 0);

			WritableFont h1 = new WritableFont(WritableFont.ARIAL, 16, WritableFont.BOLD);
			WritableFont h = new WritableFont(WritableFont.ARIAL, 14, WritableFont.NO_BOLD);
			WritableCellFormat hFormat1 = new WritableCellFormat(h1);
			WritableCellFormat hFormat = new WritableCellFormat(h);
			// Escribo encabezado de tabla
			sheet.addCell(new jxl.write.Label(0, 0, "MODELO", hFormat1));
			sheet.addCell(new jxl.write.Label(1, 0, "CONSUMO", hFormat1));
			sheet.addCell(new jxl.write.Label(2, 0, "EMISIONES", hFormat1));
			sheet.addCell(new jxl.write.Label(3, 0, "C_ENERGETICA", hFormat1));
			// Escribo contenido
			for (int i = 1; i < arrayConsulta.size(); i++) {
				sheet.addCell(new jxl.write.Label(0, i, arrayConsulta.get(i).getModelo(), hFormat));
				sheet.addCell(new jxl.write.Number(1, i, arrayConsulta.get(i).getConsumo(), hFormat));
				sheet.addCell(new jxl.write.Number(2, i, arrayConsulta.get(i).getEmisiones(), hFormat));
				sheet.addCell(new jxl.write.Label(3, i, arrayConsulta.get(i).getcEnergetica(), hFormat));
			}
			/*
			 * Cierro e informo al usuario de que se creado correctamente
			 */
			workbook.write();
			workbook.close();
			JOptionPane.showMessageDialog(null, "XLS CREADO CORRECTAMENTE", "INFORMACION",
					JOptionPane.INFORMATION_MESSAGE);
			/* Capturo excepciones */
		} catch (IOException ex) {
			JOptionPane.showMessageDialog(null, "ERROR: EL SISTEMA NO PUEDE ENCONTRAR LA RUTA ESPECIFICADA", "ERROR",
					JOptionPane.ERROR_MESSAGE);
		} catch (WriteException ex) {
			JOptionPane.showMessageDialog(null, "ERROR: AL CREAR DOCUMENTO", "ERROR", JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * xmlEmisiones(Float emision) - M�todo que crea un documento XQL de una emisi�n
	 * menor o igual a la seleccionada, en orden descendente
	 * 
	 * @param emision - emisi�n elegida
	 */
	public void xmlEmisiones(Float emision) {
		/* Cargo informaci�n */
		ArrayList<Modelo> arrayConsulta = consul.consultaEmiDes(emision);
		BufferedWriter bw = null;
		FileWriter fw = null;
		try {
			String fichero = "documentosExportados/emisionesXML/emisionMenorOIgual_" + emision + ".xml";
			File file = new File(fichero);
			// Si el archivo no existe, se crea
			if (!file.exists()) {
				file.createNewFile();
			}
			/* Flag true, indica adjuntar informaci�n al fichero */
			fw = new FileWriter(file.getAbsoluteFile(), true);
			bw = new BufferedWriter(fw);
			bw.write("<Modelos>");
			bw.newLine();
			for (int i = 0; i < arrayConsulta.size(); i++) {
				bw.write("	<ModeloElegivo>");
				bw.newLine();
				bw.write("		<Modelo>" + arrayConsulta.get(i).getModelo() + "</Modelo>");
				bw.newLine();
				bw.write("" + "		<Consumo>" + arrayConsulta.get(i).getConsumo() + "</Consumo>");
				bw.newLine();
				bw.write("" + "		<Emisiones>" + arrayConsulta.get(i).getEmisiones() + "</Emisiones>");
				bw.newLine();
				bw.write("		<C_Energetica>" + arrayConsulta.get(i).getcEnergetica() + "</C_Energetica>");
				bw.newLine();
				bw.write("	</ModeloElegivo>");
				bw.newLine();
			}
			bw.write("</Modelos>");
			bw.newLine();
			// Informaci�n agregada al fichero
			JOptionPane.showMessageDialog(null, "XML CREADO CORRECTAMENTE", "INFORMACION",
					JOptionPane.INFORMATION_MESSAGE);
		} catch (IOException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "ERROR: FICHERO NO CREADO", "ERROR", JOptionPane.ERROR_MESSAGE);
		} finally {
			try {
				/* Cierra instancias de FileWriter y BufferedWriter */
				if (bw != null) {
					bw.close();
				}
				if (fw != null) {
					fw.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, "ERROR: AL CERRRAR FICHERO", "ERROR", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	/**
	 * sqlEmisiones(Float emision) - M�todo que crea un documento SQL de una emisi�n
	 * menor o igual a la seleccionada, en orden descendente
	 * 
	 * @param emision - emisi�n elegida
	 */
	public void sqlEmisiones(Float emision) {
		/* Cargo informaci�n */
		ArrayList<Modelo> arrayConsulta = consul.consultaEmiDes(emision);
		BufferedWriter bw = null;
		FileWriter fw = null;
		try {
			String fichero = "documentosExportados/emisionesSQL/emisionMenorOIgual_" + emision + ".sql";
			File file = new File(fichero);
			// Si el archivo no existe, se crea
			if (!file.exists()) {
				file.createNewFile();
			}
			/* Flag true, indica adjuntar informaci�n al fichero */
			fw = new FileWriter(file.getAbsoluteFile(), true);
			bw = new BufferedWriter(fw);
			// Creo tabla
			bw.write("\nCREATE TABLE ConsultaEmision(");
			bw.write("\n\t MODELO VARCHAR(200) NOT NULL,");
			bw.write("\n\t CONSUMO FLOAT,");
			bw.write("\n\t EMISIONES FLOAT,");
			bw.write("\n\t C_ENERGETICA VARCHAR(2),");
			bw.write("\n\t CONSTRAINT PK_ConsultaEmision_MODELO PRIMARY KEY (MODELO),");
			bw.write("\n\t CONSTRAINT FK_ConsultaEmision_Consumo_MODELO FOREIGN KEY (MODELO)");
			bw.write("\n\t\t REFERENCES modelos(MODELO),");
			bw.write("\n\t CONSTRAINT FK_ConsultaEmision_CONSUMO FOREIGN KEY (CONSUMO)");
			bw.write("\n\t\t REFERENCES modelos(CONSUMO),");
			bw.write("\n\t CONSTRAINT FK_ConsultaEmision_EMISIONES FOREIGN KEY (EMISIONES)");
			bw.write("\n\t\t REFERENCES modelos(EMISIONES),");
			bw.write("\n\t CONSTRAINT FK_ConsultaEmision_C_ENERGETICA FOREIGN KEY (C_ENERGETICA)");
			bw.write("\n\t\t REFERENCES modelos(C_ENERGETICA)");
			bw.write("\n);\n");
			bw.newLine();
			// Creo inserts
			for (int i = 0; i < arrayConsulta.size(); i++) {
				bw.write("INSERT INTO ConsultaEmision VALUES ('" + arrayConsulta.get(i).getModelo() + "',"
						+ arrayConsulta.get(i).getConsumo() + "," + arrayConsulta.get(i).getEmisiones() + ",'"
						+ arrayConsulta.get(i).getcEnergetica() + "');");
				bw.newLine();
			}

			// Informaci�n agregada al fichero
			JOptionPane.showMessageDialog(null, "SQL CREADO CORRECTAMENTE", "INFORMACION",
					JOptionPane.INFORMATION_MESSAGE);
		} catch (IOException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "ERROR: FICHERO NO CREADO", "ERROR", JOptionPane.ERROR_MESSAGE);
		} finally {
			try {
				/* Cierra instancias de FileWriter y BufferedWriter */
				if (bw != null) {
					bw.close();
				}
				if (fw != null) {
					fw.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, "ERROR: AL CERRRAR FICHERO", "ERROR", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	/**
	 * xslEmision(Float emision) - M�todo que crea un documento XSL de una emisi�n
	 * menor o igual a la seleccionada, en orden descendente
	 * 
	 * @param emision - emisi�n elegida
	 */
	public void xslEmision(Float emision) {
		/* Cargo informaci�n */
		ArrayList<Modelo> arrayConsulta = consul.consultaEmiDes(emision);
		try {
			/* Creo documento con formato */
			String ruta = ("documentosExportados/emisionesXLS/emisionMenorOIgual_" + emision + ".xls");
			WorkbookSettings conf = new WorkbookSettings();
			conf.setEncoding("ISO-8859-1");
			WritableWorkbook workbook = jxl.Workbook.createWorkbook(new File(ruta), conf);
			WritableSheet sheet = workbook.createSheet("Resultado", 0);

			WritableFont h1 = new WritableFont(WritableFont.ARIAL, 16, WritableFont.BOLD);
			WritableFont h = new WritableFont(WritableFont.ARIAL, 14, WritableFont.NO_BOLD);
			WritableCellFormat hFormat1 = new WritableCellFormat(h1);
			WritableCellFormat hFormat = new WritableCellFormat(h);
			// Escribo encabezado de tabla
			sheet.addCell(new jxl.write.Label(0, 0, "MODELO", hFormat1));
			sheet.addCell(new jxl.write.Label(1, 0, "CONSUMO", hFormat1));
			sheet.addCell(new jxl.write.Label(2, 0, "EMISIONES", hFormat1));
			sheet.addCell(new jxl.write.Label(3, 0, "C_ENERGETICA", hFormat1));
			// Escribo contenido
			for (int i = 1; i < arrayConsulta.size(); i++) {
				sheet.addCell(new jxl.write.Label(0, i, arrayConsulta.get(i).getModelo(), hFormat));
				sheet.addCell(new jxl.write.Number(1, i, arrayConsulta.get(i).getConsumo(), hFormat));
				sheet.addCell(new jxl.write.Number(2, i, arrayConsulta.get(i).getEmisiones(), hFormat));
				sheet.addCell(new jxl.write.Label(3, i, arrayConsulta.get(i).getcEnergetica(), hFormat));
			}
			/*
			 * Cierro e informo al usuario de que se creado correctamente
			 */
			workbook.write();
			workbook.close();
			JOptionPane.showMessageDialog(null, "XLS CREADO CORRECTAMENTE", "INFORMACION",
					JOptionPane.INFORMATION_MESSAGE);
			/* Capturo excepciones */
		} catch (IOException ex) {
			JOptionPane.showMessageDialog(null, "ERROR: EL SISTEMA NO PUEDE ENCONTRAR LA RUTA ESPECIFICADA", "ERROR",
					JOptionPane.ERROR_MESSAGE);
		} catch (WriteException ex) {
			JOptionPane.showMessageDialog(null, "ERROR: AL CREAR DOCUMENTO", "ERROR", JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * xmlC_Energetica(String cer) - M�todo que crea un documento XML de un
	 * Certificaci�n Energ�tica seleccionada, en orden ascendente
	 * 
	 * @param cer - certificaci�n elegida
	 */
	public void xmlC_Energetica(String cer) {
		/* Cargo informaci�n */
		ArrayList<Modelo> arrayConsulta = consul.consultaCEnerAsc(cer);
		BufferedWriter bw = null;
		FileWriter fw = null;
		try {
			String fichero = "documentosExportados/certificacionesXML/" + cer + ".xml";
			File file = new File(fichero);
			// Si el archivo no existe, se crea!
			if (!file.exists()) {
				file.createNewFile();
			}
			/* Flag true, indica adjuntar informaci�n al fichero */
			fw = new FileWriter(file.getAbsoluteFile(), true);
			bw = new BufferedWriter(fw);
			bw.write("<Modelos>");
			bw.newLine();
			for (int i = 0; i < arrayConsulta.size(); i++) {
				bw.write("	<ModeloElegivo>");
				bw.newLine();
				bw.write("		<Modelo>" + arrayConsulta.get(i).getModelo() + "</Modelo>");
				bw.newLine();
				bw.write("" + "		<Consumo>" + arrayConsulta.get(i).getConsumo() + "</Consumo>");
				bw.newLine();
				bw.write("" + "		<Emisiones>" + arrayConsulta.get(i).getEmisiones() + "</Emisiones>");
				bw.newLine();
				bw.write("		<C_Energetica>" + arrayConsulta.get(i).getcEnergetica() + "</C_Energetica>");
				bw.newLine();
				bw.write("	</ModeloElegivo>");
				bw.newLine();
			}
			bw.write("</Modelos>");
			bw.newLine();
			// Informaci�n agregada al fichero
			JOptionPane.showMessageDialog(null, "XML CREADO CORRECTAMENTE", "INFORMACION",
					JOptionPane.INFORMATION_MESSAGE);
		} catch (IOException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "ERROR: FICHERO NO CREADO", "ERROR", JOptionPane.ERROR_MESSAGE);
		} finally {
			try {
				/* Cierra instancias de FileWriter y BufferedWriter */
				if (bw != null) {
					bw.close();
				}
				if (fw != null) {
					fw.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, "ERROR: AL CERRRAR FICHERO", "ERROR", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	/**
	 * sqlC_Energetica(String cer) - M�todo que crea un documento SQL de un
	 * Certificaci�n Energ�tica seleccionada, en orden ascendente
	 * 
	 * @param cer - certificaci�n elegida
	 */
	public void sqlC_Energetica(String cer) {
		/* Cargo informaci�n */
		ArrayList<Modelo> arrayConsulta = consul.consultaCEnerAsc(cer);
		BufferedWriter bw = null;
		FileWriter fw = null;
		try {
			String fichero = "documentosExportados/certificacionesSQL/" + cer + ".sql";
			File file = new File(fichero);
			// Si el archivo no existe, se crea!
			if (!file.exists()) {
				file.createNewFile();
			}
			/* Flag true, indica adjuntar informaci�n al fichero */
			fw = new FileWriter(file.getAbsoluteFile(), true);
			bw = new BufferedWriter(fw);
			// CREO TABLA DE CONSULTA
			bw.write("\nCREATE TABLE " + cer + "(");
			bw.write("\n\t MODELO VARCHAR(200) NOT NULL,");
			bw.write("\n\t CONSUMO FLOAT,");
			bw.write("\n\t EMISIONES FLOAT,");
			bw.write("\n\t C_ENERGETICA VARCHAR(2),");
			bw.write("\n\t CONSTRAINT PK_" + cer + "_MODELO PRIMARY KEY (MODELO),");
			bw.write("\n\t CONSTRAINT FK_" + cer + "_Consumo_MODELO FOREIGN KEY (MODELO)");
			bw.write("\n\t\t REFERENCES modelos(MODELO),");
			bw.write("\n\t CONSTRAINT FK_" + cer + "_CONSUMO FOREIGN KEY (CONSUMO)");
			bw.write("\n\t\t REFERENCES modelos(CONSUMO),");
			bw.write("\n\t CONSTRAINT FK_" + cer + "_EMISIONES FOREIGN KEY (EMISIONES)");
			bw.write("\n\t\t REFERENCES modelos(EMISIONES),");
			bw.write("\n\t CONSTRAINT FK_" + cer + "_C_ENERGETICA FOREIGN KEY (C_ENERGETICA)");
			bw.write("\n\t\t REFERENCES modelos(C_ENERGETICA)");
			bw.write("\n);\n");
			bw.newLine();
			// INTRODUZCO DATOS
			for (int i = 0; i < arrayConsulta.size(); i++) {
				bw.write("INSERT INTO " + cer + " VALUES ('" + arrayConsulta.get(i).getModelo() + "',"
						+ arrayConsulta.get(i).getConsumo() + "," + arrayConsulta.get(i).getEmisiones() + ",'"
						+ arrayConsulta.get(i).getcEnergetica() + "');");
				bw.newLine();
			}

			// Informaci�n agregada al fichero
			JOptionPane.showMessageDialog(null, "SQL CREADO CORRECTAMENTE", "INFORMACION",
					JOptionPane.INFORMATION_MESSAGE);
		} catch (IOException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "ERROR: FICHERO NO CREADO", "ERROR", JOptionPane.ERROR_MESSAGE);
		} finally {
			try {
				/* Cierra instancias de FileWriter y BufferedWriter */
				if (bw != null) {
					bw.close();
				}
				if (fw != null) {
					fw.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, "ERROR: AL CERRRAR FICHERO", "ERROR", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	/**
	 * xslCEnergetica(String cer)- M�todo que crea un documento XSL de un
	 * Certificaci�n Energ�tica seleccionada, en orden ascendente
	 * 
	 * @param cer - certificaci�n elegida
	 */
	public void xslCEnergetica(String cer) {
		/* Cargo informaci�n */
		ArrayList<Modelo> arrayConsulta = consul.consultaCEnerAsc(cer);
		try {
			/* Creo documento con formato */
			String ruta = ("documentosExportados/certificacionesXLS/" + cer + ".xls");
			WorkbookSettings conf = new WorkbookSettings();
			conf.setEncoding("ISO-8859-1");
			WritableWorkbook workbook = jxl.Workbook.createWorkbook(new File(ruta), conf);
			WritableSheet sheet = workbook.createSheet("Resultado", 0);

			WritableFont h1 = new WritableFont(WritableFont.ARIAL, 16, WritableFont.BOLD);
			WritableFont h = new WritableFont(WritableFont.ARIAL, 14, WritableFont.NO_BOLD);
			WritableCellFormat hFormat1 = new WritableCellFormat(h1);
			WritableCellFormat hFormat = new WritableCellFormat(h);
			// Escribo encabezado de tabla
			sheet.addCell(new jxl.write.Label(0, 0, "MODELO", hFormat1));
			sheet.addCell(new jxl.write.Label(1, 0, "CONSUMO", hFormat1));
			sheet.addCell(new jxl.write.Label(2, 0, "EMISIONES", hFormat1));
			sheet.addCell(new jxl.write.Label(3, 0, "C_ENERGETICA", hFormat1));
			// Escribo contenido
			for (int i = 1; i < arrayConsulta.size(); i++) {
				sheet.addCell(new jxl.write.Label(0, i, arrayConsulta.get(i).getModelo(), hFormat));
				sheet.addCell(new jxl.write.Number(1, i, arrayConsulta.get(i).getConsumo(), hFormat));
				sheet.addCell(new jxl.write.Number(2, i, arrayConsulta.get(i).getEmisiones(), hFormat));
				sheet.addCell(new jxl.write.Label(3, i, arrayConsulta.get(i).getcEnergetica(), hFormat));
			}
			/*
			 * Cierro e informo al usuario de que se creado correctamente
			 */
			workbook.write();
			workbook.close();
			JOptionPane.showMessageDialog(null, "XLS CREADO CORRECTAMENTE", "INFORMACION",
					JOptionPane.INFORMATION_MESSAGE);
			/* Capturo excepciones */
		} catch (IOException ex) {
			JOptionPane.showMessageDialog(null, "ERROR: EL SISTEMA NO PUEDE ENCONTRAR LA RUTA ESPECIFICADA", "ERROR",
					JOptionPane.ERROR_MESSAGE);
		} catch (WriteException ex) {
			JOptionPane.showMessageDialog(null, "ERROR: AL CREAR DOCUMENTO", "ERROR", JOptionPane.ERROR_MESSAGE);
		}
	}
}
