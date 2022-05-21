<template>
  <v-container>
    <v-layout>
      <h3>Your Cart</h3>
    </v-layout>
    <v-layout align-space-around justify-start column reverse>
        <cart-item-card v-for="item in sortedCart"
                      :key="item.id"
                      :item="item"/>
    </v-layout>
  </v-container>
</template>

<script>
import { mapGetters } from 'vuex'
import CartItemCard from "components/cart/CartItemCard.vue";

export default {
  name: "CartItemList",
  components: {
    CartItemCard,
  },
  data() {
    return {
      item: null
    }
  },
  computed: mapGetters(['sortedCart']),
}
</script>

<style scoped>

</style>