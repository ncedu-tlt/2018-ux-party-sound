<template>
    <svg
        v-if="isPlaying(id)"
        width="50"
        height="55"
        viewBox="0 0 50 55"
        fill="none"
        xmlns="http://www.w3.org/2000/svg"
        @click="playlistClick(id)"
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
    <svg v-else width="50" height="55" @click="playlistClick(id)">
        <polygon points="0,0 0,55 50,27" :fill="generatedColor" />
    </svg>
</template>

<script>
import genresColors from '../constants/genresColors';
import { mapGetters } from 'vuex';

export default {
    name: 'PlaylistElementTriangleButton',
    props: {
        id: {
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
            linearGradientId: 'linear-gradient-' + this.id,
            linearGradientUrl: `url(#linear-gradient-${this.id})`
        };
    },
    computed: {
        ...mapGetters('player', [
            'isPlaying'
        ]),
        generatedColor: function () {
            let index = Math.floor(Math.random() * 8);
            return genresColors[index].color;
        }
    },
    methods: {
        playlistClick: function (id) {
            this.$emit('playlist-click', id);
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
