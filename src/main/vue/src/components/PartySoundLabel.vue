<template>
    <section class="main-logo">
        <div class="logotype" />
        <div class="titles">
            <h1>Party <b>Sound</b></h1>
            <h2>The collaborative audio player</h2>
        </div>
    </section>
</template>
<style scoped lang="scss">
    .main-logo{
        display: flex;
        align-items: center;
        justify-content: center;
        margin-top: 50px;
    }
    .logotype{
        width: 380px;
        height: 285px;
        background: url("./../assets/sound.svg") center no-repeat;
    }
    .titles{
        margin-left: 50px;
    }
    h1{
        font-weight: 300;
        font-size: 135px;
        line-height: 140px;
        color: white;
        width: max-content;
        margin: 0;
    }
    h2{
        font-style: normal;
        font-weight: normal;
        font-size: 55px;
        line-height: normal;
        color: white;
        margin: 0;
    }
</style>
