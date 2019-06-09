import client from '@/api/rest/client';
import qs from 'qs';

/**
 * @param { int } pageNumber задает номер странички, с которой брать треки.
 */
export async function getPlaylistsByPage(pageNumber) {
    const response = await client.get('/playlists', { params: { pageNumber: pageNumber } });
    return response.data;
}

/**
 * @param { string } playlistName задает имя плейлиста, по которому необходимо искать.
 * @param { string } genresArray задает жанры, по которым необходимо искать.
 * @param { string } singer задает имя исполнителя, по которому необходимо искать.
 * @param { int } pageNumber задает номер странички, с которой брать треки.
 */
export async function getPlaylistsBySearchParams({ playlistName, genresArray, singer, pageNumber }) {
    const response = await client.get('/filter', {
        params: {
            playlistName: playlistName,
            singer: singer,
            genresArray: genresArray,
            pageNumber: pageNumber
        },
        paramsSerializer: function (params) {
            return qs.stringify(params, { arrayFormat: 'repeat' });
        }
    });
    return response.data;
}

export async function createdPlaylist({ playlistName, playlistDescription }) {
    const response = await client.post('/create-playlist', {
        name: playlistName,
        description: playlistDescription
    });
    return response.data;
}

export async function getTopPlaylists() {
    const response = await client.get('/topPlaylists');
    return response.data;
}
