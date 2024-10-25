package com.verticallogistica.desafio.service;

import com.verticallogistica.desafio.model.User;
import com.verticallogistica.desafio.repository.UserRepository;

import org.springframework.transaction.annotation.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Serviço responsável por operações relacionadas a usuários.
 */
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    /**
     * Retorna a lista de todos os usuários cadastrados.
     *
     * @return Lista de usuários.
     */
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }


    /**
     * Busca um usuário pelo seu ID.
     *  
     * @param userId ID do usuário a ser encontrado.
     * @return Um Optional contendo o usuário, caso encontrado.
     */
    public Optional<User> findUserById(Long userId) {
        return userRepository.findById(userId);
    }


    /**
     * Salva um novo usuário no banco de dados.
     *
     * @param user Usuário a ser salvo.
     * @return Usuário salvo.
     */
    @Transactional
    public User saveUser(User user) {
        return userRepository.save(user);
    }
    
}