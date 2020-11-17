package excel;

import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;

public class ExcelGenerator {
	
	// Excel work book
	private HSSFWorkbook workbook;
	
	// Fonts
	private HSSFFont headerFont;
	private HSSFFont contentFont;
	
	// Styles
	private HSSFCellStyle headerStyle;
	private HSSFCellStyle oddRowStyle;
	private HSSFCellStyle evenRowStyle;
	
	// Integer to store the index of the next row
	private int rowIndex;
	

	/**
	 * Make a new excel workbook with sheet that contains a stylized table
	 * 
	 * @return
	 */
	public HSSFWorkbook generateExcel() {
		
		// Initialize rowIndex
		rowIndex = 0;
		
		// New Workbook
		workbook = new HSSFWorkbook();
		
		// Generate fonts   (HSSFColor.WHITE.index, (short)12, true);
		headerFont  = createFont((short) 1, (short)12, true);
		contentFont = createFont((short)0, (short)10, true);
		
		// Generate styles
		headerStyle  = createStyle(headerFont,  (short)12, true, (short)0); 
		oddRowStyle  = createStyle(contentFont, (short)1, true, (short)0); 
		evenRowStyle = createStyle(contentFont, (short)1, true, (short)0);
		
		// New sheet
		HSSFSheet sheet = workbook.createSheet("ALMACÉN");
		
		// Table header
		HSSFRow      headerRow    = sheet.createRow( rowIndex++ );
		List<String> headerValues = FakeDataProvider.getTableHeaders();
		
		HSSFCell headerCell = null;
		for (int i = 0; i < headerValues.size(); i++) {
			headerCell = headerRow.createCell(i);
			headerCell.setCellStyle(headerStyle);
			headerCell.setCellValue( headerValues.get(i) );
		}
		
		
		// Table content
		HSSFRow  contentRow  = null;
		HSSFCell contentCell = null;
		
		// Obtain table content values
		List<List<String>> contentRowValues = FakeDataProvider.getTableContent(20);
		for (List<String> rowValues : contentRowValues) {
			
			// At each row creation, rowIndex must grow one unit
			contentRow = sheet.createRow( rowIndex++ );
			for (int i = 0; i < rowValues.size(); i++) {
				contentCell = contentRow.createCell(i);
				contentCell.setCellValue( rowValues.get(i) );
				
				// Style depends on if row is odd or even
				contentCell.setCellStyle( rowIndex % 2 == 0 ? oddRowStyle : evenRowStyle );
			}
		}
		
		
		// Autosize columns
		for (int i = 0; i < headerValues.size(); sheet.autoSizeColumn(i++));
		
		return workbook;
	}
	
	
	/**
	 * Create a new font on base workbook
	 * 
	 * @param fontColor       Font color (see {@link HSSFColor})
	 * @param fontHeight      Font height in points
	 * @param fontBold        Font is boldweight (<code>true</code>) or not (<code>false</code>)
	 * 
	 * @return New cell style
	 */
	private HSSFFont createFont(short fontColor, short fontHeight, boolean fontBold) {
		
		HSSFFont font = workbook.createFont();
		font.setBold(fontBold);
		font.setColor(fontColor);
		font.setFontName("Arial");
		font.setFontHeightInPoints(fontHeight);
		
		return font;
	}
	
	
	/**
	 * Create a style on base workbook
	 * 
	 * @param font            Font used by the style
	 * @param cellAlign       Cell alignment for contained text (see {@link HSSFCellStyle})
	 * @param cellColor       Cell background color (see {@link HSSFColor})
	 * @param cellBorder      Cell has border (<code>true</code>) or not (<code>false</code>)
	 * @param cellBorderColor Cell border color (see {@link HSSFColor})
	 * 
	 * @return New cell style
	 */
	private HSSFCellStyle createStyle(HSSFFont font,  short cellColor, boolean cellBorder, short cellBorderColor) {

		HSSFCellStyle style = workbook.createCellStyle();
		style.setFont(font);
		style.setAlignment(HorizontalAlignment.CENTER);
		style.setFillForegroundColor(cellColor);
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		
		if (cellBorder) {
			//style.setBorderTop(HSSFCellStyle.BORDER_THIN);
                        style.setBorderTop(BorderStyle.THIN);
			style.setBorderLeft(BorderStyle.THIN);
			style.setBorderRight(BorderStyle.THIN);
			style.setBorderBottom(BorderStyle.THIN);
			
			style.setTopBorderColor(cellBorderColor);
			style.setLeftBorderColor(cellBorderColor);
			style.setRightBorderColor(cellBorderColor);
			style.setBottomBorderColor(cellBorderColor);
		}
		
		return style;
	}
}
