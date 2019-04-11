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
                    :list-items="genres"
                    @plus-clicked="addGenre"
                    @x-clicked="deleteGenre"
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
    data: function () {
        return {
            genres: ['Жанр1', 'Жанр2'],
            chosenGenres: [],
            singerName: '',
            playlistName: ''
        };
    },
    methods: {
        addGenre: function (genre) {
            this.chosenGenres.push(genre);
            this.genres.splice(this.genres.indexOf(genre), 1);
        },
        deleteGenre: function (genre) {
            this.chosenGenres.splice(this.chosenGenres.indexOf(genre), 1);
            this.genres.push(genre);
        },
        getAllData: function () {
            this.$store.dispatch('FOUND_PLAYLISTS', { playlistName: this.playlistName, genresArray: this.chosenGenres, singer: this.singerName });
        }
    }
};
</script>
<style scoped lang="scss">
    .playlist-search{
        width: 100%;
        padding-top: 125px;

        &-fields{

            &__block{
                display: flex;
                justify-content: space-between;
                align-items: flex-end;
                width: 590px;
            }
        }
    }
</style>
