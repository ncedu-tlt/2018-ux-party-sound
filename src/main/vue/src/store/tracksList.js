import { getTracks } from '../api/rest/track.jamendo.api';

const state = {
    tracksFromJamendo: [],
    successLoadFromJamendoAPI: true,
    nextTracks: undefined
};

const getters = {
    TRACKS_FROM_JAMENDO: state => (state.tracksFromJamendo),
    IS_SUCCESS_LOAD_FROM_JAMENDO_API: state => (state.successLoadFromJamendoAPI),
    NEXT_TRACKS: state => (state.nextTracks)
};

const mutations = {
    SET_TRACKS_FROM_JAMENDO: (state, res) => {
        state.tracksFromJamendo = res.results;
        state.nextTracks = res.headers.next;
        state.successLoadFromJamendoAPI = true;
    }
};

const actions = {
    GET_TRACKS_FROM_JAMENDO: ({ commit }, filters) => {
        getTracks(filters)
            .then((res) => {
                commit('SET_TRACKS_FROM_JAMENDO', res);
            });
    }
};

export default {
    state,
    getters,
    mutations,
    actions
};
