<template>

	<div class="pop">
		<template v-if="content && popType">
			<div class="content" v-if="popType === 'component'">
				<!--<div class="title"></div>-->
				<component style="height: 100%" :is="content.type"
						   :require="content.required"
						   :def="content.def"
						   :feilds="content.feilds"
						   :operates="content.operates"
				></component>
			</div>
			<div class="confirm" v-else-if="popType === 'confirm'">
				<div class="text">
					{{content.text}}
				</div>
				<div class="btn">
					<span class="ok">确认</span>
					<span class="no">取消</span>
				</div>
			</div>
			<div class="alter" v-else-if="popType === 'alter'">
				<div class="text">
					{{content.text}}
				</div>
			</div>
		</template>
	</div>

</template>

<script type="text/ecmascript-6">
    import VForm from 'components/VForm/VForm'

    export default {
        props: ['content', 'popType'],
        name: 'pop',
        data () {
            return {}
        },
        methods: {
			add (params) {
                this.$parent.add(params)
			}
        },
        created () {

        },
        components: {
            VForm
        }
    }
</script>

<style lang="stylus" ref="stylesheet/stylus" scope>
	@import "../../common/stylus/mixin.styl";
	@import "pop.styl";
</style>