import { getTracksByPlaylistId } from '../api/rest/tracks.api';

const state = {
    tracks: [],
    playlistId: '',
    activeTrackNumber: 0,
    successLoad: true,
    currentSeconds: 0,
    playMethod: true,
    playing: false,
    playlistName: ''
};

const getters = {
    TRACKS: state => (state.tracks),
    PLAYLIST_ID: state => (state.playlistId),
    ACTIVE_TRACK_NUMBER: state => (state.activeTrackNumber),
    IS_SUCCESS_LOAD: state => (state.successLoad),
    CURRENT_SECONDS: state => (state.currentSeconds),
    IS_PLAY_METHOD: state => (state.playMethod),
    IS_PLAYING: state => (state.playing),
    PLAYLIST_NAME: state => (state.playlistName)
};

const mutations = {
    SET_ACTIVE_PLAYLIST: (state, playlist) => {
        state.tracks = playlist.tracks;
        if (state.activeTrackNumber === 0) {
            state.playMethod = true;
        } else {
            state.activeTrackNumber = 0;
            state.playMethod = false;
        }
        state.playlistName = playlist.name;
        state.playlistId = playlist.playlistId;
        state.successLoad = true;
    },
    PLAYLIST_LOADING_ERROR: state => {
        state.tracks = [];
        state.playlistId = '';
        state.playlistName = '';
        state.activeTrackNumber = 0;
        state.successLoad = false;
    },
    SET_ACTIVE_TRACK_NUMBER: (state, number) => {
        state.activeTrackNumber = number;
    },
    SET_CURRENT_SECONDS: (state, time) => {
        state.currentSeconds = time;
    },
    SET_PLAYING: (state, isPlaying) => {
        state.playing = isPlaying;
    }
};

const actions = {
    GET_ACTIVE_PLAYLIST: ({ commit }, playlistId) => {
        getTracksByPlaylistId(playlistId)
            .then(res => {
                commit('SET_ACTIVE_PLAYLIST', {
                    ...res,
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
