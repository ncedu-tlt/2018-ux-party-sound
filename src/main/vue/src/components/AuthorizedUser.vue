<template>
    <div class="authorized-user">
        <div class="static" v-on:click="isOpen=!isOpen">
            <img class="photo" :src="photo"/>
            <div class="name">{{name}}</div>
            <div class="arrow" v-bind:class="{'open-arrow':isOpen===true}"/>
        </div>
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
        },
        props:{
          name:{
              type:String,
              required:true
          },
          photo:{
              type: String,
              required: true
          }
        }
    }
</script>

<style scoped>
    .authorized-user{
        position: relative;

    }
    .static{
        cursor: pointer;
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
        right: 0;
        background: white;
        border-radius: 7px;
        padding: 10px;
        height: 50px;
        width: 100px;
        display: flex;
        flex-direction: column;
        justify-content: space-around;
        box-shadow:1px 2px 12px 0 rgba(0,0,0,0.49);
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