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
            fill="#FF7F23"
        />
        <rect
            x="29"
            y="8"
            width="10"
            height="40"
            fill="#FF7F23"
        />
    </svg>
    <svg v-else width="50" height="55" @click="onPlaylistClick(playlistId)">
        <linearGradient :id="linearGradientId">
            <stop
                v-for="(fill, index) in makeColorsArray"
                :key="index"
                :offset="fill.offset"
                :stop-color="fill.color"
            />
        </linearGradient>
        <polygon points="0,0 0,55 50,27" :fill="linearGradientUrl" />
    </svg>
</template>

<script>
import genresColors from './../../constants/genresColors';
import { green, darkBlue } from '@/constants/colors';

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
        makeColorsArray: function () {
            let existingGenresColors = genresColors.filter(genreColor => {
                return this.genres.some(genre => {
                    return genreColor.genre === genre.name;
                });
            });

            const RANGE = existingGenresColors.length === 1 ? 100 : 100 / (existingGenresColors.length - 1);
            let offset = -1 * RANGE;

            if (existingGenresColors.length === 0) {
                return [
                    { color: darkBlue, offset: 0 },
                    { color: green, offset: 100 }
                ];
            } else {
                return existingGenresColors.map(genreColor => {
                    offset += RANGE;
                    return { color: genreColor.color, offset: offset + '%' };
                });
            }
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
