import client from '@/api/rest/client';

export function registration(regDataObject) {
    return client.post('/auth/registration', regDataObject)
        .then(response => response.data)
        .catch(error => error);
}

export function authorization(authDataObject) {
    return client.post('/auth/login', authDataObject)
        .then(response => response.status)
        .catch(error => error.response.status);
}

export function logout() {
    return client.post('/auth/logout')
        .then(response => response.status)
        .catch(error => error.response.status);
}
