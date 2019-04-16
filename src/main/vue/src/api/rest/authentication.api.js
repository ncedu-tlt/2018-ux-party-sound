import client from '@/api/rest/client';
import router from '../../router';

export function registration(info) {
    return client.post('/auth/registration', info)
        .then(
            response => Promise.resolve(response.data),
            error => Promise.reject(error)
        );
}

export function authorization(info) {
    return client.post('/auth/login', info).then(
        response => {
            response.status;
            router.push('/');
        },
        error => error.response.status
    );
}
