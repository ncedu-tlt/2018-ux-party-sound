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
        <TheAuthorizationFormErrorMessages :type="errorType" />
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
import TheAuthorizationFormErrorMessages from './TheAuthorizationFormErrorMessages';

export default {
    name: 'AuthorizationForm',
    components: { TheAuthorizationFormErrorMessages, Button, TextInput },
    data() {
        return {
            login: '',
            password: '',
            errorType: 'success'
        };
    },
    methods: {
        ...mapActions('user', [
            'GET_USER_INFO'
        ]),
        validation() {
            if (this.login.length === 0 || this.password.length === 0) {
                this.errorType = 'isEmpty';
            } else {
                this.setClientInfo();
            }
        },
        async setClientInfo() {
            let data = new FormData();
            data.append('username', this.login);
            data.append('password', this.password);
            let response = await authorization(data);
            if (response === undefined) {
                this.errorType = 'notFound';
            } else {
                this.GET_USER_INFO();
                this.errorType = 'success';
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

        .register {
            margin-top: 20px;
        }
    }
</style>
