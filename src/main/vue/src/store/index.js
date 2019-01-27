import Vue from 'vue';
import Vuex from 'vuex';

Vue.use(Vuex);

export default new Vuex.Store({
    modules: {
        // Put your Vuex modules here
    },
    strict: process.env.NODE_ENV !== 'production'
});
