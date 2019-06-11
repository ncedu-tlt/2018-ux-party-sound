import { getPlaylistsByPage, getPlaylistsBySearchParams } from '../api/rest/playlists.api';

const state = {
    isLoading: false,
    playlistsArray: [],
    error: null,
    numberPagePlaylists: 0
};

const mutations = {
    START_PLAYLIST_LOADING: state => {
        state.isLoading = true;
        state.error = null;
    },
    PLAYLIST_LOADING_SUCCESS: (state, payload) => {
        state.isLoading = false;
        state.playlistsArray = payload;
    },
    PAGE_LOADING_SUCCESS: (state, payload) => {
        state.isLoading = false;
        state.playlistsArray = state.playlistsArray.concat(payload);
    },
    PLAYLIST_LOADING_ERROR: (state, payload) => {
        state.isLoading = false;
        state.error = payload;
    },
    INCREMENT_PAGE_NUMBER: state => {
        state.numberPagePlaylists++;
    },
    ZEROIZE_PAGE_NUMBER: state => {
        state.numberPagePlaylists = 0;
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
        context.commit('ZEROIZE_PAGE_NUMBER');
        context.commit('START_PLAYLIST_LOADING');
        getPlaylistsBySearchParams({
            playlistName: context.rootState.sortComponent.playlistName,
            genresArray: context.rootState.sortComponent.chosenGenres,
            singer: context.rootState.sortComponent.singerName,
            pageNumber: context.state.numberPagePlaylists
        })
            .then(res => {
                context.commit('PLAYLIST_LOADING_SUCCESS', res);
            })
            .catch(e => {
                context.commit('PLAYLIST_LOADING_ERROR', e);
            });
    },
    GET_NEW_PAGE: context => {
        context.commit('START_PLAYLIST_LOADING');
        context.commit('INCREMENT_PAGE_NUMBER');
        getPlaylistsBySearchParams({
            playlistName: context.rootState.sortComponent.playlistName,
            genresArray: context.rootState.sortComponent.chosenGenres,
            singer: context.rootState.sortComponent.singerName,
            pageNumber: context.state.numberPagePlaylists
        })
            .then(res => {
                context.commit('PAGE_LOADING_SUCCESS', res);
            })
            .catch(e => {
                context.commit('PLAYLIST_LOADING_ERROR');
            });
    }
};

export default {
    namespaced: true,
    state,
    mutations,
    actions
};
