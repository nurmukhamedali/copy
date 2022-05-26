<template>
  <v-container color="grey lighten-4">
    <v-card-title >
      <h3>Order's History
        &nbsp;
        <span>({{ orders.length }} orders)</span>
      </h3>
    </v-card-title>
    <v-layout justify-space-around row>
      <v-flex md6>
        <v-layout align-space-around justify-start column reverse>
          <order-card v-for="order in orders" :key="order.id" :order="order"  :showOrder="showOrder"></order-card>
        </v-layout>
      </v-flex>
      <v-flex md6>
        <order-item-short-list :order="order"/>
      </v-flex>
    </v-layout>
  </v-container>
</template>

<script>
import {mapGetters, mapState,} from 'vuex'
import OrderItemShortList from "components/order/OrderItemShortList.vue";
import Checkout from "components/cart/Checkout.vue";
import OrderCard from "components/order/OrderCard.vue";

export default {
  name: "OrderList",
  components: {
    OrderCard,
    Checkout,
    OrderItemShortList,
  },
  data() {
    return {
      order: null,
    }
  },
  computed: mapState(['orders']),
  methods: {
    showOrder(order){
      this.order = order;
    }
  }
}
</script>

<style scoped>

</style>