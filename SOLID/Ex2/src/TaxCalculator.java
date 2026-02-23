public interface TaxCalculator {
    double calculateTax(String customerType, double subtotal);
    double getTaxPercentage(String customerType);
}
