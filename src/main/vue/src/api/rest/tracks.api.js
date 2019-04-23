import client from '@/api/rest/client';

export function createTrack(track) {
    return client.post('/tracks', track)
        .then(res => (
            res.data
        ));
}

export function addTrackInPlaylist({ playlistId, track }) {
    return client.post('/tract/add-on-playlist', {
        playlistId: playlistId,
        track: track
    })
        .then(res => (
            res.data
        ));
}

/**
 * @param { long } playlistId принимает id трека, по которому будут взяты треки
 */
export function getTracksByPlaylistIdWithRight(playlistId) {
    return client.get('/tracks/with-right', { params: { playlistId: playlistId } })
        .then(res => (
            res.data
        ));
}

export function deleteTrack({ playlistId, trackId }) {
    return client.delete('/track-delete', { params: { playlistId: playlistId, trackId: trackId } })
        .then(res => (
            res.data
        ));
}
