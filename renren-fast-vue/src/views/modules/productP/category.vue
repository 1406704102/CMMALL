<template>
  <div>
    <el-button type="danger" size="mini" style="margin-bottom: 20px" @click="batchDelete">删除</el-button>
    <el-tree :data="categoryData" :props="defaultProps" :expand-on-click-node="true" :show-checkbox="true"
             :default-expanded-keys="expandedKeys"
             node-key="catId"
             ref="categoryTree"
    >
      <span class="custom-tree-node" slot-scope="{ node, data }">
        <span>
          <el-button v-if="node.level<=2"
                     icon="el-icon-plus"
                     type="text"
                     size="mini"
                     @click="() => append(data)">
          </el-button>
          <el-button
            icon="el-icon-edit-outline"
            type="text"
            size="mini"
            @click="() => edit(data)">
          </el-button>
          <el-button v-if="node.childNodes.length === 0"
                     icon="el-icon-delete"
                     type="text"
                     size="mini"
                     @click="() => remove(node, data)">
          </el-button>
        </span>
        <span style="margin-left: 20px">{{ node.label }}</span>
      </span>
    </el-tree>

    <!--    <el-dialog :title="categoryDialogVisible === 1 ? '添加分类':'修改分类'" :visible.sync="categoryDialogVisible !== 0" width="30%">-->
    <el-dialog :title="categoryDialogType === 1 ? '添加分类':'修改分类'" :visible.sync="categoryDialogVisible" width="30%"
               :close-on-click-modal="false">
      <el-form :model="category">
        <el-form-item label="分类名称" label-width="80px">
          <el-input v-model="category.name" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="分类图标" label-width="80px">
          <el-input v-model="category.icon" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="计量单位" label-width="80px">
          <el-input v-model="category.productUnit" autocomplete="off"></el-input>
        </el-form-item>
        <!--        <el-form-item label="活动区域" :label-width="formLabelWidth">-->
        <!--          <el-select v-model="category.region" placeholder="请选择活动区域">-->
        <!--            <el-option label="区域一" value="shanghai"></el-option>-->
        <!--            <el-option label="区域二" value="beijing"></el-option>-->
        <!--          </el-select>-->
        <!--        </el-form-item>-->
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="categoryDialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="submitForm">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
// 这里可以导入其他文件（比如：组件，工具 js，第三方插件 js，json 文件，图片文件等等）
// 例如：import 《组件名称》 from '《组件路径》 ';

export default {
  // import 引入的组件需要注入到对象中才能使用
  components: {},
  props: {},
  data () {
    // 这里存放数据
    return {
      categoryDialogVisible: false,
      categoryDialogType: 1,
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
    append (data) {
      // // eslint-disable-next-line no-undef
      // const newChild = {id: id++, label: 'testtest', children: []}
      // if (!data.children) {
      //   this.$set(data, 'children', [])
      // }
      // data.children.push(newChild)
      this.category.catId = null
      this.category.name = ''
      this.category.parentCid = data.catId
      this.category.catLevel = data.catLevel + 1
      this.category.showStatus = 1
      this.category.sort = 0
      this.category.icon = ''
      this.category.productUnit = ''
      this.categoryDialogVisible = true
      this.categoryDialogType = 1
    },
    edit (data) {
      this.$http.get(`/product/category/info/${data.catId}`).then(({data}) => {
        this.category = data
        this.categoryDialogVisible = true
        this.categoryDialogType = 2
      })
    },
    submitForm () {
      if (this.categoryDialogType === 1) {
        this.$http.post('/product/category/save', this.category).then(res => {
          this.getMenus()

          this.$message({
            type: 'success',
            message: '保存成功 !'
          })
          this.categoryDialogVisible = false
          this.expandedKeys = [this.category.parentCid]
        })
      } else {
        let {catId, name, icon, productUnit} = this.category
        this.$http.post('/product/category/update', {catId, name, icon, productUnit}).then(res => {
          this.getMenus()

          this.$message({
            type: 'success',
            message: '修改成功 !'
          })
          this.categoryDialogVisible = false
          this.expandedKeys = [this.category.parentCid]
        })
      }
    },
    batchDelete () {
      let checkedNodes = this.$refs.categoryTree.getCheckedNodes()

      this.$confirm(`是否删除选中分类 ?`, '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        let ids = checkedNodes.map(m => {
          return m.catId
        })
        this.$http.post('/product/category/delete', ids).then(() => {
          this.getMenus()
          this.$message({
            type: 'success',
            message: '删除成功 !'
          })
        })
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除'
        })
      })
    },
    remove (node, data) {
      console.log(node)
      this.$confirm(`是否删除【${data.name}】分类 ?`, '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        let ids = [data.catId]
        this.$http.post('/product/category/delete', ids).then(res => {
          this.getMenus()
          this.expandedKeys = [node.parent.data.catId]
          this.$message({
            type: 'success',
            message: '删除成功 !'
          })
        })
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除'
        })
      })
    }
  },
  // 生命周期 - 创建完成（可以访问当前this 实例）
  created () {
    this.getMenus()
  },
  // 生命周期 - 挂载完成（可以访问 DOM 元素）
  mounted () {
  },
  beforeCreate () {
  },
  beforeMount () {
  }, // 生命周期 - 挂载之前
  beforeUpdate () {
  }, // 生命周期 - 更新之前
  updated () {
  }, // 生命周期 - 更新之后
  beforeDestroy () {
  }, // 生命周期 - 销毁之前
  destroyed () {
  }, // 生命周期 - 销毁完成
  activated () {
  } // 如果页面有 keep-alive 缓存功能,这个函数会触发
}
</script>
<style scoped>
</style>
