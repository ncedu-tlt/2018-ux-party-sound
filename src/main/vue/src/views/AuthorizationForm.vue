<template>
    <div class="authorization">
        <div class="content">
            <TextInput
                v-model="login"
                class="field"
                placeholder="E-mail или логин"
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
            <div class="error-message"/>
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
            password: '',
        };
    },
    methods: {
        validation() {
            if (this.login.length === 0 || this.password.length === 0) {
                document.getElementsByClassName('error-message')[0].innerHTML = 'Все поля должны быть заполнены!';
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
                document.getElementsByClassName('error-message')[0].innerHTML = 'Пользователь, с введенными данными, не найден';
            }
        }
    }
};
</script>

<style lang="scss" scoped>
    .authorization {
        width: 100%;
        height: 89vh;
        display: flex;
        justify-content: center;
        .content {
            display: flex;
            flex-direction: column;
            align-items: center;
            justify-content: center;
            .field{
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

            .error-message{
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
    }
</style>
