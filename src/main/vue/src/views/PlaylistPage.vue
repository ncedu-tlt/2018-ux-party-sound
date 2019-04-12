<template>
    <div class="container playlist-page">
        <div class="playlist-page">
            <h1><span>Плейлист</span> {{playlist.name}}</h1>
            <div v-for="(track, index) in playlist.tracks">
                <PlaylistTrack :track-name="track.name" :index="index"/>
            </div>
        </div>
    </div>
</template>

<script>
import { getTracksByPlaylistId } from '../api/rest/tracks.api';
import PlaylistTrack from '../components/TrackElement/PlaylistTrack';

export default {
    name: 'PlaylistPage',
    components: { PlaylistTrack },
    data: function () {
        return {
            playlist: Object
        };
    },
    async created() {
        this.playlist = await this.getTracks();
        this.$store.commit('SET_ACTIVE_PLAYLIST', this.playlist);
    },
    methods: {
        async getTracks() {
            const response = await getTracksByPlaylistId(
                this.$route.params.id
            );
            return response;
        }
    }
};
</script>

<style lang="scss" scoped>
    .container {
        width: 100%;
        background: #4183c4;

        .playlist-page {
            display: flex;
            flex-direction: column;
            width: 50%;
            margin-left: 20px;

            h1 {
                font-weight: 100;
            }

            span {
                color: white;
            }
        }
    }
</style>
