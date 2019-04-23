package ru.ncedu.partysound.services.impl;

import org.springframework.stereotype.Service;
import ru.ncedu.partysound.models.domain.RolesDAO;
import ru.ncedu.partysound.repositories.RolesRepository;
import ru.ncedu.partysound.services.RolesService;

@Service
public class RolesServiceImpl implements RolesService {


    private final RolesRepository rolesRepository;

    public RolesServiceImpl(RolesRepository rolesRepository) {
        this.rolesRepository = rolesRepository;
    }


    @Override
    public RolesDAO createAuthor() {
        RolesDAO rolesDAO = new RolesDAO();
        rolesDAO.setAddTrack(true);
        rolesDAO.setChangeTracksOrder(true);
        rolesDAO.setDeleteTrack(true);
        rolesDAO.setDeleteUser(true);
        rolesDAO.setSeePlaylist(true);
        rolesDAO.setName("Author");
        rolesRepository.save(rolesDAO);
        return rolesDAO;
    }
}
