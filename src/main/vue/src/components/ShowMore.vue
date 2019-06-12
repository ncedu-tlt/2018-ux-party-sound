<template>
    <button v-if="showButton" class="show-more" @click="getPlaylists()">
        Показать еще
    </button>
</template>
<script>
import { mapActions, mapState } from 'vuex';

export default {
    name: 'ShowMore',
    computed: {
        ...mapState('playlists', [
            'playlistsArray'
        ]),
        showButton: function () {
            return this.playlistsArray.length % 15 === 0;
        }
    },
    methods: {
        ...mapActions('playlists', [
            'GET_NEW_PAGE'
        ]),
        getPlaylists: function () {
            this.GET_NEW_PAGE();
        }
    }
};
</script>
<style scoped lang="scss">
.show-more{
    margin-top: 100px;
    font-size: 28px;
    border: none;
    background: transparent;
    cursor: pointer;
    outline: none;
}
</style>
