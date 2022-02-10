package local.caongocson.project.repository;

import local.caongocson.project.model.Project;
import local.caongocson.project.model.SummaryTest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SummaryTestRepository extends JpaRepository<SummaryTest, Long> {
    @Query(value = "SELECT * FROM SummaryTest s WHERE s.createDate >= ?1 AND s.createDate <= ?2 AND s.project_id = ?3", nativeQuery = true)
    List<SummaryTest> findAllByCreateDateBetweenAndProject(String startDate, String endDate, int project_id);

}
