<template>
    <div class="container playlist-page">
        <div class="playlist-page">
            <h1><span>Плейлист</span> {{ playlist.name }}</h1>
            <div v-for="(track, index) in playlist.tracks" :key="index">
                <PlaylistTrack
                    :can-delete-track="right.deleteTrack"
                    :track-name="track.name"
                    :index="index"
                    :track-id="Number(track.id)"
                    @click-on-track="setPlaylistAndTrack"
                    @click-on-minus="emitDeleteTrack"
                />
            </div>
            <div v-if="right.addTrack" class="add_tracks" @click="isOpenFindTracks = true">
                Добавить треки
            </div>
            <div v-if="isOpenFindTracks" class="window" :class="{'full': isOpenFindTracks === true}">
                <div v-if="isOpenFindTracks" class="close" @click="isOpenFindTracks = false">
                    Х
                </div>
                <FindTracksFromJamendo @click-on-track="addTrack" v-if="isOpenFindTracks" class="tracks_jamendo_list"/>
            </div>
        </div>
    </div>
</template>

<script>
import { getTracksByPlaylistIdWithRight, deleteTrack, addTrackInPlaylist } from '../api/rest/tracks.api';
import PlaylistTrack from '../components/TrackElement/PlaylistTrack';
import FindTracksFromJamendo from '../components/FindTracksFromJamendo';

export default {
    name: 'PlaylistPage',
    components: { PlaylistTrack, FindTracksFromJamendo },
    data: function () {
        return {
            playlist: Object,
            right: Object,
            isOpenFindTracks: false
        };
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
        },
        tracksInSearchJamendo: function () {
            return this.$store.getters.TRACKS_FROM_JAMENDO;
        }
    },
    async created() {
        const tracksWithRight = await this.getTracksWithRight();
        this.playlist = tracksWithRight.playlistsWithTracksDTO;
        this.right = tracksWithRight.rolesDTO;
    },
    methods: {
        async addTrack(id) {
            console.log(this.tracksInSearchJamendo);
            for (let index in this.tracksInSearchJamendo) {
                if (Number(this.tracksInSearchJamendo[index].id) === Number(id)) {
                    const track = this.tracksInSearchJamendo[index];
                    const q = await addTrackInPlaylist({
                        playlistId: this.$route.params.id,
                        track: {
                            id: track.id,
                            name: track.name,
                            artistId: track.artist_id,
                            artistName: track.artist_name,
                            albumName: track.album_name,
                            albumId: track.album_id,
                            url: track.audio,
                            genresString: track.musicinfo.tags.genres
                        }
                    });
                    console.log(q);
                    if (q) {
                        this.playlist.tracks = this.playlist.tracks.concat(track);
                        this.$store.commit('ADD_TRACK_IN_PLAYLIST', track);
                    }
                }
            }
        },
        async emitDeleteTrack(trackId) {
            const isDelete = await deleteTrack({
                playlistId: this.$route.params.id,
                trackId: trackId
            });
            if (isDelete) {
                for (let i = 0; i < this.playlist.tracks.length; i++) {
                    if (Number(this.playlist.tracks[i].id) === Number(trackId)) {
                        this.playlist.tracks.splice(i, 1);
                        break;
                    }
                }
                this.$store.commit('DELETE_TRACK_BY_ID', trackId);
            }
        },
        async getTracksWithRight() {
            const response = await getTracksByPlaylistIdWithRight(
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
            .window {
                display: block;
                position: fixed;
                top: 0;
                left: 0;
                background-color: white;
                width: 100%;
                height: 0;
                transition: 1s;
                .tracks_jamendo_list {
                    width: 500px;
                    height: calc(100% - 150px);
                    margin: 0 auto;
                }
                .close{
                    margin-left: calc(100% - 50px);
                    margin-top: 10px;
                    cursor: pointer;
                    color: black;
                    font-weight: 900;
                    font-size: 30px;
                }
            }
            .full {
                height: 100%;
            }
            .add_tracks {
                cursor: pointer;
                margin: 0 auto;
                width: 200px;
                height: 50px;
                background-color: blue;
                color: white;
                display: flex;
                align-items: center;
                justify-content: center;
            }
            h1 {
                font-weight: 100;
            }
            span {
                color: white;
            }
        }
    }
</style>
