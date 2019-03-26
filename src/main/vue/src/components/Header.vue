<template>
    <main>
        <header class="container">
            <router-link to="/">
                <h1>Party <b>Sound</b></h1>
            </router-link>
            <div class="right-menu">
                <router-link to="/authorization">
                    <Button label="Вход" type="light" />
                </router-link>
                <router-link to="/registration">
                    <Button label="Регистрация" type="light" />
                </router-link>
            </div>
        </header>
        <router-view />
        <Player />
    </main>
</template>

<script>
import Button from './Button';
import Player from './Player';

export default {
    name: 'Header',
    components: { Button, Player }
};
</script>

<style scoped>
    main {
        display: flex;
        flex-direction: column;
        align-items: center;
    }

    header {
        background: darkslategrey; /*временно, чтобы было видно белые кнопки*/
        display: flex;
        width: 100%;
        align-items: center;
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
    }

</style>
