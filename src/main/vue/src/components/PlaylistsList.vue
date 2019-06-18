<template>
    <section class="playlists-section container">
        <div class="playlists-list">
            <PlaylistElement
                v-for="(prop, index) in playlistsArray"
                :id="prop.id"
                :key="index"
                :name="prop.name"
                color="blue"
                :genres="prop.genres"
                class="playlist"
            />
        </div>
    </section>
</template>
<script>
import PlaylistElement from './PlaylistElement.vue';
import { mapActions, mapState } from 'vuex';

export default {
    name: 'PlaylistsList',
    components: {
        PlaylistElement
    },
    computed: {
        ...mapState('playlists', [
            'playlistsArray'
        ])
    },
    mounted() {
        let isPlaylistsEmpty = this.playlistsArray.length === 0;
        if (isPlaylistsEmpty) {
            this.POPULATE_PLAYLISTS();
        }
    },
    methods: {
        ...mapActions('playlists', [
            'POPULATE_PLAYLISTS'
        ])
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
