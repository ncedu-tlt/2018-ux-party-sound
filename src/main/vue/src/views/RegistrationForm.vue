<template>
    <div class="content">
        <div class="container">
            <TextInput class="child" placeholder="E-mail" class-name="input-field" v-model="email" />
            <TextInput class="child" placeholder="Логин" class-name="input-field" v-model="login" />
            <TextInput class="child" placeholder="Имя" class-name="input-field" v-model="name" />
            <TextInput
                class="child"
                placeholder="Пароль"
                type="password"
                class-name="input-field"
                v-model="firstPassword"
            />
            <TextInput
                class="child"
                placeholder="Введите пароль еще раз"
                type="password"
                class-name="input-field"
                v-model="secondPassword"
            />
            <Button class="child" label="Зарегистрироваться" v-on:click.native="setClientInfo"/>
            <router-link to="/authorization">
                <Button class="child" label="Войти" type="light"/>
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
        height: 89vh;
        width: 100%;
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
