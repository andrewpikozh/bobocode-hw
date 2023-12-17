package com.example.nasastealer.repository;

import com.example.nasastealer.db.Camera;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CameraRepository extends JpaRepository<Camera, Long> {

}
