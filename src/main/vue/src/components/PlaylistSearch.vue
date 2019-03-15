<template>
    <section class="playlist-search container">
        <div class="playlist-search-fields">
            <div class="playlist-search-fields__top">
                <SearchInput />
            </div>
            <div class="playlist-search-fields__bottom">
                <ListInput placeholder="Исполнители" />
                <ListInput placeholder="Жанры" />
            </div>
        </div>
    </section>
</template>
<script>
import ListInput from './ListInput.vue';
import SearchInput from './SearchInput.vue';

export default {
    name: 'PlaylistSearch',
    components: {
        ListInput,
        SearchInput
    }
};
</script>
<style scoped lang="scss">
    .playlist-search{
        width: 100%;
        padding-top: 125px;

        &-fields{

            &__bottom{
                display: flex;
                justify-content: space-between;
                width: 590px;
            }
        }
    }
</style>
