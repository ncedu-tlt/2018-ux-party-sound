import client from '@/api/rest/client';

export async function registration(regDataObject) {
    const response = await client.post('/auth/registration', regDataObject);
    return response.data;
}

export async function authorization(authDataObject) {
    const response = await client.post('/auth/login', authDataObject);
    return response.data;
}

export async function logout() {
    const response = await client.post('/auth/logout');
    return response.data;
}
