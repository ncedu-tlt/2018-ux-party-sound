<template>
    <div class="container playlist-page">
        <div class="playlist-page">
            <h1><span>Плейлист</span> {{ playlist.name }}</h1>
            <div v-for="(track, index) in playlist.tracks" :key="index">
                <PlaylistTrack :track-name="track.name" :index="index" />
            </div>
        </div>
    </div>
</template>

<script>
import { getTracksByPlaylistIdWithRight } from '../api/rest/tracks.api';
import PlaylistTrack from '../components/TrackElement/PlaylistTrack';

export default {
    name: 'PlaylistPage',
    components: { PlaylistTrack },
    data: function () {
        return {
            playlist: Object,
            right: Object
        };
    },
    async created() {
        const tracksWithRight = await this.getTracksWithRight();
        this.playlist = tracksWithRight.playlistsWithTracksDTO;
        this.right = tracksWithRight.rolesDTO;
    },
    methods: {
        async getTracksWithRight() {
            const response = await getTracksByPlaylistIdWithRight(
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
        min-height: 89vh;
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
