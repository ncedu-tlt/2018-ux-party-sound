import client from '@/api/rest/client';

export function getPlaylistById(playlistId) {
    return client.get('/playlist', { params: { playlist_id: playlistId } })
        .then(res => (
            res.data
        ));
}
