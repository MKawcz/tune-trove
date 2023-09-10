package com.tunetrove.service;

import com.tunetrove.dto.ImageDto;
import com.tunetrove.dto.UserDto;
import com.tunetrove.mapper.ImageMapper;
import com.tunetrove.model.Image;
import com.tunetrove.model.User;
import com.tunetrove.repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ImageService {

    private final ImageMapper imageMapper;
    private final ImageRepository imageRepository;

    public void setUserProfileImageFromApiResponse(UserDto userDto, OAuth2User oauth2User) {
        List<Map<String, Object>> images = oauth2User.getAttribute("images");
        if (images != null && !images.isEmpty()) {
            List<ImageDto> imageDtos = images.stream()
                    .map(imageMap -> {
                        ImageDto imageDto = new ImageDto();
                        imageDto.setUrl((String) imageMap.get("url"));
                        imageDto.setHeight((Integer) imageMap.get("height"));
                        imageDto.setWidth((Integer) imageMap.get("width"));
                        return imageDto;
                    }).toList();
            userDto.setProfileImage(imageDtos.get(0));
        } else {
            userDto.setProfileImage(null);
        }
    }

    public void setUserProfileImageFromDto(User user, UserDto dto) {
        if(dto.getProfileImage() != null) {
            Image profileImage = imageMapper.toEntityFromDto(dto.getProfileImage());
            updateUserProfileImage(user, profileImage);
            profileImage.setUser(user);
        } else {
            user.setProfileImage(null); // Jeśli DTO nie ma obrazu, usuń obraz z użytkownika
        }
    }

    private void updateUserProfileImage(User user, Image profileImage) {
        Image existingImage = user.getProfileImage();

        if(!profileImage.equals(existingImage) && existingImage != null) {
            existingImage.setHeight(profileImage.getHeight());
            existingImage.setWidth(profileImage.getWidth());
            existingImage.setUrl(profileImage.getUrl());
            imageRepository.save(existingImage);
        } else if(existingImage == null) {
            user.setProfileImage(profileImage);
            profileImage.setUser(user);
        }
    }
}
