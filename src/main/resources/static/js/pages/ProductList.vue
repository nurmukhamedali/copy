<template>
  <v-container>
    <v-layout align-start justify-space-around row reverse wrap>
      <v-flex class="hidden-sm-and-down" md3>
        <cart-item-short-list></cart-item-short-list>
      </v-flex>
      <v-flex xs12 sm8 md9>
        <v-layout>
          <filter-by-category-list></filter-by-category-list>
        </v-layout>
        <v-layout justify-center>
          <v-pagination
              v-model="page"
              :length="totalPages"
              @input="load"
          ></v-pagination>
        </v-layout>
        <v-layout align-start justify-start row wrap>
          <product-card v-for="product in sortedProducts"
                        :key="product.id"
                        :product="product"/>
        </v-layout>
        <v-layout justify-center>
          <v-pagination
              v-model="page"
              :length="totalPages"
              @input="load"
          ></v-pagination>
        </v-layout>
      </v-flex>
    </v-layout>
  </v-container>
</template>

<script>
import {mapActions, mapGetters, mapState} from 'vuex'
import ProductCard from "components/product/ProductCard.vue";
import CartItemShortList from "components/cart/CartItemShortList.vue";
import FilterByCategoryList from "components/category/FilterByCategoryList.vue";

export default {
  name: "ProductList",
  components: {
    CartItemShortList,
    ProductCard,
    FilterByCategoryList
  },
  data() {
    return {
      page: 1
    }
  },
  computed: {
    ...mapGetters(['sortedProducts']),
    ...mapState(['totalPages', 'currentPage'])
  },
  created() {
    this.page = this.currentPage + 1;
    this.load()
  },
  methods: {
    ...mapActions(['loadPageAction']),
    load(){
      this.loadPageAction(this.page - 1)
    }
  }
}
</script>

<style scoped>

</style>