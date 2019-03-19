import jamendoClient from '@/api/rest/jamendo.client';

/**
 * Assign the project to an filters.
 * @param { Object } filters объект параметров для поиска
 * @param { Number } filters.limit задает лимит треков
 * @param { String } filters.namesearch задает подстроку для поиска
 * @param { Array } filters.artist_id задает id исполнителей для поиска
 * @param { Array } filters.tags задает массив жанров для поиска
 * @param { String } filters.durationbetween задает диапазон длительности трека для поиска
 * @param { String } filters.include задает импорт дополнительной инфы для трека
 */
export function getTracks({ namesearch, artist_id, tags, durationbetween, limit, include }) {
    return jamendoClient.get('/tracks', { params: { ...arguments[0] } })
        .then(res => (
            res.data.results
        ));
}