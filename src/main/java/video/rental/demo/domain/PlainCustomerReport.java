package video.rental.demo.domain;

import java.util.List;

public class PlainCustomerReport extends AbstractCustomerReport {

	public PlainCustomerReport(Customer customer) {
		super(customer);
	}

	@Override
	public String getReport() {
		StringBuilder result = new StringBuilder();
		
		writeTitle(result);
		
		List<Rental> rentals = customer.getRentals();
		
		for (Rental rental : rentals) {
			writeRentalInfo(result, rental);
		}
		
		writeTotalCharge(result);
		
		result.append("\t");
		
		writeTotalPoints(result);
		
		result.append("\n");
		
		writeCouponInfo(result);
		
		return result.toString();
	}

}
