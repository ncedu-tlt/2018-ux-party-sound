<template>
    <section class="playlist-search container">
        <div class="playlist-search-fields">
            <div class="playlist-search-fields__bottom">
                <InputWithWindow />
            </div>
        </div>
    </section>
</template>
<script>
import InputWithWindow from './../components/InputWithWindow.vue';

export default {
    name: 'PlaylistSearch',
    components: {
        InputWithWindow
    }
};
</script>
<style scoped lang="scss">
    .playlist-search{
        width: 100%;
        padding: 100px 0;
    }
</style>
