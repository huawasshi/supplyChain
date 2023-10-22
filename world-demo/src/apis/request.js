/*
 * @Author: yanchenxi
 * @Date: 2022-05-07 10:57:12
 * @LastEditTime: 2022-06-29 15:43:34
 * @LastEditors: yanchenxi
 * @Description: 
 * @FilePath: \frontend\src\apis\request.js
 */
import HttpAxios from '@utils/http.js'

// 不同环境配置不同的base_url
const baseURL = process.env.VUE_APP_BASEURL ? process.env.VUE_APP_BASEURL : ''

const httpAxios = new HttpAxios(baseURL)

export default httpAxios.request()