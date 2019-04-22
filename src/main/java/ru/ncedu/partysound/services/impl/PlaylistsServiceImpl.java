package ru.ncedu.partysound.services.impl;

import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.ncedu.partysound.converters.PlaylistsMapper;
import ru.ncedu.partysound.converters.TracksMapper;
import ru.ncedu.partysound.models.domain.*;
import ru.ncedu.partysound.models.dto.PlaylistForCreateDTO;
import ru.ncedu.partysound.models.dto.PlaylistsDTO;
import ru.ncedu.partysound.models.dto.PlaylistsWithTracksDTO;
import ru.ncedu.partysound.models.dto.TracksDTO;
import ru.ncedu.partysound.repositories.PlaylistUserRoleRepository;
import ru.ncedu.partysound.repositories.PlaylistsRepository;
import ru.ncedu.partysound.repositories.UsersRepository;
import ru.ncedu.partysound.services.PlaylistsService;
import ru.ncedu.partysound.services.RolesService;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Service
public class PlaylistsServiceImpl implements PlaylistsService {

    @Autowired
    private final PlaylistsRepository playlistsRepository;
    private final RolesService rolesService;
    private final UsersRepository usersRepository;
    private final PlaylistUserRoleRepository playlistUserRoleRepository;
    private final PlaylistsMapper playlistsMapper = Mappers.getMapper(PlaylistsMapper.class);
    private final TracksMapper tracksMapper = Mappers.getMapper(TracksMapper.class);


    public PlaylistsServiceImpl(PlaylistsRepository playlistsRepository, RolesService rolesService, UsersRepository usersRepository, PlaylistUserRoleRepository playlistUserRoleRepository) {
        this.playlistsRepository = playlistsRepository;
        this.rolesService = rolesService;
        this.usersRepository = usersRepository;
        this.playlistUserRoleRepository = playlistUserRoleRepository;
    }

    @Override
    public List<PlaylistsDTO> getPlaylists(int pageNumber, int pageSize) {
        Pageable playlistPage = PageRequest.of(pageNumber, pageSize);
        Page<PlaylistsDAO> playlistsDAOPage = playlistsRepository.findAllByPrivateAccessFalse(playlistPage);
        return playlistsMapper.toPlaylistDTOs(playlistsDAOPage.getContent());
    }

    @Override
    public PlaylistsWithTracksDTO getPlaylistById(long playlistId) {
        PlaylistsDAO playlistDAO = playlistsRepository.findById(playlistId);
        List<PlaylistTrackDAO> playlistTrackDAOS = playlistDAO.getTracks();
        List<TracksDTO> tracksDTOS = new ArrayList<>();
        for (PlaylistTrackDAO track : playlistTrackDAOS) {
            tracksDTOS.add(tracksMapper.toTracksDTO(track));
        }
        PlaylistsWithTracksDTO playlistsWithTracksDTO = playlistsMapper.toPlaylistWithTracksDTO(playlistDAO);
        playlistsWithTracksDTO.setTracks(tracksDTOS);
        return playlistsWithTracksDTO;
    }

    @Override
    public List<PlaylistsDTO> getTopPlaylists() {
        List<BigInteger> topPlaylistsId = playlistsRepository.getTopPlaylistsId();
        List<PlaylistsDAO> topPlaylists = getPlaylistsDAOS(topPlaylistsId);
        return playlistsMapper.toPlaylistDTOs(topPlaylists);
    }

    private List<PlaylistsDAO> getPlaylistsDAOS(List<BigInteger> topPlaylistsId) {
        List<PlaylistsDAO> playlistsDAOS = new ArrayList<>();
        for (BigInteger id : topPlaylistsId) {
            playlistsDAOS.add(playlistsRepository.findById(id.longValue()));
        }
        return playlistsDAOS;
    }

    @Override
    public List<PlaylistsDTO> getPlaylistsBySearchParams(int pageNumber, int pageSize, String playlistName, String[] genresArray, String singer) {

        Pageable playlistsPage = PageRequest.of(pageNumber, pageSize);
        Page<PlaylistsDAO> playlistsDAOPage;

        boolean isPlaylistNameEmpty = playlistName.equals("");
        boolean isGenresArrayEmpty = genresArray[0].equals("empty");
        boolean isSingerEmpty = singer.equals("");

        if (!isGenresArrayEmpty) {
            playlistsDAOPage = playlistsRepository.findAllByNameAndAndGenresAndSingers(playlistName, genresArray, singer, playlistsPage);
        } else {
            if (!isSingerEmpty) {
                playlistsDAOPage = playlistsRepository.findAllByNameAndSinger(playlistName, singer, playlistsPage);
            } else {
                if (!isPlaylistNameEmpty) {
                    playlistsDAOPage = playlistsRepository.findAllByNameContainsAndPrivateAccessFalse(playlistName, playlistsPage);
                } else {
                    playlistsDAOPage = playlistsRepository.findAllByPrivateAccessFalse(playlistsPage);
                }
            }
        }
        return playlistsMapper.toPlaylistDTOs(playlistsDAOPage.getContent());
    }

    @Override
    public boolean createPlaylist(PlaylistForCreateDTO playlistForCreateDTO, String login) {
        RolesDAO rolesDAO = rolesService.createAuthor();

        PlaylistsDAO playlistsDAO = new PlaylistsDAO();
        playlistsDAO.setDescription(playlistForCreateDTO.getDescription());
        playlistsDAO.setName(playlistForCreateDTO.getName());
        playlistsDAO.setPrivateAccess(false);
        playlistsRepository.save(playlistsDAO);

        PlaylistUserRoleDAO playlistUserRoleDAO = new PlaylistUserRoleDAO();

        playlistUserRoleDAO.setId(new PlaylistUserRoleId(playlistsDAO.getId(), usersRepository.findByLogin(login).getId(), rolesDAO.getId()));
        playlistUserRoleDAO.setPlaylist(playlistsDAO);
        playlistUserRoleDAO.setUser(usersRepository.findByLogin(login));
        playlistUserRoleDAO.setRole(rolesDAO);
        playlistUserRoleRepository.save(playlistUserRoleDAO);
        return true;
    }
}
