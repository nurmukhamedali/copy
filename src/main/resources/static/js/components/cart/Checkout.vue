<template>
  <div>
    <v-card-text>
      <v-layout align-start>
        <span>Subtotal</span>&nbsp;<span color="grey">({{ personalInfo.cart.totalItems }} items)</span>
        <v-spacer></v-spacer>
        <div>${{ parseFloat(personalInfo.cart.totalPrice).toFixed(2) }}</div>
      </v-layout>
    </v-card-text>
    <v-divider></v-divider>
    <v-card-text>
      <v-layout>
        <span>Estimated Total</span>
        <v-spacer></v-spacer>
        <h4>$ {{ parseFloat(personalInfo.cart.totalPrice).toFixed(2) }}</h4>
      </v-layout>
    </v-card-text>
  <v-layout justify-center class="ma-2" v-show="$route.path !== '/checkout'">
    <v-btn round depressed block color="success" @click="showCart">Checkout</v-btn>
  </v-layout>
  </div>
</template>

<script>
import {mapState} from "vuex";

export default {
  name: "Checkout",
  computed: mapState(['personalInfo']),
  methods: {
    showCart() {
      this.$router.push('/checkout')
    }
  }
}
</script>

<style scoped>

</style>