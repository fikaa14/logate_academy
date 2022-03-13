package com.academy.demo.service;

import com.academy.demo.entity.Document;
import com.academy.demo.repository.DocumentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class DocumentService {

    @Value("${fs.base-path}")
    private String filePath;

    private final DocumentRepository documentRepository;

    @Transactional
    public void upload(MultipartFile multipartFile) throws IOException {
        String originalFileName = multipartFile.getOriginalFilename();
        String fullFilePath = filePath + originalFileName;

        Document document = new Document();
        document.setName(originalFileName);
        document.setCreatedAt(new Date());
        document.setPath(fullFilePath);
        documentRepository.save(document);

        //save file to F
        Path path = Paths.get(fullFilePath);
        Files.write(path, multipartFile.getBytes());
    }

    public byte[] download(Integer id, HttpServletResponse httpServletResponse) throws IOException {
        Optional<Document> documentOptional = documentRepository.findById(id);
        if(documentOptional.isPresent())
        {
            Document document = documentOptional.get();
            Path path = Paths.get(document.getPath());

            httpServletResponse.setHeader("FILE-NAME", document.getName());

            return Files.readAllBytes(path);
        }
        return new byte[0];
    }

    public List<Document> findAll()
    {
        return documentRepository.findAll();
    }
}
