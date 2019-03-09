
const state = {
    isLoading: false,
    playlists: [],
    error: null
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
        state.playlists.push(payload);
    },
    PLAYLIST_LOADING_ERROR: (state, payload) => {
        state.isLoading = false;
        state.error = payload;
    }
};

const actions = {
    POPULATE_PLAYLISTS: (context, payload) => {
        context.commit('PLAYLIST_LOADING_SUCCESS', payload);
    }
};

export default {
    state,
    getters,
    mutations,
    actions
};
