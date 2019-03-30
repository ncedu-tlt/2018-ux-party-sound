<template>
    <section class="playlist-search container">
        <div class="playlist-search-fields">
            <div class="playlist-search-fields__block">
                <TextInput v-model="searchText" class-name="search-input" placeholder="Поиск" />
                <Button type="search-button" label="Найти" @on-click="getAllData" />
            </div>
            <div class="playlist-search-fields__block">
                <ListInput
                    placeholder="Исполнители"
                    :chosen="chosenSingers"
                    :list-items="singers"
                    @plus-clicked="addSinger"
                    @x-clicked="deleteSinger"
                />
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
            singers: ['Исполнитель1', 'Исполнитель2'],
            chosenGenres: [],
            chosenSingers: [],
            searchText: ''
        };
    },
    methods: {
        addSinger: function (singer) {
            this.chosenSingers.push(singer);
            this.singers.splice(this.singers.indexOf(singer), 1);
        },
        addGenre: function (genre) {
            this.chosenGenres.push(genre);
            this.genres.splice(this.genres.indexOf(genre), 1);
        },
        deleteSinger: function (singer) {
            this.chosenSingers.splice(this.chosenSingers.indexOf(singer), 1);
            this.singers.push(singer);
        },
        deleteGenre: function (genre) {
            this.chosenGenres.splice(this.chosenGenres.indexOf(genre), 1);
            this.genres.push(genre);
        },
        getAllData: function () {
            console.log(this.searchText);
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
                width: 590px;
            }
        }
    }
</style>
