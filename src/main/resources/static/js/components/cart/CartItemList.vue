<template>
  <v-container>
    <v-layout>
      <h3>Cart <span color="grey">({{ cart.totalItems }} items)</span></h3>
    </v-layout>
    <v-layout align-space-around justify-start column>
      <v-flex>
          <v-layout align-space-around justify-start column reverse>
            <cart-item-card v-for="item in sortedCart"
                            :key="item.id"
                            :item="item"/>
          </v-layout>
      </v-flex>
      <v-flex>
          <v-layout align-space-around justify-end column>
            <checkout></checkout>
          </v-layout>
      </v-flex>
    </v-layout>
  </v-container>
</template>

<script>
import {mapGetters, mapState,} from 'vuex'
import CartItemCard from "components/cart/CartItemCard.vue";
import Checkout from "components/cart/Checkout.vue";

export default {
  name: "CartItemList",
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
    ...mapState(['cart'])
  }
}
</script>

<style scoped>

</style>