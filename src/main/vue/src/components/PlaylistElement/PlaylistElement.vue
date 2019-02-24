<template>
    <div class="playlist-element">
        <TriangleButton :playlist-id="playlistId" :color="color" @on-playlist-clicked="parentMethod" />
        <div class="playlist-element__info">
            <h4>{{ playlistName }}</h4>
            <div class="genres-list">
                <span v-for="genre in genres" :key="genre">{{ genre }}</span>
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
        }
    },
    methods: {
        parentMethod: function (playlistId) {
            alert(`Начали проигрывать плейлист с id=${playlistId}`);
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
                display: flex;
                flex-wrap: wrap;

                span{
                    margin-right: 8px;
                }
            }
        }
    }
</style>
