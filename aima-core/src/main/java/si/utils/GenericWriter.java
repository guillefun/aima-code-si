package si.utils;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class GenericWriter {

	private static GenericWriter instance;
	private static Workbook workbook; // new HSSFWorkbook() for generating `.xls` file

	private GenericWriter() {
		// Exists only to defeat instantiation.
	}

	public static GenericWriter getInstance() {
		if (instance == null) {
			instance = new GenericWriter();
			workbook = new XSSFWorkbook();
		}
		return instance;
	}

	public Workbook getWorkbook() {
		return workbook;
	}

	public void writeHeader(String[] headerNames, int row) {
//		Row row = sheet.createRow(row);
//		for (String string : headerNames) {
//		
//			row.createCell(0).setCellValue(b.getId());
//			
//		}
	}

	public int writeAStarBoard(List<AStarBoard> boards, String heuristic, int initRow) {
		int totalRows = 0;
		String[] columns = { "ID", "BOARD SIZE", "NODES EXPANDED", "NODES REINSERTED", "PATH COST", "TIME" };

		CreationHelper createHelper = workbook.getCreationHelper();

		Sheet sheet = null;
		
		try {
			sheet = workbook.getSheet("AStar heuristics");
			if(sheet == null) {
				sheet = workbook.createSheet("AStar heuristics");
			}
		}catch(Exception e) {
			sheet = workbook.createSheet("AStar heuristics");
		}
		
		Font headerFont = workbook.createFont();
		headerFont.setBold(true);
		headerFont.setFontHeightInPoints((short) 14);
		headerFont.setColor(IndexedColors.RED.getIndex());

		CellStyle headerCellStyle = workbook.createCellStyle();
		headerCellStyle.setFont(headerFont);

		sheet.createRow(initRow + 1).createCell(0).setCellValue(heuristic);
		totalRows++;
		
		Row headerRow = sheet.createRow(initRow + 2);
		totalRows++;
		
		for (int i = 0; i < columns.length; i++) {
			Cell cell = headerRow.createCell(i);
			cell.setCellValue(columns[i]);
			cell.setCellStyle(headerCellStyle);
		}

		int rowNum = initRow + 3;
		for (AStarBoard b : boards) {
			Row row = sheet.createRow(rowNum++);
			if (b.isOom()) {
				row.createCell(0).setCellValue("OOM");
				row.createCell(1).setCellValue(b.getBoard_size());
				totalRows++;
			} else {
				row.createCell(0).setCellValue(b.getId());
				row.createCell(1).setCellValue(b.getBoard_size());
				row.createCell(2).setCellValue(b.getNodesExpanded());
				row.createCell(3).setCellValue(b.getNodesExpandedReinsertedInFrontier());
				row.createCell(4).setCellValue(b.getPathCost());
				row.createCell(5).setCellValue(b.getTimeTaken());
				totalRows++;
			}
		}

		// Resize all columns to fit the content size
		for (int i = 0; i < columns.length; i++) {
			sheet.autoSizeColumn(i);
		}
		
		totalRows++;

		return totalRows++; //Espacio entre heuristicos

	}

	public void writeGeneticBoard(List<GeneticBoard> boards) {
		try {
			String[] columns = { "ID", "BOARD SIZE", "GENERATIONS", "POPULATION", "CROSSOVER PROB", "MUTATION PROB",
					"ITERATIONS CONSUMED", "FITNESS", "ISGOAL", "TIME" };
			CreationHelper createHelper = workbook.getCreationHelper();
			// Create a Sheet
			Sheet sheet = workbook.createSheet("Genetic Algorithms");
			// Create a Font for styling header cells
			Font headerFont = workbook.createFont();
			headerFont.setBold(true);
			headerFont.setFontHeightInPoints((short) 14);
			headerFont.setColor(IndexedColors.RED.getIndex());
			// Create a CellStyle with the font
			CellStyle headerCellStyle = workbook.createCellStyle();
			headerCellStyle.setFont(headerFont);
			// Create a Row
			Row headerRow = sheet.createRow(0);
			// Create cells
			for (int i = 0; i < columns.length; i++) {
				Cell cell = headerRow.createCell(i);
				cell.setCellValue(columns[i]);
				cell.setCellStyle(headerCellStyle);
			}

			// Create Cell Style for formatting Date
			CellStyle dateCellStyle = workbook.createCellStyle();
			dateCellStyle.setDataFormat(createHelper.createDataFormat().getFormat("dd-MM-yyyy"));

			// Create Other rows and cells with employees data
			int rowNum = 1;
			for (GeneticBoard b : boards) {
				Row row = sheet.createRow(rowNum++);
				row.createCell(0).setCellValue(b.getId());
				row.createCell(1).setCellValue(b.getBoard_size());
				row.createCell(2).setCellValue(b.getIterations());
				row.createCell(3).setCellValue(b.getPopulation_size());
				row.createCell(4).setCellValue(b.getCrossoverProbability());
				row.createCell(5).setCellValue(b.getMutationProbability());
				row.createCell(6).setCellValue(b.getIterationsConsumed());
				row.createCell(7).setCellValue(b.getFitness());
				row.createCell(8).setCellValue(b.isGoal());
				row.createCell(9).setCellValue(b.getTime());
			}

			// Resize all columns to fit the content size
			for (int i = 0; i < columns.length; i++) {
				sheet.autoSizeColumn(i);
			}

			// Write the output to a file
			FileOutputStream fileOut = new FileOutputStream("poi-generated-file.xlsx");
			workbook.write(fileOut);
			fileOut.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void writeEmptyRows() {
		// MOCK SEPARACION CELDAS ENTRE GENETIC Y A STAR
	}

	public void saveSheet() {
		try {
			// Write the output to a file
			FileOutputStream fileOut = new FileOutputStream("poi-generated-file.xlsx");
			workbook.write(fileOut);
			fileOut.close();
			workbook.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
