package video.rental.demo.domain;


public abstract class AbstractCustomerReport {
	
	protected Customer customer;
	
	public AbstractCustomerReport(Customer customer) {
		this.customer = customer;
	}
	
	public abstract String getReport();
	
	protected void writeTitle(StringBuilder result) {
		result.append("Customer Report for " + customer.getName() + "\n");
	}
	
	protected void writeRentalInfo(StringBuilder result, Rental rental) {
		result.append("\t" + rental.getVideo().getTitle() 
				+ "\tDays rented: " + rental.getDaysRented()
				+ "\tCharge: " + rental.getCharge()
				+ "\tPoint: " + rental.getPoints() + "\n");
	}
	
	protected void writeTotalCharge(StringBuilder result) {
		result.append("Total charge: " + customer.getTotalCharge());
	}
	
	protected void writeTotalPoints(StringBuilder result) {
		result.append("Total point: " + customer.getTotalPoints());
	}
	
	protected void writeCouponInfo(StringBuilder result) {
		int totalPoints = customer.getTotalPoints();
		
		if (totalPoints >= 10) {
			result.append("Congrat! You earned one free coupon\n");
		}
		
		if (totalPoints >= 30) {
			result.append("Congrat! You earned two free coupon\n");
		}
	}

}
