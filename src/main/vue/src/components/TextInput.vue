<template>
    <div class="input-form">
        <label class="input-label">
            {{ label }}
        </label>
        <input class="input-field" :placeholder="placeholder" :type="type" :value="value">
    </div>
</template>

<script>
export default {
    name: 'TextInput',
    props: {
        label: {
            type: String,
            required: false,
            default: ''
        },
        placeholder: {
            type: String,
            required: false,
            default: ''
        },
        value: {
            type: String,
            default: ''
        },
        type: {
            type: String,
            default: 'text'
        }
    }
};
</script>

<style scoped>
    .input-form {
        display: flex;
        flex-direction: column;
    }
    .input-label{
        width: max-content;
        color: white;
        margin-bottom: 7px;
        font-size: 16px;
    }
    .input-field {
        outline: none;
        border: 1px solid #0C0094;
        width: 300px;
        padding: 10px;
        font-size: 15px;
        border-radius: 4px;
        box-shadow:1px 2px 12px 0 rgba(0,0,0,0.3);
    }
</style>
