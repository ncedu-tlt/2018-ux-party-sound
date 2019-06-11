import { getAllGenres } from '@/api/rest/genres.api';

const state = {
    allGenres: [],
    filteredGenres: [],
    chosenGenres: [],
    singerName: '',
    playlistName: ''
};

const mutations = {
    CHOSE_GENRES: (state, payload) => {
        state.chosenGenres.push(payload);
        state.filteredGenres.splice(state.filteredGenres.indexOf(payload), 1);
        state.allGenres.splice(state.allGenres.indexOf(payload), 1);
    },
    DELETE_GENRE: (state, payload) => {
        state.chosenGenres.splice(state.chosenGenres.indexOf(payload), 1);
        state.filteredGenres.push(payload);
        state.allGenres.push(payload);
    },
    FILTER_GENRES: (state, payload) => {
        state.filteredGenres = state.allGenres.filter(genre => {
            return ~genre.indexOf(payload);
        });
    },
    START_GENRES_LOADING: state => {
        state.isLoading = true;
        state.error = null;
    },
    GENRES_LOADING_SUCCESS: (state, payload) => {
        state.isLoading = false;
        state.allGenres = payload.map(genre => {
            return genre.name;
        });
    },
    GENRES_LOADING_ERROR: (state, payload) => {
        state.isLoading = false;
        state.error = payload;
    },
    PLAYLIST_NAME_INPUT: (state, payload) => {
        state.playlistName = payload;
    },
    SINGER_NAME_INPUT: (state, payload) => {
        state.singerName = payload;
    }
};

const actions = {
    GET_ALL_GENRES: context => {
        context.commit('START_GENRES_LOADING');
        getAllGenres()
            .then(res => {
                context.commit('GENRES_LOADING_SUCCESS', res);
            })
            .catch(e => {
                context.commit('GENRES_LOADING_ERROR', e);
            });
    }
};

export default {
    namespaced: true,
    state,
    mutations,
    actions
};
