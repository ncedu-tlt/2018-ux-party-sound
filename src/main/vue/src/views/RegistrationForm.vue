<template>
    <div class="content">
        <div class="container">
            <TextInput class="child" placeholder="E-mail" class-name="input-field" :value="email" />
            <TextInput class="child" placeholder="Логин" class-name="input-field" :value="login" />
            <TextInput class="child" placeholder="Имя" class-name="input-field" :value="name" />
            <TextInput
                class="child"
                placeholder="Пароль"
                type="password"
                class-name="input-field"
                :value="firstPassword"
            />
            <TextInput
                class="child"
                placeholder="Введите пароль еще раз"
                type="password"
                class-name="input-field"
                :value="secondPassword"
            />
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
    .child{
        margin-bottom: 20px;
    }
    .child:nth-child(5){
        margin-bottom: 30px;
    }
</style>
