/**
 * Created by melon on 2018/2/13.
 */
export default {
    install (Vue) {
        Vue.prototype.ENUM = {
            'isOrNot': [{text: '否', value: '0'}, {text: '是', value: '1'}],
            'role': [{text: '全部', value: '0'}, {text: '老师', value: '1'}, {text: '家长', value: '2'}],
            'exam': [{text: '全部', value: '0'}, {text: '英语周测成绩', value: '1'}, {text: '期中考试成绩', value: '2'}, {text: '期末考试成绩', value: '3'}],
            'lesson': [{text: '全部', value: '0'}, {text: '好', value: '1'}, {text: '良', value: '2'}, {text: '差', value: '3'}],
            'phone': [{text: '全部', value: '0'}, {text: '上交', value: '1'}, {text: '未上交', value: '2'}, {text: '未带', value: '3'}],
            'homework': [{text: '全部', value: '0'}, {text: '上交', value: '1'}, {text: '迟交', value: '2'}, {text: '未交', value: '3'}],
            'daily': [{text: '全部', value: '0'}, {text: '到勤', value: '1'}, {text: '缺勤', value: '2'}],
            'clean': [{text: '全部', value: '0'}, {text: '值日', value: '1'}, {text: '未值日', value: '2'}]
        }

        Vue.prototype.enumToValue = (name, value) => {
            let result = ''
            if(name && value){
                Vue.prototype.ENUM[name].forEach(item => {
                    if(item.value === value){
                        result = item.text
                        return
                    }
                })
                return result
            }else {
                return result
            }
        }
    }
}