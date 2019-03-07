<template>
    <div class="authorized-user">
        <img class="photo" src=""/>
        <div class="name">Имя</div>
        <div class="arrow" v-bind:class="{'open-arrow':isOpen===true}" v-on:click="isOpen=!isOpen"/>
        <div class="dropdown-menu" v-bind:class="{'open-dropdown-menu':isOpen===true}">
            <div class="item">Профиль</div>
            <div class="item">Выход</div>
        </div>
    </div>
</template>

<script>
    export default {
        name: "AuthorizedUser",

        data() {
            return {
                isOpen: false,
            }
        }
    }
</script>

<style scoped>
    .authorized-user{
        position: relative;
        display: flex;
        align-items: center;
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
        cursor: pointer;
        margin-left: 20px;
        height: 10px;
        width: 10px;
        border-bottom: 2px solid white;
        border-right: 2px solid white;
        transform: rotate(45deg);
        margin-bottom: 5px;
        transition: 400ms;
    }
    .open-arrow{
        margin-top: 9px;
        transform: rotate(-135deg);
        transition: 400ms;
    }
    .dropdown-menu{
        position: absolute;
        bottom: -90px;
        background: white;
        border-radius: 7px;
        padding: 10px;
        height: 50px;
        width: 100%;
        display: flex;
        flex-direction: column;
        justify-content: space-around;
        opacity: 0;
        pointer-events: none;
        transition: 400ms;
    }
    .open-dropdown-menu{
        transition: 400ms;
        opacity: 1;
        pointer-events: auto;
    }
    .item{
        cursor: pointer;
    }
</style>