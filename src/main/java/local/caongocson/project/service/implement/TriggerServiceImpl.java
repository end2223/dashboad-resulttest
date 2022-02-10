package local.caongocson.project.service.implement;

import io.minio.Result;
import io.minio.errors.*;
import io.minio.messages.Item;
import local.caongocson.project.model.ResultTest;
import local.caongocson.project.model.SummaryTest;
import local.caongocson.project.model._SummaryResult;
import local.caongocson.project.model._TestCase;
import local.caongocson.project.repository.SummaryTestRepository;
import local.caongocson.project.service.SummaryTestService;
import local.caongocson.project.service.TriggerService;
import local.caongocson.project.utils.JsonUtil;
import local.caongocson.project.utils.MinioUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class TriggerServiceImpl implements TriggerService {
    private final SummaryTestRepository summaryTestRepository;

    @Override
    public String updateSummaryTestAndResultTest(String project, String filename) {
        try {
            Instant instant = this.getCreateDateOfFileObject(project, filename);
            if (instant!=null){
                this.downloadFileObject(project, filename);
                SummaryTest summaryTest = this.convertSummaryResultToSummaryTest(this.readAndConvertObjectFromObjectFile(), instant);
                this.summaryTestRepository.save(summaryTest);
                return "Insert Success!";
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return "Insert Failure!";
    }

    private void downloadFileObject(String bucketName, String objectName){
        try {
            MinioUtil.downloadObjectAndSaveToFile(bucketName, objectName);
        } catch (ServerException | InsufficientDataException | ErrorResponseException | IOException | NoSuchAlgorithmException | InvalidKeyException | InvalidResponseException | XmlParserException | InternalException e) {
            e.printStackTrace();
            log.error("Connection is failures to the minio server!", e);
        }
    }

    private Instant getCreateDateOfFileObject(String bucketName, String objectName){
        try {
            Iterable<Result<Item>> results = MinioUtil.listObjects(bucketName, true, objectName);
            if (results.iterator().hasNext()){
                return results.iterator().next().get().lastModified().toInstant();
            }
        } catch (ServerException | InsufficientDataException | ErrorResponseException | IOException | NoSuchAlgorithmException | InvalidKeyException | InvalidResponseException | XmlParserException | InternalException e) {
            e.printStackTrace();
            log.error("Connection is failures to the minio server!", e);
        }
        return null;
    }

    private _SummaryResult readAndConvertObjectFromObjectFile(){
        String data = MinioUtil.getDataOfObjectFromFile();
        _SummaryResult tmp = JsonUtil.convertDataToSummaryResult(data);
        return tmp;
    }

    private SummaryTest convertSummaryResultToSummaryTest(_SummaryResult summaryResult, Instant instant){
        return SummaryTest.builder()
                .pass(summaryResult.getPass())
                .fail(summaryResult.getFail())
                .resultTests(summaryResult.getTestcases().stream().map(this::convertTestCaseToResultTest).collect(Collectors.toList()))
                .createDate(instant)
                .build();
    }

    private ResultTest convertTestCaseToResultTest(_TestCase testCase){
        return ResultTest.builder()
                .input(testCase.getInput())
                .name(testCase.getName())
                .description(testCase.getDescription())
                .duration(testCase.getDuration())
                .error_message(testCase.getError_message())
                .steps(testCase.getSteps().toString())
                .build();
    }
}
