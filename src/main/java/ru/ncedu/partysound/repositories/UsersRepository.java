package ru.ncedu.partysound.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.ncedu.partysound.models.domain.UsersDAO;

import java.util.List;

public interface UsersRepository extends CrudRepository<UsersDAO, Long> {

    List<UsersDAO> findAll();
}
