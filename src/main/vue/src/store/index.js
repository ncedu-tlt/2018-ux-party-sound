import Vue from 'vue';
import Vuex from 'vuex';
import playlists from './playlists';
import activePlaylist from './Player';

Vue.use(Vuex);

export default new Vuex.Store({
    modules: {
        playlists,
        activePlaylist
    },
    strict: process.env.NODE_ENV !== 'production'
});
