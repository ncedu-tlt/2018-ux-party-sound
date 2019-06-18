<template>
    <main class="main-page">
        <section class="top-content">
            <section class="head">
                <PartySoundLabel class="patry-sound-label" />
            </section>
            <FindPlaylist class="find-playlist" @click.native="scrollWindow" />
        </section>

        <PlaylistSearch />
        <PlaylistsList />
        <ShowMore />
    </main>
</template>
<script>
import PartySoundLabel from './../components/PartySoundLabel.vue';
import FindPlaylist from '../components/TheFindPlaylist.vue';
import PlaylistsList from './../components/PlaylistsList.vue';
import ShowMore from '../components/ShowMoreButton.vue';
import PlaylistSearch from './../components/PlaylistSearch.vue';

export default {
    name: 'Main',
    components: {
        PartySoundLabel,
        FindPlaylist,
        PlaylistsList,
        ShowMore,
        PlaylistSearch
    },
    methods: {
        async scrollWindow() {
            let finishPlace = document.documentElement.clientHeight - window.pageYOffset;
            const delay = ms => new Promise(resolve => setTimeout(resolve, ms));

            for (let i = 0; i < (finishPlace - finishPlace % 10) / 10; i++) {
                window.scrollBy(0, 10);
                await delay(5 - Math.pow(i, 4) / 40000);
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
                justify-content: center;
                .top-playlists{
                    animation: move 1s 1 ease-out;
                }
                .patry-sound-label{
                    animation: move 3s 1 ease-out;
                }
                @keyframes move {
                    from{
                        transform: translateX(70px);
                        opacity: .0;
                    }
                    to {
                        transform: translateX(0);
                        opacity: 1;
                    }
                }
            }
            .find-playlist{
                margin-bottom: 70px;
            }
        }
    }
</style>
