<template>
    <label>
        <input v-model="text" placeholder="Поиск" type="text" class="search-input">
    </label>
</template>
<script>
export default {
    name: 'SearchInput',
    data: function () {
        return {
            text: ''
        };
    }
};
</script>
<style scoped lang="scss">
    .search-input{
        width: 420px;
        height: 40px;
        padding: 0 15px;
        font-size: 18px;
        border: 1px solid #0C0094;
        border-radius: 6px;
        outline: none;
    }
</style>
