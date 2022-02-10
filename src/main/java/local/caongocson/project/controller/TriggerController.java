package local.caongocson.project.controller;

import local.caongocson.project.service.ResultTestService;
import local.caongocson.project.service.SummaryTestService;
import local.caongocson.project.service.TriggerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/trigger")
@RequiredArgsConstructor
public class TriggerController {
    private final TriggerService triggerService;

    @GetMapping("/update-result-test/{project}")
    public String updateResultTest(@PathVariable("project") String project, @RequestParam(name = "filename") String filename){
        return this.triggerService.updateSummaryTestAndResultTest(project, filename);
    }
}
