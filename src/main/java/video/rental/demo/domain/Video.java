package video.rental.demo.domain;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "VIDEO", uniqueConstraints = { @UniqueConstraint(columnNames = { "title" }) })
public class Video {
	@Id
	private String title;
	private Rating videoRating;
	private int priceCode;

	public static final int REGULAR = 1;
	public static final int NEW_RELEASE = 2;
	public static final int CHILDREN = 3;

	private VideoType videoType;

	private LocalDate registeredDate;
	private boolean rented;

	public Video() {
	} // for hibernate

	public Video(String title, VideoType videoType, int priceCode, Rating videoRating, LocalDate registeredDate) {
		this.title = title;
		this.videoType = videoType;
		this.priceCode = priceCode;
		this.videoRating = videoRating;
		this.registeredDate = registeredDate;
		this.rented = false;
	}

	public int getLateReturnPointPenalty() {
		return videoType.getLateReturnPointPenalty();
	}

	public int getPriceCode() {
		return priceCode;
	}

	public void setPriceCode(int priceCode) {
		this.priceCode = priceCode;
	}

	public String getTitle() {
		return title;
	}

	public Rating getVideoRating() {
		return videoRating;
	}

	public boolean isRented() {
		return rented;
	}

	public void setRented() {
		this.rented = true;
	}
	
	public void setReturned() {
		this.rented = false;
	}

	public LocalDate getRegisteredDate() {
		return registeredDate;
	}

	public VideoType getVideoType() {
		return videoType;
	}

	public boolean isUnderAge(Customer customer) {
		// calculate customer's age in years and months

		// parse customer date of birth
		Calendar calDateOfBirth = Calendar.getInstance();
		try {
			calDateOfBirth.setTime(new SimpleDateFormat("yyyy-MM-dd").parse(customer.getDateOfBirth().toString()));
		} catch (ParseException e) {
			e.printStackTrace();
		}

		// get current date
		Calendar calNow = Calendar.getInstance();
		calNow.setTime(new java.util.Date());

		// calculate age different in years and months
		int ageYr = (calNow.get(Calendar.YEAR) - calDateOfBirth.get(Calendar.YEAR));
		int ageMo = (calNow.get(Calendar.MONTH) - calDateOfBirth.get(Calendar.MONTH));

		// decrement age in years if month difference is negative
		if (ageMo < 0) {
			ageYr--;
		}
		int age = ageYr;

		return videoRating.test(age);
	}

	public void getVideoDescription(StringBuilder builder) {
		builder.append("Video type: " + videoType.getVideoTypeDescription() + "\tPrice code: " + priceCode
				+ "\tRating: " + videoRating + "\tTitle: " + title + "\n");
	}

	public boolean isRentable(Customer customer) {
		return isRented() == false && !isUnderAge(customer);
	}
}
