import axios from 'axios';
import client from './client';

let key;

async function getKey() {
    if (key) {
        return key;
    }

    const response = await client.get('/captcha/key');
    key = response.data;
    return key;
}

const jamendoClient = axios.create({
    baseURL: 'https://api.jamendo.com/v3.0',
    params: {
        format: 'jsonpretty'
    }
});

jamendoClient.interceptors.request.use(
    async request => {
        const key = await getKey();
        const params = request.params || {};
        params['client_id'] = key;
        request.params = params;

        return request;
    },
    error => Promise.reject(error)
);

export default jamendoClient;
