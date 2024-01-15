package com.findting.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Entity
@Getter
@NoArgsConstructor
public class UploadFile {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String name;

    private String extension;

    public UploadFile(MultipartFile file) {
        this.name = file.getOriginalFilename();
        this.extension = findExtension(file.getOriginalFilename());
    }

    private String findExtension(String originalFilename) {
        if (originalFilename == null) {
            return null;
        }
        int index = originalFilename.lastIndexOf(".");
        return originalFilename.substring(index + 1);
    }
}
