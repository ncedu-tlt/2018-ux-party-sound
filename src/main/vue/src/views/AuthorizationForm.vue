<template>
    <div class="content">
        <div class="container">
            <TextInput v-model="login" class="field" placeholder="E-mail или логин" type="text" />
            <TextInput v-model="password" class="field" placeholder="Пароль" type="password" />
            <Button class="forget-password" label="Забыли пароль?" type="light" />
            <Button class="entry" label="Вход" v-on:click.native="setClientInfo" />
            <router-link to="/registration">
                <Button class="register" label="Зарегистрироваться" type="light" />
            </router-link>
        </div>
    </div>
</template>

<script>
import TextInput from '../components/TextInput';
import Button from '../components/Button';
import { authorization } from '../api/rest/authentication.api';

export default {
    name: 'AuthorizationForm',
    components: { Button, TextInput },
    data() {
        return {
            login: '',
            password: ''
        };
    },
    methods: {
        async setClientInfo() {
            alert(await authorization({
                username: this.login,
                password: this.password
            })
            );
            alert(
                this.login +
                this.password
            );
        }
    }
};
</script>

<style scoped>
    .content {
        width: 100%;
        height: 100vh;
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

    .field:nth-child(1) {
        margin-bottom: 20px
    }

    .field:nth-child(2) {
        margin-bottom: 10px
    }

    .forget-password {
        width: 100%;

        text-align: end;
    }

    .entry {
        margin-top: 20px;
    }

    .register {
        margin-top: 20px;
    }
</style>
