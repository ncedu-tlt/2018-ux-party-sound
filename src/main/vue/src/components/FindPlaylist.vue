<template>
    <button type="button" class="find-playlist">
        <span>Найти плэйлист</span>
        <div class="down-arrow">
            <img class="down-arrow__icon" src="./../assets/down-arrow.png" alt="arrow">
        </div>
    </button>
</template>
<style scoped lang="scss">
    .find-playlist {
        width: 270px;
        border: none;
        background: transparent;
        outline: none;
        cursor: pointer;

        &:hover {
            .down-arrow {

                .down-arrow__icon{
                    margin-top: 5px;
                    transition: margin-top 300ms;
                }
            }
        }

        span {
            font-size: 30px;
            color: #0C0094;
        }

        .down-arrow {
            margin: 15px auto 0 auto;
            width: 55px;
            height: 40px;

            .down-arrow__icon{
                width: 55px;
                height: 35px;
                transition: margin-top 300ms;
            }
        }
    }
</style>
