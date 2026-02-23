import java.util.*;

public class AttendanceRule implements EligibilityRule {
    private boolean failing = false;

    @Override
    public void evaluate(StudentProfile profile, List<String> reasons) {
        if (profile.attendancePct < 75) {
            failing = true;
            reasons.add("attendance below 75");
        }
    }

    @Override
    public boolean isFailing() {
        return failing;
    }
}
