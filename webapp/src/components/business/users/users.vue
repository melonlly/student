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
            return {
                pop: false
			}
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
                        {name: 'password', text: '密 码', require: true},
                        {name: 'relation', text: '关系人'},
                        {
                            name: 'is_admin',
                            text: '管理员',
                            component: 'drop',
                            entries: this.ENUM.isOrNot,
                            readonly: true
                        },
                        {
                            name: 'status',
                            text: '是否有效',
                            component: 'drop',
                            entries: this.ENUM.isOrNot,
                            readonly: true
                        },
                        {
                            name: 'role',
                            text: '角色',
                            component: 'drop',
                            entries: this.ENUM.role,
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
				params.password = this.encrypt(params.password)
            },
            edit (params) {

            },
            remove (params) {

            }
        },
        created () {
            let user = this.getItem('user')

            this.options = {
                url: '/users',
                params: {
                    role: user.role === '2' ? '1': '',
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
                {name: 'is_admin', text: '是否管理员', enum: 'isOrNot'},
                {name: 'status', text: '是否有效', enum: 'isOrNot'},
                {name: 'role', text: '角色', enum: 'role'},
                {name: 'relation', text: '关系人'},
                {name: 'phone', text: '手机号'},
				{
                    name: '$operate',
                    text: '操作',
                    operate: ['remove', 'edit']
                }
            ]

            this.required = false
            this.def = this.options.params
            this.feilds = [
                {name: 'name', text: '名称'},
                {name: 'relation', text: '关系人'},
				{
                    name: 'is_admin',
                    text: '管理员',
                    component: 'drop',
                    entries: this.ENUM.isOrNot,
                    readonly: true
                },
                {
                    name: 'status',
                    text: '是否有效',
                    component: 'drop',
                    entries: this.ENUM.isOrNot,
                    readonly: true
                },
                {
                    name: 'role',
                    text: '角色',
                    component: 'drop',
                    entries: this.ENUM.role,
					default: user.role === '2' ? '1': '',
					noDrop: user.role === '2',
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
	@import "users.styl";
</style>