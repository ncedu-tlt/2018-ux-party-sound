<template>
    <button v-if="showButton" class="show-more" @click="getPlaylists()">
        Показать еще
    </button>
</template>
<script>
export default {
    name: 'ShowMore',
    computed: {
        showButton: function () {
            return this.$store.getters.PLAYLISTS.length % 15 === 0;
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
