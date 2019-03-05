<template>
    <div class="content">
        <div class="container">
            <TextInput class="field" placeholder="E-mail или логин" type="text" :value="email"/>
            <TextInput class="field" placeholder="Пароль" type="password"  :value="password"/>
            <Button class="forget-password" label="Забыли пароль?" type="light"/>
            <Button class="entry" label="Вход"/>
            <Button class="register" label="Зарегистрироваться" type="light"/>
        </div>
    </div>
</template>

<script>
    import TextInput from "../components/TextInput";
    import Button from "../components/Button";

    export default {
        name: "AuthorizationForm",
        components: {Button, TextInput},
        data() {
            return {
                email: "",
                password:""
            }
        }
    }
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