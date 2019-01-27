module.exports = {
    root: true,
    env: {
        node: true
    },
    'extends': [
        'plugin:vue/recommended',
        '@vue/standard'
    ],
    rules: {
    // allow async-await
        'generator-star-spacing': 'off',
        // allow debugger during development
        'no-debugger': process.env.NODE_ENV === 'production' ? 'error' : 'off',
        'no-console': ['warn', {
            allow: ['warn', 'error']
        }],
        'indent': ['error', 4, {
            SwitchCase: 1
        }],
        'semi': ['error', 'always'],
        'space-before-function-paren': ['error', {
            anonymous: 'always',
            named: 'never',
            asyncArrow: 'always'
        }],
        'brace-style': ['error', '1tbs', {
            allowSingleLine: false
        }],
        'object-curly-newline': ['error', {
            consistent: true
        }],
        'vue/html-indent': ['error', 4],
        'vue/max-attributes-per-line': ['error', {
            singleline: 4,
            multiline: {
                max: 1,
                allowFirstLine: false
            }
        }]
    },
    parserOptions: {
        parser: 'babel-eslint'
    }
};
