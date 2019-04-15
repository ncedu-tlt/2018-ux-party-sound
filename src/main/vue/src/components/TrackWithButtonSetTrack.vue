<template>
    <div class="track_with_plus">
        <Track
            :id="id"
            :artist-name="artistName"
            :track-name="name"
            :duration="duration"
            @click.native="playOrStopTrack"
        />
        <button class="plus" @click="addTrackInPlaylist">
            +
        </button>
    </div>
</template>

<script>
import Track from './TrackForPlayer';

export default {
    name: 'TrackWithButtonSetTrack',
    components: {
        Track
    },
    props: {
        id: {
            type: Number,
            default: 0
        },
        name: {
            type: String,
            default: ''
        },
        artistName: {
            type: String,
            default: ''
        },
        duration: {
            type: Number,
            default: 0
        }
    },
    computed: {
        tracks() {
            return this.$store.getters.TRACKS_FROM_JAMENDO;
        },
        activeTrack() {
            return this.$store.getters.ACTIVE_TRACK;
        },
        playing() {
            return this.$store.getters.IS_PLAYING;
        }
    },
    methods: {
        playOrStopTrack() {
            if (this.id === Number(this.activeTrack.id)) {
                this.$store.commit('SET_PLAYING', !this.playing);
            } else {
                this.$store.commit('SET_LIST_FORM_JAMENDO', { tracks: this.tracks, trackId: this.id });
            }
        },
        addTrackInPlaylist() {
            alert('Ну тут будет добален трек с id ' + this.id);
        }
    }
};
</script>

<style scoped lang="scss">

    .track_with_plus{
        display: grid;
        grid-template-columns: 1fr 40px;
        .plus {
            width: 100%;
            height: 100%;
            padding: 0;
            display: flex;
            text-align: center;
            justify-content: center;
            font-size: 20px;
            font-weight: bold;
            border: none;
            background: transparent;
            outline: none;
            cursor: pointer;
        }
        span {
            text-overflow: ellipsis;
        }
    }
</style>
