<template>
    <main>
        <header class="container">
            <h1>Party <b>Song</b></h1>
            <div class="right-menu">
                <Button label="Регистрация" type="light"/>
                <Button label="Вход" type="light"/>
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
    .right-menu:nth-child(2n-1){
        display: none;
    }
    .photo{
        border-radius: 50%;
        background: aqua;
        height: 50px;
        width: 50px;
       object-fit: cover;
    }
    .name{
        font-size: 18px;
        color: white;
        margin-left: 20px;
    }
    .arrow{
        margin-left: 20px;
        height: 10px;
        width: 10px;
        border-bottom: 2px solid white;
        border-right: 2px solid white;
        transform: rotate(45deg);
    }
</style>