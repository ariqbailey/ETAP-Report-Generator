import java.util.ArrayList;

public class DonorList {
	
	ArrayList<Donor> donorList = new ArrayList<Donor>();
	
	public DonorList() {
	}
	
	public boolean contains(String name) { 
		for(Donor d : donorList) {
			if(d.getName().equals(name)) return true;
		}
		return false;
	}
	
	public ArrayList<Donor> getList() {
		return donorList;
	}
	
	public int getIndex(String name) {
		int i = 0;
		for(Donor d : donorList) {
			if(d.getName().equals(name)) return i;
			i++;
		}
		try {
			throw new Exception("Element Does Not Exist");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;
	}
	
	public void add(String name, String address, String phone) {
		if(!contains(name)) donorList.add(new Donor(name, address, phone, new Campaign(0, 0), new Campaign(0, 0), new Campaign(0, 0)));
	}
	
	public void updateCamapign(String name, String campaignYear, Campaign campaign) {
		Donor temp = donorList.get(getIndex(name));
		int year = Integer.parseInt(campaignYear.split(" ")[0]);
//		System.out.println(name + " | " + campaignYear + " | " + campaign.getOpen() + " | " + (CampaignRunner.CURRENT_FISCAL_YEAR - year));
		switch(CampaignRunner.CURRENT_FISCAL_YEAR - year) {
			case 0:
				temp.addFY2019(campaign);
				break;
			case 1:
				temp.addFY2018(campaign);
				break;
			case 2:
				temp.addFY2017(campaign);
				break;
		}
		temp.recalculateOpenTotals();
		donorList.set(getIndex(name), temp);
	}
}
