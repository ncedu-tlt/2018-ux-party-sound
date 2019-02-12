package ru.ncedu.partysound.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.ncedu.partysound.models.domain.RolesDAO;

public interface RolesRepository extends CrudRepository<RolesDAO,Long> {

}
