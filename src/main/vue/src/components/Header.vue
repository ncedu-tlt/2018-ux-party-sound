<template>
    <main>
        <header class="container">
            <router-link to="/"><h1>Party <b>Song</b></h1></router-link>
            <div class="right-menu">
                <router-link to="/authorization">
                    <Button label="Вход" type="light"/>
                </router-link>
                <router-link to="/registration">
                    <Button label="Регистрация" type="light"/>
                </router-link>
            </div>
            <div class="right-menu">
                <img class="photo" src=""/>
                <div class="name">Имя</div>
                <div class="arrow"/>
            </div>
        </header>
        <router-view></router-view>
    </main>
</template>

<script>
    import Button from "./Button";

    export default {
        name: "Header",
        components: {Button}
    }
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

    .right-menu:nth-child(2n-1) {
        display: none;
    }

    .photo {
        border-radius: 50%;
        background: aqua;
        height: 50px;
        width: 50px;
        object-fit: cover;
    }

    .name {
        font-size: 18px;
        color: white;
        margin-left: 20px;
    }

    .arrow {
        margin-left: 20px;
        height: 10px;
        width: 10px;
        border-bottom: 2px solid white;
        border-right: 2px solid white;
        transform: rotate(45deg);
    }
</style>