<template>
    <div class="authorization">
        <AuthorizationForm />
    </div>
</template>

<script>
import AuthorizationForm from '../components/TheAuthorizationForm';

export default {
    name: 'AuthorizationPage',
    components: { AuthorizationForm }
};
</script>

<style lang="scss" scoped>
    .authorization {
        width: 100%;
        height: 89vh;
        display: flex;
        justify-content: center;
    }
</style>
