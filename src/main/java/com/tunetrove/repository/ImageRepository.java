package com.tunetrove.repository;

import com.tunetrove.model.Image;
import com.tunetrove.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {
    Optional<Image> findByUrl(String url);
    Optional<Image> findByUser(User user);
}
