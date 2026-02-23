import java.util.*;

public class CafeteriaSystem {
    private final Map<String, MenuItem> menu = new LinkedHashMap<>();
    private final Store store;
    private final TaxCalculator taxCalculator;
    private final DiscountCalculator discountCalculator;
    private final InvoicePrinter invoicePrinter;
    private int invoiceSeq = 1000;

    public CafeteriaSystem(Store store, TaxCalculator taxCalculator, 
                          DiscountCalculator discountCalculator, InvoicePrinter invoicePrinter) {
        this.store = store;
        this.taxCalculator = taxCalculator;
        this.discountCalculator = discountCalculator;
        this.invoicePrinter = invoicePrinter;
    }

    public CafeteriaSystem() {
        this(new FileStore(), new CustomerTaxCalculator(), 
             new CustomerDiscountCalculator(), new InvoicePrinter());
    }

    public void addToMenu(MenuItem i) { menu.put(i.id, i); }

    public void checkout(String customerType, List<OrderLine> lines) {
        String invId = "INV-" + (++invoiceSeq);
        
        List<InvoiceData.InvoiceLine> invoiceLines = new ArrayList<>();
        double subtotal = 0.0;
        for (OrderLine l : lines) {
            MenuItem item = menu.get(l.itemId);
            double lineTotal = item.price * l.qty;
            subtotal += lineTotal;
            invoiceLines.add(new InvoiceData.InvoiceLine(item.name, l.qty, lineTotal));
        }

        double tax = taxCalculator.calculateTax(customerType, subtotal);
        double taxPct = taxCalculator.getTaxPercentage(customerType);

        double discount = discountCalculator.calculateDiscount(customerType, subtotal, lines.size());

        double total = subtotal + tax - discount;

        InvoiceData invoiceData = new InvoiceData(invId, subtotal, tax, taxPct, discount, total, invoiceLines);

        String printable = invoicePrinter.format(invoiceData);
        System.out.print(printable);

        store.save(invId, printable);
        System.out.println("Saved invoice: " + invId + " (lines=" + store.countLines(invId) + ")");
    }
}
