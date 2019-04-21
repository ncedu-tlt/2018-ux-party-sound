import { getTracksByPlaylistId } from '../api/rest/tracks.api';

const state = {
    tracks: [],
    playlistId: '',
    activeTrack: {
        id: 0,
        url: '',
        name: 'Трек не выбран!'
    },
    successLoad: true,
    currentSeconds: 0,
    playing: false,
    playlistName: ''
};

const getters = {
    TRACKS: state => (state.tracks),
    PLAYLIST_ID: state => (state.playlistId),
    ACTIVE_TRACK: state => (state.activeTrack),
    IS_SUCCESS_LOAD: state => (state.successLoad),
    CURRENT_SECONDS: state => (state.currentSeconds),
    IS_PLAYING: state => (state.playing),
    PLAYLIST_NAME: state => (state.playlistName)
};

const mutations = {
    SET_ACTIVE_PLAYLIST: (state, playlist) => {
        state.tracks = playlist.tracks;
        state.activeTrack = playlist.tracks[0];
        state.playlistName = playlist.name;
        state.playlistId = playlist.playlistId;
        state.successLoad = true;
    },
    PLAYLIST_LOADING_ERROR: state => {
        state.tracks = [];
        state.playlistId = '';
        state.playlistName = '';
        state.activeTrack = {
            id: 0,
            url: '',
            name: 'Ошибка загрузки!'
        };
        state.successLoad = false;
    },
    SET_LIST_FORM_JAMENDO: (state, { tracks, trackId }) => {
        state.tracks = tracks;
        for (let i = 0; i < tracks.length; i++) {
            if (Number(tracks[i].id) === Number(trackId)) {
                state.activeTrack = tracks[i];
                break;
            }
        }
        state.playlistName = 'Поиск';
    },
    SET_ACTIVE_TRACK_BY_ID: (state, id) => {
        for (let i = 0; i < state.tracks.length; i++) {
            if (Number(state.tracks[i].id) === Number(id)) {
                state.activeTrack = state.tracks[i];
                break;
            }
        }
    },
    SET_CURRENT_SECONDS: (state, time) => {
        state.currentSeconds = time;
    },
    SET_PLAYING: (state, isPlaying) => {
        state.playing = isPlaying;
    },
    SET_NEXT_TRACK: (state) => {
        let trackNumber = 0;
        for (let i = 0; i < state.tracks.length; i++) {
            if (Number(state.tracks[i].id) === Number(state.activeTrack.id)) {
                trackNumber = i;
                break;
            }
        }
        if (trackNumber + 1 === state.tracks.length) {
            state.activeTrack = state.tracks[0];
        } else {
            state.activeTrack = state.tracks[trackNumber + 1];
        }
    },
    SET_PREVIOUS_TRACK: (state) => {
        let trackNumber = 0;
        for (let i = 0; i < state.tracks.length; i++) {
            if (Number(state.tracks[i].id) === Number(state.activeTrack.id)) {
                trackNumber = i;
                break;
            }
        }
        if (trackNumber - 1 < 0) {
            state.activeTrack = state.tracks[state.tracks.length - 1];
        } else {
            state.activeTrack = state.tracks[trackNumber - 1];
        }
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
