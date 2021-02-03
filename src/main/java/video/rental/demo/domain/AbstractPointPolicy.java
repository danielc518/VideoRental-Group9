package video.rental.demo.domain;

public abstract class AbstractPointPolicy {
	
	public int getPoints(Rental rental) {
		int points = 1;
		
		points += getExtraPoints(rental);
		
		if (rental.getDaysRented() > rental.getDaysRentedLimit())
			points -= Math.min(points, rental.getVideo().getLateReturnPointPenalty());
		
		return points;
	}
	
	protected abstract int getExtraPoints(Rental rental);

}
