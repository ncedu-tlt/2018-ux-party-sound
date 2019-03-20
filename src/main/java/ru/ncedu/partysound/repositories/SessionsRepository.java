package ru.ncedu.partysound.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.ncedu.partysound.models.domain.SessionsDAO;

import java.util.List;

public interface SessionsRepository extends CrudRepository<SessionsDAO, Long> {
    List<SessionsDAO> findAll();
}
