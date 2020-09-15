
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class CampaignRunner {

	static int CURRENT_FISCAL_YEAR;
	static int NUMBER_SHEET;
	static int NAME_COLUMN;
	static int PHONE_COLUMN;
	static int ADDRESS_COLUMN;
	static int CAMPAIGN_COLUMN;
	static int PLEDGE_COLUMN;
	static int PAID_COLUMN;
	static int OUTSTANDING_COLUMN;
	static int MAX_YEARS = 10;
	static int LOWEST_YEAR = Integer.MAX_VALUE;
	static boolean SKIP_EMPTY_TOTALS = true;

	
	public static void main(String[] args) {

		//Prompt code
		System.out.println("Enter the name of the file where the data is contained: ");
		Scanner s = new Scanner(System.in);
		String file = s.nextLine();
		if(!file.endsWith(".xlsx"))
			file += ".xlsx";
		
		System.out.println("What number sheet is the data on. Starts with 1.");
		NUMBER_SHEET = s.nextInt();

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
//		

		//testing
//		String file = "ARI -reprot.xlsx";
//		String file = "Copy of Ari Spreadsheet 5.7.20.xlsx";
//		NUMBER_SHEET = 1;
//		CURRENT_FISCAL_YEAR = 2020;
		
		//static columns
		NAME_COLUMN = 1;
		PHONE_COLUMN = 3;
		ADDRESS_COLUMN = 2;
		CAMPAIGN_COLUMN = 5;
		PLEDGE_COLUMN = 6;
		PAID_COLUMN = 7;
		OUTSTANDING_COLUMN = 8;
		//
		
		CampaignRead read = new CampaignRead(file);
		ArrayList<Donor> donorList = read.read();

		//For testing
		//		ArrayList<Donor> donorList = new ArrayList<Donor>();
		//		donorList.add(new Donor("Ari", "123 Test Drive, Test City, NM 12345-1234", "123-456-789", new Campaign(1000, 1000), new Campaign(500, 500), new Campaign(1250, 0)));

		CampaignWrite write = new CampaignWrite(donorList);
				
		System.out.println("press ENTER to exit");
		try {
			System.in.read();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		s.close();

	}

}
