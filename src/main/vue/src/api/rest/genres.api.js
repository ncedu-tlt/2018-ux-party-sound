import client from '@/api/rest/client';

export function getAllGenres() {
    return client.get('/genres')
        .then(res => (
            res.data
        ));
}