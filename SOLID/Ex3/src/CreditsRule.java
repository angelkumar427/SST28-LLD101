import java.util.*;

public class CreditsRule implements EligibilityRule {
    private boolean failing = false;

    @Override
    public void evaluate(StudentProfile profile, List<String> reasons) {
        if (profile.earnedCredits < 20) {
            failing = true;
            reasons.add("credits below 20");
        }
    }

    @Override
    public boolean isFailing() {
        return failing;
    }
}
