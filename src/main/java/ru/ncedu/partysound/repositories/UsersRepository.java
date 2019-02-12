package ru.ncedu.partysound.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.ncedu.partysound.models.domain.UsersDAO;

public interface UsersRepository extends CrudRepository<UsersDAO, Long> {

}
