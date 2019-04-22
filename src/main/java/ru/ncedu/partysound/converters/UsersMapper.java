package ru.ncedu.partysound.converters;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.ncedu.partysound.models.domain.UsersDAO;
import ru.ncedu.partysound.models.dto.UsersDTO;

@Mapper
public interface UsersMapper {

    @Mapping(target = "name", source = "name")
    UsersDTO toUsersDTO(String name);
}
