<template>
  <v-container>
    <v-layout align-start justify-space-around row reverse wrap>
      <v-flex d-flex xs12 sm5 md3>
        <category-form :categoryAttr="category"/>
      </v-flex>
      <v-flex d-flex xs12 sm7 md9>
        <v-layout align-start justify-start row wrap>
          <category-card v-for="category in sortedCategories"
                        :key="category.id"
                        :category="category"
                        :editCategory="editCategory"/>
        </v-layout>
      </v-flex>
    </v-layout>
  </v-container>
</template>

<script>
import { mapGetters } from 'vuex'
import CategoryCard from "components/admin/category/DevCategoryCard.vue";
import CategoryForm from "components/admin/category/DevCategoryForm.vue";

export default {
  name: "DevCategoryList",
  components: {
    CategoryCard,
    CategoryForm
  },
  data() {
    return {
      category: null
    }
  },
  computed: mapGetters(['sortedCategories']),
  methods: {
    editCategory(category) {
      this.category = category;
    }
  }
}
</script>

<style scoped>

</style>