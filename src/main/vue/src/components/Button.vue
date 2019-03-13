<template>
    <button :class="type">
        {{label}}
    </button>
</template>

<script>
    export default {
        name: "Button",
        props: {
            label: {
                type: String,
                required: true
            },
            type: {
                type: String,
                default: "default"
            }
        }
    }
</script>

<style scoped>
    button {
        cursor: pointer;
        outline: none;
        font-size: 18px;
    }

    .default {
        min-width: 220px;
        padding: 14px;
        box-sizing: border-box;
        border: none;
        color: white;
        background: #0C0094;
        border-radius: 4px;
        box-shadow:1px 2px 12px 0 rgba(0,0,0,0.49);
    }
    .default:hover{
        transition: 400ms;
        background: #1B1096;
    }

    .light {
        border: none;
        background: none;
        color: white;
    }

    .awesome {
        min-width: 220px;
        padding: 14px;
        box-sizing: border-box;
        border: none;
        color: white;
        font-weight: bold;
        background: #7F6AFF;
    }
</style>