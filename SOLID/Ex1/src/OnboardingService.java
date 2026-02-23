import java.util.*;

public class OnboardingService {
    private final StudentStore store;
    private final InputParser parser;
    private final StudentValidator validator;
    private final RegistrationPrinter printer;

    public OnboardingService(StudentStore store) {
        this.store = store;
        this.parser = new InputParser();
        this.validator = new StudentValidator();
        this.printer = new RegistrationPrinter();
    }

    public void registerFromRawInput(String raw) {
        printer.printInput(raw);

        Map<String, String> data = parser.parse(raw);

        ValidationResult result = validator.validate(data);
        if (!result.isValid()) {
            printer.printValidationErrors(result.getErrors());
            return;
        }

        String name = data.get("name");
        String email = data.get("email");
        String phone = data.get("phone");
        String program = data.get("program");

        String id = IdUtil.nextStudentId(store.count());
        StudentRecord rec = new StudentRecord(id, name, email, phone, program);

        store.save(rec);

        printer.printSuccess(id, store.count(), rec);
    }
}
