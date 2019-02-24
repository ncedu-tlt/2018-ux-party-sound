<template>
    <section class="top-list">
        <h2>Топ - 5</h2>
        <div class="top-list__playlists">
            <PlaylistElement v-for="prop in fakeProps"
                             :key="prop.playlistId"
                             :playlist-id="prop.playlistId"
                             :playlist-name="prop.playlistName"
                             :color="prop.color"
                             class="playlist" />
        </div>
    </section>
</template>
<script>
import PlaylistElement from './PlaylistElement/PlaylistElement.vue';

const fakeData = [
    {
        playlistId: 0,
        playlistName: 'Название',
        color: '#D96DFF'
    },
    {
        playlistId: 1,
        playlistName: 'Название',
        color: '#FFF500'
    },
    {
        playlistId: 2,
        playlistName: 'Название',
        color: '#FF7F23'
    },
    {
        playlistId: 3,
        playlistName: 'Название',
        color: '#D96DFF'
    },
    {
        playlistId: 4,
        playlistName: 'Название',
        color: '#C1C1C1'
    }
];

export default {
    name: 'TopPlaylists',
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
