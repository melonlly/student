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
        name: 'users',
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
                            name: 'exam',
                            text: '考试',
                            component: 'drop',
                            entries: this.ENUM.exam,
                            default: '0',
                            readonly: true
                        },
                        {name: 'week1', text: '周测第一次'},
                        {name: 'week2', text: '周测第二次'},
                        {name: 'week3', text: '周测第三次'},
                        {name: 'english', text: '英语'},
                        {name: 'social', text: 'social'},
                        {name: 'science', text: 'science'},
                        {name: 'chinese', text: '语文'},
                        {name: 'math', text: '数学'},
                        {name: 'physical', text: '物理'}
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
                url: '/score',
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
                },{name: 'name', text: '名称'},
				{name: 'exam', text: '考试', enum: 'exam'},
				{name: 'week1', text: '周测第一次'},
				{name: 'week2', text: '周测第二次'},
				{name: 'week3', text: '周测第三次'},
				{name: 'english', text: '英语'},
				{name: 'social', text: 'social'},
                {name: 'science', text: 'science'},
                {name: 'chinese', text: '语文'},
                {name: 'math', text: '数学'},
                {name: 'physical', text: '物理'},
				{
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
					text: '名称',
                    default: user.role === '2' ? user.relation : '',
                    readonly: user.role === '2'
				},
				{
                    name: 'exam',
                    text: '考试',
                    component: 'drop',
                    entries: this.ENUM.exam,
                    default: '0',
                    readonly: true
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
	@import "score.styl";
</style>