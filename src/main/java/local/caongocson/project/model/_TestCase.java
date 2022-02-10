package local.caongocson.project.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class _TestCase {
    private String input;
    private String name;
    private String description;
    private int duration;
    private String error_message;
    private List<String> steps;

    @Override
    public String toString() {
        return "TestCase{" +
                "input='" + input + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", duration=" + duration +
                ", error_message='" + error_message + '\'' +
                ", steps=" + steps +
                '}';
    }
}
