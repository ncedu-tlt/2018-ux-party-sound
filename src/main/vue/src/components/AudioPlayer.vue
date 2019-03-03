<template>
    <audio controls="controls">
        <source src="https:\/\/mp3l.jamendo.com\/?trackid=169&format=mp31&from=app-eeded1fc">
        <source src="https:\/\/mp3l.jamendo.com\/?trackid=198&format=mp31&from=app-eeded1fc">
    </audio>
</template>

<script>
    export default {
        name: "AudioPlayer",
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