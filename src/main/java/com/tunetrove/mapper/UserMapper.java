package com.tunetrove.mapper;

import com.tunetrove.dto.UserDto;
import com.tunetrove.model.User;

import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserMapper implements Mapper<User, UserDto> {
    private final ImageMapper imageMapper;

    @Override
    public User toEntityFromDto(UserDto dto) {
        User user = new User();
        user.setSpotifyId(dto.getSpotifyId());
        user.setUsername(dto.getUsername());
        user.setEmailAddress(dto.getEmailAddress());
//        imageService.setUserProfileImageFromDto(user, dto);
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

    public UserDto toDtoFromApiResponse(OAuth2User oauth2User) {
        UserDto userDto = new UserDto();

        userDto.setSpotifyId(oauth2User.getAttribute("id"));
        userDto.setUsername(oauth2User.getAttribute("display_name"));
        userDto.setEmailAddress(oauth2User.getAttribute("email"));
        userDto.setExternalUrls(oauth2User.getAttribute("external_urls"));

        return userDto;
    }

}
