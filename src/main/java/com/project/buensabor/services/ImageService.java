package com.project.buensabor.services;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Map;

@Service
public class ImageService {

    @Autowired
    private Cloudinary cloudinary;

    public Map<String, Object> uploadImage(MultipartFile imagen, Long idProducto, String folderName) throws IOException {
        File file = File.createTempFile("temp", null);
        imagen.transferTo(file);

        var params = ObjectUtils.asMap(
                "public_id", folderName+"/"+idProducto,
                "overwrite", true,
                "resource_type", "image"
        );
        return cloudinary.uploader().upload(file, params);
    }

    public Map<String, Object> deleteImage(Long idProducto, String folderName) throws IOException {
        return cloudinary.uploader().destroy(folderName+"/"+idProducto, ObjectUtils.asMap("resource_type","image"));
    }
}
