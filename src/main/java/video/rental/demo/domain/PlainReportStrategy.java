package video.rental.demo.domain;

import java.util.List;

public class PlainReportStrategy implements ICustomerReportStrategy {
	
	private Customer customer;
	
	@Override
	public String getReport(Customer customer) {
		this.customer = customer;
		
		StringBuilder result = new StringBuilder();
		
		writeTitle(result);
		writeRentalInfo(result);
		writeTotalCharge(result);
		writeTotalPoints(result);
		writeCouponInfo(result);
		
		return result.toString();
	}
	
	private void writeTitle(StringBuilder result) {
		result.append("Customer Report for " + customer.getName() + "\n");
	}
	
	private void writeRentalInfo(StringBuilder result) {
		List<Rental> rentals = customer.getRentals();
		
		for (Rental rental : rentals) {
			result.append("\t" + rental.getVideo().getTitle() 
					+ "\tDays rented: " + rental.getDaysRented()
					+ "\tCharge: " + rental.getCharge()
					+ "\tPoint: " + rental.getPoints() + "\n");
		}
	}
	
	private void writeTotalCharge(StringBuilder result) {
		result.append("Total charge: " + customer.getTotalCharge() + "\t");
	}
	
	private void writeTotalPoints(StringBuilder result) {
		result.append("Total point: " + customer.getTotalPoints() + "\n");
	}
	
	private void writeCouponInfo(StringBuilder result) {
		int totalPoints = customer.getTotalPoints();
		
		if (totalPoints >= 10) {
			result.append("Congrat! You earned one free coupon\n");
		}
		
		if (totalPoints >= 30) {
			result.append("Congrat! You earned two free coupon\n");
		}
	}

}
