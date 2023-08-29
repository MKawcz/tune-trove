package com.tunetrove.repository;

import com.tunetrove.model.Song;
import com.tunetrove.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    Optional<User> findBySpotifyId(String username);
}

