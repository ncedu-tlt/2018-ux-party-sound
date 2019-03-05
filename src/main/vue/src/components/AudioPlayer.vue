<template>
    <audio controls="controls">
        <source ref="player" v-bind:src="Array[index]">
    </audio>
</template>

<script>
    export default {
        name: "AudioPlayer",
        data:{
            trackIndex:0
        },
        mounted: function () {
            this.$watch('content', function () {
                this.$refs.player.load()
            })
        },
        props: {
            PlayList:{
                type:Array,
            }
        }
    }
</script>

<style scoped>
    audio {
        width:100%;
    }

    audio:nth-child(2), audio:nth-child(4), audio:nth-child(6) {
        margin: 0;
    }
</style>