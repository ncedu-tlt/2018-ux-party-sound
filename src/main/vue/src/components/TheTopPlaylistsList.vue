<template>
    <section class="top-list">
        <h2>Топ - 5</h2>
        <div class="top-list__playlists">
            <PlaylistElement
                v-for="prop in fakeProps"
                :id="prop.playlistId"
                :key="prop.playlistId"
                :name="prop.playlistName"
                :genres="prop.genres"
                :show-genres="false"
                class="playlist"
            />
        </div>
    </section>
</template>
<script>
import PlaylistElement from './PlaylistElement.vue';
import { fakeData } from './fakeData';
import { getTopPlaylists } from '@/api/rest/playlists.api';

export default {
    name: 'TopPlaylists',
    components: {
        PlaylistElement
    },
    data: function () {
        return {
            fakeProps: fakeData.filter((elem, index) => {
                return index < 5;
            }),
            topPlaylists: []
        };
    },
    mounted() {
        if (this.topPlaylists.length === 0) {
            getTopPlaylists()
                .then(res => {
                    this.topPlaylists = res;
                });
        }
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
