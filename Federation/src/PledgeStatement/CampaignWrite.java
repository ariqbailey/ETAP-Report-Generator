package PledgeStatement;

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
		cell.setCellValue("Donor");
		cell.setCellStyle(style);
		
		cell = row.createCell(cellCount++);
		cell.setCellValue("First Name");
		cell.setCellStyle(style);
		
		cell = row.createCell(cellCount++);
		cell.setCellValue("Last Name");
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
		cell.setCellValue("Voice Phone");
		cell.setCellStyle(style);
		
		cell = row.createCell(cellCount++);
		cell.setCellValue("Mobile Phone");
		cell.setCellStyle(style);
		
		cell = row.createCell(cellCount++);
		cell.setCellValue("Email Address");
		cell.setCellStyle(style);
		
		//Add border

		int year = CampaignRead.oldestYear;
		while(year <= CampaignRunner.CURRENT_FISCAL_YEAR) {
			cell = row.createCell(cellCount++);
			cell.setCellValue(year + " Campaign");
			cell.setCellStyle(style);
			cell = row.createCell(cellCount++);
			sheet.addMergedRegion(new CellRangeAddress(0, 0, cellCount-2, cellCount-1));
			year++;
		}
		
		cell = row.createCell(cellCount);
		cell.setCellValue("Open Totals");
		cell.setCellStyle(style);

		//Add the second row to the sheet
		row = sheet.createRow(1);
		CellStyle fill = workbook.createCellStyle();
		fill.setFillForegroundColor((short) 0x8);
		fill.setFillPattern(FillPatternType.FINE_DOTS);
		for(int c = 0; c < 10; c++) {
			cell = row.createCell(c);
			cell.setCellStyle(fill);
		}
//		System.out.println(CampaignRead.oldestYear);
		for(int i = 10; i <= (10 + 2*(CampaignRunner.CURRENT_FISCAL_YEAR - CampaignRead.oldestYear)); i += 2) {
			cell = row.createCell(i);
			cell.setCellValue("Pledge");
			cell.setCellStyle(style);
			cell = row.createCell(i+1);
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

			//Add the donor's name and address
			cell = row.createCell(cellCount++);
			cell.setCellValue(donorEntries.get(i).getName().getFullName());
			cell = row.createCell(cellCount++);
			cell.setCellValue(donorEntries.get(i).getName().getFirstName());
			cell = row.createCell(cellCount++);
			cell.setCellValue(donorEntries.get(i).getName().getLastName());
			cell = row.createCell(cellCount++);
			cell.setCellValue(donorEntries.get(i).getAddress().getAddress());
			cell = row.createCell(cellCount++);
			cell.setCellValue(donorEntries.get(i).getAddress().getCity());
			cell = row.createCell(cellCount++);
			cell.setCellValue(donorEntries.get(i).getAddress().getState());
			cell = row.createCell(cellCount++);
			cell.setCellValue(donorEntries.get(i).getAddress().getZipCode());
			cell = row.createCell(cellCount++);
			cell.setCellValue(donorEntries.get(i).getPhoneVoice());
			cell = row.createCell(cellCount++);
			cell.setCellValue(donorEntries.get(i).getPhoneMobile());
			cell = row.createCell(cellCount++);
			cell.setCellValue(donorEntries.get(i).getEmail());
			
			for(int t = CampaignRead.oldestYear; t <= CampaignRunner.CURRENT_FISCAL_YEAR; t++) {
				cell = row.createCell(cellCount++);
				cell.setCellValue(donorEntries.get(i).getCampaign(t).getPledge());
				cell = row.createCell(cellCount++);
				cell.setCellValue(donorEntries.get(i).getCampaign(t).getOpen());
			}
//			int count = 0;
//			for(int t = 0; t < donorEntries.get(i).getCampaigns().size(); t++) {
//				cell = row.createCell(cellCount++);
//				cell.setCellValue(donorEntries.get(i).getCampaigns().get(t).getPledge());
//				cell = row.createCell(cellCount++);
//				cell.setCellValue(donorEntries.get(i).getCampaigns().get(t).getOpen());
//				count = t;
//			}
//			
//			while(count < CampaignRunner.CURRENT_FISCAL_YEAR - CampaignRead.oldestYear) {
//				cell = row.createCell(cellCount++);
//				cell.setCellValue(0);
//				cell = row.createCell(cellCount++);
//				cell.setCellValue(0);
//				count++;
//			}

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
			= new FileOutputStream(new File("ETAP Pledge " 
					+ CampaignRunner.CURRENT_FISCAL_YEAR + "-"
					+ (CampaignRead.oldestYear) + " "
					+ formattedDate + ".xlsx"));

			workbook.write(out);
			System.out.println("File: " + "ETAP Pledge " 
					+ CampaignRunner.CURRENT_FISCAL_YEAR + " "
					+ (CampaignRead.oldestYear) + " "
					+ formattedDate + ".xlsx created.");
			out.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}

}
