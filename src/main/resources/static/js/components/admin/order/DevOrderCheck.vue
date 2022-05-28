<template>
  <div>
    <v-card-text>
      <v-layout align-start>
        <span>Subtotal</span>&nbsp;<span color="grey">({{ order.totalItems }} items)</span>
        <v-spacer></v-spacer>
        <div>$ {{ parseFloat(order.totalPrice).toFixed(2) }}</div>
      </v-layout>
      <v-layout>
        <span>Estimated Total</span>
        <v-spacer></v-spacer>
        <h4>$ {{ parseFloat(order.totalPrice).toFixed(2) }}</h4>
      </v-layout>
    </v-card-text>
    <v-divider></v-divider>
    <v-card-text>
      <h3>Delivery Info</h3>
      <v-layout>
        <span>Recipient Name</span>
        <v-spacer></v-spacer>
        <h4>{{ order.recipientName }}</h4>
      </v-layout>
      <v-layout>
        <span>Recipient Phone</span>
        <v-spacer></v-spacer>
        <h4>{{ order.recipientPhoneNumber }}</h4>
      </v-layout>
      <v-layout>
        <span>Delivery Address</span>
        <v-spacer></v-spacer>
        <h4>{{ order.deliveryAddress }}</h4>
      </v-layout>
      <v-layout>
        <span>Delivery Date</span>
        <v-spacer></v-spacer>
        <h4>{{ order.deliveryDate }}</h4>
      </v-layout>
      <v-layout>
        <span>Payment Type</span>
        <v-spacer></v-spacer>
        <h4>{{ order.paymentType }}</h4>
      </v-layout>
    </v-card-text>
    <v-divider></v-divider>
    <v-card-text>
      <v-layout>
        <span class="font-italic">Created Date</span>
        <v-spacer></v-spacer>
        <h4>{{ order.createdDate }}</h4>
      </v-layout>

      <v-layout>
        <span class="font-italic">Delivered Date</span>
        <v-spacer></v-spacer>
        <h4>{{ order.deliveredDate }}</h4>
      </v-layout>
    </v-card-text>
    <v-divider v-if="order.deliveryInstructions"></v-divider>
    <v-card-text v-if="order.deliveryInstructions">
      <p>{{ order.deliveryInstructions }}</p>
    </v-card-text>
  </div>
</template>

<script>
import {mapState} from "vuex";

export default {
  name: "OrderCheck",
  props: ['order']
}
</script>

<style scoped>

</style>