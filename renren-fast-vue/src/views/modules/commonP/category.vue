<template>
  <div>
    <el-tree :data="categoryData"
             :props="defaultProps"
             :default-expanded-keys="expandedKeys"
             node-key="catId"
             ref="categoryTree"
             @node-click="nodeClick"
    >
      <span class="custom-tree-node" slot-scope="{ node, data }">
        <span>{{ node.label }}</span>
      </span>
    </el-tree>
  </div>
</template>

<script>
// 这里可以导入其他文件（比如：组件，工具 js，第三方插件 js，json 文件，图片文件等等）
// 例如：import  《组件名称》  from '《组件路径》 ';

export default {
  // import 引入的组件需要注入到对象中才能使用
  components: {},
  props: {},
  data () {
    // 这里存放数据
    return {
      categoryData: [],
      category: {name: '', parentCid: 0, catLevel: 0, showStatus: 1, sort: 0, productUnit: '', icon: ''},
      expandedKeys: [],
      defaultProps: {
        children: 'children',
        label: 'name'
      }
    }
  },
  // 计算属性 类似于 data 概念
  computed: {},
  // 监控 data 中的数据变化
  watch: {},
  // 方法集合
  methods: {
    getMenus () {
      this.$http.get('/product/category/list/tree').then(({data}) => {
        this.categoryData = data
      })
    },
    nodeClick (data, node, component) {
      console.log(data)
      // 向父组件发送事件
      this.$emit('tree-node-click', data, node, component)
    }
  },
  // 生命周期 - 创建完成（可以访问当前this 实例）
  created () {
    this.getMenus()
  },
  // 生命周期 - 挂载完成（可以访问 DOM 元素）
  onMounted () {
  },
  onBeforeMount () {
  }, // 生命周期 - 挂载之前
  onBeforeUpdate () {
  }, // 生命周期 - 更新之前
  onUpdated () {
  }, // 生命周期 - 更新之后
  onBeforeUnmount () {
  }, // 生命周期 - 销毁之前
  onUnmounted () {
  }, // 生命周期 - 销毁完成
  onErrorCaptured () {
  } // 如果页面有 keep-alive 缓存功能,这个函数会触发
}
</script>

<style scoped>
</style>
