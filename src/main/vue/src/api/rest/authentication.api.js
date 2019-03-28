import client from '@/api/rest/client';

export function registration(info) {
    return client.post('/auth/registration', info)
        .then(
            response => Promise.resolve(response.data),
            error => Promise.reject(error)
        );
}

export function authorization(info) {
    return client.post('/auth/login', info)
        .then(res => (
            res.data
        ));
}