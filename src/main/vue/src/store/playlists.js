import { getPlaylistsBySearchParams } from '../api/rest/playlists.api';

const state = {
    isLoading: false,
    playlists: [],
    error: null,
    numberPagePlaylists: 0,
    playlistName: 'номер',
    genresArray: ['pop'],
    singer: ''
};

const getters = {
    IS_LOADING: state => {
        return state.isLoading;
    },
    PLAYLISTS: state => {
        return state.playlists;
    },
    ERROR: state => {
        return state.error;
    }
};

const mutations = {
    START_PLAYLIST_LOADING: (state, payload) => {
        state.isLoading = true;
        state.error = null;
    },
    PLAYLIST_LOADING_SUCCESS: (state, payload) => {
        state.isLoading = false;
        state.playlists = state.playlists.concat(payload);
        state.numberPagePlaylists++;
    },
    PLAYLIST_LOADING_ERROR: (state, payload) => {
        state.isLoading = false;
        state.error = payload;
    }
};

const actions = {
    POPULATE_PLAYLISTS: (context) => {
        getPlaylistsBySearchParams(context.state.playlistName, context.state.genresArray, context.state.singer, context.state.numberPagePlaylists)
            .then(res => {
                context.commit('PLAYLIST_LOADING_SUCCESS', res);
            })
            .catch(e => {
                context.commit('PLAYLIST_LOADING_ERROR');
            });
    }
};

export default {
    state,
    getters,
    mutations,
    actions
};
