import client from '@/api/rest/client';

export async function createTrack(track) {
    const response = await client.post('/tracks', track);
    return response.data;
}

export async function addTrackInPlaylist({ playlistId, track }) {
    const response = await client.post('/tract/add-on-playlist', {
        playlistId: playlistId,
        track: track
    });
    return response.data;
}

/**
 * @param { int } playlistId принимает id трека, по которому будут взяты треки
 */
export async function getTracksByPlaylistIdWithRight(playlistId) {
    const response = await client.get('/tracks/with-right', { params: { playlistId: playlistId } });
    return response.data;
}

export async function deleteTrack({ playlistId, trackId }) {
    const response = await client.delete('/track-delete', { params: { playlistId: playlistId, trackId: trackId } });
    return response.data;
}
