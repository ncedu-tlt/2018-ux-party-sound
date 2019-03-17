import jamendoClient from '@/api/rest/jamendo.client';

/**
 * @param { String } namesearch задает подстроку для поиска
 * @param { Number } limit задает количество исполнителей
 */
export function findArtistsByName(limit, namesearch) {
    return jamendoClient.get('/artists', { params: { ...{ limit: limit, namesearch: namesearch } } })
        .then(res => (
            res.data.results
        ));
}
