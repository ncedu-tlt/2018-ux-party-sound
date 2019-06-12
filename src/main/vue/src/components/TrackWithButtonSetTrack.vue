<template>
    <div class="track_with_plus">
        <Track
            :id="id"
            :artist-name="artistName"
            :track-name="name"
            :duration="duration"
            class="track"
            @click.native="playOrStopTrack"
        />
        <button class="plus" @click="addTrackInPlaylist">
            +
        </button>
    </div>
</template>

<script>
import Track from './TrackForPlayer';
import { mapMutations, mapState } from 'vuex';

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
        ...mapState('player', [
            'tracks',
            'activeTrack',
            'playing'
        ])
    },
    methods: {
        ...mapMutations('player', [
            'SET_PLAYING',
            'SET_LIST_FORM_JAMENDO'
        ]),
        playOrStopTrack() {
            if (this.id === Number(this.activeTrack.id)) {
                this.SET_PLAYING(!this.playing);
            } else {
                this.SET_LIST_FORM_JAMENDO({ tracks: this.tracks, trackId: this.id });
            }
        },
        addTrackInPlaylist() {
            this.$emit('click-on-track', this.id);
        }
    }
};
</script>

<style scoped lang="scss">

    .track_with_plus{
        display: grid;
        grid-template-columns: calc(100% - 40px) 40px;
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
