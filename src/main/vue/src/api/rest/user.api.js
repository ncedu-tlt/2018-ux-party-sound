import client from '@/api/rest/client';

export function getUserInfo() {
    return client.get('/auth/userInfo')
        .then(
            response => Promise.resolve(response.data),
            error => Promise.reject(error)
        );
}
