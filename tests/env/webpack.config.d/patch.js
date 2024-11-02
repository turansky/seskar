// env-variables
;(function (config) {
    const DefinePlugin = require('webpack').DefinePlugin

    config.plugins.push(
        new DefinePlugin({
            'import.meta.env.VITE_BUILD_NUMBER': JSON.stringify('generic-number'),
            'import.meta.env.VITE_NUMBER': JSON.stringify('42'),
        })
    )
})(config)
