<template>
    <section class="playlists-section container">
        <button class="settings-button" type="button">
            <img src="./../assets/settings.png" alt="settings">
        </button>
        <div class="playlists-list">
            <PlaylistElement
                v-for="prop in fakeProps"
                :key="prop.playlistId"
                :playlist-id="prop.playlistId"
                :playlist-name="prop.playlistName"
                :color="prop.color"
                :genres="prop.genres"
                class="playlist"
            />
        </div>
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
    .playlists-section{
        width: 100%;

        .settings-button{
            margin-top: 125px;
            width: 60px;
            height: 60px;
            border: none;
            background: transparent;
            outline: none;
            cursor: pointer;

            img{
                width: 60px;
                height: 60px;
            }
        }

        .playlists-list {
            margin-top: 80px;
            display: grid;
            grid-template-columns: repeat(3, 300px);
            justify-content: space-between;

            .playlist {
                margin-top: 20px;
            }
        }
    }
</style>
