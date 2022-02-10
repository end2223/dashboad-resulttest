package local.caongocson.project.service;

import local.caongocson.project.model.Project;
import local.caongocson.project.model.ResultTest;
import local.caongocson.project.model.SummaryTest;

import java.util.List;

public interface ResultTestService {
    List<ResultTest> getAllResultTestBySummaryTest(int summary_test_id);

    List<ResultTest> getAllResultTestBySummaryTestAndStatus(int summary_test_id, String status);

    List<ResultTest> insertSummaryTests(List<ResultTest> resultTests);

    ResultTest insertSummaryTest(ResultTest resultTests);
}
