<template>
    <main class="main-page">
        <section class="head">
            <TopPlaylists />
            <PartySoundLabel />
        </section>
        <FindPlaylist />
        <PlaylistsList />
    </main>
</template>
<script>
import TopPlaylists from './../components/TopPlaylists.vue';
import PartySoundLabel from './../components/PartySoundLabel.vue';
import FindPlaylist from './../components/FindPlaylist.vue';
import PlaylistsList from './../components/PlaylistsList.vue';

export default {
    name: 'Main',
    components: {
        TopPlaylists,
        PartySoundLabel,
        FindPlaylist,
        PlaylistsList
    }
};
</script>
<style scoped lang="scss">
    .main-page{
        display: flex;
        flex-direction: column;
        align-items: center;
        padding-bottom: 100px;

        .head{
            width: 700px;
            display: flex;
            align-items: center;
            margin-bottom: 80px;
        }
    }
</style>
