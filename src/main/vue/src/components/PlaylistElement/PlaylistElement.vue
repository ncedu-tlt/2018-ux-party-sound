<template>
    <div class="playlist-element">
        <TriangleButton :playlist-id="playlistId" :color="color" @on-playlist-clicked="parentMethod" />
        <router-link :to="{ path: 'playlist/'+playlistId }" class="playlist-element__info">
            <h4>{{ playlistName }}</h4>
            <div v-if="showGenres" class="genres-list">
                <span v-for="(genre, index) in genreColor" :key="index" :style="{backgroundColor: genre.color.color}">
                    {{ genre.name }}
                </span>
            </div>
        </router-link>
    </div>
</template>

<script>
import TriangleButton from './TriangleButton';
import genresColors from '@/constants/genresColors';

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
    computed: {
        genreColor: function () {
            return this.genres.map(genre => {
                return { name: genre.name,
                    color: genresColors.filter(color => {
                        return genre.name === color.genre;
                    })[0] };
            });
        }
    },
    methods: {
        parentMethod: function (playlistId) {
            if (this.$store.getters.PLAYLIST_ID !== playlistId) {
                this.$store.dispatch('GET_ACTIVE_PLAYLIST', playlistId);
            } else {
                this.$store.commit('SET_PLAYING', !this.$store.getters.IS_PLAYING);
            }
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
            text-decoration: none;
            color: #000;
            margin-left: 20px;
            h4 {
                margin: 0;
                font-size: 20px;
                font-weight: 400;
                line-height: 28px;
            }

            .genres-list {
                max-width: 230px;
                max-height: 50px;
                overflow-y: hidden;
                display: flex;
                flex-wrap: wrap;
                span{
                    background-color: #008800;
                    padding: 2px 5px;
                    border-radius: 10px;
                    color: white;
                    font-weight: lighter;
                    margin-right: 8px;
                    margin-bottom: 5px;
                    font-size: 14px;
                }
            }
        }
    }
</style>
