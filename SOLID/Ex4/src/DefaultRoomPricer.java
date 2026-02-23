public class DefaultRoomPricer implements RoomPricer {
    @Override
    public Money calculatePrice(int roomType) {
        double price;
        switch (roomType) {
            case LegacyRoomTypes.SINGLE -> price = 14000.0;
            case LegacyRoomTypes.DOUBLE -> price = 15000.0;
            case LegacyRoomTypes.TRIPLE -> price = 12000.0;
            default -> price = 16000.0;
        }
        return new Money(price);
    }
}
