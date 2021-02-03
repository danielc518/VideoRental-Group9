package video.rental.demo.domain;

public class NewReleaseChargePolicy implements IChargePolicy {

	@Override
	public double getRentalCharge(int daysRented) {
		return daysRented * 3;
	}

}
