<template>
    <div class="content">
        <div class="container">
            <TextInput v-model="email" class="child" placeholder="E-mail" />
            <TextInput v-model="login" class="child" placeholder="Логин" />
            <TextInput v-model="name" class="child" placeholder="Имя" />
            <TextInput v-model="firstPassword" class="child" placeholder="Пароль" type="password" />
            <TextInput v-model="secondPassword" class="child" placeholder="Введите пароль еще раз" type="password" />
            <Button class="child" label="Зарегистрироваться" @click.native="setClientInfo" />
            <router-link to="/authorization">
                <Button class="child" label="Войти" type="light" />
            </router-link>
        </div>
    </div>
</template>

<script>
import TextInput from '../components/TextInput';
import Button from '../components/Button';
import { registration } from '../api/rest/authentication.api';

export default {
    name: 'RegistrationForm',
    components: { Button, TextInput },
    data() {
        return {
            email: '',
            login: '',
            name: '',
            firstPassword: '',
            secondPassword: ''
        };
    },
    methods: {
        async setClientInfo() {
            const responce = await registration({
                login: this.login,
                password: this.firstPassword,
                mail: this.email,
                name: this.name
            });
            console.log(responce);
        }
    }
};
</script>

<style scoped>
    .content {
        height: 100vh;
        width: 100%;
        background: slateblue; /*временно, пока нет фоновой картинки*/
        display: flex;
        align-items: center;
        justify-content: center;
    }

    .container {
        display: flex;
        flex-direction: column;
        align-items: center;
    }

    .child {
        margin-bottom: 20px;
    }

    .child:nth-child(5) {
        margin-bottom: 30px;
    }
</style>
