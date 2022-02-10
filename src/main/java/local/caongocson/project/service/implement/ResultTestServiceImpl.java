package local.caongocson.project.service.implement;

import local.caongocson.project.model.ResultTest;
import local.caongocson.project.model.SummaryTest;
import local.caongocson.project.repository.ResultTestRepository;
import local.caongocson.project.service.ResultTestService;
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
public class ResultTestServiceImpl implements ResultTestService {
    private final ResultTestRepository resultTestRepository;

    @Override
    public List<ResultTest> getAllResultTestBySummaryTest(int summary_test_id) {
        try{
            return this.resultTestRepository.findAllBySummary_test(summary_test_id);
        }
        catch (Exception e){
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Override
    public List<ResultTest> getAllResultTestBySummaryTestAndStatus(int summary_test_id, String status) {
        try {
            return this.resultTestRepository.findAllBySummary_testAndStatus(summary_test_id, status);
        }
        catch (Exception e){
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Override
    public List<ResultTest> insertSummaryTests(List<ResultTest> resultTests) {
        try {
            for(ResultTest resultTest: resultTests){
                this.insertSummaryTest(resultTest);
            }
            return resultTests;
        }
        catch (Exception e){
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Override
    public ResultTest insertSummaryTest(ResultTest resultTests) {
        try {
            return this.resultTestRepository.save(resultTests);
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
