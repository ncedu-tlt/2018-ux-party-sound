import axios from 'axios';

const jamendo = axios.create({
    baseURL: 'https://api.jamendo.com/v3.0'
});

export default jamendo;
