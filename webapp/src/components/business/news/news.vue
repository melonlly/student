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
        name: 'news',
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
                        {name: 'publisher', text: '发 布 者', require: true},
                        {name: 'updater', text: '更 新 者'},
                        {name: 'content', text: '内容', require: true, tag: 'textarea'}
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
            this.options = {
                url: '/news',
                params: {
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
				{name: 'create_time', text: '创建时间'},
                {name: 'update_time', text: '更新时间'},
                {name: 'publisher', text: '发布者'},
                {name: 'updater', text: '更新者'},
				{name: 'content', text: '内容'},
                {
                    name: '$operate',
                    text: '操作',
                    operate: ['remove', 'edit']
                }
            ]

			this.required = false
            this.def = this.options.params
            this.feilds = [
                {name: 'name', text: '名 称'},
                {name: 'publisher', text: '发 布 者'},
                {name: 'updater', text: '更 新 者'},
                {
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
	@import "news.styl";
</style>