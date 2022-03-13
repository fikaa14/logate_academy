package com.academy.demo.controller;

import com.academy.demo.entity.Document;
import com.academy.demo.service.DocumentService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/document")
public class DocumentController {

    private final DocumentService documentService;

    @PostMapping(value = "upload")
    public ResponseEntity<Void> uploadFile(@RequestParam(value = "file")MultipartFile multipartFile) throws IOException
    {
        documentService.upload(multipartFile);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "download/{id}")
    public ResponseEntity<ByteArrayResource> downloadFile(@PathVariable Integer id,
                                                          HttpServletResponse httpServletResponse) throws IOException
    {
        byte[] fileBytes = documentService.download(id, httpServletResponse);
        if(fileBytes.length>0)
        {
            ByteArrayResource byteArrayResource = new ByteArrayResource(fileBytes);
            return new ResponseEntity<>(byteArrayResource, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping(value = "all")
    public ResponseEntity<List<Document>> all()
    {
        List<Document> documents = documentService.findAll();
        return new ResponseEntity<>(documents, HttpStatus.OK);
    }
}
