import Vue from 'vue';
import VueRouter from 'vue-router';
import Header from './components/Header';
import Main from './views/Main.vue';
import RegistrationForm from './views/RegistrationForm';
import AuthorizationForm from './views/AuthorizationForm';
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
                    name: 'authorizationForm',
                    component: AuthorizationForm
                },
                {
                    path: '/registration',
                    name: 'registrationForm',
                    component: RegistrationForm
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
