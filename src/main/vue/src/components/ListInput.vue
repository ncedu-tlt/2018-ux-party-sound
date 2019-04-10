<template>
    <div class="list-input">
        <div class="chosen-list">
            <div v-for="item in chosen" :key="item" class="chosen-list__item">
                <span class="chosen-list__item__genre">
                    {{ item }}
                </span>
                <button
                    class="chosen-list__item__x-button"
                    @click="onXButtonClick(item)"
                />
            </div>
        </div>
        <div :class="['input-with-window', {'_opened': isOpen}]">
            <label>
                <input
                    v-model="message"
                    class="input-with-window__input"
                    type="text"
                    :placeholder="placeholder"
                    @input="isOpen = openWindow"
                >
            </label>
            <div class="input-with-window__window">
                <div v-for="item in listItems" :key="item" class="item">
                    <span>{{ item }}</span>
                    <button class="plus" @click="onPlusClick(item)">
                        +
                    </button>
                </div>
            </div>
        </div>
    </div>
</template>
<script>
export default {
    name: 'ListInput',
    props: {
        placeholder: {
            type: String,
            required: true
        },
        chosen: {
            type: Array,
            default: function () {
                return [];
            }
        },
        listItems: {
            type: Array,
            default: function () {
                return [];
            }
        }
    },
    data: function () {
        return {
            isOpen: false,
            message: ''
        };
    },
    computed: {
        openWindow: function () {
            return this.message !== '' && this.listItems.length !== 0;
        }
    },
    methods: {
        onPlusClick: function (item) {
            this.$emit('plus-clicked', item);
        },
        onXButtonClick: function (item) {
            this.$emit('x-clicked', item);
        }
    }
};
</script>
<style scoped lang="scss">
    .list-input {

        .chosen-list {
            height: 60px;
            margin-bottom: 10px;
            display: flex;
            align-items: flex-end;
            flex-wrap: wrap;

            &__item {
                margin-right: 10px;

                &__genre {
                    font-size: 14px;
                }

                &__x-button {
                    width: 10px;
                    height: 10px;
                    margin-left: 5px;
                    background: url("./../assets/x.png") center no-repeat;
                    background-size: contain;
                    border: none;
                    cursor: pointer;
                    outline: none;
                }
            }
        }

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

            &__window {
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

                .item {
                    display: flex;
                    justify-content: space-between;
                    width: 200px;
                    padding: 5px 0;
                    margin: 0 auto;

                    .plus {
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

        ._opened {
            .input-with-window__input {
                border-bottom-left-radius: 0;
                border-bottom-right-radius: 0;
            }

            .input-with-window__window {
                display: inline-block;
            }
        }
    }
    input:focus{
        box-shadow: 1px 2px 12px 0 rgba(0, 0, 0, 0.3);
    }
</style>
