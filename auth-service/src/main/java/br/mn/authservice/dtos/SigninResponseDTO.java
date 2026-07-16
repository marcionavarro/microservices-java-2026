package br.mn.authservice.dtos;

import br.mn.authservice.entities.UserEntity;

public record SigninResponseDTO(UserEntity user, String token) {

}
