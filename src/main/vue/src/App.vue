<template>
    <div id="app">
        <router-view />
    </div>
</template>

<style lang="scss">
    @import 'assets/fonts/fonts';

    body {
        padding: 0;
        margin: 0;
        overflow-x: hidden;
    }

    .container {
        max-width: 1200px;
        margin: 0 20px;
    }

    #app {
        font-family: 'Roboto', sans-serif;
        color: #000000;
    }
</style>
