<template>
  <v-container>
    <v-card color="grey lighten-4">
      <v-card-title >
        <h3>Cart <span>({{ personalInfo.cart.totalItems }} items)</span></h3>
      </v-card-title>
      <v-card-text class="py-0">
        <v-layout align-space-around justify-start column reverse>
          <cart-item-card v-for="item in sortedCart"
                          :key="item.id"
                          :item="item"/>
        </v-layout>
      </v-card-text>
      <v-flex>
        <v-layout align-space-around justify-end column>
          <checkout></checkout>
        </v-layout>
      </v-flex>

    </v-card>
  </v-container>
</template>

<script>
import {mapGetters, mapState} from 'vuex'
import CartItemCard from "components/cart/CartItemCard.vue";
import Checkout from "components/cart/Checkout.vue";

export default {
  name: "CartItemShortList",
  components: {
    Checkout,
    CartItemCard,
  },
  data() {
    return {
      item: null
    }
  },
  computed: {
    ...mapGetters(['sortedCart']),
    ...mapState(['personalInfo'])
  }
}
</script>

<style scoped>

</style>