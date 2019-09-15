
import java.io.File;
import java.io.FileOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class CampaignWrite {

	ArrayList<Donor> donorEntries;

	public CampaignWrite(ArrayList<Donor> donorEntries) {
		this.donorEntries = donorEntries;
		write();
	}

	public void write() {
		XSSFWorkbook workbook = new XSSFWorkbook();

		XSSFSheet sheet = workbook.createSheet("Campaign Contributions");

		//Create a bold style
		CellStyle style = workbook.createCellStyle();
		XSSFFont font = workbook.createFont();
		font.setBold(true);
		style.setAlignment(HorizontalAlignment.CENTER);
		style.setFont(font);
		
//		style.setBorderBottom(BorderStyle.THIN);

		//Add the first row to the sheet
		Row row = sheet.createRow(0);
		int cellCount = 0;
		Cell cell = row.createCell(cellCount++);
		cell.setCellValue("Name");
		cell.setCellStyle(style);
		
		cell = row.createCell(cellCount++);
		cell.setCellValue("First Name");
		cell.setCellStyle(style);
		
		cell = row.createCell(cellCount++);
		cell.setCellValue("Address");
		cell.setCellStyle(style);
		
		cell = row.createCell(cellCount++);
		cell.setCellValue("City");
		cell.setCellStyle(style);
		
		cell = row.createCell(cellCount++);
		cell.setCellValue("State");
		cell.setCellStyle(style);
		
		cell = row.createCell(cellCount++);
		cell.setCellValue("Zip");
		cell.setCellStyle(style);
		
		cell = row.createCell(cellCount++);
		cell.setCellValue("Phone");
		cell.setCellStyle(style);
		
		//Add border

		cell = row.createCell(cellCount++);
		cell.setCellValue(CampaignRunner.CURRENT_FISCAL_YEAR - 2 + " Campaign");
		cell.setCellStyle(style);
		cell = row.createCell(cellCount++);
		cell = row.createCell(cellCount++);
		sheet.addMergedRegion(new CellRangeAddress(0, 0, cellCount-3, cellCount-1));
		
		cell = row.createCell(cellCount++);
		cell.setCellValue(CampaignRunner.CURRENT_FISCAL_YEAR - 1 + " Campaign");
		cell.setCellStyle(style);
		cell = row.createCell(cellCount++);
		cell = row.createCell(cellCount++);
		sheet.addMergedRegion(new CellRangeAddress(0, 0, cellCount-3, cellCount-1));
		
		cell = row.createCell(cellCount++);
		cell.setCellValue(CampaignRunner.CURRENT_FISCAL_YEAR + " Campaign");
		cell.setCellStyle(style);
		cell = row.createCell(cellCount++);
		cell = row.createCell(cellCount++);
		sheet.addMergedRegion(new CellRangeAddress(0, 0, cellCount-3, cellCount-1));
		
		cell = row.createCell(cellCount);
		cell.setCellValue("Open Totals");
		cell.setCellStyle(style);

		//Add the second row to the sheet
		row = sheet.createRow(1);
		CellStyle fill = workbook.createCellStyle();
		fill.setFillForegroundColor((short) 0x8);
		fill.setFillPattern(FillPatternType.FINE_DOTS);
		for(int c = 0; c < 6; c++) {
			cell = row.createCell(c);
			cell.setCellStyle(fill);
		}

		for(int i = 7; i < 16; i += 3) {
			cell = row.createCell(i);
			cell.setCellValue("Pledge");
			cell.setCellStyle(style);
			cell = row.createCell(i+1);
			cell.setCellValue("Recived");
			cell.setCellStyle(style);
			cell = row.createCell(i+2);
			cell.setCellValue("Open");
			cell.setCellStyle(style);
		}
		cell = row.createCell(cellCount++);
		
//		style.setAlignment(HorizontalAlignment.LEFT);

		for(int i = 0; i < donorEntries.size(); i++) {
			//reset cellCount to zero
			cellCount = 0;

			//Create a donor row
			row = sheet.createRow(i + 2);
			
			//Isolate first name
			String[] names = donorEntries.get(i).getName().split(" ");
			String firstName = "";
			String firstNameTwo = "";
			if(names.length == 2) firstName = names[0];
			else {
				if(donorEntries.get(i).getName().contains(" and ")) {
					names = donorEntries.get(i).getName().split(" and ");
					firstName = names[0].trim().split(" ")[0];
					firstNameTwo = " and " + names[1].trim().split(" ")[0];
				} else if (donorEntries.get(i).getName().contains(" & ")) {
					names = donorEntries.get(i).getName().split(" & ");
					firstName = names[0].trim().split(" ")[0];
					firstNameTwo = " and " + names[1].trim().split(" ")[0];
				} else {
					firstName = donorEntries.get(i).getName();
				}
			}
			firstName = firstName + firstNameTwo;

			//Add the donor's name and address
			cell = row.createCell(cellCount++);
			cell.setCellValue(donorEntries.get(i).getName());
			cell = row.createCell(cellCount++);
			cell.setCellValue(firstName);
			cell = row.createCell(cellCount++);
			cell.setCellValue(donorEntries.get(i).getAddress());
			cell = row.createCell(cellCount++);
			cell.setCellValue(donorEntries.get(i).getCity());
			cell = row.createCell(cellCount++);
			cell.setCellValue(donorEntries.get(i).getState());
			cell = row.createCell(cellCount++);
			cell.setCellValue(donorEntries.get(i).getZip());
			cell = row.createCell(cellCount++);
			cell.setCellValue(donorEntries.get(i).getPhone());

			//Add FY - 2 contributions
			cell = row.createCell(cellCount++);
			cell.setCellValue(donorEntries.get(i).getFY2017().getPledge());
			cell = row.createCell(cellCount++);
			cell.setCellValue(donorEntries.get(i).getFY2017().getRecieve());
			cell = row.createCell(cellCount++);
			cell.setCellValue(donorEntries.get(i).getFY2017().getOpen());
			cell.setCellStyle(style);

			//Add FY - 1 contributions
			cell = row.createCell(cellCount++);
			cell.setCellValue(donorEntries.get(i).getFY2018().getPledge());
			cell = row.createCell(cellCount++);
			cell.setCellValue(donorEntries.get(i).getFY2018().getRecieve());
			cell = row.createCell(cellCount++);
			cell.setCellValue(donorEntries.get(i).getFY2018().getOpen());
			cell.setCellStyle(style);

			//Add FY contributions
			cell = row.createCell(cellCount++);
			cell.setCellValue(donorEntries.get(i).getFY2019().getPledge());
			cell = row.createCell(cellCount++);
			cell.setCellValue(donorEntries.get(i).getFY2019().getRecieve());
			cell = row.createCell(cellCount++);
			cell.setCellValue(donorEntries.get(i).getFY2019().getOpen());
			cell.setCellStyle(style);

			//Add Open Totals
			cell = row.createCell(cellCount++);
			cell.setCellValue(donorEntries.get(i).getOpenTotals());
			cell.setCellStyle(style);
		}


		try {
			Date date = new Date();
		    String strDateFormat = "yyyy-MM-dd hh-mm-ss a";
		    DateFormat dateFormat = new SimpleDateFormat(strDateFormat);
		    String formattedDate= dateFormat.format(date);
			FileOutputStream out 
			= new FileOutputStream(new File("ETAP Report " 
					+ CampaignRunner.CURRENT_FISCAL_YEAR + " "
					+ (CampaignRunner.CURRENT_FISCAL_YEAR - 1) + " "
					+ (CampaignRunner.CURRENT_FISCAL_YEAR - 2) 
					+ " for outstanding balances " + formattedDate + ".xlsx"));

			workbook.write(out);
			System.out.println("File: " + "ETAP Report " 
					+ CampaignRunner.CURRENT_FISCAL_YEAR + " "
					+ (CampaignRunner.CURRENT_FISCAL_YEAR - 1) + " "
					+ (CampaignRunner.CURRENT_FISCAL_YEAR - 2) 
					+ " for outstanding balances " + formattedDate + ".xlsx created.");
			out.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}

}
