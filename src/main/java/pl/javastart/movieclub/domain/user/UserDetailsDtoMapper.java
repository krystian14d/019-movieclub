package pl.javastart.movieclub.domain.user;

import pl.javastart.movieclub.domain.user.dto.UserDetailsDto;

public class UserDetailsDtoMapper {

    static UserDetailsDto map(User user){
        return new UserDetailsDto(
                user.getId(),
                user.getEmail(),
                user.getRoles(),
                user.getUserComments(),
                user.getUserRatings()
        );
    }
}
