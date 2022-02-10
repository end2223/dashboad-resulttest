package local.caongocson.project.utils;

import io.minio.*;
import io.minio.errors.*;
import io.minio.messages.Bucket;
import io.minio.messages.Item;

import java.io.*;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

public class MinioUtil {
    private static MinioUtil instance;
    private MinioClient minioClient;

    private MinioUtil() {
        this.minioClient = MinioClient.builder()
                .endpoint("https://play.min.io")
                .credentials("Q3AM3UQ867SPQQA43P2F", "zuf+tfteSlswRu7BJ86wekitnifILbZam1KYY3TG")
                .build();
    }

    public static MinioUtil getInstance(){
        if (instance==null){
            instance = new MinioUtil();
            return instance;
        }
        return instance;
    }

    public static List<Bucket> listBuckets() throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        List<Bucket> bucketList = MinioUtil.getInstance().minioClient.listBuckets();
        return bucketList==null? new ArrayList<>(): bucketList;
    }

    public static void makeBucket(String bucketName) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        MinioUtil.getInstance().minioClient.makeBucket(MakeBucketArgs.builder()
                .bucket(bucketName)
                .build());
    }

    //List folders and objects top 1 => recursive = false
    public static Iterable<Result<Item>> listObjects(String bucketName, boolean recursive, String prefix){
        return MinioUtil.getInstance().minioClient.listObjects(
                ListObjectsArgs.builder()
                        .bucket(bucketName)
                        .recursive(recursive)
                        .prefix(prefix)
                        .build()
        );
    }

    public static String getDataOfObjectFromFile(){
        StringBuilder data = new StringBuilder();
        BufferedReader bufferedReader = null;
        try{
            String strCurrentLine;
            bufferedReader = new BufferedReader(new FileReader("data.json"));
            while ((strCurrentLine = bufferedReader.readLine())!=null){
                data.append(strCurrentLine).append("\n");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data.toString();
    }

    public static void uploadObject(String filePath, String bucketName, String objectName) throws IOException, ServerException, InsufficientDataException, ErrorResponseException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        InputStream inputStream = new FileInputStream(filePath);
        MinioUtil.getInstance().minioClient.putObject(
                PutObjectArgs.builder()
                        .bucket(bucketName)
                        .object(objectName)
                        .stream(inputStream, inputStream.available(), -1).build()
        );
    }

    public static void downloadObjectAndSaveToFile(String bucketName, String objectName) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        MinioUtil.getInstance().minioClient.downloadObject(
                DownloadObjectArgs.builder()
                        .bucket(bucketName)
                        .object(objectName)
                        .filename("data.json").build()
        );
    }
}
