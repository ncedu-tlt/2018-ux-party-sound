import Vue from 'vue';
import Vuex from 'vuex';
import playlists from './playlists';
import sortComponent from './sortComponent';
import player from './player';
import tracksList from './tracksList';
import user from './user';

Vue.use(Vuex);

export default new Vuex.Store({
    modules: {
        playlists,
        player,
        tracksList,
        sortComponent,
        user
    },
    strict: process.env.NODE_ENV !== 'production'
});
