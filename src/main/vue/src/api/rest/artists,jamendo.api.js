import jamendo from '@/api/rest/jamendo';

export function artistsFindByName(set) {
    return jamendo.get('/artists', { params: set });
}
