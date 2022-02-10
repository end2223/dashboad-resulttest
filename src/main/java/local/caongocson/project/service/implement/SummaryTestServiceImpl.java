package local.caongocson.project.service.implement;

import local.caongocson.project.model.Project;
import local.caongocson.project.model.SummaryTest;
import local.caongocson.project.repository.SummaryTestRepository;
import local.caongocson.project.service.SummaryTestService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class SummaryTestServiceImpl implements SummaryTestService {
    private final SummaryTestRepository summaryTestRepository;

    @Override
    public List<SummaryTest> getAllSummaryTestBetweenAndProject(String startDate, String endDate, int project_id) {
        try{
            return this.summaryTestRepository.findAllByCreateDateBetweenAndProject(startDate, endDate, project_id);
        }
        catch (Exception e){
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Override
    public SummaryTest insertSummaryTest(SummaryTest summaryTest) {
        try {
            return this.summaryTestRepository.save(summaryTest);
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
