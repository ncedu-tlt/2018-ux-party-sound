import Vue from 'vue';
import Vuex from 'vuex';
import playlists from './playlists';
import sortComponent from './sortComponent';
import player from './player';
import tracksList from './tracksList';

Vue.use(Vuex);

export default new Vuex.Store({
    modules: {
        playlists,
        player,
        tracksList,
        sortComponent
    },
    strict: process.env.NODE_ENV !== 'production'
});
