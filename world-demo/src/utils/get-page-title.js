/*
 * @Author: yanchenxi
 * @Date: 2022-06-21 14:02:22
 * @LastEditTime: 2022-08-17 14:51:40
 * @LastEditors: yanchenxi
 * @Description: 
 * @FilePath: \yanshi\src\utils\get-page-title.js
 */
export default function getPageTitle(pageTitle) {
    if (pageTitle) {
        return `vcsp - ${pageTitle}`
    }
    return `vcsp`
}