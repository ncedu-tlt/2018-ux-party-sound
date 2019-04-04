<template>
    <div class="APITest">
        <div class="search_artists">
            <p>Демострация поиска исполнителей: </p>
            <input v-model="artistName" type="text" @keyup="findArtistsByName()">
            <p v-for="artist in artists" :key="artist.id">
                Имя исполнителя: {{ artist.name }} id исполнителя: {{ artist.id }}
            </p>
        </div>
        <div class="box">
            <div v-for="description in ['Введите полное или частичное название трэка', 'Выберите исполнителя','Выберите жанры', 'Длительность трэка']" :key="description" class="stash">
                {{ description }}
            </div>
            <div class="stash track">
                <input v-model="trackName">

                <button @click="getTracks()">
                    Отправить запрос
                </button>

                <button @click="createTrackOnBack()">
                    Создасть сущность на сервере
                </button>
            </div>
            <div class="stash">
                <div v-for="artist in [{name: 'Skaut', id: '9'}, {name: 'TriFace', id: '7'}, {name: 'Both', id: '5'}]" :key="artist.id">
                    <input
                        :id="artist.id"
                        v-model="activeIdsArtist"
                        type="checkbox"
                        :value="artist.id"
                    >
                    <label>{{ artist.name }}</label>
                </div>
                {{ activeIdsArtist }}
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
            <div v-for="obj in content" :key="obj.id" class="stash">
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
import { createTrack } from '@/api/rest/tracks.api';
import { getTracks } from '@/api/rest/track.jamendo.api';
import { findArtistsByName } from '@/api/rest/artists.jamendo.api';

const DEFAULT_TIME = '0_10000';

export default {
    name: 'APITest',
    data() {
        return {
            artistName: '',
            trackName: '',
            activeIdsArtist: [],
            time: undefined,
            activeGenres: [],
            content: [],
            artists: []
        };
    },
    mounted: function () {
        this.$watch('content', function () {
            this.$refs.player.map((item) => {
                item.load();
            });
        });
    },
    methods: {
        async findArtistsByName() {
            this.artists = await findArtistsByName({ limit: 5, namesearch: this.artistName });
        },
        async getTracks() {
            this.content = await getTracks({
                namesearch: this.trackName,
                artistId: this.activeIdsArtist,
                tags: this.activeGenres,
                durationbetween: this.time || DEFAULT_TIME,
                limit: 5,
                include: 'musicinfo'
            });
        },
        async createTrackOnBack() {
            const track = this.content[0];
            if (track) {
                alert(await createTrack({
                    id: track.id,
                    name: track.name,
                    artistId: track.artist_id,
                    artistName: track.artist_name,
                    albumName: track.album_name,
                    albumId: track.album_id,
                    url: track.audio,
                    genresString: track.musicinfo.tags.genres
                }));
            } else {
                alert('Нет трека в списке!');
            }
        }
    }
};
</script>

<style>
    .search_artists {
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
