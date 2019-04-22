<template>
    <main>
        <header>
            <div class="content container">
                <router-link to="/">
                    <h1>Party <b>Sound</b></h1>
                </router-link>
                <div v-if="authorized === false" class="right-menu">
                    <router-link to="/authorization">
                        <Button label="Вход" type="light" />
                    </router-link>
                    <router-link to="/registration">
                        <Button label="Регистрация" type="light" />
                    </router-link>
                </div>
                <AuthorizedUser v-else />
            </div>
        </header>
        <router-view />
        <Player />
    </main>
</template>

<script>
import Button from './Button';
import Player from './Player';
import AuthorizedUser from './AuthorizedUser';

export default {
    name: 'Header',
    components: { AuthorizedUser, Button, Player },
    computed: {
        authorized() {
            return this.$store.getters.IS_AUTHORIZED;
        }
    },
    created() {
        this.$store.dispatch('GET_USER_INFO');
    }
};
</script>

<style scoped>
    main {
        display: flex;
        flex-direction: column;
        align-items: center;
    }

    header {
        display: flex;
        width: 100%;
        height: 10vh;
        align-items: center;
        justify-content: center;
    }
    .content{
        width: 100%;
        display: flex;
        justify-content: space-between;
    }
    a{
        text-decoration: none;
    }
    h1 {
        color: white;
        left: 0;
        font-weight: 300;
        font-size: 40px;
    }
    .right-menu {
        display: flex;
        align-items: center;
        justify-content: space-between;
        width: 170px;
    }

</style>
