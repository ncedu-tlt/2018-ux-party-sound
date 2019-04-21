<template>
    <div class="container playlist-page">
        <div class="playlist-page">
            <h1><span>Плейлист</span> {{ playlist.name }}</h1>
            <div v-for="(track, index) in playlist.tracks" :key="index">
                <PlaylistTrack @click-on-track="setPlaylistAndTrack" :track-name="track.name" :index="index" :track-id="Number(track.id)" />
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
    },
    computed: {
        playing: function () {
            return this.$store.getters.IS_PLAYING;
        },
        activeTrack: function () {
            return this.$store.getters.ACTIVE_TRACK;
        },
        playlistId: function () {
            return this.$store.getters.PLAYLIST_ID;
        }
    },
    methods: {
        async getTracks() {
            const response = await getTracksByPlaylistId(
                this.$route.params.id
            );
            return response;
        },
        async setPlaylistAndTrack(trackId) {
            if (Number(this.$route.params.id) === Number(this.playlistId)) {
                if (Number(trackId) === Number(this.activeTrack.id)) {
                    this.$store.commit('SET_PLAYING', !this.playing);
                } else {
                    this.$store.commit('SET_ACTIVE_TRACK_BY_ID', trackId);
                }
            } else {
                this.$store.commit('SET_ACTIVE_PLAYLIST', {
                    ...this.playlist,
                    playlistId: Number(this.$route.params.id)
                });
                this.$store.commit('SET_ACTIVE_TRACK_BY_ID', trackId);
            }
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
