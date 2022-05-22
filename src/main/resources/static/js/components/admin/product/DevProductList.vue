<template>
  <v-container>
    <v-layout align-start justify-space-around row reverse wrap>
      <v-flex d-flex xs12 sm4 md3>
          <dev-product-form :productAttr="product"/>
      </v-flex>
      <v-flex xs12 sm8 md9>
        <v-layout justify-center>
          <v-pagination
              v-model="page"
              :length="totalPages"
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
    editProduct(product) {
      this.product = product;
    },
    load(){
      this.loadPageAction(this.page - 1)
    }
  }
}
</script>

<style scoped>

</style>