package video.rental.demo.domain;

import java.util.function.IntPredicate;;

public enum Rating implements IntPredicate {
	TWELVE(age -> (age >= 0 && age < 12)), 
	FIFTEEN(age -> (age >= 0 && age < 15)),
	EIGHTEEN(age -> (age >= 0 && age < 18));

	private final IntPredicate predicate;
		
	private Rating(IntPredicate predicate) {
		this.predicate = predicate;
	}

	@Override
	public boolean test(int age) {
		return predicate.test(age);
	}
}
