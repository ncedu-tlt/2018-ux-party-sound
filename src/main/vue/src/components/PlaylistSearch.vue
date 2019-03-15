<template>
    <section class="playlist-search container">
        <div class="playlist-search-fields">
            <div class="playlist-search-fields__block">
                <SearchInput />
                <SearchButton />
            </div>
            <div class="playlist-search-fields__block">
                <ListInput placeholder="Исполнители" :chosen="['Исполнитель1', 'Исполнитель2']" :list-items="['Исполнитель1', 'Исполнитель2']" />
                <ListInput placeholder="Жанры" :chosen="['Жанр1', 'Жанр2']" :list-items="['Жанр1', 'Жанр2']" />
            </div>
        </div>
    </section>
</template>
<script>
import ListInput from './ListInput.vue';
import SearchInput from './SearchInput.vue';
import SearchButton from './SearchButton.vue';

export default {
    name: 'PlaylistSearch',
    components: {
        ListInput,
        SearchInput,
        SearchButton
    }
};
</script>
<style scoped lang="scss">
    .playlist-search{
        width: 100%;
        padding-top: 125px;

        &-fields{

            &__block{
                display: flex;
                justify-content: space-between;
                width: 590px;
            }
        }
    }
</style>
