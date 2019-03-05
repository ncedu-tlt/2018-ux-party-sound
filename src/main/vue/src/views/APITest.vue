<template>
    <div class="APITest">
        <div class="searchArtists">
            <p>Демострация поиска исполнителей: </p>
            <input v-model="nameArtist" type="text" @keyup="artistList()">
            <p v-for="artist in artists.results">
                Имя исполнителя: {{artist.name}} id исполнителя: {{artist.id}}
            </p>
        </div>
        <div class="box">
            <div class="name" v-for="description in descriptions">
                {{description}}
            </div>
            <div class="stash track">
                <input v-model="nameTrack">

                <button v-on:click="send()">Отправить запрос</button>

                <button v-on:click="createTrackOnBack()">Создасть сущность на сервере
                </button>
            </div>
            <div class="stash">
                <div v-for="nameArtist in nameArtists">
                    <input type="checkbox" v-bind:id="nameArtist.id" v-bind:value="nameArtist.id"
                           v-model="idActiveArtist">
                    <label>{{nameArtist.name}}</label>
                </div>
                {{idActiveArtist}}
            </div>
            <div class="stash">
                <div v-for="genre in genres">
                    <input type="checkbox" v-bind:id="genre" v-bind:value="genre" v-model="activeGenres">
                    <label>{{genre}}</label>
                </div>
                {{activeGenres}}
            </div>
            <div class="stash">
                <div v-for="time1 in timeBetween">
                    <input type="radio" v-bind:id="time1.name" v-bind:value="time1.value" v-model="time">
                    <label>{{time1.name}}</label>
                </div>
                {{time}}
            </div>
            <div class="stash">
            </div>
            <div class="stash">
            </div>
            <div class="stash">
            </div>
        </div>
        <div class="content">
            <div v-for="obj in content.results" class="line">
                <div>
                    name:{{obj.name}}
                </div>
                <div>
                    artist name: {{obj.artist_name}}
                </div>

                <div>
                    album name: {{obj.album_name}}
                </div>
                <audio ref="player" controls>
                    <source v-bind:src="obj.audio" type="audio/mpeg"/>
                </audio>
            </div>
        </div>
    </div>
</template>

<script>
    import axios from "axios";

    const API_ARTISTS = "https://api.jamendo.com/v3.0/artists/?client_id=eeded1fc&format=jsonpretty&limit=5";
    const API_TRACKS = "https://api.jamendo.com/v3.0/tracks/?client_id=eeded1fc&format=jsonpretty&limit=5&include=musicinfo";

    export default {
        name: 'APITest',
        data() {
            return {
                nameArtist: "",
                descriptions: ["Введите полное или частичное название трэка",
                    "Выберите исполнителя",
                    "Выберите жанры", "Длительность трэка"],
                nameTrack: "",
                nameArtists: [{name: "Skaut", id: "9"}, {name: "TriFace", id: "7"}, {name: "Both", id: "5"}],
                idActiveArtist: [],
                time: "",
                timeBetween: [{name: "< 5 минут", value: "0_300"}, {
                    name: "5-10 минут",
                    value: "300_600"
                }, {name: "> 10 минут", value: "600_1600"}],
                content: {results: []},
                artists: {results: []},
                genres: ["rock", "pop", "triphop", "indie"],
                activeGenres: []
            }
        },
        mounted: function () {
            this.$watch('content', function () {
                this.$refs.player.map((index, item) => {
                    index.load()
                })
            })
        },
        methods: {
            artistList() {
                var set = "";
                if (this.nameArtist !== "") {
                    set = "&namesearch=" + this.nameArtist;
                }

                axios.get(API_ARTISTS + set)
                    .then(result => {
                        this.artists = result.data;
                    })
            },
            send() {
                var nameTrack = "&namesearch=" + this.nameTrack;
                var idArtists = "";
                this.idActiveArtist.map((id) => {
                    idArtists += "&artist_id[]=" + id;
                });

                var nameGenres = "";
                this.activeGenres.map((genre) => {
                    nameGenres += "&tags[]=" + genre;
                });

                var time = "0_10000";
                if (this.time !== "") {
                    time = this.time;
                }

                var durationBetween = "&durationbetween=" + time;

                axios.get(API_TRACKS + nameTrack + idArtists + nameGenres + durationBetween)
                    .then(response => {
                        console.log(response);
                        this.content = response.data;
                    });
            },

            createTrackOnBack() {
                if (this.content.results[0]) {
                    let genre;
                    if (this.content.results[0].musicinfo.tags.genres[0]) {
                        genre = this.content.results[0].musicinfo.tags.genres[0]
                    } else {
                        genre = "Не определен!"
                    }
                    console.log("Запрос ушел!");
                    axios.post("/api/test", {
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
                } else {
                    alert("Нет трека в списке!");
                }
            },
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

    .APITest {
        display: flex;
        align-items: center;
        flex-direction: column;
    }
</style>
