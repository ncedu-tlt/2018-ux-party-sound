<template>
    <button type="button" class="find-playlist">
        <span>Найти плейлист</span>
        <div class="down-arrow">
            <div class="animate-arrow" />
            <div class="animate-arrow" />
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
                .animate-arrow{
                    top: 5px;
                }
            }
        }

        span {
            font-size: 30px;
            color: #0C0094;
            opacity: .8;
        }

        .down-arrow {
            position: relative;
            margin: 15px auto 0 auto;
            width: 55px;
            height: 40px;

            .down-arrow__icon{
                width: 65px;
                height: 35px;
                object-fit: cover;
                transition: margin-top 300ms;
            }
            .animate-arrow{ top: 0;
                position: absolute;
               background: url("../assets/down-arrow.png")center no-repeat;
                width: 65px;
                height: 35px;
                background-size: cover;
                animation: move 4s infinite linear;
                &:nth-child(1){
                    animation-delay: 2s;
                }
            }
            @keyframes move {
                from{
                    opacity: .8;
                }
                to {
                    top: 130%;
                    opacity: .0;
                }
            }
        }
    }
</style>
