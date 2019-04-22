<template>
    <section>
        <audio ref="player" />
        <PlayerPlaylist
            class="playlist"
            :view-playlist="viewPlaylist"
        />
        <div class="player">
            <div ref="seek" class="progress_bar" @click="seek">
                <div class="percent_complete" :style="{width: percentComplete + '%'}" />
            </div>
            <div class="management_player">
                <div class="buttons">
                    <svg class="player_button" @click="playPreviousTrack()">
                        <polygon points="23,7 23,22 9,15" fill="#0C0094" />
                        <polygon points="11,7 11,22 8,22 8,7" fill="#0C0094" />
                    </svg>
                    <div class="player_button" @click="reversePlaying">
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
                    <svg class="player_button" @click="reverseViewPlaylist()">
                        <polygon points="10,7 10,11 30,11 30,7" fill="#0C0094" />
                        <polygon points="10,14 10,18 30,18 30,14" fill="#0C0094" />
                        <polygon points="10,25 10,21 30,21 30,25" fill="#0C0094" />
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
import PlayerPlaylist from './PlayerPlaylist';
export default {
    name: 'Player',
    components: {
        PlayerPlaylist
    },
    data: () => ({
        viewPlaylist: false,
        player: undefined,
        volume: 50,
        durationSeconds: 0
    }),
    computed: {
        trackName() {
            return this.activeTrack.artistName !== undefined ? this.activeTrack.artistName + ' - ' + this.activeTrack.name : this.activeTrack.name;
        },
        percentComplete() {
            return parseInt(this.currentTime / this.durationSeconds * 100);
        },
        playing() {
            return this.$store.getters.IS_PLAYING;
        },
        playlistId() {
            return this.$store.getters.PLAYLIST_ID;
        },
        activeTrack() {
            return this.$store.getters.ACTIVE_TRACK;
        },
        load() {
            return this.$store.getters.IS_SUCCESS_LOAD;
        },
        tracks() {
            return this.$store.getters.TRACKS;
        },
        currentTime() {
            return this.$store.getters.CURRENT_SECONDS;
        }
    },
    watch: {
        volume() {
            this.player.volume = this.volume / 100;
        },
        playing(value) {
            if (value) {
                this.player.play()
                    .catch(e => {
                        this.$store.commit('SET_PLAYING', false);
                    });
            } else {
                this.player.pause();
            }
        },
        activeTrack() {
            this.player.setAttribute('src', this.activeTrack.url || this.activeTrack.audio);
            this.$store.commit('SET_PLAYING', true);
            this.player.play();
        }
    },
    mounted() {
        this.player = this.$refs.player;
        this.player.addEventListener('timeupdate', this.update);
        this.player.addEventListener('loadeddata', this.loaded);
        this.player.addEventListener('ended', this.playNextTrack);
        this.player.volume = this.volume / 100;
    },
    methods: {
        reverseViewPlaylist() {
            if (this.tracks.length !== 0) {
                this.viewPlaylist = !this.viewPlaylist;
            }
        },
        reversePlaying() {
            this.$store.commit('SET_PLAYING', !this.playing);
        },
        loaded() {
            if (this.player.readyState >= 2) {
                this.durationSeconds = parseInt(this.player.duration);
            }
        },
        update(e) {
            this.$store.commit('SET_CURRENT_SECONDS', parseInt(this.player.currentTime));
        },
        seek(e) {
            const el = this.$refs.seek.getBoundingClientRect();
            const seekPos = (e.clientX - el.left) / el.width;

            if (this.player.currentTime) {
                this.player.currentTime = parseInt(this.player.duration * seekPos);
            }
        },
        playNextTrack() {
            this.$store.commit('SET_NEXT_TRACK');
        },
        playPreviousTrack() {
            this.$store.commit('SET_PREVIOUS_TRACK');
        }
    }
};
</script>

<style scoped lang="scss">
    $mainColor: #0C0094;
    $whiteColor: #f1f1f1;
    $blackColor: #000;
    $grayColor: #9a905d;
    .playlist{
        z-index: 1;
        position: fixed;
        bottom: 0;
        left: 0;
    }
    .player {
        z-index: 2;
        width: 100%;
        position: fixed;
        bottom: 0;
        right: 0;

        .progress_bar{
            transition: 100ms;
            position: relative;
            width: 100%;
            height: 5px;
            box-shadow:inset 0 1px 5px 0 rgba(0,0,0,0.25);
            background-color: $whiteColor;

            .percent_complete {
                height: 100%;
                background-color: $mainColor;
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
            background-color: $whiteColor;
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
                border-radius: 8px;
                cursor: pointer;
                overflow: hidden;
                width: 80px;
                -webkit-appearance: none;
                background-color: $grayColor;
                margin-right: 100px;
            }

            .volume::-webkit-slider-thumb {
                -webkit-appearance: none;
                width: 10px;
                height: 10px;
                border-radius: 100%;
                background: $whiteColor;
                box-shadow: -80px 0 0 74px $mainColor;
                border: 1px solid #000;
            }

            .volume:focus {
                outline: none;
            }
        }
    }
</style>
