package video.rental.demo.application;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import video.rental.demo.domain.*;
import video.rental.demo.presentation.CmdUI;
import video.rental.demo.utils.Constants;

public class Interactor {

	private Repository repository;
	private Sentence sentence;

	public Interactor(Repository repository, Sentence sentence) {
		super();
		this.repository = repository;
		this.sentence = sentence;
	}

	public String clearRentals(int customerCode) {
		Customer foundCustomer = getRepository().findCustomerById(customerCode);

		String sentenceForClearRentals = getSentence().makeSentenceForClearRentals(foundCustomer);

		if (foundCustomer != null) {
			List<Rental> rentals = new ArrayList<Rental>();
			foundCustomer.setRentals(rentals);

			getRepository().saveCustomer(foundCustomer);
		}

		return sentenceForClearRentals;
	}

	public void returnVideo(int customerCode, String videoTitle) {
		Customer foundCustomer = getRepository().findCustomerById(customerCode);
		if (foundCustomer == null)
			return;

		List<Rental> customerRentals = foundCustomer.getRentals();

		for (Rental rental : customerRentals) {
			if (rental.getVideo().getTitle().equals(videoTitle) && rental.getVideo().isRented()) {
				Video video = rental.returnVideo();
				video.setRented(false);
				getRepository().saveVideo(video);
				break;
			}
		}

		getRepository().saveCustomer(foundCustomer);
	}

	public String listVideos() {
		StringBuilder builder = new StringBuilder();
		
		List<Video> videos = getRepository().findAllVideos();

		for (Video video : videos) {
			builder.append("Video type: " + video.getVideoType() + "\tPrice code: " + video.getPriceCode()
					+ "\tRating: " + video.getVideoRating() + "\tTitle: " + video.getTitle() + "\n");
		}
		
		return builder.toString();
	}

	public String listCustomers() {
		StringBuilder sb = new StringBuilder();
		
		List<Customer> customers = getRepository().findAllCustomers();

		for (Customer customer : customers) {
			sb.append("ID: " + customer.getCode() + "\nName: " + customer.getName() + "\tRentals: "
					+ customer.getRentals().size() + "\n");
			for (Rental rental : customer.getRentals()) {
				sb.append("\tTitle: " + rental.getVideo().getTitle() + " ");
				sb.append("\tPrice Code: " + rental.getVideo().getPriceCode());
				sb.append("\tReturn Status: " + rental.getStatus() + "\n");
			}
		}
		
		return sb.toString();
	}

	public String getCustomerReport(int code) {
		Customer foundCustomer = getRepository().findCustomerById(code);
		
		StringBuilder sb = new StringBuilder();

		if (foundCustomer == null) {
			sb.append(Constants.NO_CUSTOMER_FOUND).append("\n");
		} else {
			String result = foundCustomer.getReport();
			sb.append(result + "\n");
		}
		
		return sb.toString();
	}

	public void rentVideo(int code, String videoTitle) {
		Customer foundCustomer = getRepository().findCustomerById(code);

		if (foundCustomer == null)
			return;
		Video foundVideo = getRepository().findVideoByTitle(videoTitle);

		if (foundVideo == null)
			return;

		if (foundVideo.isRented() == true)
			return;

		Boolean status = foundVideo.rentFor(foundCustomer);
		if (status == true) {
			getRepository().saveVideo(foundVideo);
			getRepository().saveCustomer(foundCustomer);
		} else {
			return;
		}
	}

	public void registerCustomer(String name, int code, String dateOfBirth) {
		Customer customer = new Customer(code, name, LocalDate.parse(dateOfBirth));
		getRepository().saveCustomer(customer);
	}

	public void registerVideo(CmdUI cmdUI, String title, int videoType, int priceCode, int videoRating,
			LocalDate registeredDate) {
		Rating rating;
		if (videoRating == 1)
			rating = Rating.TWELVE;
		else if (videoRating == 2)
			rating = Rating.FIFTEEN;
		else if (videoRating == 3)
			rating = Rating.EIGHTEEN;
		else
			throw new IllegalArgumentException("No such rating " + videoRating);

		Video video = new Video(title, videoType, priceCode, rating, registeredDate);

		getRepository().saveVideo(video);
	}

	public void registerVideo(String title, int videoType, int priceCode, int videoRating) {
		LocalDate registeredDate = LocalDate.now();

		Rating rating;
		if (videoRating == 1)
			rating = Rating.TWELVE;
		else if (videoRating == 2)
			rating = Rating.FIFTEEN;
		else if (videoRating == 3)
			rating = Rating.EIGHTEEN;
		else
			throw new IllegalArgumentException("No such rating " + videoRating);

		Video video = new Video(title, videoType, priceCode, rating, registeredDate);

		getRepository().saveVideo(video);
	}

	private Repository getRepository() {
		return repository;
	}

	private Sentence getSentence() {
		return sentence;
	}
}
