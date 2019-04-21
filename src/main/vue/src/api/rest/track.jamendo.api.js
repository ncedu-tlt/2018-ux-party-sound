import jamendoClient from '@/api/rest/jamendo.client';

/**
 * Assign the project to an filters.
 * @param { Object } filters объект параметров для поиска
 * @param { Number } filters.limit задает лимит треков
 * @param { String } filters.nameSearch задает подстроку для поиска
 * @param { Array } filters.artistId задает id исполнителей для поиска
 * @param { Array } filters.tags задает массив жанров для поиска
 * @param { String } filters.durationbetween задает диапазон длительности трека для поиска
 * @param { String } filters.include задает импорт дополнительной инфы для трека
 * @param { Number } filters.offset задает чмсло треков, которое будет пропущено при поиске
 */
export function getTracks({ nameSearch, artistId, tags, durationbetween, limit, include, offset }) {
    return jamendoClient.get('/tracks', { params: { namesearch: nameSearch || '',
        artist_id: artistId || '',
        tags: tags || [],
        durationbetween: durationbetween || '',
        limit: limit || 5,
        include: include || '',
        offset: offset || 0 } })
        .then(res => (
            res.data
        ));
};
