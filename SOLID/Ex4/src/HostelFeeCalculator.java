import java.util.*;

public class HostelFeeCalculator {
    private final FakeBookingRepo repo;
    private final RoomPricer roomPricer;
    private final AddOnPricer addOnPricer;

    public HostelFeeCalculator(FakeBookingRepo repo) {
        this.repo = repo;
        this.roomPricer = new DefaultRoomPricer();
        this.addOnPricer = new DefaultAddOnPricer();
    }

    public HostelFeeCalculator(FakeBookingRepo repo, RoomPricer roomPricer, AddOnPricer addOnPricer) {
        this.repo = repo;
        this.roomPricer = roomPricer;
        this.addOnPricer = addOnPricer;
    }

    public void process(BookingRequest req) {
        Money monthly = calculateMonthly(req);
        Money deposit = new Money(5000.00);

        ReceiptPrinter.print(req, monthly, deposit);

        String bookingId = "H-" + (7000 + new Random(1).nextInt(1000));
        repo.save(bookingId, req, monthly, deposit);
    }

    private Money calculateMonthly(BookingRequest req) {
        Money roomFee = roomPricer.calculatePrice(req.roomType);
        Money addOnsFee = new Money(0.0);

        for (AddOn addOn : req.addOns) {
            Money addOnPrice = addOnPricer.calculatePrice(addOn);
            addOnsFee = new Money(addOnsFee.amount + addOnPrice.amount);
        }

        return new Money(roomFee.amount + addOnsFee.amount);
    }
}
