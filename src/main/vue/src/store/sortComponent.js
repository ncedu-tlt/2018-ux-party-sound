import { getAllGenres } from '@/api/rest/genres.api';

const state = {
    allGenres: [],
    filteredGenres: [],
    chosenGenres: [],
    singerName: '',
    playlistName: ''
};

const getters = {
    ALL_GENRES: state => {
        return state.allGenres;
    },
    FILTERED_GENRES: state => {
        return state.filteredGenres;
    },
    CHOSEN_GENRES: state => {
        return state.chosenGenres;
    },
    SEARCH_PLAYLIST_NAME: state => {
        return state.playlistName;
    },
    SEARCH_SINGER_NAME: state => {
        return state.singerName;
    }
};

const mutations = {
    CHOSE_GENRES: (state, payload) => {
        state.chosenGenres.push(payload);
        state.filteredGenres.splice(state.filteredGenres.indexOf(payload), 1);
        state.allGenres.splice(state.allGenres.indexOf(payload), 1);
    },
    DELETED_GENRES: (state, payload) => {
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
    },
    FILTER_GENRES_LIST: (context, payload) => {
        context.commit('FILTER_GENRES', payload);
    },
    ADD_GENRE: (context, payload) => {
        context.commit('CHOSE_GENRES', payload);
    },
    DELETE_GENRE: (context, payload) => {
        context.commit('DELETED_GENRES', payload);
    },
    GET_PLAYLIST_NAME: (context, payload) => {
        context.commit('PLAYLIST_NAME_INPUT', payload);
    },
    GET_SINGER_NAME: (context, payload) => {
        context.commit('SINGER_NAME_INPUT', payload);
    }
};

export default {
    state,
    getters,
    mutations,
    actions
};
