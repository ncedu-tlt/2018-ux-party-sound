import { getPlaylistsByPage, getPlaylistsBySearchParams } from '../api/rest/playlists.api';

const state = {
    isLoading: false,
    playlists: [],
    error: null,
    numberPagePlaylists: 0,
    playlistName: '',
    genresArray: [],
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
    START_PLAYLIST_LOADING: state => {
        state.isLoading = true;
        state.error = null;
    },
    PLAYLIST_LOADING_SUCCESS: (state, payload) => {
        state.isLoading = false;
        state.playlists = payload;
    },
    PAGE_LOADING_SUCCESS: (state, payload) => {
        state.isLoading = false;
        state.playlists = state.playlists.concat(payload);
    },
    PLAYLIST_LOADING_ERROR: (state, payload) => {
        state.isLoading = false;
        state.error = payload;
    },
    CHANGE_PAGE_NUMBER: state => {
        state.numberPagePlaylists++;
    }
};

const actions = {
    POPULATE_PLAYLISTS: (context) => {
        context.commit('START_PLAYLIST_LOADING');
        getPlaylistsByPage(context.state.numberPagePlaylists)
            .then(res => {
                context.commit('PLAYLIST_LOADING_SUCCESS', res);
            })
            .catch(e => {
                context.commit('PLAYLIST_LOADING_ERROR');
            });
    },
    FOUND_PLAYLISTS: context => {
        context.commit('START_PLAYLIST_LOADING');
        getPlaylistsBySearchParams(context.state.playlistName, context.state.genresArray, context.state.singer, context.state.numberPagePlaylists)
            .then(res => {
                context.commit('PLAYLIST_LOADING_SUCCESS', res);
            })
            .catch(e => {
                context.commit('PLAYLIST_LOADING_ERROR', e);
            });
    },
    GET_NEW_PAGE: context => {
        context.commit('START_PLAYLIST_LOADING');
        getPlaylistsBySearchParams(context.state.playlistName, context.state.genresArray, context.state.singer, context.state.numberPagePlaylists++)
            .then(res => {
                context.commit('CHANGE_PAGE_NUMBER');
                context.commit('PAGE_LOADING_SUCCESS', res);
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
