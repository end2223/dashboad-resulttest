package local.caongocson.project.repository;

import local.caongocson.project.model.Project;
import local.caongocson.project.model.ResultTest;
import local.caongocson.project.model.SummaryTest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResultTestRepository extends JpaRepository<ResultTest, Long> {
    @Query(value = "SELECT * FROM ResultTest r WHERE r.summary_test_id = ?1", nativeQuery = true)
    List<ResultTest> findAllBySummary_test(int summary_test_id);

    @Query(value = "SELECT * FROM ResultTest r WHERE r.summary_test_id = ?1 AND r.status = ?2", nativeQuery = true)
    List<ResultTest> findAllBySummary_testAndStatus(int summary_test_id, String status);
}
