<template>
    <v-btn depressed outline class="ma-2" >
      <v-card-text>
        <span class="d-flex justify-center">{{ category.name }}</span>
      </v-card-text>
    </v-btn>
</template>

<script>
import { mapActions } from 'vuex'
export default {
  name: "FilterByCategoryCard",
  props: ['category', 'setCategory'],
  methods: {
    ...mapActions(['removeCategoryAction']),
    edit() {
      this.editCategory(this.category)
    },
    del() {
      this.removeCategoryAction(this.category)
    }
  }
}
</script>

<style scoped>
.v-btn{
  border: thin solid #CCCCCC;
  border-radius: 10px;
}
</style>