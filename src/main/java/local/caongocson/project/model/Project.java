package local.caongocson.project.model;

import lombok.*;

import javax.persistence.*;
import java.time.Instant;
import java.util.List;

@Entity
@Table(name = "project")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "bucketName", unique = true)
    private String bucketName;

    @Column(name = "createDate")
    private Instant createDate;

    @OneToMany(mappedBy = "project", fetch = FetchType.LAZY)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private List<SummaryTest> summaryTests;
}
