package local.caongocson.project.controller;

import local.caongocson.project.model.Project;
import local.caongocson.project.model.ResultTest;
import local.caongocson.project.model.SummaryTest;
import local.caongocson.project.service.ProjectService;
import local.caongocson.project.service.ResultTestService;
import local.caongocson.project.service.SummaryTestService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/result-test")
@RequiredArgsConstructor
public class TestInformationController {
    private final ProjectService projectService;
    private final SummaryTestService summaryTestService;
    private final ResultTestService resultTestService;

    @GetMapping("/list-buckets")
    public ResponseEntity<List<Project>> getListProjects(){
        try {
            List<Project> projects = this.projectService.getAllProject();
            return ResponseEntity.ok().body(projects);
        }
        catch (Exception e){
            e.printStackTrace();
            log.error("Error! Don't get buckets list!", e);
            return ResponseEntity.ok().body(null);
        }
    }

    @GetMapping("/list-summary-tests")
    public ResponseEntity<List<SummaryTest>> getListSummaryTests(@RequestParam(name="startDate") String startDate,
                                                                 @RequestParam(name="endDate") String endDate,
                                                                 @RequestParam(name="project_id") int project_id){
        try {
            List<SummaryTest> summaryTests = this.summaryTestService.getAllSummaryTestBetweenAndProject(startDate, endDate, project_id);
            return ResponseEntity.ok().body(summaryTests);
        }
        catch (Exception e){
            e.printStackTrace();
            log.error("Error! Don't get summary test list!", e);
            return ResponseEntity.ok().body(null);
        }
    }

    @GetMapping("/list-result-tests")
    public ResponseEntity<List<ResultTest>> getListResultTests(@RequestParam(name="summary_test_id") int summary_test_id){
        try {
            List<ResultTest> resultTests = this.resultTestService.getAllResultTestBySummaryTest(summary_test_id);
            return ResponseEntity.ok().body(resultTests);
        }
        catch (Exception e){
            e.printStackTrace();
            log.error("Error! Don't get result test!", e);
            return ResponseEntity.ok().body(null);
        }
    }

    @GetMapping("/list-result-tests-by-status")
    public ResponseEntity<List<ResultTest>> getListResultTestsByStatus(@RequestParam(name="summary_test_id") int summary_test_id,
                                                                       @RequestParam(name="status") String status){
        try {
            List<ResultTest> resultTests = this.resultTestService.getAllResultTestBySummaryTestAndStatus(summary_test_id, status);
            return ResponseEntity.ok().body(resultTests);
        }
        catch (Exception e){
            e.printStackTrace();
            log.error("Error! Don't get result test!", e);
            return ResponseEntity.ok().body(null);
        }
    }
}
