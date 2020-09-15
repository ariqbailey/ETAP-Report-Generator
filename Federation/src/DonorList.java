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
		if(!contains(name)) donorList.add(new Donor(name, address, phone));
	}
	
	public void updateCamapign(String name, String campaignYear, Campaign campaign) {
		Donor temp = donorList.get(getIndex(name));
		int year = Integer.parseInt(campaignYear.split(" ")[0]);
		
		if (year < CampaignRunner.LOWEST_YEAR) {
			CampaignRunner.LOWEST_YEAR = year;
		}
		
		temp.addYear(year, campaign);
		
		temp.recalculateOpenTotals();
		donorList.set(getIndex(name), temp);
	}
}
