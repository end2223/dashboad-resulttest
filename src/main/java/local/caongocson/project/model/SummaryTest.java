package local.caongocson.project.model;

import lombok.*;

import javax.persistence.*;
import java.time.Instant;
import java.util.List;

@Entity
@Table(name = "summarytest")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class SummaryTest {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "pass")
    private int pass;

    @Column(name = "fail")
    private int fail;

    @Column(name = "createDate")
    private Instant createDate;

    @ManyToOne
    @JoinColumn(name = "project_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Project project;

    @OneToMany(mappedBy = "summary_test", fetch = FetchType.LAZY)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private List<ResultTest> resultTests;

}
