package video.rental.demo.domain;

public class RegularChargePolicy implements IChargePolicy {

	@Override
	public double getRentalCharge(int daysRented) {
		double charge = 2.0;
		
		if (daysRented > 2)
			charge += (daysRented - 2) * 1.5;
		
		return charge;
	}

}
