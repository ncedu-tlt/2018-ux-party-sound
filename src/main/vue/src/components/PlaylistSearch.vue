<template>
    <section class="playlist-search container">
        <div class="playlist-search-fields">
            <div class="playlist-search-fields__bottom">
                <ListInput placeholder="Исполнители" />
                <ListInput placeholder="Жанры" />
            </div>
        </div>
    </section>
</template>
<script>
import ListInput from './ListInput.vue';

export default {
    name: 'PlaylistSearch',
    components: {
        ListInput
    }
};
</script>
<style scoped lang="scss">
    .playlist-search{
        width: 100%;
        padding: 100px 0;

        &-fields{

            &__bottom{
                display: flex;
                justify-content: space-between;
                width: 590px;
            }
        }
    }
</style>
