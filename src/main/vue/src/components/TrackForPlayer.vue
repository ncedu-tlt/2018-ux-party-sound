<template>
    <div class="track">
        <div v-if="id === Number(activeTrack.id) && playing" class="player_button_box">
            <svg class="player_button">
                <polygon points="10,8 10,21 14,21 14,8" fill="#0C0094" />
                <polygon points="16,8 16,21 20,21 20,8" fill="#0C0094" />
            </svg>
        </div>
        <div v-else class="player_button_box">
            <svg class="player_button">
                <polygon points="10,8 10,21 24,15" fill="#0C0094" />
            </svg>
        </div>
        <div class="text artist_name">
            <span>{{ artistName }}</span>
        </div>
        <div class="text track_name">
            <span>{{ trackName }}</span>
        </div>
        <div v-if="id === Number(activeTrack.id)" class="text duration">
            {{ currentTime }}
        </div>
        <div v-else class="text">
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
    .track:hover{
        cursor: pointer;
    }
    .track {
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

        margin-right: 5px;
        box-sizing: border-box;
        position: relative;
        height: 50px;
        display: grid;
        grid-template-columns: 40px calc((100%)/5*2 - 44px) calc((100%)/5*3 - 66px) 70px;

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

        .player_button_box{
            display: flex;
            align-items: center;
            justify-content: center;
        }
        .text {
            text-indent: 15px;
            margin: auto 0;
            height: 20px;
            overflow: hidden;
        }
    }
</style>
