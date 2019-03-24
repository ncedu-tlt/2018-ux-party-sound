package ru.ncedu.partysound.services;

import ru.ncedu.partysound.models.domain.TracksDAO;
import ru.ncedu.partysound.models.dto.TrackDTO;

public interface TrackService {
    TracksDAO createTrack (TrackDTO trackDTO);
}
