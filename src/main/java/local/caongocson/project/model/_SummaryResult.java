package local.caongocson.project.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class _SummaryResult {
    private int total;
    private int pass;
    private int fail;
    private Instant createDate;
    private List<_TestCase> testcases;

    @Override
    public String toString() {
        return "SummaryResult{" +
                "total=" + total +
                ", pass=" + pass +
                ", fail=" + fail +
                ", testcases=" + testcases +
                '}';
    }
}
