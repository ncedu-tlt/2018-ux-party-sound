<template>
    <section class="playlists-section container">
        <div class="playlists-list">
            <PlaylistElement
                v-for="(prop, index) in playlists"
                :id="prop.id"
                :key="index"
                :name="prop.name"
                color="blue"
                :genres="prop.genres"
                class="playlist"
            />
        </div>
    </section>
</template>
<script>
import PlaylistElement from './PlaylistElement/PlaylistElement.vue';

export default {
    name: 'PlaylistsList',
    components: {
        PlaylistElement
    },
    computed: {
        playlists: function () {
            return this.$store.getters.PLAYLISTS;
        }
    },
    mounted() {
        if (this.$store.getters.PLAYLISTS.length === 0) {
            this.$store.dispatch('POPULATE_PLAYLISTS');
        }
    }
};
</script>
<style scoped lang="scss">
    .playlists-section{
        width: 100%;

        .settings-button{
            margin-top: 125px;
            width: 60px;
            height: 60px;
            border: none;
            background: transparent;
            outline: none;
            cursor: pointer;

            img{
                width: 60px;
                height: 60px;
            }
        }

        .playlists-list {
            margin-top: 80px;
            display: grid;
            grid-template-columns: repeat(3, 300px);
            justify-content: space-between;

            .playlist {
                margin-top: 20px;
            }
        }
    }
</style>
