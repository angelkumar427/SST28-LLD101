import java.util.*;

public class InvoiceData {
    public final String invoiceId;
    public final double subtotal;
    public final double taxAmount;
    public final double taxPercentage;
    public final double discountAmount;
    public final double total;
    public final List<InvoiceLine> lines;

    public InvoiceData(String invoiceId, double subtotal, double taxAmount, double taxPercentage,
                       double discountAmount, double total, List<InvoiceLine> lines) {
        this.invoiceId = invoiceId;
        this.subtotal = subtotal;
        this.taxAmount = taxAmount;
        this.taxPercentage = taxPercentage;
        this.discountAmount = discountAmount;
        this.total = total;
        this.lines = Collections.unmodifiableList(lines);
    }

    public static class InvoiceLine {
        public final String itemName;
        public final int quantity;
        public final double lineTotal;

        public InvoiceLine(String itemName, int quantity, double lineTotal) {
            this.itemName = itemName;
            this.quantity = quantity;
            this.lineTotal = lineTotal;
        }
    }
}
