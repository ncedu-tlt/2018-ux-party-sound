<template>
    <button v-if="showButton" class="show-more" @click="getPlaylists()">
        Показать еще
    </button>
</template>
<script>
import { mapState } from 'vuex';

export default {
    name: 'ShowMore',
    computed: {
        ...mapState([
            'playlists'
        ]),
        showButton: function () {
            return this.playlists.length % 15 === 0;
        }
    },
    methods: {
        getPlaylists: function () {
            this.$store.dispatch('GET_NEW_PAGE');
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
