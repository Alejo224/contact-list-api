package com.backend.ContactListApi.service.implementation;

import com.backend.ContactListApi.presentation.dto.UserRequestDTO;
import com.backend.ContactListApi.presentation.dto.UserResponseDTO;
import com.backend.ContactListApi.persistence.entity.User;
import com.backend.ContactListApi.exception.exception.DuplicateEmailException;
import com.backend.ContactListApi.exception.exception.DuplicateUsernameException;
import com.backend.ContactListApi.exception.exception.InvalidPasswordException;
import com.backend.ContactListApi.exception.exception.ResourceNotFoundException;
import com.backend.ContactListApi.persistence.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public  class UserService {
    //inyectar el repositorio
    @Autowired
    private UserRepository userRepository;
    //Inyeccion de dependencia
    private final ModelMapper mapper = new ModelMapper();

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User create(UserRequestDTO userRequestDTO){

        validateUserFields(userRequestDTO.getUsername(), userRequestDTO.getEmail(), null);

        /* validarPassword si cumple con los requerimientos*/
        validarPassword(userRequestDTO.getPassword());

        // Transferir valores del DTO a la entidad
        User user = mapper.map(userRequestDTO, User.class);

        //Codificar contraseña
        userRequestDTO.setPassword(passwordEncoder.encode(userRequestDTO.getPassword()));

        // Guardar usuario en la base de datos
        return  userRepository.save(user);
    }

    private void validateUserFields(String username, String email, Long userId){
        if (userRepository.existsByUsername(username) &&
                (userId == null || !userRepository.findByUsername(username).get().getId().equals(userId))){
            throw new DuplicateUsernameException("El nombre de usuario ya esta en uso.");
        }
        if (userRepository.existsByEmail(email) &&
                (userId == null || !userRepository.findByEmail(email).get().getId().equals(userId))){
            throw new DuplicateEmailException("El correo ya está en uso.");
        }
    }
    public List<UserResponseDTO> getAllUsers(){
        //obtener la lista de usuarios de forma ascendente
        List<User> users = userRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));

        return users.stream().map(user -> mapper.map(user, UserResponseDTO.class))
                .collect(Collectors.toList());
    }

    public UserResponseDTO getUserById(Long id){
        User user =  userRepository.findById(id).orElseThrow(ResourceNotFoundException::new);

        return new UserResponseDTO(user.getId(), user.getUsername(), user.getRole(), user.getEmail());
    }

    public User updateUser(Long id, UserRequestDTO userRequestDTO){
        User userFormOb =  userRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
        //validar si los datos son únicos
        validateUserFields(userRequestDTO.getUsername(), userRequestDTO.getEmail(), id);

        /* validarPassword si cumple con los requerimientos*/
        validarPassword(userRequestDTO.getPassword());

        // Transferir valores de la entidad al DTO
        mapper.map(userRequestDTO, userFormOb);

        //Codificar contraseña actualizada
        userRequestDTO.setPassword(passwordEncoder.encode(userRequestDTO.getPassword()));

        return  userRepository.save(userFormOb);
    }

    public void delete(Long id){
        Optional<User> findUser = userRepository.findById(id);

        if (findUser.isEmpty()){
            throw new ResourceNotFoundException();
        }

        userRepository.deleteById(id);
    }

    private void validarPassword(String password){
        if (password.length() < 5 ){
            throw new InvalidPasswordException("La contraseña debe tener minimo 5 caracteres.");
        }
        if (!password.matches(".*[A-Z].*")){
            throw new InvalidPasswordException("The password debe tener almenos una letra en mayuscula ");
        }
        if (!password.matches(".*[0-9].*")){
            throw new InvalidPasswordException("The passord should have almost one number.");
        }
        if (!password.matches(".*[!#$%&()*^].*")){
            throw new InvalidPasswordException("The password should have almost one special charater");
        }
    }
}
