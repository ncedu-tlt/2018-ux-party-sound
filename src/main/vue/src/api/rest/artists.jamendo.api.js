import jamendoClient from '@/api/rest/jamendo.client';

/**
 * @param { Object } params задает подстроку для поиска
 * @param { String } params.namesearch задает подстроку для поиска
 * @param { Number } params.limit задает количество исполнителей
 */
export async function findArtistsByName({ limit, namesearch }) {
    const response = await jamendoClient.get('/artists', { params: arguments[0] });
    return response.data.results;
}
