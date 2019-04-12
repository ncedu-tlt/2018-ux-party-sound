<template>
    <div class="playlist-element">
        <router-link :to="{ path: 'playlist/'+playlistId }">
            <TriangleButton :playlist-id="playlistId" :color="color" @on-playlist-clicked="parentMethod" />
        </router-link>
        <div class="playlist-element__info">
            <h4>{{ playlistName }}</h4>
            <div v-if="showGenres" class="genres-list">
                <span v-for="(genre, index) in genres" :key="index">
                    {{ genre.name }}
                </span>
            </div>
        </div>
    </div>
</template>

<script>
import TriangleButton from './TriangleButton';

export default {
    name: 'PlaylistElement',
    components: {
        TriangleButton
    },
    props: {
        playlistId: {
            type: Number,
            required: true
        },
        playlistName: {
            type: String,
            required: true
        },
        genres: {
            type: Array,
            default: function () {
                return [''];
            }
        },
        color: {
            type: String,
            default: '#D96DFF'
        },
        showGenres: {
            type: Boolean,
            default: true
        }
    },
    methods: {
        parentMethod: function (playlistId) {
            this.$store.dispatch('GET_ACTIVE_PLAYLIST', playlistId);
            // alert(`Начали проигрывать плейлист с id=${playlistId}`);
        }
    }
};
</script>

<style scoped lang="scss">
    .playlist-element {
        width: 300px;
        height: 55px;
        display: flex;
        align-items: center;

        &__info {
            margin-left: 20px;
            h4 {
                margin: 0;
                font-size: 20px;
                font-weight: 400;
                line-height: 28px;
            }

            .genres-list {
                max-width: 230px;
                max-height: 35px;
                overflow-y: hidden;
                display: flex;
                flex-wrap: wrap;

                span{
                    margin-right: 8px;
                    font-size: 14px;
                }
            }
        }
    }
</style>
