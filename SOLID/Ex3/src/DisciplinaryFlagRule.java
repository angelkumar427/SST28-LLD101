import java.util.*;

public class DisciplinaryFlagRule implements EligibilityRule {
    private boolean failing = false;

    @Override
    public void evaluate(StudentProfile profile, List<String> reasons) {
        if (profile.disciplinaryFlag != LegacyFlags.NONE) {
            failing = true;
            reasons.add("disciplinary flag present");
        }
    }

    @Override
    public boolean isFailing() {
        return failing;
    }
}
