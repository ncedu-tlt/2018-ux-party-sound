import client from '@/api/rest/client';

export function createTrack(track) {
    return client.post('/tracks', track)
        .then(res => (
            res.data
        ));
}
/**
 * @param { long } playlistId принимает id трека, по которому будут взяты треки
 */
export function getTracksByPlaylistId(playlistId) {
    return client.get('/tracks', { params: { playlistId: playlistId } })
        .then(res => (
            res.data
        ));
}
