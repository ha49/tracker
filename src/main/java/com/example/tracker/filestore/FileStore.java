//package com.example.tracker.filestore;
//
//import com.amazonaws.AmazonServiceException;
//import com.amazonaws.services.s3.AmazonS3;
//import com.amazonaws.services.s3.model.ObjectMetadata;
//import org.springframework.beans.factory.annotation.Autowired;
//
//public class FileStore {
//
//    private final AmazonS3 s3;
//
//    @Autowired
//    public FileStore(AmazonS3 s3){
//        this.s3 = s3;
//    }
//
//    public void save (String path,
//                      String fileName,
//                      java.util.Optional<java.util.Map<String, String>> optionalMetaData,
//                      java.io.InputStream inputStream
//    ){
//        ObjectMetadata metadata = new ObjectMetadata();
//        optionalMetaData.ifPresent(map ->{
//            if(!map.isEmpty()){
//                map.forEach(metadata::addUserMetadata);
//            }
//        });
//        try{
//            s3.putObject(path, fileName, inputStream, metadata);
//        }catch (AmazonServiceException e){
//            throw new IllegalStateException("The file could not be stored to s3!", e);
//        }
//    }
//
//
//}
