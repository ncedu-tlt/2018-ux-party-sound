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
        width: 350px;
        height: 255px;
        background: url("./../assets/sound.svg") center no-repeat;
        background-size: contain;
    }
    .titles{
        margin-left: 50px;
    }
    h1{
        font-family: 'Comfortaa', serif;
        font-weight: 300;
        font-size: 135px;
        line-height: 140px;
        color: white;
        width: max-content;
        margin: 0;
    }
    h2{
        font-family: 'Comfortaa', serif;
        font-style: normal;
        font-weight: 300;
        font-size: 55px;
        line-height: normal;
        color: white;
        margin: 0;
    }
</style>
