import jamendoClient from '@/api/rest/jamendo.client';

/**
 * @param { Object } params задает подстроку для поиска
 * @param { String } params.namesearch задает подстроку для поиска
 * @param { Number } params.limit задает количество исполнителей
 */
export function findArtistsByName({ limit, namesearch }) {
    return jamendoClient.get('/artists', { params: arguments[0] })
        .then(res => (
            res.data.results
        ));
}
