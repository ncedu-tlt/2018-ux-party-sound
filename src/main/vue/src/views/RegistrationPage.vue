<template>
    <div class="content">
        <TheRegistrationForm />
    </div>
</template>

<script>
import TheRegistrationForm from '../components/TheRegistrationForm';

export default {
    name: 'RegistrationPage',
    components: { TheRegistrationForm }
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
</style>
