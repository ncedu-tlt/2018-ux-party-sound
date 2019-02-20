<template>
    <div class="APITest">
        <div class="box">
            <div class="name">
                Введите полное или частичное название трэка
            </div>
            <div class="name">
                Введите полное имя исполнителя
            </div>
            <div class="name">
                Введите жанр
            </div>
            <div class="name">
                Введите минимальную длительность трэка
            </div>
            <div class="name">
                Введите максимальную длительность трэка
            </div>
            <input v-model="nameTrack" class="js_name_track">
            <input v-model="nameArtist" class="js_name_artist" @keyup="art()">
            <input v-model="nameGenre" class="js_name_genre">
            <input v-model="minTime" class="js_min_time">
            <input v-model="maxTime" class="js_max_time">
            <div class="stash">
                <button v-on:click="send()" id="js_send">Отправить запрос</button>
            </div>
            <div class="stash">
                <p>Примеры исполнителей:</p>
                <div v-for="artist1 in artists.results">
                    {{artist1.name}}
                </div>
            </div>
            <div class="stash">
                <p>
                    Примеры жанров:
                </p>
                <div>
                    rock
                </div>
                <div>
                    pop
                </div>
            </div>
            <div class="stash">
            </div>
            <div class="stash">
                <button v-on:click="create()" id="js_create">Создаст сущность на сервере по первому треку</button>
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
    function http(typeReq, url, set) {
        return new Promise(function (resolve, reject) {
            var xhr = new XMLHttpRequest();
            xhr.open(typeReq, url, true);
            if(set !== undefined){
                xhr.send(set);
            }else{
                xhr.send();
            }

            xhr.onreadystatechange = function () {
                if (xhr.readyState != 4) return;
                resolve(xhr.responseText);
            }
        })
    }


    export default {
        name: 'APITest',
        data() {
            return {
                nameTrack: "",
                nameArtist: "",
                nameGenre: "",
                minTime: "",
                maxTime: "",
                content: {results: []},
                artists: {results: []}
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
            art() {
                var set = "";
                if (this.nameArtist !== "") {
                    set = "&namesearch=" + this.nameArtist;
                }
                http('GET', "https://api.jamendo.com/v3.0/artists/?client_id=eeded1fc&format=jsonpretty&limit=5" + set)
                    .then(result => {
                        console.log(result);
                        this.artists = JSON.parse(result);
                    })
            },
            send() {
                var nameTrack = "&namesearch=" + this.nameTrack;
                var nameArtist = "";
                if (this.nameArtist !== "") {
                    nameArtist = "&artist_name=" + this.nameArtist;
                }
                var nameGenre = "";
                if (this.nameGenre !== "") {
                    nameGenre = "&tags=" + this.nameGenre;
                }
                var minTime = "";
                var maxTime = "";
                if (this.minTime !== "") {
                    minTime = "" + this.minTime;
                } else {
                    minTime = "0";
                }
                if (this.maxTime !== "") {
                    maxTime = "" + this.maxTime;
                } else {
                    maxTime = "1000";
                }
                var durationbetween = "&durationbetween=" + minTime + "_" + maxTime;
                console.log("https://api.jamendo.com/v3.0/tracks/?client_id=eeded1fc&format=jsonpretty&limit=5&include=musicinfo" +
                    nameTrack + nameArtist + nameGenre + durationbetween);
                http("GET", "https://api.jamendo.com/v3.0/tracks/?client_id=eeded1fc&format=jsonpretty&limit=5&include=musicinfo" +
                    nameTrack + nameArtist + nameGenre + durationbetween)
                    .then(result => {
                        this.content = JSON.parse(result);
                    });
            },
            create() {
                http('POST', "/api/test", JSON.stringify(this.content.results[0]))
                    .then(result => {
                        alert(result);
                    })
            }
        }
    };
</script>

<style>
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
