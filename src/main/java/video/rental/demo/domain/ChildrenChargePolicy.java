package video.rental.demo.domain;

public class ChildrenChargePolicy implements IChargePolicy {

	@Override
	public double getRentalCharge(int daysRented) {
		double charge = 1.5;
		
		if (daysRented > 3)
			charge += (daysRented - 3) * 1.5;
		
		return charge;
	}

}
