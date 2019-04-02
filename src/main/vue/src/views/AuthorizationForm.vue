<template>
    <div class="authorization">
        <div class="content">
            <TextInput
                class="field"
                placeholder="E-mail или логин"
                type="text"
                class-name="input-field"
                :value="email"
            />
            <TextInput
                class="field"
                placeholder="Пароль"
                type="password"
                class-name="input-field"
                :value="password"
            />
            <div class="forget-password-container">
                <Button label="Забыли пароль?" type="light" />
            </div>

            <Button class="entry" label="Вход" />
            <router-link to="/registration">
                <Button class="register" label="Зарегистрироваться" type="light" />
            </router-link>
        </div>
    </div>
</template>

<script>
import TextInput from '../components/TextInput';
import Button from '../components/Button';

export default {
    name: 'AuthorizationForm',
    components: { Button, TextInput },
    data() {
        return {
            email: '',
            password: ''
        };
    }
};
</script>

<style scoped>
    .authorization {
        width: 100%;
        height: 90vh;
        display: flex;
        justify-content: center;
    }

    .content {
        display: flex;
        flex-direction: column;
        align-items: center;
        justify-content: center;
    }

    .field:nth-child(1) {
        margin-bottom: 20px
    }

    .field:nth-child(2) {
        margin-bottom: 10px
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
</style>
