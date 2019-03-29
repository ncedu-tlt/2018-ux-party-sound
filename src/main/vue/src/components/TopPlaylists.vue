<template>
    <section class="top-list">
        <h2>Топ - 5</h2>
        <div class="top-list__playlists">
            <PlaylistElement
                v-for="prop in fakeProps"
                :key="prop.playlistId"
                :playlist-id="prop.playlistId"
                :playlist-name="prop.playlistName"
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
    name: 'TopPlaylists',
    components: {
        PlaylistElement
    },
    data: function () {
        return {
            fakeProps: fakeData.filter((elem, index) => {
                return index < 5;
            })
        };
    }
};
</script>
<style scoped lang="scss">
    .top-list {
        h2 {
            font-size: 36px;
            font-weight: 400;
            color: white;
            text-shadow: 1px 1px 6px rgba(150, 150, 150, 1);
        }

        .playlist {
            margin-bottom: 20px;
        }
    }
</style>
