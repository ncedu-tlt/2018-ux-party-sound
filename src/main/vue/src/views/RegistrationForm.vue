<template>
    <main class="content">
        <div class="container">
            <TextInput class="child" placeholder="E-mail"/>
            <TextInput class="child" placeholder="Логин"/>
            <TextInput class="child" placeholder="Имя"/>
            <TextInput class="child" placeholder="Пароль" type="password"/>
            <TextInput class="child" placeholder="Введите пароль еще раз" type="password"/>
            <Button class="child" label="Зарегистрироваться"/>
            <Button class="child" label="Войти" type="light"/>
        </div>
    </main>
</template>

<script>
    import TextInput from "../components/TextInput";
    import Button from "../components/Button";

    export default {
        name: "RegistrationForm",
        components: {Button, TextInput}
    }
</script>

<style scoped>
    .content {
        height: 100vh;
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