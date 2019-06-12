<template>
    <section class="find">
        <form class="filters" @keypress.enter="getTracks">
            <TextInput v-model="searchWord" class-name="search-input" placeholder="Поиск" />
            <ListInput
                placeholder="Жанры"
                :chosen="activeGenres"
                :list-items="genresByString"
                @on-input="setGenreWord"
                @add-genre="addGenre"
                @delete-genre="deleteGenre"
            />
            <Button class="search_button" type="search-button" label="Найти" @click.native="getTracks" />
        </form>
        <div class="tracks_list">
            <TrackWithButtonSetTrack
                v-for="(track, index) in tracksFromJamendo"
                :id="Number(track.id)"
                :key="index"
                :name="track.name"
                :artist-name="track.artist_name"
                :duration="track.duration"
                @click-on-track="clickOnTrack"
            />
            <Button
                v-if="tracksFromJamendo.length !== 0"
                class="loadMore"
                type="search-button"
                label="Показать еще"
                @click.native="loadMore()"
            />
        </div>
    </section>
</template>

<script>
import TrackWithButtonSetTrack from './TrackWithButtonSetTrack';
import TextInput from './TextInput.vue';
import ListInput from './ListInput.vue';
import Button from './Button.vue';
import { mapActions, mapState } from 'vuex';

export default {
    name: 'FindTracksFromJamendo',
    components: {
        TrackWithButtonSetTrack,
        TextInput,
        ListInput,
        Button
    },
    props: {
        viewPlaylist: {
            type: Boolean,
            default: false
        }
    },
    data: () => ({
        searchWord: '',
        genreWord: '',
        genres: [
            'rock',
            'punk',
            'metal',
            'pop',
            'classical',
            'gospel',
            'folk',
            'electronic',
            'electrorock',
            'disco',
            'funk',
            'experimental',
            'downtempo',
            'chillout',
            'triphop',
            'techno',
            'newage',
            'ambient',
            'reggae',
            'industrial',
            'hiphop',
            'breakbeat',
            'dance',
            'ska',
            'drumnbass',
            'rnb',
            'jazz',
            'nujazz',
            'electropop',
            'world',
            'trance',
            'filmscore',
            'grunge',
            'hardcore',
            'chansonfrancaise',
            'blues',
            'house',
            'eurodance',
            'dub',
            'country',
            'postrock',
            'jungle',
            'soul',
            'indie',
            'idm',
            'synthpop',
            'symphonic',
            'coldwave',
            'newwave',
            'celtic',
            'ragga',
            'rap',
            'oriental',
            'flamenco',
            'latin',
            'stoner',
            'intro',
            'americana',
            'emo',
            'salsa',
            'progressiverock',
            'singersongwriter',
            'electronik',
            'lofi',
            'gothic',
            'darkambient',
            'drone',
            'metalcore',
            'samba',
            'acidjazz',
            'glitch',
            'classicrock',
            'opera',
            'tribal',
            'poprock',
            'garage',
            'cabaret',
            'bluesrock',
            'bossanova',
            'asian'],
        activeGenres: []
    }),
    computed: {
        genresByString() {
            return this.genres.filter((str) => {
                return str.search(new RegExp(this.genreWord, 'i')) !== -1;
            });
        },
        maxHeight() {
            return this.viewPlaylist ? document.documentElement.clientHeight : 0;
        },
        ...mapState('tracksList', [
            'tracksFromJamendo',
            'activeTrack',
            'playing'
        ])
    },
    methods: {
        ...mapActions('tracksList', [
            'GET_TRACKS_FROM_JAMENDO',
            'GET_NEXT_TRACKS_FROM_JAMENDO'
        ]),
        clickOnTrack(id) {
            this.$emit('click-on-track', id);
        },
        getTracks() {
            this.GET_TRACKS_FROM_JAMENDO({
                nameSearch: this.searchWord,
                limit: 30,
                tags: this.activeGenres,
                include: 'musicinfo'
            });
        },
        addGenre(genre) {
            this.activeGenres.push(genre);
            for (let i = 0; i < this.genres.length; i++) {
                if (this.genres[i] === genre) {
                    this.genres.splice(i, 1);
                }
            }
        },
        deleteGenre(genreForDel) {
            for (let i = 0; i < this.activeGenres.length; i++) {
                if (this.activeGenres[i] === genreForDel) {
                    this.activeGenres.splice(i, 1);
                }
            }
            this.genres.push(genreForDel);
        },
        loadMore() {
            this.GET_NEXT_TRACKS_FROM_JAMENDO({
                nameSearch: this.searchWord,
                limit: 30,
                tags: this.activeGenres,
                offset: this.tracksFromJamendo.length,
                include: 'musicinfo'
            });
        },
        setGenreWord(genreWord) {
            this.genreWord = genreWord;
        }
    }
};
</script>

<style scoped lang="scss">

    $scrollbarTrackColor: #dae1e8;
    $scrollbarThumbColor: #bec8d3;
    $scrollbarThumbHoverColor:#0C0094;
    ::-webkit-scrollbar{
        &-button {
            height: 0;
            width: 0;
        }
        &-track {
            border-radius: 5px;
            background-color: $scrollbarTrackColor;
        }
        &-thumb {
            border-radius: 5px;
            background-color:$scrollbarThumbColor;
        }
        &-thumb:hover{
            background-color: $scrollbarThumbHoverColor;
        }
        width: 7px;
    }
    .find{
        display: grid;
        grid-template-rows: 150px 1fr;
        grid-gap: 5px;
        position: relative;
        .filters {
            display: grid;
            grid-template-columns: 1fr 1fr;
            :first-child{
                grid-column: 1 / 3;
                grid-row: 1;
            }
            .search_button{
                margin: auto auto 0 auto;
            }
        }
        .tracks_list{
            overflow-y: auto;
            text-align: center;
            .loadMore {
                margin: 5px 0;
            }
        }
    }
</style>
