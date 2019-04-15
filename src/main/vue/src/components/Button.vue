<template>
    <button :class="type">
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
    }
};
</script>

<style scoped>
    button {
        cursor: pointer;
        outline: none;
        font-size: 18px;
        border: none;
        padding: 0 0 2px 0;
    }

    .default {
        min-width: 220px;
        padding: 14px;
        box-sizing: border-box;
        color: white;
        background: #0C0094;
        border-radius: 4px;
        box-shadow: 1px 2px 12px 0 rgba(0, 0, 0, 0.49);
        transition: 400ms;
    }

    .default:hover {
        transition: 400ms;
       transform: scale(1.03);
        background: #0B0088;
    }

    .light {
        position: relative;
        background: none;
        color: white;
        border-bottom: 1px solid transparent;
    }
   .light:after{
        content: '';
        background: white;
        position: absolute;
        width: 0;
        height: 1px;
        transition: all .3s linear;
    }
    .light:after{
        top: 100%;
        left: 0;
    }
   .light:hover:after{
        width: 100%;
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
        transform: scale(1.03);
        background: #0B0088;
    }
</style>
