package com.example.nasastealer.service;

import com.example.nasastealer.model.Sol;
import com.example.nasastealer.client.NasaClient;
import com.example.nasastealer.db.Camera;
import com.example.nasastealer.db.Picture;
import com.example.nasastealer.repository.CameraRepository;
import com.example.nasastealer.repository.PictureRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NasaService {
    private final NasaClient nasaClient;
    private final CameraRepository cameraRepository;
    private final PictureRepository pictureRepository;

    @Value("${nasa.key}")
    private String apiKey;

    public void steal(Sol request) {
        var nasaPhotos = nasaClient.getNasaPhotos(request.getSol(), apiKey);
        nasaPhotos.getPhotoList()
                .forEach(photo -> {
                    var camera = photo.getCamera();
                    Camera entity = cameraRepository.save(new Camera(camera.getId(), photo.getId(), camera.getName(), photo.getEarthDate()));
                    pictureRepository.save(new Picture(photo.getId(), photo.getImgSrc(), entity, photo.getEarthDate()));
                });

    }
}
