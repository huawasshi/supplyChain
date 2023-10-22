/*
 * @Author: yanchenxi
 * @Date: 2022-05-06 19:20:43
 * @LastEditTime: 2023-10-21 09:59:00
 * @LastEditors: yanchenxi
 * @Description: 
 * @FilePath: \world-demo\vue.config.js
 */
const path = require('path')
const UglifyJsWebpackPlugin = require('uglifyjs-webpack-plugin');
const isProd = process.env.NODE_ENV === 'production'

function resolve(dir) {
    return path.join(__dirname, dir)
}

function addStyleResource(rule) {
    rule.use('style-resource')
        .loader('style-resources-loader')
        .options({
            patterns: [
                path.resolve(__dirname, './src/style/base/commom.less')
            ]
        })
}
module.exports = {
    outputDir: 'dist', // build生成的生产环境目录， 默认是dist目录
    assetsDir: 'static', // build生成的静态资源js css img文件夹相对于outputDir的位置

    // 调整webpack内部配置
    chainWebpack: config => {
        // 全局配置，简化不同文件夹的引用
        config.resolve.alias
            .set('@', resolve('src'))
            .set('@apis', resolve('src/apis'))
            .set('@assets', resolve('src/assets'))
            .set('@components', resolve('src/components'))
            .set('@router', resolve('src/router'))
            .set('@store', resolve('src/store'))
            .set('@style', resolve('src/style'))
            .set('@utils', resolve('src/utils'))
            .set('@views', resolve('src/views'))

        // 配置全局的颜色样式文件，页面里颜色、宽度等可以当做变量被拿到
        const types = ['vue-modules', 'vue', 'normal-modules', 'normal']
        types.forEach(type => addStyleResource(config.module.rule('less').oneOf(type)))

        // npm run build --report    监控打包体积大小（方便监控优化包体积）
        if (isProd) {
            if (process.env.npm_config_report) {
                config.plugin('webpack-bundle-analyzer')
                    .use(require('webpack-bundle-analyzer').BundleAnalyzerPlugin)
                    .end();
                config.plugins.delete('prefetch')
            }
        }
    },
    configureWebpack: () => {
        const plugins = []
        if (isProd) {
            plugins.push(
                new UglifyJsWebpackPlugin({
                    // 默认为 false
                    sourceMap: false, // 生产环境关闭sourceMap，避免暴露源码
                    uglifyOptions: {
                        warnings: false,
                        compress: {
                            drop_console: true,
                            drop_debugger: true,
                            pure_funcs: ['console.log'],
                        },
                    },
                }),
            );
        }
        return {
            plugins
        };
    },
    devServer: {
        host: '0.0.0.0',
        port: 4000, // dev启动端口
        open: true, // 配置自动启动浏览器
        proxy: {
            '/api': {
                // 请求地址
                target: 'http://22.11.215.17:8181', // 请求/api/user 会被代理到 http://22.11.145.22:8083/api/user
                // 是否跨域
                changeOrigin: true,
                // 路径重写
                pathRewrite: {
                    '^/api': '' // 处理后请求/api/user 会被代理到 http://22.11.145.22:8083/user
                }
            },
            // 不同微服的代理
            '/test': {
                target: 'http://localhost:8083',
                changeOrigin: true,
                pathRewrite: {
                    '^/test': ''
                }
            }
        }
    }
}