<template>

    <main class="main-page">
        <section class="top-content">
            <section class="head">
                <TopPlaylists/>
                <PartySoundLabel/>
            </section>
            <FindPlaylist v-on:click.native="scrollWindow"/>
        </section>

        <PlaylistSearch/>
        <PlaylistsList/>
        <ShowMore/>
    </main>
</template>
<script>
import TopPlaylists from './../components/TopPlaylists.vue';
import PartySoundLabel from './../components/PartySoundLabel.vue';
import FindPlaylist from './../components/FindPlaylist.vue';
import PlaylistsList from './../components/PlaylistsList.vue';
import ShowMore from './../components/ShowMore.vue';
import PlaylistSearch from './../components/PlaylistSearch.vue';

export default {
    name: 'Main',
    components: {
        TopPlaylists,
        PartySoundLabel,
        FindPlaylist,
        PlaylistsList,
        ShowMore,
        PlaylistSearch
    },
    methods: {
        async scrollWindow() {
            let finishPlace = document.documentElement.clientHeight;
            const delay = ms => new Promise(resolve => setTimeout(resolve, ms));

            for (let i = 0; i < (finishPlace - finishPlace % 10) / 10; i++) {
                console.log(finishPlace);
                window.scrollBy(0, 10);
                await delay(5-Math.pow(i,4)/40000);
            }
            await window.scrollBy(0, finishPlace % 10);
        }
    }
};
</script>
<style scoped lang="scss">
    .main-page {
        display: flex;
        flex-direction: column;
        align-items: center;
        padding-bottom: 100px;

        .top-content {
            display: flex;
            flex-direction: column;
            align-items: center;
            justify-content: space-around;
            height: 90vh;

            .head {
                width: 700px;
                display: flex;
                align-items: center;
            }
        }


    }
</style>
