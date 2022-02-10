package local.caongocson.project.utils.convertobject;

import io.minio.messages.Bucket;
import local.caongocson.project.model.Project;

import java.util.ArrayList;
import java.util.List;

public class BucketAndProject {
    public static Project convertBucketToProject(Bucket bucket){
        return Project.builder()
                .bucketName(bucket.name())
                .createDate(bucket.creationDate().toInstant())
                .build();
    }

    public static List<Project> convertBucketsToProjects(List<Bucket> buckets){
        List<Project> projects = new ArrayList<>();
        for (Bucket bucket: buckets){
            projects.add(convertBucketToProject(bucket));
        }
        return projects;
    }
}
