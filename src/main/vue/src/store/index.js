import Vue from 'vue';
import Vuex from 'vuex';
import playlists from './playlists';
import activePlaylist from './Player';
import sortComponent from './sortComponent';

Vue.use(Vuex);

export default new Vuex.Store({
    modules: {
        playlists,
        activePlaylist,
        sortComponent
    },
    strict: process.env.NODE_ENV !== 'production'
});
