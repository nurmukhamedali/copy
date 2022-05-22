<template>
  <v-card class="ma-2">
    <v-card-actions>
      <v-layout justify-center class="ma-2">
        <v-btn round depressed color="info">Order Items</v-btn>
      </v-layout>
    </v-card-actions>
    <v-divider></v-divider>
    <v-card-text>
      <div color="grey">({{ cart.totalItems }} items)</div>
      <v-layout align-start>
        <span>Subtotal</span>&nbsp;
        <v-spacer></v-spacer>
        <div>${{ parseFloat(cart.totalPrice).toFixed(2) }}</div>
      </v-layout>
      <v-layout>
        <span>Delivery</span>
        <v-spacer></v-spacer>
        <span>$ 2.00</span>
      </v-layout>
    </v-card-text>
    <v-divider></v-divider>
    <v-card-text>
      <v-layout>
        <span>Estimated Total</span>
        <v-spacer></v-spacer>
        <h4>$ {{ parseFloat(cart.totalPrice + 2).toFixed(2) }}</h4>
      </v-layout>
    </v-card-text>
  </v-card>
</template>

<script>
import {mapState} from "vuex";

export default {
  name: "Checkout",
  computed: mapState(['cart']),
}
</script>

<style scoped>

</style>