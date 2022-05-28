<template>
  <v-container>
    <v-layout align-start justify-space-around row reverse wrap>
      <v-flex d-flex xs12 sm4 md3>
          <dev-product-form :productAttr="product"/>
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

        <v-layout justify-center>
          <v-pagination
              v-model="page"
              :length="productInfo.totalPages"
              @input="load"
          ></v-pagination>
        </v-layout>
        <v-layout align-start justify-start row wrap>
          <dev-product-card v-for="product in sortedProducts"
                        :key="product.id"
                        :product="product"
                        :editProduct="editProduct"/>
        </v-layout>
        <v-layout justify-center>
          <v-pagination
              v-model="page"
              :length="productInfo.totalPages"
              @input="load"
          ></v-pagination>
        </v-layout>
      </v-flex>
    </v-layout>
  </v-container>
</template>

<script>
import {mapActions, mapGetters, mapState} from 'vuex'
import DevProductCard from "components/admin/product/DevProductCard.vue";
import DevProductForm from "components/admin/product/DevProductForm.vue";

export default {
  name: "DevProductList",
  components: {
    DevProductCard,
    DevProductForm
  },
  data() {
    return {
      product: null,
      page: 1,
      price: [0, 20],
      categoryId: null,
      totalProducts: null,
    }
  },
  computed: {
    ...mapGetters(['sortedProducts', 'sortedCategories']),
    ...mapState(['productInfo'])
  },
  created() {
    this.page = this.productInfo.currentPage + 1;
    this.load()
  },
  methods: {
    ...mapActions(['loadPageAction']),
    editProduct(product) {
      this.product = product;
    },
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