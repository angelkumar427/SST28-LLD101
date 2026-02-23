public class InvoicePrinter {
    public String format(InvoiceData data) {
        StringBuilder out = new StringBuilder();
        out.append("Invoice# ").append(data.invoiceId).append("\n");

        for (InvoiceData.InvoiceLine line : data.lines) {
            out.append(String.format("- %s x%d = %.2f\n", line.itemName, line.quantity, line.lineTotal));
        }

        out.append(String.format("Subtotal: %.2f\n", data.subtotal));
        out.append(String.format("Tax(%.0f%%): %.2f\n", data.taxPercentage, data.taxAmount));
        out.append(String.format("Discount: -%.2f\n", data.discountAmount));
        out.append(String.format("TOTAL: %.2f\n", data.total));

        return out.toString();
    }
}
