
public class Campaign {
	
	private double pledge;
	private double recieve;
	private double open_var;
	
	public Campaign() {
		this.pledge = 0;
		this.recieve = 0;
		this.open_var = 0;
	}
	
	public Campaign(double p, double r, double o) {
		pledge = p;
		recieve = r;
		open_var = o;
	}
	
	public double getPledge() {
		return pledge;
	}

	public void setPledge(double d) {
		this.pledge = d;
	}

	public double getRecieve() {
		return recieve;
	}

	public void setRecieve(double d) {
		this.recieve = d;
	}

	public double getOpen() {
		return open_var;
	}

	public void setOpen(double openAmmount) {
		this.open_var = openAmmount;
	}
	
	

}
