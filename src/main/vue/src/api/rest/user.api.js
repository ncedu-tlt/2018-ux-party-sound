import client from '@/api/rest/client';

export async function getUserInfo() {
    const response = await client.get('/auth/userInfo');
    return response.data;
}
