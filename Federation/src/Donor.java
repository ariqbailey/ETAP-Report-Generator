public class Donor {

	private String name;
	private String address;
	private String city;
	private String state;
	private String zip;
	private String phone;
	private Campaign [] years;
	private double openTotals;

	public Donor(String name, String address, String phone) {
		this.name = name.trim();
		String [] temp = address.split(",");
		this.phone = phone.trim();
		years = new Campaign[CampaignRunner.MAX_YEARS];
		
		try {
			if(address.trim().equals("")) {
				this.address = "";
				this.city = "";
				this.state = "";
				this.zip = "";
			}
			else if(temp.length > 3) {
				this.address = temp[0].trim() + temp[1].trim();
				this.city = temp[2].trim();
				this.state = temp[3].trim().split(" ")[0];
				this.zip = temp[3].trim().split(" ")[1];
			} else {
				this.address = temp[0].trim();
				this.city = temp[1].trim();
				this.state = temp[2].trim().split(" ")[0];
				this.zip = temp[2].trim().split(" ")[1];
			}
		} catch (Exception e) {		
			this.address = address.trim();
			this.city = "";
			this.state = "";
			this.zip = "";
			
			System.err.println("ERROR: MALFORMATED ADDRESS FOR DONOR " + name + "\nCONTINUING...");
		}
	}

	public void recalculateOpenTotals() {
		openTotals = 0;
		for(int i = 0; i < CampaignRunner.MAX_YEARS; i++) {
			if(years[i] != null) {
				openTotals += years[i].getOpen();
			}
		}
	}
	
	public void addYear(int year, Campaign campaign) {
		int index = CampaignRunner.CURRENT_FISCAL_YEAR - year;
		if(years[index] == null) {
			years[index] = campaign;
		} else { //If there is already some contributions that year, increase them instead of overwritting
			years[index].setPledge(years[index].getPledge() + campaign.getPledge()); 
			years[index].setRecieve(years[index].getRecieve() + campaign.getRecieve());
			years[index].setOpen(years[index].getOpen() + campaign.getOpen());
		}
		recalculateOpenTotals();
	}
	
	public Campaign getYearIndex(int index) { 
		return years[index];
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

//	public Campaign getFY2017() {
//		return FY2017;
//	}
//
//	public void setFY2017(Campaign fY2017) {
//		FY2017 = fY2017;
//	}
//
//	public Campaign getFY2018() {
//		return FY2018;
//	}
//
//	public void setFY2018(Campaign fY2018) {
//		FY2018 = fY2018;
//	}
//
//	public Campaign getFY2019() {
//		return FY2019;
//	}
//
//	public void setFY2019(Campaign fY2019) {
//		FY2019 = fY2019;
//	}

	public double getOpenTotals() {
		return openTotals;
	}

	public void setOpenTotals(int openTotals) {
		this.openTotals = openTotals;
	}

	
//	public void addFY2017(Campaign campaign) {
//		FY2017 = new Campaign(FY2017.getPledge() + campaign.getPledge(), FY2017.getRecieve() + campaign.getRecieve());
//	}
//	
//	public void addFY2018(Campaign campaign) {
//		FY2018 = new Campaign(FY2018.getPledge() + campaign.getPledge(), FY2018.getRecieve() + campaign.getRecieve());
//	}
//
//	public void addFY2019(Campaign campaign) {
//		FY2019 = new Campaign(FY2019.getPledge() + campaign.getPledge(), FY2019.getRecieve() + campaign.getRecieve());
//	}
//




}
