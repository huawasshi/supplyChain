/*
 * @Author: yanchenxi
 * @Date: 2022-05-07 10:07:18
 * @LastEditTime: 2023-10-21 14:28:47
 * @LastEditors: yanchenxi
 * @Description: 封装axios公共方法
 * @FilePath: \world-demo\src\utils\http.js
 */
import axios from 'axios'
import {
    Message
} from 'element-ui'
class HttpAxios {
    constructor(baseURL) {
        this.baseURL = baseURL
    }
    getBaseConfig() {
        let config = {
            baseURL: this.baseURL,
            timeout: 10000
        }
        return config
    }
    // 拦截器
    interceptors(instance) {
        // 请求拦截
        instance.interceptors.request.use(config => {
            // Do something before request is sent
            // TODO token等全局参数处理
            return config;
        }, error => {
            // Do something with request error
            return Promise.reject(error);
        });

        // 响应拦截
        instance.interceptors.response.use(response => {
            // Do something before response is sent
            if (response.status === 200) {
                return response.data
            } else {
                return Promise.reject(response)
            }
        }, error => {
            // Do something with response error
            let messageTip = ''
            if (error.response) {
                switch (error.response.status) {
                    case 401:
                        // todo 业务处理
                        break
                    case 404:
                        // todo 业务处理
                        messageTip = '服务未找到'
                        break
                    case 413:
                        // todo 业务处理
                        messageTip = '文件过大'
                        break
                    case 502:
                        // todo 业务处理
                        messageTip = '后端网关错误'
                        break
                    case 503:
                        // todo 业务处理
                        messageTip = '服务不可用'
                        break
                    default:
                        // todo 业务处理
                        messageTip = error.message
                }
            } else {
                if (error.message.includes('timeout')) {
                    messageTip = '请求超时，请检查网络是否正常'
                } else {
                    messageTip = '请求失败，请检查网络是否连接'
                }
            }
            Message({
                message: messageTip,
                type: 'error',
                duration: 2000
            })
            return Promise.reject(error)
        });
    }
    // 创建实例
    request() {
        return (options) => {
            const instance = axios.create()
            // TODO: 如果需要开启mock，则执行以下代码，将普通接口的url中 datax 替换成 mock
            options.url = options.url.replace(/^\/datax/, '/mock');
            const newOptions = Object.assign(this.getBaseConfig(), options)
            this.interceptors(instance)
            return instance(newOptions)
        }
    }
}
export default HttpAxios