const path = require('path');

const backendProxy = {
    target: 'http://localhost:8080',
    changeOrigin: true
};

module.exports = {

    devServer: {
        port: 3000,
        proxy: {
            '/api': backendProxy,
            '/login': backendProxy,
            '/logout': backendProxy
        }
    },

    outputDir: path.resolve(__dirname, '../resources/static')

};
