import java.util.*;

public interface EligibilityRule {
    void evaluate(StudentProfile profile, List<String> reasons);
    boolean isFailing();
}
