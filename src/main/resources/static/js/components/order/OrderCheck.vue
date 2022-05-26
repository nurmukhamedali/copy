<template>
  <div>
    <v-card-text>
      <v-layout align-start>
        <span>Subtotal</span>&nbsp;<span color="grey">({{ order.totalItems }} items)</span>
        <v-spacer></v-spacer>
        <div>${{ parseFloat(order.totalPrice).toFixed(2) }}</div>
      </v-layout>
    </v-card-text>
    <v-divider></v-divider>
    <v-card-text>
      <v-layout>
        <span>Estimated Total</span>
        <v-spacer></v-spacer>
        <h4>$ {{ parseFloat(order.totalPrice).toFixed(2) }}</h4>
      </v-layout>
    </v-card-text>
  </div>
</template>

<script>
import {mapState} from "vuex";

export default {
  name: "OrderCheck",
  props: ['order'],
}
</script>

<style scoped>

</style>