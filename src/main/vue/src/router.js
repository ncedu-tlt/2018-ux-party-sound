import Vue from 'vue';
import Router from 'vue-router';
import Main from './views/Main.vue';
import AuthorizationForm from './views/AuthorizationForm'

Vue.use(Router);

export default new Router({
    routes: [
        {
            path: '/',
            name: 'main',
            component: Main
        },
        {
            path: '/authorization',
            name: 'authorizationForm',
            component: AuthorizationForm
        },
    ]
});
