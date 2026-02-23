import java.util.*;

public class CGRRule implements EligibilityRule {
    private boolean failing = false;

    @Override
    public void evaluate(StudentProfile profile, List<String> reasons) {
        if (profile.cgr < 8.0) {
            failing = true;
            reasons.add("CGR below 8.0");
        }
    }

    @Override
    public boolean isFailing() {
        return failing;
    }
}
