<template>
    <form class="container">
        <TextInput v-model="email" class="child" placeholder="E-mail" class-name="input-field" />
        <TextInput v-model="login" class="child" placeholder="Логин" class-name="input-field" />
        <TextInput v-model="name" class="child" placeholder="Имя" class-name="input-field" />
        <TextInput
            v-model="firstPassword"
            class="child"
            placeholder="Пароль"
            type="password"
            class-name="input-field"
        />
        <TextInput
            v-model="secondPassword"
            class="child"
            placeholder="Введите пароль еще раз"
            type="password"
            class-name="input-field"
        />
        <Button class="child" label="Зарегистрироваться" @click.native="validation" />
        <RegistrationFormErrorMessages
            :type="errorType"
        />
        <div class="success-response" :class="{'is-visible' : isSuccessfully===true}">
            Вы успешно зарегистрировались!
        </div>
        <router-link to="/authorization">
            <Button class="child" label="Войти" type="light" />
        </router-link>
    </form>
</template>

<script>
import TextInput from './BaseInput';
import Button from './BaseButton';
import RegistrationFormErrorMessages from './TheRegistrationFormErrorMessages';
import { registration } from '../api/rest/authentication.api';

export default {
    name: 'TheRegistrationForm',
    components: { Button, TextInput, RegistrationFormErrorMessages },
    data() {
        return {
            email: '',
            login: '',
            name: '',
            firstPassword: '',
            secondPassword: '',
            isSuccessfully: false,
            errorType: 'success'
        };
    },
    methods: {
        validation() {
            if (this.email.length === 0 || this.login.length === 0 || this.name.length === 0 || this.firstPassword.length === 0 || this.secondPassword.length === 0) {
                this.errorType = 'empty';
                this.isSuccessfully = false;
            } else {
                if (this.firstPassword.length >= 8) {
                    if (this.firstPassword !== this.secondPassword) {
                        this.errorType = 'incorrectPassword';
                        this.isSuccessfully = false;
                    } else {
                        this.setClientInfo();
                    }
                } else {
                    this.errorType = 'shortPassword';
                    this.isSuccessfully = false;
                }
            }
        },
        async setClientInfo() {
            const response = await registration({
                login: this.login,
                password: this.firstPassword,
                mail: this.email,
                name: this.name
            });
            let wordForMessage = {
                mail: 'email',
                password: 'password',
                login: 'login',
                Size: 'IsShort',
                Duplicate: 'AlreadyExist',
                Invalid: 'Invalid'
            };
            for (let i = 0; i < response.length; i++) {
                this.errorType = wordForMessage[response[i].field] + wordForMessage[response[i].code];
            }
            if (response.length === 0) {
                this.isSuccessfully = true;
                this.errorType = 'success';
            } else {
                this.isSuccessfully = false;
            }
        }
    }
};
</script>

<style lang="scss" scoped>

    .container {
        display: flex;
        flex-direction: column;
        align-items: center;
    }

    .child {
        margin-bottom: 20px;
    }
    .response-message{
        margin-bottom: 10px;
        color: #ff8a8a;
        font-weight: 600;
    }
    .success-response{
        display: none;
        margin-bottom: 10px;
        color: white;
        background: #31ad54;
        border-radius: 10px;
        padding: 20px;
        font-weight: 600;
        &.is-visible{
            display: flex;
        }
    }
    .child:nth-child(5) {
        margin-bottom: 30px;
    }
</style>
