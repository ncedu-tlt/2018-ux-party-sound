<template>
    <div class="error-message">
        <div v-if="type === 'empty'" class="response-message">
            Все поля формы должны быть заполнены!
        </div>
        <div v-if="type === 'incorrectPassword'" class="response-message">
            Пароли не совпадают!
        </div>
        <div v-if="type === 'shortPassword'" class="response-message">
            Пароль должен содержать от 8 символов
        </div>
        <div v-if="type === 'loginIsShort'" class="response-message">
            Логин должен быть от 8 до 32 символов
        </div>
        <div v-if="type === 'loginAlreadyExist'" class="response-message">
            Логин уже занят
        </div>
        <div v-if="type === 'loginInvalid'" class="response-message">
            Логин не корректный
        </div>
        <div v-if="type === 'emailIsShort'" class="response-message">
            E-mail должен быть от 8 до 32 символов
        </div>
        <div v-if="type === 'emailAlreadyExist'" class="response-message">
            E-mail уже занят
        </div>
        <div v-if="type === 'emailInvalid'" class="response-message">
            E-mail не корректный
        </div>
        <div v-if="type === 'passwordIsShort'" class="response-message">
            Пароль должен быть от 8 до 32 символов
        </div>
        <div v-if="type === 'passwordAlreadyExist'" class="response-message">
            Пароль уже занят
        </div>
        <div v-if="type === 'passwordInvalid'" class="response-message">
            Пароль не корректный
        </div>
    </div>
</template>

<script>
export default {
    name: 'RegistrationErrorMessage',
    props: {
        type: {
            type: String,
            default: 'success'
        }
    }
};
</script>

<style scoped lang="scss">
    .response-message{
        margin-bottom: 10px;
        color: #ff8a8a;
        font-weight: 600;
    }
</style>
