package video.rental.demo.presentation;

import video.rental.demo.domain.Customer;
import video.rental.demo.domain.Rental;
import video.rental.demo.domain.Sentence;
import video.rental.demo.utils.Constants;

public class PlainSentenceImpl implements Sentence {
    @Override
    public String makeSentenceForClearRentals(Customer foundCustomer) {
        StringBuilder builder = new StringBuilder();

        if (foundCustomer == null) {
            return builder.append(Constants.NO_CUSTOMER_FOUND).append("\n").toString();
        } else {
            builder.append("Id: " + foundCustomer.getCode() + "\nName: " + foundCustomer.getName() + "\tRentals: "
                    + foundCustomer.getRentals().size() + "\n");
            for (Rental rental : foundCustomer.getRentals()) {
                builder.append("\tTitle: " + rental.getVideo().getTitle() + " ");
                builder.append("\tPrice Code: " + rental.getVideo().getPriceCode());
            }
            return builder.toString();
        }
    }
}
