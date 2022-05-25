<template>
  <v-container>
    <v-layout align-start justify-space-around row reverse wrap>
      <v-flex class="hidden-sm-and-down" md3>
        <cart-item-short-list></cart-item-short-list>
      </v-flex>
      <v-flex xs12 sm8 md9>
        <v-layout class="categories">
          <v-radio-group row v-model="categoryId" class="">
            <v-btn depressed round small><v-radio label="All" :value="null"></v-radio></v-btn>
            <v-btn depressed round small
                   v-for="category in sortedCategories"
                   :key="category.id">
              <v-radio :label="category.name"
                       :value="category.id"
              >
              </v-radio>
            </v-btn>
          </v-radio-group>
        </v-layout>
        <v-layout class="mt-2 mx-4" column>
          <v-range-slider thumb-label="always" thumb-color="" color="green" always-dirty
                          v-model="price"
                          :max="100"
                          :min="0"
                          :step="1"
          ></v-range-slider>
          <v-btn depressed color="success" @click="load">Apply</v-btn>
        </v-layout>
        <v-container class="products-navigation">
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
        </v-container>
      </v-flex>
    </v-layout>
  </v-container>
</template>

<script>
import {mapActions, mapGetters, mapState} from 'vuex'
import ProductCard from "components/product/ProductCard.vue";
import CartItemShortList from "components/cart/CartItemShortList.vue";

export default {
  name: "MainPage",
  components: {
    CartItemShortList,
    ProductCard,
  },
  data() {
    return {
      page: null,
      category: {id: null, name: '', image: ''},
      categoryId: null,
      price: [0, 100],
    }
  },
  computed: {
    ...mapGetters(['sortedProducts', 'sortedCategories']),
    ...mapState(['totalPages', 'currentPage'])
  },
  watch: {
    categoryId: function () {
      // this.load()
    }
  },
  created() {
    this.page = this.currentPage + 1;
    this.load()
  },
  methods: {
    ...mapActions(['loadPageAction']),
    load(){
      this.loadPageAction({
        page: this.page - 1,
        categoryId: this.categoryId,
        minPrice: this.price[0],
        maxPrice: this.price[1]
      })
    }
  }
}
</script>

<style scoped>

</style>