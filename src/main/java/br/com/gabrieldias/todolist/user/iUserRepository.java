package br.com.gabrieldias.todolist.user;


import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface iUserRepository extends JpaRepository<UserModel, UUID> {


}
