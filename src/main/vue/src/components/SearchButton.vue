<template>
    <button class="search-button">
        Найти
    </button>
</template>
<script>
export default {
    name: 'SearchButton'
};
</script>
<style scoped lang="scss">
    .search-button{
        width: 110px;
        height: 40px;
        background-color: #0C0094;
        color: #FFFFFF;
        border: none;
        border-radius: 6px;
        cursor: pointer;
        opacity: 1;
        transition: opacity 300ms;
        outline: none;

        &:hover{
            opacity: 0.8;
            transition: opacity 300ms;
        }
    }
</style>
