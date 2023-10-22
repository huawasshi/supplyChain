/*
 * @Author: yanchenxi
 * @Date: 2022-05-06 22:17:06
 * @LastEditTime: 2022-06-13 19:34:00
 * @LastEditors: yanchenxi
 * @Description: 按需引入bfe框架，减少打包体积，提升性能
 * @FilePath: \frontend\src\plugins\bfeUi.js
 */
import {
    Button,
    Select,
    Icon,
    Container,
    Header,
    Aside,
    Footer,
    Card,
    Radio,
    Checkbox,
    Input,
    Upload,
    Form,
    Table,
    Dialog,
    Tooltip,
    Loading,
    Popover,
    Menu,
    Tabs,
    Dropdown,
    Breadcrumb,
    Pagination,
    DropdownMenu,
    DropdownItem,
    Main,
    BreadcrumbItem,
    Submenu,
    MenuItem,
    TableColumn,
    TabPane,
    FormItem,
    Option,
    Transfer,
    Autocomplete,
    TimePicker,
    TimeSelect
    // Message
} from 'bfe-ui'

const install = (Vue) => {
    Vue.use(Button)
    Vue.use(Select)
    Vue.use(Icon)
    Vue.use(Container)
    Vue.use(Header)
    Vue.use(Aside)
    Vue.use(Footer)
    Vue.use(Card)
    Vue.use(Radio)
    Vue.use(Checkbox)
    Vue.use(Input)
    Vue.use(Upload)
    Vue.use(Form)
    Vue.use(Table)
    Vue.use(Dialog)
    Vue.use(Tooltip)
    Vue.use(Loading)
    Vue.use(Popover)
    Vue.use(Menu)
    Vue.use(Tabs)
    Vue.use(Dropdown)
    Vue.use(Breadcrumb)
    Vue.use(Pagination)
    Vue.use(DropdownMenu)
    Vue.use(DropdownItem)
    Vue.use(Main)
    Vue.use(BreadcrumbItem)
    Vue.use(Submenu)
    Vue.use(MenuItem)
    Vue.use(TableColumn)
    Vue.use(TabPane)
    Vue.use(FormItem)
    Vue.use(Option)
    Vue.use(Transfer)
    Vue.use(Autocomplete)
    Vue.use(TimePicker)
    Vue.use(TimeSelect)
    // Vue.use(Message)
}

export default install