<template>
    <label>
        <input
            :class="className"
            :placeholder="placeholder"
            :type="type"
            :value="value"
            @input="$emit('input', $event.target.value)"
        >
    </label>
</template>

<script>
export default {
    name: 'TextInput',
    props: {
        placeholder: {
            type: String,
            required: true
        },
        type: {
            type: String,
            default: 'text'
        },
        className: {
            type: String,
            required: true
        },
        value: {
            type: String,
            default: ''
        }
    }
};
</script>

<style scoped>
    .input-field {
        outline: none;
        border: 1px solid #0C0094;
        width: 300px;
        padding: 10px;
        font-size: 15px;
        border-radius: 4px;
        box-shadow: 1px 2px 12px 0 rgba(0, 0, 0, 0.3);
    }

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
