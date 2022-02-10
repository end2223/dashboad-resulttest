package local.caongocson.project.service.implement;

import io.minio.errors.*;
import local.caongocson.project.model.Project;
import local.caongocson.project.repository.ProjectRepository;
import local.caongocson.project.service.ProjectService;
import local.caongocson.project.utils.MinioUtil;
import local.caongocson.project.utils.convertobject.BucketAndProject;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.NonUniqueObjectException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {
    private final ProjectRepository projectRepository;

    @Override
    public List<Project> getAllProject() {
        try {
            this.autoInsertProject();
            return this.projectRepository.findAll();
        }
        catch (Exception e){
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    private void autoInsertProject(){
        try {
            List<Project> projects = BucketAndProject.convertBucketsToProjects(MinioUtil.listBuckets());
            this.projectRepository.saveAll(projects);
        }
        catch (ServerException | InsufficientDataException | ErrorResponseException | NoSuchAlgorithmException | InvalidKeyException | InvalidResponseException | XmlParserException | InternalException | IOException e) {
            e.printStackTrace();
            log.error("Error when connection the minio server!", e);
        }
        catch (NonUniqueObjectException e){
            e.printStackTrace();
            log.warn("Don't insert project name! It's existing in the system!", e);
        }
    }

    @Override
    public Project getProjectByBucketName(String bucketName) {
        try {
            return this.projectRepository.findFirstByBucketName(bucketName);
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Project insertProject(Project project) {
        try {
            return this.projectRepository.save(project);
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
