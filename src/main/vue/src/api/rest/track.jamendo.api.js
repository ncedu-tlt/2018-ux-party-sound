import jamendo from '@/api/rest/jamendo';

export function trackFilter(set) {
    return jamendo.get('/tracks', { params: set });
}
