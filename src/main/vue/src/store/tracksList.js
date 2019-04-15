import { getTracks } from '../api/rest/track.jamendo.api';

const state = {
    tracksFromJamendo: [],
    successLoadFromJamendoAPI: true
};

const getters = {
    TRACKS_FROM_JAMENDO: state => (state.tracksFromJamendo),
    IS_SUCCESS_LOAD_FROM_JAMENDO_API: state => (state.successLoadFromJamendoAPI)
};

const mutations = {
    SET_TRACKS_FROM_JAMENDO: (state, res) => {
        state.tracksFromJamendo = res.results;
        state.successLoadFromJamendoAPI = true;
    },
    ADD_TRACKS_FROM_JAMENDO: (state, res) => {
        state.tracksFromJamendo = state.tracksFromJamendo.concat(res.results);
        state.successLoadFromJamendoAPI = true;
    }
};

const actions = {
    GET_TRACKS_FROM_JAMENDO: ({ commit }, filters) => {
        getTracks(filters)
            .then((res) => {
                commit('SET_TRACKS_FROM_JAMENDO', res);
            });
    },
    GET_NEXT_TRACKS_FROM_JAMENDO: ({ commit }, filters) => {
        getTracks(filters)
            .then((res) => {
                commit('ADD_TRACKS_FROM_JAMENDO', res);
            });
    }
};

export default {
    state,
    getters,
    mutations,
    actions
};
