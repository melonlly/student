require('es6-promise').polyfill() // 支持promise
import axios from 'axios'

const ERR_OK = "0"

const Axios = axios.create({
    baseURL: '/api',
    method: 'post', // 默认get
    // 即将被发送的自定义请求头
    headers: {
        'X-Requested-With': 'XMLHttpRequest'
    },
    withCredentials: false, // 跨域请求时是否需要使用凭证
    // `auth` 表示应该使用 HTTP 基础验证，并提供凭据
    // 这将设置一个 `Authorization` 头，覆写掉现有的任意使用 `headers` 设置的自定义 `Authorization`头
    auth: {
        username: 'admin',
        password: 'admin'
    },
    timeout: 20000,
    responseType: 'json', // 服务器响应的数据类型，可以是 'arraybuffer', 'blob', 'document', 'json', 'text', 'stream'
    // 允许为上传处理进度事件
    onUploadProgress: function (progressEvent) {},
    // 允许为下载处理进度事件
    onDownloadProgress: function (progressEvent) {},
    maxContentLength: 2000, // 定义允许的响应内容的最大尺寸
    // 定义代理服务器的主机名称和端口
    // proxy: {
    //     host: '127.0.0.1',
    //     port: '9898',
    //     auth: {
    //         username: 'admin',
    //         password: 'admin'
    //     }
    // }
})

// 请求拦截器
Axios.interceptors.request.use(req => {
    // console.log(req)
    if (req.method === 'post') {
        // json 转 FormData
        // const formData = new FormData()
        // Object.keys(req.data).forEach(key => formData.append(key, req.data[key]))
        // req.data = formData

        req.method = 'GET'
    }

    if (localStorage.token) {
        req.headers.Authorization = localStorage.token
    }
    return req
}, error => {
    alert('错误的传参')
    return Promise.reject(error)
})

// 响应拦截器
Axios.interceptors.response.use(res => {
    // console.log(res)
    if (res.status !== 200) {
        return Promise.reject(res)
    }
    if (res.data.code === ERR_OK) {
        res.getData = type => type === 'list' ? res.data.data : res.data.data[0]
        return res
    } else {
        alert(res.data.error)
    }
}, error => {
    const code = error.response.status
    switch (code) {
        // 401 说明 token 验证失败 可以直接跳转到登录页面，重新登录获取 token
        case 401: {

        }
        case 404: {

        }
    }

    return Promise.reject(error.response.data)
})

const getAttributes = (ele, property, pseudo) => window.getComputedStyle(ele, pseudo).getPropertyValue(property).replace('px', '')

const getWaveTarget = target => {
    if (target.nodeName === 'BODY') {
        return null
    } else {
        if (target.className.indexOf('wave') < 0) {
            if (target.parentNode.className.indexOf('wave') < 0) {
                return getWaveTarget(target.parentNode)
            } else {
                return target.parentNode
            }
        } else {
            return target
        }
    }
}

// wave
document.addEventListener('mousedown', e => {
    let target = getWaveTarget(e.target)
    if (target) {
        const x = e.clientX
        const y = e.clientY
        const width = Number(getAttributes(target, 'width'))
        const height = Number(getAttributes(target, 'height'))
        const t_left = target.getBoundingClientRect().left
        const t_top = target.getBoundingClientRect().top
        // 相对父级
        let left = x - t_left
        let top = y - t_top
        let right = width - left
        let bottom = height - top
        // 到四个角的距离
        let toLeftTop = Math.sqrt(Math.pow(left, 2) + Math.pow(top, 2))
        let toRightTop = Math.sqrt(Math.pow(right, 2) + Math.pow(top, 2))
        let toLeftBottom = Math.sqrt(Math.pow(left, 2) + Math.pow(bottom, 2))
        let toRightBottom = Math.sqrt(Math.pow(right, 2) + Math.pow(bottom, 2))
        const max = Math.max(toLeftTop, toRightTop, toLeftBottom, toRightBottom)

        let div = document.createElement('div')
        div.className = 'ripple'
        div.style.left = left - max + 'px'
        div.style.top = top - max + 'px'
        div.style.width = max * 2 + 'px'
        div.style.height = max * 2 + 'px'
        target.appendChild(div)
        window.setTimeout(() => {
            target.removeChild(div)
        }, 300)
    }
})
// document.addEventListener('mouseup', e => {
//     let target = e.target
//     let parent = target.parentNode
//     if(target.className.indexOf('wave') < 0){
//         if(parent.className.indexOf('wave') < 0){
//             return false
//         }else{
//             target = parent
//         }
//     }
//     window.setTimeout(() => {
//         let nodes = target.childNodes
//         for(let node of nodes){
//             if(node.className === 'ripple'){
//                 target.removeChild(node)
//             }
//         }
//     }, 100)
// })

export default {
    install (Vue) {
        // 成功
        Vue.prototype.ERR_OK = ERR_OK

        // 计算页面高度
        Vue.prototype.$height = () => window.innerHeight

        /**
         * 获取元素属性（只读）
         * @param ele       dom元素
         * @param property  属性名
         * @param pseudo    伪类名
         */
        Vue.prototype.$getAttributes = getAttributes

        // axios
        Vue.prototype.$http = Axios

        // 菜单目录
        Vue.prototype.menu = []

        // 获取或赋值菜单目录
        Vue.prototype.$menu = menu => menu ? Vue.prototype.menu = menu : Vue.prototype.menu

        // 当前组件document（只能在mounted中初始化，created中还未生成dom）
        Vue.prototype.$doc = component => component.$vnode.elm
    }
}