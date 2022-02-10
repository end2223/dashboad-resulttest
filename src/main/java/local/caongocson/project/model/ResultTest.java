package local.caongocson.project.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "resulttest")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class ResultTest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "input")
    private String input;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "duration")
    private int duration;

    @Column(name = "status")
    private String status;

    @Column(name = "error_message")
    private String error_message;

    @Column(name = "steps")
    private String steps;

    @ManyToOne
    @JoinColumn(name = "summary_test_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private SummaryTest summary_test;
}
