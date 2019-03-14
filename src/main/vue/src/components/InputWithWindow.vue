<template>
    <div class="input-with-window _opened">
        <label>
            <input class="input-with-window__input">
        </label>
        <div class="input-with-window__window">
            <div class="item">
                <span>sjy</span>
                <button class="plus">
                    +
                </button>
            </div>
        </div>
    </div>
</template>
<script>
export default {
    name: 'InputWithWindow'
};
</script>
<style scoped lang="scss">
    .input-with-window {
        width: 270px;
        position: relative;

        &__input {
            width: 100%;
            height: 30px;
            border: 1px solid #0C0094;
            border-radius: 6px;
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
