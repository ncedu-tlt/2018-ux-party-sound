<template>
    <svg
        v-if="isPlaying"
        width="50"
        height="55"
        viewBox="0 0 50 55"
        fill="none"
        xmlns="http://www.w3.org/2000/svg"
        @click="onPlaylistClick(playlistId)"
    >
        <rect width="50" height="55" />
        <rect
            x="10"
            y="8"
            width="10"
            height="40"
            :fill="generatedColor"
        />
        <rect
            x="29"
            y="8"
            width="10"
            height="40"
            :fill="generatedColor"
        />
    </svg>
    <svg v-else width="50" height="55" @click="onPlaylistClick(playlistId)">
        <polygon points="0,0 0,55 50,27" :fill="generatedColor" />
    </svg>
</template>

<script>
import genresColors from './../../constants/genresColors';

export default {
    name: 'TriangleButton',
    props: {
        playlistId: {
            type: Number,
            required: true
        },
        genres: {
            type: Array,
            default: function () {
                return [''];
            }
        }
    },
    data: function () {
        return {
            linearGradientId: 'linear-gradient-' + this.playlistId,
            linearGradientUrl: `url(#linear-gradient-${this.playlistId})`
        };
    },
    computed: {
        isPlaying: function () {
            return this.playlistId === this.$store.getters.PLAYLIST_ID && this.$store.getters.IS_PLAYING;
        },
        generatedColor: function () {
            let index = Math.floor(Math.random() * 8);
            return genresColors[index].color;
        }
    },
    methods: {
        onPlaylistClick: function (playlistId) {
            this.$emit('on-playlist-clicked', playlistId);
        }
    }
};
</script>

<style scoped lang="scss">
    svg {
        cursor: pointer;
        transition: opacity 300ms;

        &:hover {
            opacity: 0.8;
            transition: opacity 300ms;
        }
    }
</style>
