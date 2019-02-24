<template>
    <button type="button" class="find-playlist">
        <span>Найти плэйлист</span>
        <div class="down-arrow"/>
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
                margin-top: 15px;
                transition: margin-top 300ms;
            }
        }

        span {
            font-size: 30px;
            color: #0C0094;
        }

        .down-arrow {
            background: url("./../assets/down-arrow.png") center no-repeat;
            background-size: contain;
            width: 55px;
            height: 35px;
            margin: 10px auto 0 auto;
            transition: margin-top 300ms;
        }
    }
</style>
