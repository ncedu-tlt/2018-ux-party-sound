<template>
    <form class="content">
        <TextInput
            v-model="login"
            class="field"
            placeholder="Логин"
            type="text"
            class-name="input-field"
        />
        <TextInput
            v-model="password"
            class="field"
            placeholder="Пароль"
            type="password"
            class-name="input-field"
        />
        <div class="forget-password-container">
            <Button label="Забыли пароль?" type="light" />
        </div>

        <Button class="entry" label="Вход" @click.native="validation" />
        <div class="error-message">
            {{ errorMessage }}
        </div>
        <router-link to="/registration">
            <Button class="register" label="Зарегистрироваться" type="light" />
        </router-link>
    </form>
</template>

<script>
import TextInput from './BaseInput';
import Button from './BaseButton';
import { authorization } from '../api/rest/authentication.api';
import { mapActions } from 'vuex';

export default {
    name: 'AuthorizationForm',
    components: { Button, TextInput },
    data() {
        return {
            login: '',
            password: '',
            errorMessage: ''
        };
    },
    methods: {
        ...mapActions('user', [
            'GET_USER_INFO'
        ]),
        validation() {
            if (this.login.length === 0 || this.password.length === 0) {
                this.errorMessage = 'Все поля должны быть заполнены!';
            } else {
                this.setClientInfo();
            }
        },
        async setClientInfo() {
            let data = new FormData();
            data.append('username', this.login);
            data.append('password', this.password);
            let response = await authorization(data);
            if (response === 401) {
                this.errorMessage = 'Пользователь, с введенными данными, не найден';
            } else {
                this.GET_USER_INFO();
            }
        }
    }
};
</script>

<style lang="scss" scoped>
    .content {
        display: flex;
        flex-direction: column;
        align-items: center;
        justify-content: center;

        .field {
            &:nth-child(1) {
                margin-bottom: 20px
            }

            &:nth-child(2) {
                margin-bottom: 10px
            }
        }

        .forget-password-container {
            width: 100%;
            display: flex;
            justify-content: flex-end;
        }

        .entry {
            margin-top: 20px;
        }

        .error-message {
            max-width: 322px;
            text-align: center;
            margin-top: 10px;
            color: #ff8a8a;
            font-weight: 600;
        }

        .register {
            margin-top: 20px;
        }
    }
</style>
