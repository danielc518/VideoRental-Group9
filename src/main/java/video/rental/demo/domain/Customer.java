package video.rental.demo.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Customer {
	@Id
	private int code;
	private String name;
	private LocalDate dateOfBirth;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<Rental> rentals = new ArrayList<Rental>();
	
	private ICustomerReportStrategy reportStrategy;

	public Customer() {	// for hibernate
	}

	public Customer(int code, String name, LocalDate dateOfBirth, ICustomerReportStrategy report) {
		this.code = code;
		this.name = name;
		this.dateOfBirth = dateOfBirth;
		this.reportStrategy = report;
	}

	public int getCode() {
		return code;
	}

	public String getName() {
		return name;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public List<Rental> getRentals() {
		return rentals;
	}

	public void setRentals(List<Rental> rentals) {
		this.rentals = rentals;
	}
	
	public double getTotalCharge() {
		List<Rental> rentals = getRentals();
		
		double totalCharge = 0.0;
		
		for (Rental each : rentals) {
			totalCharge += each.getCharge();
		}
		
		return totalCharge;
	}
	
	public int getTotalPoints() {
		List<Rental> rentals = getRentals();
		
		int totalPoints = 0;
		
		for (Rental each : rentals) {
			totalPoints += each.getPoints();
		}
		
		return totalPoints;
	}
	
	public String getReport() {
		return reportStrategy.getReport(this);
	}
	
}
