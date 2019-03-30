import { getTracksByPlaylistId } from '../api/rest/tracks.api';

const state = {
    tracks: [],
    playlistId: '',
    activeTrackNumber: 0,
    successLoad: true
};

const getters = {
    TRACKS: state => (state.tracks),
    PLAYLIST_ID: state => (state.playlistId),
    ACTIVE_TRACK_NUMBER: state => (state.activeTrackNumber),
    IS_SUCCESS_LOAD: state => (state.successLoad)
};

const mutations = {
    SET_ACTIVE_PLAYLIST: (state, playlist) => {
        state.tracks = playlist.tracks;
        state.playlistId = playlist.playlistId;
        state.activeTrackNumber = 0;
        state.successLoad = true;
    },
    PLAYLIST_LOADING_ERROR: state => {
        state.tracks = [];
        state.playlistId = '';
        state.activeTrackNumber = 0;
        state.successLoad = false;
    },
    SET_ACTIVE_TRACK_NUMBER: (state, number) => {
        state.activeTrackNumber = number;
    }
};

const actions = {
    GET_ACTIVE_PLAYLIST: ({ commit }, playlistId) => {
        getTracksByPlaylistId(playlistId)
            .then(res => {
                commit('SET_ACTIVE_PLAYLIST', {
                    tracks: res.tracks,
                    playlistId: playlistId
                });
            })
            .catch(e => {
                commit('PLAYLIST_LOADING_ERROR');
            });
    }
};

export default {
    state,
    getters,
    mutations,
    actions
};
