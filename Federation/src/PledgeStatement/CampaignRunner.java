package PledgeStatement;

import java.util.ArrayList;
import java.util.Scanner;

public class CampaignRunner {

	static int CURRENT_FISCAL_YEAR;
	static int NUMBER_SHEET;
	static int FIRST_NAME_COLUMN;
	static int FULL_NAME_COLUMN;
	static int LAST_NAME_COLUMN;
	static int VOICE_PHONE_COLUMN;
	static int MOBILE_PHONE_COLUMN;
	static int ADDRESS_COLUMN;
	static int CITY_COLUMN;
	static int STATE_COLUMN;
	static int ZIP_CODE_COLUMN;
	static int EMAIL_COLUMN;
	static int CAMPAIGN_COLUMN;
	static int PLEDGE_COLUMN;
	static int OUTSTANDING_COLUMN;

	/*
	 * Ari Bailey, v0.2, 2020
	 * If you are looking at this code in the future, sorry.
	 */
	
	public static void main(String[] args) {

		//Prompt code
		System.out.println("Enter the name of the file where the data is contained: ");
		Scanner s = new Scanner(System.in);
		String file = s.nextLine();
		if(!file.endsWith(".xlsx"))
			file += ".xlsx";
		
//		System.out.println("What number sheet is the data on. Starts with 1.");
//		NUMBER_SHEET = s.nextInt();

		System.out.println("Enter the most recent fiscal year on your sheet in YYYY format (e.g. 2019)");
		CURRENT_FISCAL_YEAR = s.nextInt();

//		System.out.println("Now we will prompt which column contains which data. The first column is 1.");
//		System.out.println("Enter which number column contains the name.");
//		NAME_COLUMN = s.nextInt();
//		System.out.println("Enter which number column contains the phone number.");
//		PHONE_COLUMN = s.nextInt();
//		System.out.println("Enter which number column contains the address.");
//		ADDRESS_COLUMN = s.nextInt();
//		System.out.println("Enter which number column contains the campaign information. Formatted in YYYY Campaign.");
//		CAMPAIGN_COLUMN = s.nextInt();
//		System.out.println("Enter which number column contains the pledge ammount.");
//		PLEDGE_COLUMN = s.nextInt();
//		System.out.println("Enter which number column contains the paid ammount.");
//		PAID_COLUMN = s.nextInt();
		

		//For testing
//		String file = "spreadsheet.xlsx";
		NUMBER_SHEET = 1;
//		CURRENT_FISCAL_YEAR = 2019;
//
		FIRST_NAME_COLUMN = 2;
		FULL_NAME_COLUMN = 1;
		LAST_NAME_COLUMN = 3;
		VOICE_PHONE_COLUMN = 8;
		MOBILE_PHONE_COLUMN = 9;
		ADDRESS_COLUMN = 4;
		CITY_COLUMN = 5;
		STATE_COLUMN = 6;
		ZIP_CODE_COLUMN = 7;
		EMAIL_COLUMN = 10;
		CAMPAIGN_COLUMN = 11;
		PLEDGE_COLUMN = 12;
		OUTSTANDING_COLUMN = 13;
		//
		
		CampaignRead read = new CampaignRead(file);
		ArrayList<Donor> donorList = read.read();

		//For testing
		//		ArrayList<Donor> donorList = new ArrayList<Donor>();
		//		donorList.add(new Donor("Ari", "123 Test Drive, Test City, NM 12345-1234", "123-456-789", new Campaign(1000, 1000), new Campaign(500, 500), new Campaign(1250, 0)));

		CampaignWrite write = new CampaignWrite(donorList);

	}

}
