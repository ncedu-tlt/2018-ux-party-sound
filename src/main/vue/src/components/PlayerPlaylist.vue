<template>
    <div class="list" :style="{height: maxHeight + 'px'}">
        <div class="playlist_name">
            {{ playlistName }}
        </div>
        <div class="trackBox">
            <Track
                v-for="(track, index) in tracks"
                :id="track.id"
                :key="index"
                :artist-name="track.artistName || track.artist_name"
                :track-name="track.name"
                :duration="track.duration"
            />
        </div>
    </div>
</template>
<script>
import Track from './TrackForPlayer';

export default {
    name: 'PlayerPlaylist',
    components: {
        Track
    },
    props: {
        viewPlaylist: {
            type: Boolean,
            default: false
        }
    },
    computed: {
        playlistName() {
            return this.$store.getters.PLAYLIST_NAME;
        },
        tracks() {
            return this.$store.getters.TRACKS;
        },
        maxHeight() {
            return this.viewPlaylist ? document.documentElement.clientHeight : 0;
        }
    }
};
</script>
<style scoped lang="scss">
    $scrollbarTrackColor: #dae1e8;
    $scrollbarThumbColor: #bec8d3;
    $scrollbarThumbHoverColor:#0C0094;
    ::-webkit-scrollbar{
        &-button {
            height: 0;
            width: 0;
        }
        &-track {
            border-radius: 5px;
            background-color: $scrollbarTrackColor;
        }
        &-thumb {
            border-radius: 5px;
            background-color:$scrollbarThumbColor;
        }
        &-thumb:hover{
            background-color: $scrollbarThumbHoverColor;
        }
        width: 7px;
    }
    .list {
        width: 100%;
        transition: 1s;
        background-color: #fff;

        .playlist_name {
            display: flex;
            align-items: center;
            justify-content: center;
            margin: 0 auto;

            max-width: 800px;
            height: 50px;

            font-size: 20px;
            font-weight: 700;
        }

        .trackBox{
            Track {
                margin-right: 4px;
            }
            overflow-y: auto;
            overflow-x: hidden;
            max-width: 800px;
            margin: 0 auto 70px auto;
        }
    }
</style>
