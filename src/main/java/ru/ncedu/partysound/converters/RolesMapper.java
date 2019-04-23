package ru.ncedu.partysound.converters;

import org.mapstruct.Mapper;
import ru.ncedu.partysound.models.domain.RolesDAO;
import ru.ncedu.partysound.models.dto.RolesDTO;

@Mapper
public interface RolesMapper {

    RolesDTO toDTO(RolesDAO rolesDAO);

}
