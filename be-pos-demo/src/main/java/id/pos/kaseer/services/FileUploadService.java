package id.pos.kaseer.services;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.*;

@Service
public class FileUploadService {
   private final Path root = Paths.get("uploads-image"); // kalo pake /uploads-image >> nanti jadi di luar project

   public void save(MultipartFile file) {
      try {
         if(!Files.exists(root)) {
            Files.createDirectories(root);
         }

         Files.copy(file.getInputStream(), this.root.resolve(file.getOriginalFilename().replaceAll(" ", "_")), StandardCopyOption.REPLACE_EXISTING);
      } catch (IOException e) {
         System.out.println(e.getMessage());
      }
   }

   public Resource load(String filename) {
      try {
         Path file = root.resolve(filename);
         Resource resource = new UrlResource(file.toUri());

         if (resource.exists() || resource.isReadable()) {
            return resource;
         } else {
            throw new RuntimeException("Could not read the file!");
         }
      } catch (Exception e) {
         System.out.println(e.getMessage());
         throw new RuntimeException();
      }
   }
}
