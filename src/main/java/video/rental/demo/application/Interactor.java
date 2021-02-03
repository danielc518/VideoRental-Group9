package video.rental.demo.application;

import java.time.LocalDate;
import java.util.List;

import video.rental.demo.domain.Customer;
import video.rental.demo.domain.Rating;
import video.rental.demo.domain.Rental;
import video.rental.demo.domain.Repository;
import video.rental.demo.domain.Video;

public class Interactor {

	private Repository repository;

	public Interactor(Repository repository) {
		super();
		this.repository = repository;
	}

	public String clearRentals(int customerCode) {
		StringBuilder builder = new StringBuilder();

		Customer foundCustomer = repository.findCustomerById(customerCode);

		if (foundCustomer == null) {
			builder.append("No customer found\n");
			return builder.toString();
		} 

		foundCustomer.getRentalsDescription(builder);
		foundCustomer.clearRentals();

		repository.saveCustomer(foundCustomer);

		return builder.toString();
	}

	public void returnVideo(int customerCode, String videoTitle) {
		Customer foundCustomer = getRepository().findCustomerById(customerCode);
		if (foundCustomer == null)
			return;

		Video returnedVideo = foundCustomer.returnVideo(videoTitle);
		if (returnedVideo == null)
			return;

		returnedVideo.setReturned();
		getRepository().saveVideo(returnedVideo);
		getRepository().saveCustomer(foundCustomer);
	}

	public String listVideos() {
		StringBuilder builder = new StringBuilder();
		
		List<Video> videos = getRepository().findAllVideos();

		for (Video video : videos) {
			video.getVideoDescription(builder);
		}
		
		return builder.toString();
	}

	public void listCustomers() {
		List<Customer> customers = getRepository().findAllCustomers();

		for (Customer customer : customers) {
			System.out.println("ID: " + customer.getCode() + "\nName: " + customer.getName() + "\tRentals: "
					+ customer.getRentals().size());
			for (Rental rental : customer.getRentals()) {
				System.out.print("\tTitle: " + rental.getVideo().getTitle() + " ");
				System.out.print("\tPrice Code: " + rental.getVideo().getPriceCode());
				System.out.println("\tReturn Status: " + rental.getStatus());
			}
		}
	}

	public void getCustomerReport(int code) {
		Customer foundCustomer = getRepository().findCustomerById(code);

		if (foundCustomer == null) {
			System.out.println("No customer found");
		} else {
			String result = foundCustomer.getReport();
			System.out.println(result);
		}
	}

	public void rentVideo(int customerId, String videoTitle) {
		Customer foundCustomer = getRepository().findCustomerById(customerId);
		if (foundCustomer == null)
			return;

		Video foundVideo = getRepository().findVideoByTitle(videoTitle);
		if (foundVideo == null)
			return;

		if (!foundVideo.isRentable(foundCustomer))
			return;
		
		foundCustomer.addRental(foundVideo);
			
		getRepository().saveVideo(foundVideo);
		getRepository().saveCustomer(foundCustomer);
	}

	public void registerCustomer(String name, int code, String dateOfBirth) {
		getRepository().saveCustomer(new Customer(code, name, LocalDate.parse(dateOfBirth)));
	}

	public void registerVideo(String title, int videoType, int priceCode, int videoRating) {
		Rating rating;
		if (videoRating == 1)
			rating = Rating.TWELVE;
		else if (videoRating == 2)
			rating = Rating.FIFTEEN;
		else if (videoRating == 3)
			rating = Rating.EIGHTEEN;
		else
			throw new IllegalArgumentException("No such rating " + videoRating);
		LocalDate registeredDate = LocalDate.now();

		getRepository().saveVideo(new Video(title, videoType, priceCode, rating, registeredDate));
	}

	private Repository getRepository() {
		return repository;
	}

}
