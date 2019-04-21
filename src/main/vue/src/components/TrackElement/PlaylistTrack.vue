<template>
    <div class="playlist-track">
        <TrackButton :track-name="trackName" :index="index" />
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
