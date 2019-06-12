<template>
    <div class="authorized-user">
        <FormForCreatePlaylist v-if="isOpenFormForCreatePlaylist" @close-window="setOpenFormForCreatePlaylist(false)" />
        <div class="static" @click="isOpen=!isOpen">
            <div class="name">
                {{ name }}
            </div>
            <div class="arrow" :class="{ 'open-arrow': isOpen === true }" />
        </div>
        <div class="dropdown-menu" :class="{'open-dropdown-menu':isOpen===true}">
            <div class="item" @click="setOpenFormForCreatePlaylist(true)">
                Создать плейлист
            </div>
            <div class="item logout-js" @click="logoutOfAccount()">
                Выход
            </div>
        </div>
    </div>
</template>

<script>
import { logout } from '../api/rest/authentication.api';
import FormForCreatePlaylist from '../components/FormForCreatePlaylist';
import { mapActions, mapMutations, mapState } from 'vuex';

export default {
    name: 'AuthorizedUser',
    components: { FormForCreatePlaylist },
    data: function () {
        return {
            isOpen: false,
            isOpenFormForCreatePlaylist: false
        };
    },
    computed: {
        ...mapState('user', [
            'name'
        ])
    },
    created() {
        this.GET_USER_INFO();
    },
    methods: {
        ...mapActions('user', [
            'GET_USER_INFO'
        ]),
        ...mapMutations('user', [
            'AUTHORIZED',
            'SET_NAME'
        ]),
        setOpenFormForCreatePlaylist(value) {
            this.isOpenFormForCreatePlaylist = value;
        },
        async logoutOfAccount() {
            const res = await logout();
            if (res.status !== 200) {
                alert('Выйти из профиля не удалось');
            } else {
                this.AUTHORIZED(false);
                this.SET_NAME('');
            }
        }
    }
};
</script>

<style scoped>
    .authorized-user {
        position: relative;
        display: flex;
        align-items: center;
    }

    .static {
        cursor: pointer;
        display: flex;
        align-items: center;
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

    .open-arrow {
        margin-top: 9px;
        transform: rotate(-135deg);
        transition: 400ms;
    }

    .dropdown-menu {
        position: absolute;
        bottom: -50px;
        right: -30px;
        background: white;
        border-radius: 7px;
        padding: 10px;
        height: 50px;
        width: 140px;
        display: flex;
        flex-direction: column;
        justify-content: space-around;
        box-shadow: 1px 2px 12px 0 rgba(0, 0, 0, 0.49);
        opacity: 0;
        pointer-events: none;
        transition: 400ms;
    }

    .open-dropdown-menu {
        transition: 400ms;
        opacity: 1;
        pointer-events: auto;
    }

    .item {
        cursor: pointer;
    }
</style>
