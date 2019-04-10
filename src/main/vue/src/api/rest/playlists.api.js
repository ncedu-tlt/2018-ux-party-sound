import client from '@/api/rest/client';
import qs from 'qs';

/**
 * @param { int } pageNumber задает номер странички, с которой брать треки.
 */
export function getPlaylistsByPage(pageNumber) {
    return client.get('/playlists', { params: { pageNumber: pageNumber } })
        .then(res => (
            res.data
        ));
}

export function getPlaylistsBySearchParams(playlistName, genresArray, singer, pageNumber) {
    return client.get('/filter', {
        params: {
            playlistName: playlistName,
            singer: singer,
            genresArray: genresArray,
            pageNumber: pageNumber
        },
        paramsSerializer: function (params) {
            return qs.stringify(params, { arrayFormat: 'repeat' });
        }
    })
        .then(res => (
            res.data
        ));
}
