import client from '@/api/rest/client';

export function createTrack(track) {
    return client.post('/tracks', track)
        .then(res => (
            res.data
        ));
}
