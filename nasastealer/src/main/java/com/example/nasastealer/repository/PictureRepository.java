package com.example.nasastealer.repository;

import com.example.nasastealer.db.Picture;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PictureRepository extends JpaRepository<Picture, Long> {
}
