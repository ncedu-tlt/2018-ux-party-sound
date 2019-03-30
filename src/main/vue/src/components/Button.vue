<template>
    <button :class="type" @click="onButtonClick()">
        {{ label }}
    </button>
</template>

<script>
export default {
    name: 'Button',
    props: {
        label: {
            type: String,
            required: true
        },
        type: {
            type: String,
            default: 'default'
        }
    },
    methods: {
        onButtonClick: function () {
            this.$emit('on-click');
        }
    }
};
</script>

<style scoped>
    button {
        cursor: pointer;
        outline: none;
        font-size: 18px;
        border: none;
    }

    .default {
        min-width: 220px;
        padding: 14px;
        box-sizing: border-box;
        color: white;
        background: #0C0094;
        border-radius: 4px;
        box-shadow: 1px 2px 12px 0 rgba(0, 0, 0, 0.49);
    }

    .default:hover {
        transition: 400ms;
        background: #1B1096;
    }

    .light {
        background: none;
        color: white;
    }

    .awesome {
        min-width: 220px;
        padding: 14px;
        box-sizing: border-box;
        color: white;
        font-weight: bold;
        background: #7F6AFF;
    }

    .search-button {
        width: 110px;
        height: 40px;
        background-color: #0C0094;
        font-size: 16px;
        color: #FFFFFF;
        border-radius: 6px;
    }

    .search-button:hover {
        transition: 400ms;
        background: #1B1096;
    }
</style>
