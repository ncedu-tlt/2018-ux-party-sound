<template>
    <div :class="['input-with-window', {'_opened': isOpen}]">
        <label>
            <input
                v-model="message"
                class="input-with-window__input"
                type="text"
                :placeholder="placeholder"
                @input="isOpen = message !== ''"
                @blur="isOpen = false"
                @focus="isOpen = message !== ''"
            >
        </label>
        <div class="input-with-window__window">
            <div v-for="item in fakeData" :key="item" class="item">
                <span>{{ item }}</span>
                <button class="plus">
                    +
                </button>
            </div>
        </div>
    </div>
</template>
<script>
export default {
    name: 'InputWithWindow',
    props: {
        placeholder: {
            type: String,
            required: true
        }
    },
    data: function () {
        return {
            isOpen: false,
            message: '',
            fakeData: ['aerg', 'sjyessr', 'aerghartahs']
        };
    }
};
</script>
<style scoped lang="scss">
    .input-with-window {
        width: 270px;
        position: relative;

        &__input {
            width: 240px;
            height: 30px;
            padding: 0 15px;
            border: 1px solid #0C0094;
            border-radius: 6px;
            outline: none;
        }

        &__window{
            position: absolute;
            display: none;
            width: 100%;
            max-height: 400px;
            padding: 15px 0;
            overflow-y: auto;
            background-color: #FFFFFF;
            border: 1px solid #0C0094;
            border-top: none;
            border-bottom-left-radius: 6px;
            border-bottom-right-radius: 6px;
            z-index: 100;

            .item{
                display: flex;
                justify-content: space-between;
                width: 200px;
                padding: 5px 0;
                margin: 0 auto;

                .plus{
                    height: 20px;
                    width: 20px;
                    padding: 0;
                    font-size: 20px;
                    font-weight: bold;
                    border: none;
                    background: transparent;
                    outline: none;
                    cursor: pointer;
                }
            }
        }
    }

    ._opened{
        .input-with-window__input{
            border-bottom-left-radius: 0;
            border-bottom-right-radius: 0;
        }

        .input-with-window__window{
            display: inline-block;
        }
    }
</style>
