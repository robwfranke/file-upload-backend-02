package nl.lotrac.bv.controller;


import java.util.List;
import java.util.stream.Collectors;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import nl.lotrac.bv.service.FileStorageServiceImpl;
import nl.lotrac.bv.message.ResponseMessage;
import nl.lotrac.bv.message.ResponseFile;
import nl.lotrac.bv.model.FileDB;



@CrossOrigin(origins = "*", maxAge = 3600)
@RestController

@Slf4j
public class FileController {

    @Autowired
    private FileStorageServiceImpl fileStorageServiceImpl;

    @PostMapping("/upload")
    public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file) {
        String message = "";
        try {
            fileStorageServiceImpl.store(file);

            message = "Uploaded the file successfully: " + file.getOriginalFilename();
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
        } catch (Exception e) {
            message = "Could not upload the file: " + file.getOriginalFilename() + "!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }
    }

    @GetMapping("/files")
    public ResponseEntity<List<ResponseFile>> getListFiles() {
        List<ResponseFile> files = fileStorageServiceImpl.getAllFiles().map(dbFile -> {
            String fileDownloadUri = ServletUriComponentsBuilder
//                    hieronder wordt eea toegevoegd o.a.
                    .fromCurrentContextPath()
                    .path("/files/")
                    .path(dbFile.getId())
                    .toUriString();

            return new ResponseFile(
                    dbFile.getId(),
                    dbFile.getName(),
                    fileDownloadUri,
                    dbFile.getType(),
                    dbFile.getData().length
            );
        }).collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(files);
    }

    @GetMapping("/files/{id}")
    public ResponseEntity<byte[]> getFile(@PathVariable String id) {
        FileDB fileDB = fileStorageServiceImpl.getFile(id);



        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileDB.getName() + "\"")
                .body(fileDB.getData());


    }



    @DeleteMapping(value="/files/{id}")
    public ResponseEntity<Object>deleteFileById(@PathVariable("id")String id) {
        fileStorageServiceImpl.deleteFileById(id);
        return ResponseEntity.noContent().build();


    }






}