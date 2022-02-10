package local.caongocson.project.service;

import local.caongocson.project.model.Project;
import local.caongocson.project.model.SummaryTest;

import java.util.List;

public interface SummaryTestService {
    List<SummaryTest> getAllSummaryTestBetweenAndProject(String startDate, String endDate, int project_id);

    SummaryTest insertSummaryTest(SummaryTest summaryTest);
}
