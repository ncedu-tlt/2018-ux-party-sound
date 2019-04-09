<template>
    <section class="track center" @click="addTrackInPlaylist(id)">
        <div v-if="id === activeTrack.id && playing" @click="playOrStopTrack()">
            <svg class="player_button">
                <polygon points="10,8 10,21 14,21 14,8" fill="#0C0094" />
                <polygon points="16,8 16,21 20,21 20,8" fill="#0C0094" />
            </svg>
        </div>
        <div v-else class="stop" @click="playOrStopTrack()">
            <svg class="player_button">
                <polygon points="10,8 10,21 24,15" fill="#0C0094" />
            </svg>
        </div>
        <span @click="console.log('Сработал клик')" class="track_name">
            {{ name }}
        </span>
        <div @click.native="addTrackInPlaylist(id)" class="artist_name">
            {{ artistName }}
        </div>
        <div @click.native="addTrackInPlaylist(id)" class="duration">
            {{ durationTime }}
        </div>
        <button  class="plus">
            +
        </button>
    </section>
</template>

<script>
export default {
    name: 'TrackWithButtonSetTrack',
    props: {
        id: {
            type: String,
            default: ''
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
        durationTime() {
            let time = parseInt(this.duration / 60) + ':';
            time += this.duration % 60 < 10 ? '0' : '';
            time += this.duration % 60;
            return time;
        },
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
            if (this.id === this.activeTrack.id) {
                this.$store.commit('SET_PLAYING', !this.playing);
            } else {
                this.$store.commit('SET_LIST_FORM_JAMENDO', { tracks1: this.tracks, trackId: this.id });
            }
        },
        addTrackInPlaylist(id1) {
            console.log('qwe');
            alert('Ну тут будет добален трек с id ' + id1);
        }
    }
};
</script>

<style scoped lang="scss">
    $mainColor: #0C0094;
    $whiteColor: #fff;

    .plus {
        height: 20px;
        width: 20px;
        padding: 0;
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
    .plus_track {
        background-color: $mainColor;
        width: 10px;
        height: 10px;
    }
    .center {
        &::before {
            border-radius: 5px;
            border-top: 2px solid $mainColor;
            border-bottom: 2px solid $mainColor;
            transform: scale3d(0,1,1);
        }

        &::after {
            border-radius: 5px;
            border-left: 2px solid $mainColor;
            border-right: 2px solid $mainColor;
            transform: scale3d(1,0,1);
        }

        &:hover::before,
        &:hover::after {
            transform: scale3d(1,1,1);
            transition: transform 0.5s;
        }
    }
    .track:hover{
        cursor: pointer;
    }
    .track {
        margin-right: 5px;
        box-sizing: border-box;
        position: relative;
        height: 50px;
        display: grid;
        grid-template-columns: 40px 1fr 1.5fr 60px 40px;
        overflow: hidden;

        &::before,
        &::after {
            box-sizing: inherit;
            content: '';
            position: absolute;
            width: 100%;
            height: 100%;
        }

        .player_button {
            cursor: pointer;
            position: relative;
            width: 25px;
            height: 25px;
        }

        .text {
            display: flex;
            align-items: center;
        }
    }
</style>
