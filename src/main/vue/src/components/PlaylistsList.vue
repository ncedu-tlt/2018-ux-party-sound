<template>
    <section class="playlists-list container">
        <PlaylistElement
            v-for="prop in fakeProps"
            :key="prop.playlistId"
            :playlist-id="prop.playlistId"
            :playlist-name="prop.playlistName"
            :color="prop.color"
            :genres="prop.genres"
            class="playlist"
        />
    </section>
</template>
<script>
import PlaylistElement from './PlaylistElement/PlaylistElement.vue';
import { fakeData } from './fakeData';

export default {
    name: 'PlaylistsList',
    components: {
        PlaylistElement
    },
    data: function () {
        return {
            fakeProps: fakeData
        };
    }
};
</script>
<style scoped lang="scss">
.playlists-list{
    width: 100%;
    margin-top: 105px;
    display: grid;
    grid-template-columns: repeat(3, 300px);
    justify-content: space-between;

    .playlist{
        margin-top: 20px;
    }
}
</style>
