package video.rental.demo.domain;

public class GeneralPointPolicy extends AbstractPointPolicy {

	@Override
	protected int getExtraPoints(Rental rental) {
		return 0;
	}

}
