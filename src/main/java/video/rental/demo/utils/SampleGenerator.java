package video.rental.demo.utils;

import java.time.LocalDate;

import video.rental.demo.domain.Customer;
import video.rental.demo.domain.Rating;
import video.rental.demo.domain.Repository;
import video.rental.demo.domain.Video;
import video.rental.demo.domain.VideoType;

public class SampleGenerator {
	private Repository repository;
	
	public SampleGenerator(Repository repository) {
		super();
		this.repository = repository;
	}

	public void generateSamples() {
		Customer james = new Customer(0, "James", LocalDate.parse("1975-05-15"));
		Customer brown = new Customer(1, "Brown", LocalDate.parse("2002-03-17"));
        Customer shawn = new Customer(2, "Shawn", LocalDate.parse("2010-11-11"));

		Video v1 = new Video("V1", VideoType.CD, Video.REGULAR, Rating.FIFTEEN, LocalDate.of(2018, 1, 1));
		Video v2 = new Video("V2", VideoType.DVD, Video.NEW_RELEASE, Rating.TWELVE, LocalDate.of(2018, 3, 1));
        Video v3 = new Video("V3", VideoType.VHS, Video.NEW_RELEASE, Rating.EIGHTEEN, LocalDate.of(2018, 3, 1));

		james.addRental(v1);
		james.addRental(v2);
		
		repository.saveVideo(v1);
		repository.saveVideo(v2);
		repository.saveVideo(v3);
		repository.saveCustomer(james);
		repository.saveCustomer(brown);
		repository.saveCustomer(shawn);
	}
}
