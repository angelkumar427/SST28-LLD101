import java.nio.charset.StandardCharsets;

public class CsvExporter extends Exporter {
    @Override
    public ExportResult export(ExportRequest req) {
        if (req == null) {
            return new ExportResult("text/csv", new byte[0]);
        }
        String title = req.title != null ? req.title : "";
        String body = req.body != null ? req.body : "";
        String csv = "title,body\n" + escapeForCsv(title) + "," + escapeForCsv(body) + "\n";
        return new ExportResult("text/csv", csv.getBytes(StandardCharsets.UTF_8));
    }

    private String escapeForCsv(String s) {
        if (s == null || s.isEmpty()) return "";
        if (s.contains("\"") || s.contains(",") || s.contains("\n")) {
            return "\"" + s.replace("\"", "\"\"") + "\"";
        }
        return s;
    }
}
