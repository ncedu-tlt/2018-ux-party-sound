<template>
    <div class="APITest">
        <div class="searchArtists">
            <p>Демострация поиска исполнителей: </p>
            <input v-model="nameArtist" type="text" @keyup="artistList()">
            <p v-for="artist in artists.results" :key="artist.id">
                Имя исполнителя: {{ artist.name }} id исполнителя: {{ artist.id }}
            </p>
        </div>
        <div class="box">
            <div v-for="description in ['Введите полное или частичное название трэка', 'Выберите исполнителя','Выберите жанры', 'Длительность трэка']" :key="description" class="stash">
                {{ description }}
            </div>
            <div class="stash track">
                <input v-model="nameTrack">

                <button @click="send()">
                    Отправить запрос
                </button>

                <button @click="createTrackOnBack()">
                    Создасть сущность на сервере
                </button>
            </div>
            <div class="stash">
                <div v-for="artistName in  [{name: 'Skaut', id: '9'}, {name: 'TriFace', id: '7'}, {name: 'Both', id: '5'}]" :key="artistName.id">
                    <input
                        :id="artistName.id"
                        v-model="idActiveArtist"
                        type="checkbox"
                        :value="artistName.id"
                    >
                    <label>{{ artistName.name }}</label>
                </div>
                {{ idActiveArtist }}
            </div>
            <div class="stash">
                <div v-for="genre in ['rock', 'pop', 'triphop', 'indie']" :key="genre">
                    <input :id="genre" v-model="activeGenres" type="checkbox" :value="genre">
                    <label>{{ genre }}</label>
                </div>
                {{ activeGenres }}
            </div>
            <div class="stash">
                <div v-for="time1 in [{ name: '< 5 минут', value: '0_300' }, {name: '5-10 минут',value: '300_600'}, { name: '> 10 минут', value: '600_1600' }]" :key="time1.value">
                    <input :id="time1.name" v-model="time" type="radio" :value="time1.value">
                    <label>{{ time1.name }}</label>
                </div>
                {{ time }}
            </div>
            <div class="stash" />
            <div class="stash" />
            <div class="stash" />
        </div>
        <div>
            <div v-for="obj in content.results" :key="obj.id" class="stash">
                <div>
                    name:{{ obj.name }}
                </div>
                <div>
                    artist name: {{ obj.artist_name }}
                </div>

                <div>
                    album name: {{ obj.album_name }}
                </div>
                <audio ref="player" controls>
                    <source :src="obj.audio" type="audio/mpeg">
                </audio>
            </div>
        </div>
    </div>
</template>

<script>
import { createTrack } from '@/api/rest/track.api';
import { trackFilter } from '@/api/rest/track.jamendo.api';
import { artistsFindByName } from '@/api/rest/artists,jamendo.api';

export default {
    name: 'APITest',
    data() {
        return {
            nameArtist: '',
            nameTrack: '',
            idActiveArtist: [],
            time: '',
            activeGenres: [],
            content: { results: [] },
            artists: { results: [] }
        };
    },
    mounted: function () {
        this.$watch('content', function () {
            this.$refs.player.map((index, item) => {
                index.load();
            });
        });
    },
    methods: {
        artistList() {
            artistsFindByName({
                client_id: 'eeded1fc',
                format: 'jsonpretty',
                limit: '5',
                namesearch: this.nameArtist
            })
                .then(res => {
                    this.artists = res.data;
                });
        },
        send() {
            let time = '0_10000';
            if (this.time !== '') {
                time = this.time;
            }

            trackFilter({
                client_id: 'eeded1fc',
                format: 'jsonpretty',
                limit: '5',
                include: 'musicinfo',
                namesearch: this.nameTrack,
                artist_id: this.idActiveArtist,
                tags: this.activeGenres,
                durationbetween: time
            })
                .then(res => {
                    this.content = res.data;
                });
        },

        createTrackOnBack() {
            if (this.content.results[0]) {
                let genre;
                if (this.content.results[0].musicinfo.tags.genres[0]) {
                    genre = this.content.results[0].musicinfo.tags.genres[0];
                } else {
                    genre = 'Не определен!';
                }
                createTrack({
                    name: this.content.results[0].name,
                    artist_id: this.content.results[0].artist_id,
                    artist_name: this.content.results[0].artist_name,
                    album_name: this.content.results[0].album_name,
                    album_id: this.content.results[0].album_id,
                    audio: this.content.results[0].audio,
                    genre: genre
                })
                    .then(result => {
                        alert(JSON.stringify(result.data));
                    })
                    .catch(e => {
                        alert(e);
                    });
            } else {
                alert('Нет трека в списке!');
            }
        }
    }
};
</script>

<style>
    .searchArtists {
        margin-bottom: 20px;
    }

    p {
        margin: 0;
        font-size: 10px;
    }

    input {
        height: 10px;
    }

    .track > * {
        width: 100%;
        margin: 10px;
    }

    .box {
        font-size: 10px;
        display: grid;
        grid-template-columns: repeat(4, 1fr);
        grid-gap: 10px;
        width: 800px;
    }

    .stash{
        display: flex;
        flex-direction: column;
        align-items: center;
        text-align: center;
    }
    .APITest {
        display: flex;
        align-items: center;
        flex-direction: column;
    }
</style>
