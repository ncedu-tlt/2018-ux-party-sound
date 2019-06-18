<template>
    <div>
        <div v-if="type === 'isEmpty'" class="error-message">
            'Все поля должны быть заполнены!'
        </div>
        <div v-if="type === 'notFound'" class="error-message">
            'Пользователь, с введенными данными, не найден'
        </div>
    </div>
</template>

<script>
export default {
    name: 'TheAuthorizationFormErrorMessages',
    props: {
        type: {
            type: String,
            default: 'success'
        }
    }
};
</script>

<style lang="scss" scoped>
    .error-message {
        max-width: 322px;
        text-align: center;
        margin-top: 10px;
        color: #ff8a8a;
        font-weight: 600;
    }
</style>
