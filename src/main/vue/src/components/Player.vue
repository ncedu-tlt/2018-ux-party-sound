<template>
    <section>
        <audio ref="player" class="js_player" />

        <div class="player">
            <div class="progress_bar js_progress_bar" @click="seek">
                <div class="percent_complete" :style="{width: percentComplete + '%'}">
                </div>
            </div>
            <div class="management_player">
                <div class="buttons">
                    <svg class="player_button" @click="playPreviousTrack()">
                        <polygon points="23,7 23,22 9,15" fill="#0C0094" />
                        <polygon points="11,7 11,22 8,22 8,7" fill="#0C0094" />
                    </svg>
                    <div class="player_button" @click="playing = !playing">
                        <svg v-if="playing" class="play">
                            <circle
                                cx="15"
                                cy="15"
                                r="15"
                                stroke="#0C0094"
                                stroke-width="5"
                                fill="#fff"
                            />
                            <polygon points="10,8 10,21 14,21 14,8" fill="#0C0094" />
                            <polygon points="16,8 16,21 20,21 20,8" fill="#0C0094" />
                        </svg>
                        <svg v-else class="play">
                            <circle
                                cx="15"
                                cy="15"
                                r="15"
                                stroke="#0C0094"
                                stroke-width="5"
                                fill="#fff"
                            />
                            <polygon points="10,8 10,21 24,15" fill="#0C0094" />
                        </svg>
                    </div>
                    <svg class="player_button" @click="playNextTrack()">
                        <polygon points="7,7 7,22 21,15" fill="#0C0094" />
                        <polygon points="19,7 19,22 22,22 22,7" fill="#0C0094" />
                    </svg>
                </div>
                <div class="track_name">
                    {{ trackName }}
                </div>
                <input
                    v-model="volume"
                    class="volume"
                    min="0"
                    max="100"
                    type="range"
                >
            </div>
        </div>
    </section>
</template>

<script>
export default {
    name: 'Player',
    data: () => ({
        player: undefined,
        playing: false,
        volume: 50,
        durationSeconds: 0,
        currentSeconds: 0
    }),
    computed: {
        playlistId() {
            return this.$store.getters.PLAYLIST_ID;
        },
        activeTrackNumber() {
            return this.$store.getters.ACTIVE_TRACK_NUMBER;
        },
        trackName() {
            if (this.tracks[this.activeTrackNumber]) {
                return this.tracks[this.activeTrackNumber].name;
            } else {
                return 'Трек не выбран!aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa';
            }
        },
        tracks() {
            return this.$store.getters.TRACKS;
        },
        percentComplete() {
            return parseInt(this.currentSeconds / this.durationSeconds * 100);
        },
        currentTime() {
            return this.currentSeconds;
        }
    },
    watch: {
        playlistId() {
            this.$store.commit('SET_ACTIVE_TRACK_NUMBER', 0);
            this.setAndPlayTrack();
        },
        volume() {
            this.player.volume = this.volume / 100;
        },
        playing(value) {
            if (value) {
                this.player.play()
                    .catch(e => {
                        this.playing = false;
                    });
            } else {
                this.player.pause();
            }
        }
    },
    mounted() {
        this.player = this.$el.getElementsByClassName('js_player')[0];
        this.player.addEventListener('timeupdate', this.update);
        this.player.addEventListener('loadeddata', this.load);
        this.player.volume = this.volume / 100;
    },
    methods: {
        load() {
            if (this.player.readyState >= 2) {
                this.durationSeconds = parseInt(this.player.duration);
            }
        },
        update(e) {
            this.currentSeconds = parseInt(this.player.currentTime);
        },
        setAndPlayTrack() {
            this.player.src = this.tracks[this.activeTrackNumber].url;
            this.playing = true;
            this.player.play();
        },
        seek(e) {
            const el = this.$el.getElementsByClassName('js_progress_bar')[0].getBoundingClientRect();
            const seekPos = (e.clientX - el.left) / el.width;

            if (this.player.currentTime) {
                this.player.currentTime = parseInt(this.player.duration * seekPos);
            }
        },
        playNextTrack() {
            if (this.activeTrackNumber + 1 === this.tracks.length) {
                this.$store.commit('SET_ACTIVE_TRACK_NUMBER', 0);
            } else {
                this.$store.commit('SET_ACTIVE_TRACK_NUMBER', this.activeTrackNumber + 1);
            }
            this.setAndPlayTrack();
        },
        playPreviousTrack() {
            if (this.activeTrackNumber - 1 < 0) {
                this.$store.commit('SET_ACTIVE_TRACK_NUMBER', this.tracks.length - 1);
            } else {
                this.$store.commit('SET_ACTIVE_TRACK_NUMBER', this.activeTrackNumber - 1);
            }
            this.setAndPlayTrack();
        }
    }
};
</script>

<style scoped lang="scss">
    .player {
        width: 100%;
        position: fixed;
        bottom: 0;
        right: 0;
        .progress_bar{
            transition: 100ms;
            position: relative;
            width: 100%;
            height: 5px;
            background-color: #f1f1f1;
            .percent_complete {
                height: 100%;
                background-color: #0C0094;
            }
        }
        .progress_bar:hover {
            cursor: pointer;
            transition: 100ms;
            height: 10px;
        }

        .management_player{
            display: flex;
            align-items: center;
            justify-content: space-between;
            background-color: #FFF;
            height: 60px;
            box-shadow:inset 0 4px 20px 0 rgba(0,0,0,0.25);
            .buttons{
                display: flex;
                margin-left: 100px;
            }

            .player_button{
                cursor: pointer;
                position: relative;
                width: 30px;
                height: 30px;
                margin: 1px;

                .play{
                    border-radius: 100%;
                    width: 100%;
                    height: 100%;
                }
            }
            .volume {
                margin-right: 100px;
            }
            .volume {
                border-radius: 8px;
                cursor: pointer;
                overflow: hidden;
                width: 80px;
                -webkit-appearance: none;
                background-color: #9a905d;
            }
            .volume::-webkit-slider-thumb {
                -webkit-appearance: none;
                width: 10px;
                height: 10px;
                border-radius: 100%;
                background: #fff;
                box-shadow: -80px 0 0 74px #0C0094;
                border: 1px solid #000;
            }
            .volume:focus {
                outline: none;
            }
        }
    }
</style>
