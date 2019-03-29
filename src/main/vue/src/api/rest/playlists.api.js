import client from '@/api/rest/client';

/**
 * @param { int } pageNumber задает номер странички, с которой брать треки.
 */
export function getPlaylistsByPage(pageNumber) {
    return client.get('/playlists', { params: { pageNumber: pageNumber } })
        .then(res => (
            res.data
        ));
}