<template>
    <div class="APITest">
        <div class="box">
            <div class="name" v-for="description in descriptions">
                {{description}}
            </div>
            <input v-model="nameTrack" class="js_name_track">
            <input v-model="nameArtist" class="js_name_artist" @keyup="artistList()">
            <input v-model="nameGenre" class="js_name_genre">
            <input v-model="minTime" class="js_min_time">
            <input v-model="maxTime" class="js_max_time">
            <div class="stash">
                <button v-on:click="send()" id="js_send">Отправить запрос</button>
            </div>
            <div class="stash">
                <p>Список исполнителей:</p>
                <div class="artist" v-for="artist1 in artists.results" @click="addArtist(artist1.id)">
                    {{artist1.name}}
                </div>
            </div>
            <div class="stash">
                <p>
                    Примеры жанров:
                </p>
                <div v-for="genre in genres">
                    {{genre}}
                </div>
            </div>
            <div class="stash">
            </div>
            <div class="stash">
                <button v-on:click="createTrackOnBack()" id="js_create">Создаст сущность на сервере по первому треку
                </button>
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

    const API_ARTISTS = "https://api.jamendo.com/v3.0/artists/?client_id=eeded1fc&format=jsonpretty&limit=5";
    const API_TRACKS = "https://api.jamendo.com/v3.0/tracks/?client_id=eeded1fc&format=jsonpretty&limit=5&include=musicinfo";

    function http(typeReq, url, set) {
        return new Promise(function (resolve, reject) {
            var xhr = new XMLHttpRequest();
            xhr.open(typeReq, url, true);

            if (set !== undefined) {
                xhr.send(set);
            } else {
                xhr.send();
            }

            xhr.onreadystatechange = function () {
                if (xhr.readyState != 4) return;
                resolve(xhr.responseText);
            }
        })
    }

    //Заметил, что аргумент artist_id не является критичным, если у исполнителей из этого списка не будет песни с нужным названием, то пойдет искать дальше

    export default {
        name: 'APITest',
        data() {
            return {
                nameTrack: "",
                nameArtist: "",
                idArtists: [],
                nameGenre: "",
                minTime: "",
                maxTime: "",
                content: {results: []},
                artists: {results: []},
                descriptions: ["Введите полное или частичное название трэка",
                    "Нажмите на исполните из списка ниже, чтобы добавить его в запрос",
                    "Введите жанр", "Введите минимальную длительность трэка",
                    "Введите максимальную длительность трэка"],
                genres: ["rock", "pop", "triphop", "indie"],
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

                http('GET', API_ARTISTS + set)
                    .then(result => {
                        this.artists = JSON.parse(result);
                    })
            },
            send() {
                var nameTrack = "&namesearch=" + this.nameTrack;
                var nameArtist = "";
                this.idArtists.map((id) => {
                    nameArtist += "&artist_id[]=" + id;
                });

                var nameGenre = "";
                if (this.nameGenre !== "") {
                    nameGenre = "&tags=" + this.nameGenre;
                }

                var minTime = "";
                if (this.minTime !== "") {
                    minTime = "" + this.minTime;
                } else {
                    minTime = "0";
                }

                var maxTime = "";
                if (this.maxTime !== "") {
                    maxTime = "" + this.maxTime;
                } else {
                    maxTime = "1000";
                }
                var durationBetween = "&durationbetween=" + minTime + "_" + maxTime;

                console.log(API_TRACKS + nameTrack + nameArtist + nameGenre + durationBetween);
                console.log(this.idArtists);
                http("GET", API_TRACKS +
                    nameTrack + nameArtist + nameGenre + durationBetween)
                    .then(result => {
                        this.content = JSON.parse(result);
                    });
            },

            createTrackOnBack() {
                if (JSON.stringify(this.content.results[0]) !== undefined) {
                    http('POST', "/api/test", JSON.stringify(this.content.results[0]))
                        .then(result => {
                            alert(result);
                        })
                } else {
                    alert("Нет трека в списке!");
                }
            },

            addArtist(id) {
                this.idArtists.push(id);
            }
        }
    };
</script>

<style>

    .artist:hover {
        cursor: pointer;
    }

    .box {
        font-size: 10px;
        display: grid;
        grid-template-columns: repeat(5, 1fr);
        grid-gap: 10px;
        width: 800px;
    }

    .APITest {
        display: flex;
        align-items: center;
        flex-direction: column;
    }
</style>
