import client from '@/api/rest/client';

export async function registration(regDataObject) {
    const response = await client.post('/auth/registration', regDataObject);
    return response.data;
}

export async function authorization(authDataObject) {
    return client.post('/auth/login', authDataObject)
        .then(res => res.data)
        .catch(err => err.status);
}

export async function logout() {
    const response = await client.post('/auth/logout');
    return response;
}
