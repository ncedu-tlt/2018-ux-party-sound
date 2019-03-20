package ru.ncedu.partysound.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.ncedu.partysound.models.domain.RolesDAO;

import java.util.List;

public interface RolesRepository extends CrudRepository<RolesDAO,Long> {

    List<RolesDAO> findAll();
}
