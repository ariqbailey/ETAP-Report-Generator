
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class CampaignRead {
	
	private String file;

	public CampaignRead(String file) {
		this.file = file;
	}

	public ArrayList<Donor> read() {
		//DonorList to store donor information
		DonorList donors = new DonorList();
		try
		{
			FileInputStream f = new FileInputStream(new File(file));

			//Create Workbook instance holding reference to .xlsx file
			XSSFWorkbook workbook = new XSSFWorkbook(f);

			//Get first/desired sheet from the workbook
			XSSFSheet sheet = workbook.getSheetAt(CampaignRunner.NUMBER_SHEET-1);

			//Iterate through each rows one by one
			Iterator<Row> rowIterator = sheet.iterator();
			
			//Skip the first row
			rowIterator.next();
			while (rowIterator.hasNext())
			{
				Row row = rowIterator.next();
				boolean skipRow = false;
				//For each row, iterate through all the columns
				
				Iterator<Cell> cellIterator = row.cellIterator();
				int column = 0;
				String name = "";
				String address = "";
				String phone = "";
				String campaignYear = "";
				Campaign campaign = new Campaign();
				Cell cell;
				while(cellIterator.hasNext()) {
					cell = cellIterator.next();
					
//					System.out.println(cell);
					
					if(column == CampaignRunner.NAME_COLUMN - 1) {
						name = cell.getStringCellValue();
						if(name == "") skipRow = true;
					}	
					else if(column == CampaignRunner.ADDRESS_COLUMN - 1)
						address = cell.getStringCellValue();
					else if(column == CampaignRunner.PHONE_COLUMN - 1)
						phone = cell.getStringCellValue();
					else if(column == CampaignRunner.CAMPAIGN_COLUMN - 1)
						campaignYear = cell.getStringCellValue();
					else if(column == CampaignRunner.PLEDGE_COLUMN - 1)
						campaign.setPledge(cell.getNumericCellValue());
					else if(column == CampaignRunner.PAID_COLUMN - 1)
						campaign.setRecieve(cell.getNumericCellValue());
					else if(column == CampaignRunner.OUTSTANDING_COLUMN - 1)
						campaign.setOpen(cell.getNumericCellValue());
					else {}
					
					column++;
				}
				if(!skipRow) {
//					campaign.calculateOpenBalance();
					donors.add(name, address, phone);
					donors.updateCamapign(name, campaignYear, campaign);
				}
			}
			f.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return donors.getList();
	}

}
