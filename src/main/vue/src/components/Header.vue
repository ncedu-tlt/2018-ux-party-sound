<template>
    <main>
        <header>
            <div class="content container">
                <router-link to="/" class="header-logo">
                    <div class="logotype" />
                    <h2>Party <b>Sound</b></h2>
                </router-link>
                <div class="right-menu">
                    <router-link to="/authorization">
                        <Button label="Вход" type="light" />
                    </router-link>
                    <router-link to="/registration">
                        <Button label="Регистрация" type="light" />
                    </router-link>
                </div>
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

    .header-logo{
        display: flex;
        align-items: center;
    }

    h2{
        font-family: 'Arvo', serif;
        color: white;
        font-weight: 500;
        font-size: 32px;
        margin-left: 15px;
    }

    .logotype{
        width: 70px;
        height: 45px;
        background: url("./../assets/sound.svg") center no-repeat;
        background-size: contain;
    }
    .right-menu {
        display: flex;
        align-items: center;
        justify-content: space-between;
        width: 170px;
        margin-left: auto;
    }

</style>
