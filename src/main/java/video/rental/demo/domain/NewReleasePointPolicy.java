package video.rental.demo.domain;

public class NewReleasePointPolicy extends AbstractPointPolicy {

	@Override
	protected int getExtraPoints(Rental rental) {
		return 1;
	}

}
