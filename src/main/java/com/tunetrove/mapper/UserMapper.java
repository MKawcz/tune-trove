package com.tunetrove.mapper;

import com.tunetrove.dto.ImageDto;
import com.tunetrove.dto.UserDto;
import com.tunetrove.exception.ImageNotFoundException;
import com.tunetrove.model.Image;
import com.tunetrove.model.User;
import com.tunetrove.repository.ImageRepository;
import com.tunetrove.response.ImageResponse;
import com.tunetrove.response.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class UserMapper implements Mapper<User, UserDto, UserResponse> {
    private final ImageMapper imageMapper;
    private final ImageRepository imageRepository;

    @Override
    public User toEntityFromDto(UserDto dto) {
        User user = new User();
        user.setSpotifyId(dto.getSpotifyId());
        user.setUsername(dto.getUsername());
        user.setEmailAddress(dto.getEmailAddress());
        setProfileImageFromDto(user, dto);
        return user;
    }
    public void updateUserFromDto(UserDto dto, User user) {
        user.setUsername(dto.getUsername());
        user.setEmailAddress(dto.getEmailAddress());
        setProfileImageFromDto(user, dto);
    }

    @Override
    public User toEntityFromResponse(UserResponse response) {
        User user = new User();
        user.setSpotifyId(response.getSpotifyId());
        user.setUsername(response.getName());
        user.setEmailAddress(response.getEmail());
        setProfileImageFromResponse(user, response);
        return user;
    }

    @Override
    public UserDto toDtoFromEntity(User user) {
        UserDto dto = new UserDto();
        dto.setIdUser(user.getIdUser());
        dto.setSpotifyId(user.getSpotifyId());
        dto.setUsername(user.getUsername());
        dto.setEmailAddress(user.getEmailAddress());

        if (user.getProfileImage() != null) {
            dto.setProfileImage(imageMapper.toDtoFromEntity(user.getProfileImage()));
        }
        return dto;
    }

    @Override
    public UserDto toDtoFromResponse(UserResponse response) {
        UserDto dto = new UserDto();
        dto.setSpotifyId(response.getSpotifyId());
        dto.setUsername(response.getName());
        dto.setEmailAddress(response.getEmail());
        dto.setExternalUrls(response.getExternalUrls());

        List<ImageResponse> images = response.getImages();
        if (images != null && !images.isEmpty()) {
            ImageResponse firstImageResponse = images.get(0);
            ImageDto profileImageDto = imageMapper.toDtoFromResponse(firstImageResponse);
            dto.setProfileImage(profileImageDto);
        }
        return dto;
    }

    public void updateUserFromResponse(User user, UserResponse response) {
        user.setEmailAddress(response.getEmail());
        user.setUsername(response.getName());
        setProfileImageFromResponse(user, response);
    }

    public UserResponse toUserResponse(OAuth2User oauth2User) {
        UserResponse userResponse = new UserResponse();

        userResponse.setSpotifyId(oauth2User.getAttribute("id"));
        userResponse.setName(oauth2User.getAttribute("display_name"));
        userResponse.setEmail(oauth2User.getAttribute("email"));
        userResponse.setExternalUrls(oauth2User.getAttribute("external_urls"));

        List<Map<String, Object>> images = oauth2User.getAttribute("images");
        if (images != null && !images.isEmpty()) {
            List<ImageResponse> imageResponses = images.stream()
                    .map(imageMap -> {
                        ImageResponse imageResponse = new ImageResponse();
                        imageResponse.setUrl((String) imageMap.get("url"));
                        imageResponse.setHeight((Integer) imageMap.get("height"));
                        imageResponse.setWidth((Integer) imageMap.get("width"));
                        return imageResponse;
                    }).toList();

            userResponse.setImages(imageResponses);
        }
        return userResponse;
    }

    private void setProfileImageFromResponse(User user, UserResponse userResponse) {
        List<ImageResponse> images = userResponse.getImages();
        if (images != null && !images.isEmpty()) {
            ImageResponse firstImageResponse = images.get(0);
            Image profileImage = imageMapper.toEntityFromResponse(firstImageResponse);
            Image existingImage = user.getProfileImage();

            user.setProfileImage(profileImage);
            if (existingImage != null) {
                existingImage.setUser(null);
                imageRepository.delete(existingImage);
            }
            profileImage.setUser(user);
        } else {
            user.setProfileImage(null);
        }
    }

    private void setProfileImageFromDto(User user, UserDto dto) {
        if(dto.getProfileImage() != null) {
            Image profileImage = imageMapper.toEntityFromDto(dto.getProfileImage());
            Image existingImage = user.getProfileImage();

            user.setProfileImage(profileImage);
            if (existingImage != null) {
                existingImage.setUser(null);
                imageRepository.delete(existingImage);
            }
            profileImage.setUser(user);
        } else {
            user.setProfileImage(null); // Jeśli DTO nie ma obrazu, usuń obraz z użytkownika
        }
    }

}
