<template>
    <div class="content">
        <div class="container">
            <TextInput class="child" placeholder="E-mail" :value="email" />
            <TextInput class="child" placeholder="Логин" :value="login" />
            <TextInput class="child" placeholder="Имя" :value="name" />
            <TextInput class="child" placeholder="Пароль" type="password" :value="firstPassword" />
            <TextInput class="child" placeholder="Введите пароль еще раз" type="password" :value="secondPassword" />
            <Button class="child" label="Зарегистрироваться" />
            <router-link to="/authorization">
                <Button class="child" label="Войти" type="light" />
            </router-link>
        </div>
    </div>
</template>

<script>
import TextInput from '../components/TextInput';
import Button from '../components/Button';

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
    }
};
</script>

<style scoped>
    .content {
        height: 100vh;
        width: 100%;
        background: slateblue;  /*временно, пока нет фоновой картинки*/
        display: flex;
        align-items: center;
        justify-content: center;
    }

    .container {
        display: flex;
        flex-direction: column;
        align-items: center;
    }
    .child{
        margin-bottom: 20px;
    }
    .child:nth-child(5){
        margin-bottom: 30px;
    }
</style>
