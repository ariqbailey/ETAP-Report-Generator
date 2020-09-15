package PledgeStatement;

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
	static int oldestYear = Integer.MAX_VALUE;

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

			//Iterate through each row one by one
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
				Name name = new Name();
				Address address = new Address();
				String voiceNumber = "";
				String mobileNumber = "";
				String email = "";
				Campaign campaign = new Campaign();
				Cell cell;	
				while(cellIterator.hasNext()) {
					cell = cellIterator.next();
					
					System.out.println(cell);
					try {
						if (column == (CampaignRunner.FULL_NAME_COLUMN - 1)) {
							name.setFullName(cell.getStringCellValue());
							if(name.getFullName().equals("")) {
								skipRow = true;
								continue;
							}
						} else if (column == (CampaignRunner.FIRST_NAME_COLUMN - 1)) {
							name.setFirstName(cell.getStringCellValue());
						} else if (column == (CampaignRunner.LAST_NAME_COLUMN - 1)) {
							name.setLastName(cell.getStringCellValue());
						} else if (column == (CampaignRunner.ADDRESS_COLUMN - 1)) {
							address.setAddress(cell.getStringCellValue());
						} else if (column == (CampaignRunner.CITY_COLUMN - 1)) {
							address.setCity(cell.getStringCellValue());
						} else if (column == (CampaignRunner.STATE_COLUMN - 1)) {
							address.setState(cell.getStringCellValue());
						} else if (column == (CampaignRunner.ZIP_CODE_COLUMN - 1)) {
							address.setZipCode(cell.getStringCellValue());
						} else if (column == (CampaignRunner.VOICE_PHONE_COLUMN - 1)) {
							voiceNumber = cell.getStringCellValue();
						} else if (column == (CampaignRunner.MOBILE_PHONE_COLUMN - 1)) {
							mobileNumber = cell.getStringCellValue();
						} else if (column == (CampaignRunner.EMAIL_COLUMN - 1)) {
							email = cell.getStringCellValue();
							if(email.contains("\n")) email = email.split("\n")[0];
						} else if (column == (CampaignRunner.CAMPAIGN_COLUMN - 1)) {
							int year = Integer.parseInt(cell.getStringCellValue().split(" ")[0]);
							if(oldestYear > year) oldestYear = year;
							campaign.setCampaignYear(cell.getStringCellValue().split(" ")[0]);
						} else if (column == (CampaignRunner.PLEDGE_COLUMN - 1)) {
							campaign.setPledge(cell.getNumericCellValue());
						} else if (column == (CampaignRunner.OUTSTANDING_COLUMN - 1)) {
							campaign.setOpen(cell.getNumericCellValue());
						}
					} catch (IllegalStateException e) {
						System.err.println("ERROR ON READING FROM: \n" + cell + "POTENTIAL ISSUES INCL. MALFORMATTED ADDRESS\n\nCONTINUING...");
					}
					column++;
				}
				if(!skipRow) {
					donors.add(name, address, voiceNumber, mobileNumber, email);
					donors.updateCamapign(name.getFullName(), campaign);
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
