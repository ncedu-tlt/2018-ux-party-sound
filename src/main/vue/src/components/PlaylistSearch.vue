<template>
    <section class="playlist-search container">
        <div class="playlist-search-fields">
            <div class="playlist-search-fields__block">
                <TextInput v-model="playlistNameComputed" class-name="search-input" placeholder="Поиск" />
                <Button type="search-button" label="Найти" @on-click="getAllData" />
            </div>
            <div class="playlist-search-fields__block">
                <TextInput v-model="singerNameComputed" class-name="small-search-input" placeholder="Исполнитель" />
                <ListInput
                    placeholder="Жанры"
                    :chosen="chosenGenres"
                    :list-items="filteredGenres"
                    @add-genre="addGenre"
                    @delete-genre="deleteGenre"
                    @on-input="filterGenres"
                />
            </div>
        </div>
    </section>
</template>
<script>
import ListInput from './ListInput.vue';
import TextInput from './TextInput.vue';
import Button from './Button.vue';
import { mapActions, mapMutations, mapState } from 'vuex';

export default {
    name: 'PlaylistSearch',
    components: {
        ListInput,
        TextInput,
        Button
    },
    computed: {
        ...mapState('sortComponent', [
            'filteredGenres',
            'chosenGenres',
            'playlistName',
            'singerName',
            'allGenres'
        ]),
        playlistNameComputed: {
            get: function () {
                return this.playlistName;
            },
            set: function (event) {
                this.PLAYLIST_NAME_INPUT(event);
            }
        },
        singerNameComputed: {
            get: function () {
                return this.singerName;
            },
            set: function (event) {
                this.SINGER_NAME_INPUT(event);
            }
        }
    },
    mounted() {
        if (this.allGenres.length === 0) {
            this.GET_ALL_GENRES();
        }
    },
    methods: {
        ...mapActions('sortComponent', [
            'PLAYLIST_NAME_INPUT',
            'SINGER_NAME_INPUT',
            'GET_ALL_GENRES'
        ]),
        ...mapActions('playlists', [
            'FOUND_PLAYLISTS'
        ]),
        ...mapMutations('sortComponent', [
            'CHOSE_GENRES',
            'DELETE_GENRE',
            'FILTER_GENRES'
        ]),
        addGenre: function (genre) {
            this.CHOSE_GENRES(genre);
        },
        deleteGenre: function (genre) {
            this.DELETE_GENRE(genre);
        },
        getAllData: function () {
            this.FOUND_PLAYLISTS();
        },
        filterGenres: function (message) {
            this.FILTER_GENRES(message);
        }
    }
};
</script>
<style scoped lang="scss">
    .playlist-search {
        width: 100%;
        padding-top: 125px;

        &-fields {

            &__block {
                display: flex;
                justify-content: space-between;
                align-items: flex-end;
                width: 590px;
            }
        }
    }
</style>
