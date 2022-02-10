package local.caongocson.project.service;

import local.caongocson.project.model.Project;

import java.util.List;

public interface ProjectService {
    List<Project> getAllProject();

    Project getProjectByBucketName(String bucketName);

    Project insertProject(Project project);
}
