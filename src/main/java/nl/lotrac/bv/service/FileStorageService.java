package nl.lotrac.bv.service;

import java.io.IOException;
import java.util.stream.Stream;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import lombok.extern.slf4j.Slf4j;
//import lombok.extern.slf4j.Slf4j;

import nl.lotrac.bv.model.FileDB;
import nl.lotrac.bv.repository.FileDBRepository;

@Slf4j

@Service
public class FileStorageService {

    @Autowired
    private FileDBRepository fileDBRepository;





    public FileDB store(MultipartFile file) throws IOException {

        System.out.println("IN store");
        log.debug("IN store");

        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        FileDB FileDB = new FileDB(fileName, file.getContentType(), file.getBytes(),file.getSize());

        return fileDBRepository.save(FileDB);
    }

    public FileDB getFile(String id) {
        return fileDBRepository.findById(id).get();
    }

    public Stream<FileDB> getAllFiles() {
        return fileDBRepository.findAll().stream();
    }

}

@
 @Transactional
public  void  deleteFileById(String id){

    fileDBRepository.deleteFileById(id);


}


