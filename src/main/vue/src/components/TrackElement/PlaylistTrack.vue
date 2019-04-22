<template>
    <div class="playlist-track">
        <TrackButton :track-name="trackName" :index="index" :track-id="trackId" @click.native="clickOnTrack" />
        <div v-if="canDeleteTrack" class="minus">
            X
        </div>
    </div>
</template>
<script>
import TrackButton from './TrackButton';
export default {
    name: 'PlaylistTrack',
    components: { TrackButton },
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
        },
        canDeleteTrack: {
            type: Boolean,
            default: false
        }
    },
    methods: {
        clickOnTrack: function () {
            this.$emit('click-on-track', this.trackId);
        }
    }
};
</script>

<style lang="scss" scoped>
    .playlist-track {
        display: flex;
        align-items: center;
        justify-content: space-between;
        margin: 10px 0;
        .minus {
            cursor: pointer;
            font-weight: 900;
        }
        .delete-track{
            height: 25px;
            opacity: .5;
            cursor: pointer;
            &:hover{
                transition: 500ms;
                opacity: 1;
            }
        }
    }
</style>
