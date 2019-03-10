<template>
    <button class="show-more" @click="getPlaylists()">
        Показать еще
    </button>
</template>
<script>
export default {
    name: 'ShowMore',
    methods: {
        getPlaylists: function () {
            this.$store.dispatch('POPULATE_PLAYLISTS');
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
