package com.tunetrove.service;

import com.tunetrove.dto.ArtistDto;
import com.tunetrove.dto.ImageDto;
import com.tunetrove.dto.UserDto;
import com.tunetrove.mapper.ImageMapper;
import com.tunetrove.model.Artist;
import com.tunetrove.model.EntityWithImage;
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

    public void setUserImageFromApiResponse(UserDto userDto, OAuth2User oauth2User) {
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
            userDto.setImage(imageDtos.get(imageDtos.size()-1));
        } else {
            userDto.setImage(null);
        }
    }

    public void setUserImageFromDto(User user, UserDto dto) {
        if(dto.getImage() != null) {
            Image newImage = imageMapper.toEntityFromDto(dto.getImage());
            Image existingImage = user.getImage();
            if(existingImage == null) {
                user.setImage(newImage);
                newImage.setUser(user);
            } else {
                updateImage(existingImage, newImage);
            }
        } else {
            user.setImage(null); // Jeśli DTO nie ma obrazu, usuń obraz z użytkownika
        }
    }

    public void setArtistImageFromDto(Artist artist, ArtistDto dto) {
        List<ImageDto> imagesDtos = dto.getImages();
        if(imagesDtos != null) {
            List<Image> images =  imagesDtos.stream().map(imageMapper::toEntityFromDto).toList();
            Image newImage = images.get(images.size() - 1);
            Image existingImage = artist.getImage();
            if(existingImage == null) {
                artist.setImage(newImage);
                newImage.setArtist(artist);
            } else {
                updateImage(existingImage, newImage);
            }
        } else {
            artist.setImage(null);
        }
    }

    private void updateImage(Image existingImage, Image newImage) {
        if(!newImage.equals(existingImage)) {
            existingImage.setHeight(newImage.getHeight());
            existingImage.setWidth(newImage.getWidth());
            existingImage.setUrl(newImage.getUrl());
            imageRepository.save(existingImage);
        }
    }
}
