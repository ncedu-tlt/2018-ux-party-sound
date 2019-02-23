<template>
    <svg width="50" height="55">
        <polygon points="0,0 0,55 50,27" :fill="color" />
    </svg>
</template>

<script>
export default {
    name: 'TriangleButton',
    props: {
        color: {
            type: String,
            default: '#D96DFF'
        }
    }
};
</script>

<style scoped lang="scss">
    polygon{
        cursor: pointer;
        transition: opacity 300ms;
        &:hover{
            opacity: 0.8;
            transition: opacity 300ms;
        }
    }
</style>
