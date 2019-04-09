<template>
    <section class="find">
        <form class="filters">
            <div class="inputs">
                <div class="box">
                    <TextInput v-model="searchWord" class-name="search-input" placeholder="Поиск" />
                </div>
                <div class="box">
                    <ListInput
                        v-model="genreWord"
                        placeholder="Жанры"
                        :chosen="activeGenres"
                        :list-items="genresByString"
                        @plus-clicked="addGenre"
                        @x-clicked="deleteGenre"
                    />
                </div>
            </div>
            <Button type="search-button" label="Найти" @click.native="getTracks" />
        </form>
        <div class="tracks_list">
            <TrackWithButtonSetTrack
                v-for="track in tracksFromJamendo"
                :id="track.id"
                :key="track.id"
                :name="track.name"
                :artist-name="track.artist_name"
                :duration="track.duration"
            />
        </div>
    </section>
</template>

<script>
import TrackWithButtonSetTrack from './TrackWithButtonSetTrack';
import TextInput from './TextInput.vue';
import ListInput from './ListInput.vue';
import Button from './Button.vue';

export default {
    name: 'FindTracksFromJamendo',
    components: {
        TrackWithButtonSetTrack,
        TextInput,
        ListInput,
        Button
    },
    data: () => ({
        searchWord: '',
        genreWord: '',
        genres: ['rock', '123', '12311'],
        activeGenres: []
    }),
    computed: {
        genresByString() {
            return this.genres.filter((str) => {
                return str.search(new RegExp(this.genreWord, 'i')) !== -1;
            });
        },
        tracksFromJamendo() {
            return this.$store.getters.TRACKS_FROM_JAMENDO;
        },
        activeTrack() {
            return this.$store.getters.ACTIVE_TRACK;
        },
        playing() {
            return this.$store.getters.IS_PLAYING;
        }
    },
    methods: {
        async getTracks() {
            this.$store.dispatch('GET_TRACKS_FROM_JAMENDO', {
                nameSearch: this.searchWord,
                limit: 10,
                tags: this.activeGenres
            });
        },
        async addGenre(genre) {
            this.activeGenres.push(genre);
            for (let i = 0; i < this.genres.length; i++) {
                if (this.genres[i] === genre) {
                    this.genres.splice(i, 1);
                }
            }
        },
        async deleteGenre(genreForDel) {
            for (let i = 0; i < this.activeGenres.length; i++) {
                if (this.activeGenres[i] === genreForDel) {
                    this.activeGenres.splice(i, 1);
                }
            }
            this.genres.push(genreForDel);
        }
    }
};
</script>

<style scoped lang="scss">
    .find{
        display: grid;
        grid-template-rows: 200px 1fr;
        .filters {
            width: 500px;
            .inputs {
                display: flex;
                .box {
                    display: flex;
                    justify-content: flex-start;
                    align-items: flex-end;
                }
            }
        }
        .tracks_list{
            overflow-y: auto;
        }
    }
</style>
