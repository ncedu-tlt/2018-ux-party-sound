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
                    @plus-clicked="addGenre"
                    @x-clicked="deleteGenre"
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

import { getAllGenres } from '@/api/rest/genres.api';

export default {
    name: 'PlaylistSearch',
    components: {
        ListInput,
        TextInput,
        Button
    },
    data: function () {
        return {
            allGenres: [],
            filteredGenres: [],
            chosenGenres: [],
            singerName: '',
            playlistName: ''
        };
    },
    mounted() {
        getAllGenres()
            .then(res => {
                this.allGenres = res.map(genre => {
                    return genre.name;
                });
            });
    },
    methods: {
        addGenre: function (genre) {
            this.chosenGenres.push(genre);
            this.filteredGenres.splice(this.filteredGenres.indexOf(genre), 1);
            this.allGenres.splice(this.allGenres.indexOf(genre), 1);
        },
        deleteGenre: function (genre) {
            this.chosenGenres.splice(this.chosenGenres.indexOf(genre), 1);
            this.filteredGenres.push(genre);
            this.allGenres.push(genre);
        },
        getAllData: function () {
            this.$store.dispatch('FOUND_PLAYLISTS', {
                playlistName: this.playlistName,
                genresArray: this.chosenGenres,
                singer: this.singerName
            });
        },
        filterGenres: function (message) {
            this.filteredGenres = this.allGenres.filter(genre => {
                return ~genre.indexOf(message);
            });
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
