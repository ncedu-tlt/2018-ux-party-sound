<template>
    <div class="content">
        <div class="container">
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
            <div class="response-message error-js"/>
            <div class="success-response" :class="{'is-visible' : isSuccessfully===true}">Вы успешно зарегистрировались!</div>
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
            secondPassword: '',
            isSuccessfully: false
        };
    },
    methods: {
        validation() {
            if (this.email.length === 0 || this.login.length === 0 || this.name.length === 0 || this.firstPassword.length === 0 || this.secondPassword.length === 0) {
                document.getElementsByClassName('error-js')[0].innerHTML = 'Все поля формы должны быть заполнены!';
                this.isSuccessfully = false;
            } else {
                if (this.firstPassword.length >= 8) {
                    if (this.firstPassword !== this.secondPassword){
                        document.getElementsByClassName('error-js')[0].innerHTML = 'Пароли не совпадают!';
                        this.isSuccessfully = false;
                    } else {
                        this.setClientInfo();
                    }
                } else {
                    document.getElementsByClassName('error-js')[0].innerHTML = 'Пароль слишкм короткий! Введите минимум 8 символов';
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
                mail: 'E-mail',
                password: 'Пароль ',
                login: 'Логин',
                Size: ' должен быть от 8 до 32 символов',
                Duplicate: ' уже занят',
                Invalid: ' не корректный'
            };
            let message = '';
            for (let i = 0; i < response.length; i++) {
                message = message + ' ' + wordForMessage[response[i].field] + wordForMessage[response[i].code];
                document.getElementsByClassName('error-js')[0].innerHTML = message;
            }
            if (response.length === 0) {
                this.isSuccessfully = true;
                document.getElementsByClassName('error-js')[0].innerHTML = '';
            } else {
                this.isSuccessfully = false;
            }
        }
    }
};
</script>

<style lang="scss" scoped>
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
