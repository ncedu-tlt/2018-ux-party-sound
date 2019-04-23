<template>
    <div class="track-button">
        <div v-if="Number($route.params.id) === playlistId && Number(trackId) === Number(activeTrack.id) && playing">
            <svg width="27" height="30">
                <rect height="30" width="7" x="2" />
                <rect height="30" width="7" x="13" />
            </svg>
        </div>
        <div v-else class="stop">
            <svg width="27" height="30">
                <polygon points="0,0 0,30 27,15" />
            </svg>
        </div>
        <div class="track-name">
            {{ trackName }}
        </div>
    </div>
</template>

<script>
export default {
    name: 'TrackButton',
    props: {
        trackName: {
            type: String,
            required: true,
            default: ''
        },
        index: {
            type: Number,
            default: 0
        },
        trackId: {
            type: Number,
            default: 0
        }
    },
    computed: {
        activeTrack() {
            return this.$store.getters.ACTIVE_TRACK;
        },
        playing() {
            return this.$store.getters.IS_PLAYING;
        },
        playlistId() {
            return this.$store.getters.PLAYLIST_ID;
        }
    }
};
</script>

<style lang="scss" scoped>
    $colorButton: rgba(random(255), random(255), random(255), 1);
    .track-button {
        display: flex;
        align-items: center;
        cursor: pointer;
        svg{
            margin-right: 10px;
            polygon{
                fill: $colorButton;
            }
            rect{
                fill: $colorButton;
            }
            &:hover{
                transition: 400ms;
                opacity: .7;
            }
        }
    }
</style>
