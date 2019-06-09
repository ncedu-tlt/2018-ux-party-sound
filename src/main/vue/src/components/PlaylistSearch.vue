<template>
    <section class="playlist-search container">
        <div class="playlist-search-fields">
            <div class="playlist-search-fields__block">
                <TextInput v-model="playlistName" class-name="search-input" placeholder="Поиск" />
                <Button type="search-button" label="Найти" @on-click="getAllData" />
            </div>
            <div class="playlist-search-fields__block">
                <TextInput v-model="singerName" class-name="small-search-input" placeholder="Исполнитель" />
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

export default {
    name: 'PlaylistSearch',
    components: {
        ListInput,
        TextInput,
        Button
    },
    computed: {
        filteredGenres: function () {
            return this.$store.getters.FILTERED_GENRES;
        },
        chosenGenres: function () {
            return this.$store.getters.CHOSEN_GENRES;
        },
        playlistName: {
            get: function () {
                return this.$store.getters.SEARCH_PLAYLIST_NAME;
            },
            set: function (event) {
                this.$store.dispatch('GET_PLAYLIST_NAME', event);
            }
        },
        singerName: {
            get: function () {
                return this.$store.getters.SEARCH_SINGER_NAME;
            },
            set: function (event) {
                this.$store.dispatch('GET_SINGER_NAME', event);
            }
        }
    },
    mounted() {
        if (this.$store.getters.ALL_GENRES.length === 0) {
            this.$store.dispatch('GET_ALL_GENRES');
        }
    },
    methods: {
        addGenre: function (genre) {
            this.$store.dispatch('ADD_GENRE', genre);
        },
        deleteGenre: function (genre) {
            this.$store.dispatch('DELETE_GENRE', genre);
        },
        getAllData: function () {
            this.$store.dispatch('FOUND_PLAYLISTS');
        },
        filterGenres: function (message) {
            this.$store.dispatch('FILTER_GENRES_LIST', message);
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
