<template>

	<div class="main">
		<VForm style="height: 20%" :required="required" :def="def" :feilds="feilds" :operates="operates"></VForm>
		<list style="height: 80%" ref="list" :options="options" :columns="columns" @filter="filter" @before="before" @after="after"></list>
	</div>

</template>

<script type="text/ecmascript-6">
    import list from 'components/list/list'
    import VForm from 'components/VForm/VForm'
    import bus from 'components/bus/bus'

    export default {
        name: 'daily',
        data () {
            return {}
        },
        methods: {
            before () {},
            after (res) {},
            // 过滤处理数据集
            filter (data) {
                const _this = this
                data.forEach(item => {

                })
                this.data = data
                return data
            },
            // list查询
            search (params) {
                this.$refs.list.params = params
                this.$refs.list.load()
            },
            // 添加弹窗
            addPop (params) {
                bus.$emit('pop', true, {
                    type: 'VForm',
                    required: true,
                    feilds: [
                        {name: 'name', text: '名 称', require: true},
                        {
                            name: 'lesson',
                            text: '上 课',
                            component: 'drop',
                            entries: this.ENUM.lesson,
                            default: '0',
                            readonly: true
                        },{
                            name: 'phone',
                            text: '手 机',
                            component: 'drop',
                            entries: this.ENUM.phone,
                            default: '0',
                            readonly: true
                        },{
                            name: 'homework',
                            text: '作 业',
                            component: 'drop',
                            entries: this.ENUM.homework,
                            default: '0',
                            readonly: true
                        },{
                            name: 'daily',
                            text: '考 勤',
                            component: 'drop',
                            entries: this.ENUM.daily,
                            default: '0',
                            readonly: true
                        },{
                            name: 'clean',
                            text: '卫 生',
                            component: 'drop',
                            entries: this.ENUM.clean,
                            default: '0',
                            readonly: true
                        }
                    ],
                    operates: [
                        {
                            name: '添加',
                            type: 'add',
                            func: 'add'
                        }
                    ]
                }, 'component')
            },
            // 添加
            add (params) {
                bus.$emit('pop', false)
            },
            edit (params) {

            },
            remove (params) {

            }
        },
        created () {
            let user = this.getItem('user')
            this.options = {
                url: '/daily',
                params: {
                    name: user.role === '2' ? user.relation : '',
                    index: 1,
                    size: 10
                },
                isPage: true,
            }
            this.columns = [
                {
                    name: 'id',
                    text: 'id',
                    hide: true
                },{
                    name: 'name',
                    text: '名称'
                },{
                    name: 'lesson',
                    text: '上课',
					enum: 'lesson'
                },{
                    name: 'phone',
                    text: '手机',
					enum: 'phone'
                },{
                    name: 'homework',
                    text: '作业',
					enum: 'homework'
                },{
                    name: 'daily',
                    text: '考勤',
					enum: 'daily'
                },{
                    name: 'clean',
                    text: '卫生',
					enum: 'daily'
                },{
                    name: 'date',
                    text: '日期'
                },{
                    name: '$operate',
                    text: '操作',
                    operate: ['remove', 'edit']
                }
            ]

            this.required = false
            this.def = this.options.params
            this.feilds = [
                {
                    name: 'name',
                    text: '名 称',
                    default: user.role === '2' ? user.relation : '',
                    readonly: user.role === '2'
                },{
                    name: 'lesson',
                    text: '上 课',
                    component: 'drop',
                    entries: this.ENUM.lesson,
                    default: '0',
                    readonly: true
                },{
                    name: 'phone',
                    text: '手 机',
                    component: 'drop',
                    entries: this.ENUM.phone,
                    default: '0',
                    readonly: true
                },{
                    name: 'homework',
                    text: '作 业',
                    component: 'drop',
                    entries: this.ENUM.homework,
                    default: '0',
                    readonly: true
                },{
                    name: 'daily',
                    text: '考 勤',
                    component: 'drop',
                    entries: this.ENUM.daily,
                    default: '0',
                    readonly: true
                },{
                    name: 'clean',
                    text: '卫 生',
                    component: 'drop',
                    entries: this.ENUM.clean,
                    default: '0',
                    readonly: true
                },{
                    name: 'startDate',
                    text: '开始日期',
                    component: 'datepicker'
                },{
                    name: 'endDate',
                    text: '结束日期',
                    component: 'datepicker'
                }
            ]
            this.operates = [
                {
                    name: '查询',
                    type: 'search',
                    func: 'search',
                    auto: true
                },
				{
                    name: '重置',
                    type: 'reset'
                },
				{
				    name: '添加',
					type: 'add',
                    func: 'addPop'
				}
            ]
        },
        components: {
            list, VForm
        }
    }
</script>

<style lang="stylus">
	@import "daily.styl";
</style>