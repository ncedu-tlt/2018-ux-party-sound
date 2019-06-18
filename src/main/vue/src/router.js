import Vue from 'vue';
import VueRouter from 'vue-router';
import Header from './components/TheHeader';
import Main from './views/Main.vue';
import RegistrationPage from './views/RegistrationPage';
import AuthorizationPage from './views/AuthorizationPage';
import APITest from './views/APITest';
import PlaylistPage from './views/PlaylistPage';

Vue.use(VueRouter);

export default new VueRouter({
    scrollBehavior() {
        return { x: 0, y: 0 };
    },
    routes: [
        {
            path: '/',
            component: Header,
            children: [
                {
                    path: '',
                    name: 'main',
                    component: Main
                },
                {
                    path: 'authorization',
                    name: 'AuthorizationPage',
                    component: AuthorizationPage
                },
                {
                    path: '/registration',
                    name: 'RegistrationPage',
                    component: RegistrationPage
                },
                {
                    path: 'api-test',
                    name: 'APITest',
                    component: APITest
                },
                {
                    path: 'playlist/:id',
                    name: 'PlaylistPage',
                    component: PlaylistPage
                }
            ]
        }

    ]
});
