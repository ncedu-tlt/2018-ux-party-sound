<template>
    <div class="track center">
        <div v-if="id === Number(activeTrack.id) && playing">
            <svg class="player_button">
                <polygon points="10,8 10,21 14,21 14,8" fill="#0C0094" />
                <polygon points="16,8 16,21 20,21 20,8" fill="#0C0094" />
            </svg>
        </div>
        <div v-else class="stop">
            <svg class="player_button">
                <polygon points="10,8 10,21 24,15" fill="#0C0094" />
            </svg>
        </div>
        <span class="text artist_name">
            {{ artistName }}
        </span>
        <span class="text track_name">
            {{ trackName }}
        </span>
        <div v-if="id === Number(activeTrack.id)" class="text duration">
            {{ currentTime }}
        </div>
        <div v-else class="text duration">
            {{ parseInt(duration / 60) + ':' }}{{ 10 > duration % 60 ? '0' : '' }}{{ duration % 60 }}
        </div>
    </div>
</template>
<script>
export default {
    name: 'TrackForPlayer',
    props: {
        id: {
            type: Number,
            default: 0
        },
        artistName: {
            type: String,
            default: 'нет исполнителя'
        },
        trackName: {
            type: String,
            default: 'нет имени'
        },
        duration: {
            type: Number,
            default: 0
        }
    },
    computed: {
        currentTime() {
            let beautifulTime = parseInt(this.$store.getters.CURRENT_SECONDS / 60) + ':';
            beautifulTime += this.$store.getters.CURRENT_SECONDS % 60 < 10 ? '0' : '';
            beautifulTime += this.$store.getters.CURRENT_SECONDS % 60;
            return beautifulTime;
        },
        activeTrack() {
            return this.$store.getters.ACTIVE_TRACK;
        },
        playing() {
            return this.$store.getters.IS_PLAYING;
        }
    }
};
</script>
<style scoped lang="scss">
    $mainColor: #0C0094;
    $whiteColor: #fff;

    .center {
        &::before {
            border-radius: 5px;
            border-top: 2px solid $mainColor;
            border-bottom: 2px solid $mainColor;
            transform: scale3d(0,1,1);
        }

        &::after {
            border-radius: 5px;
            border-left: 2px solid $mainColor;
            border-right: 2px solid $mainColor;
            transform: scale3d(1,0,1);
        }

        &:hover::before,
        &:hover::after {
            transform: scale3d(1,1,1);
            transition: transform 0.5s;
        }
    }
    .track:hover{
        cursor: pointer;
    }
    .track {
        margin-right: 5px;
        box-sizing: border-box;
        position: relative;
        height: 50px;
        display: grid;
        grid-template-columns: 40px 1fr 1.5fr 70px;

        &::before,
        &::after {
            box-sizing: inherit;
            content: '';
            position: absolute;
            width: 100%;
            height: 100%;
        }

        .player_button {
            cursor: pointer;
            position: relative;
            width: 25px;
            height: 25px;
        }

        .text {
            margin: auto 0 auto 10px;
            height: 20px;
            overflow: hidden;
        }
    }
</style>
