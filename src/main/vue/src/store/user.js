import { getUserInfo } from '../api/rest/user.api';

const state = {
    name: '',
    authorized: false
};

const getters = {
    NAME: state => {
        return state.name;
    },
    IS_AUTHORIZED: state => {
        return state.authorized;
    }
};

const mutations = {
    SET_NAME: (state, payload) => {
        state.name = payload;
    },
    AUTHORIZED: (state, payload) => {
        state.authorized = payload;
    }
};

const actions = {
    GET_USER_INFO: (context) => {
        getUserInfo()
            .then(res => {
                context.commit('SET_NAME', res);
                context.commit('AUTHORIZED', true);
            })
            .catch(e => {
                context.commit('AUTHORIZED', false);
            });
    }
};

export default {
    state,
    getters,
    mutations,
    actions
};
