<template>
  <v-container>
    <v-layout align-start justify-start row wrap>
      <filter-by-category-card v-for="category in sortedCategories"
                     :key="category.id"
                     :category="category"
                     :setCategory="setCategory"/>
    </v-layout>
  </v-container>
</template>

<script>
import { mapGetters } from 'vuex'
import FilterByCategoryCard from "components/category/FilterByCategoryCard.vue";

export default {
  name: "FilterByCategoryList",
  components: {
    FilterByCategoryCard
  },
  computed: mapGetters(['sortedCategories']),
  methods: {
    setCategory(category) {
      this.category = category;
    }
  }
}
</script>

<style scoped>

</style>