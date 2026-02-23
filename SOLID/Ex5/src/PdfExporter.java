import java.nio.charset.StandardCharsets;

public class PdfExporter extends Exporter {
    @Override
    public ExportResult export(ExportRequest req) {
        if (req == null) {
            return new ExportResult("application/pdf", new byte[0]);
        }
        String fakePdf = "PDF(" + (req.title != null ? req.title : "") + "):" + (req.body != null ? req.body : "");
        return new ExportResult("application/pdf", fakePdf.getBytes(StandardCharsets.UTF_8));
    }
}
