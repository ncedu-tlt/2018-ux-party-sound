import Vue from 'vue';
import Router from 'vue-router';
import Main from './views/Main.vue';
import RegistrationForm from './views/RegistrationForm'

Vue.use(Router);

export default new Router({
    routes: [
        {
            path: '/',
            name: 'main',
            component: Main
        },
        {
            path: '/registration',
            name: 'registrationForm',
            component: RegistrationForm
        }
    ]
});
