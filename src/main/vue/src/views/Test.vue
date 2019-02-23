<template>
    <PlaylistElement v-bind:playlist-id="1" playlist-name="artjh" v-bind:genres="['ahtrwe', 'aerghaht']" color="#478bf4" />
</template>
<script>
import PlaylistElement from '../components/PlaylistElement/PlaylistElement.vue';

export default {
    name: 'Test',
    components: {
        PlaylistElement
    }
};
</script>
