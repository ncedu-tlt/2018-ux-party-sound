import client from '@/api/rest/client';

export async function getAllGenres() {
    const response = await client.get('/genres');
    return response.data;
}
