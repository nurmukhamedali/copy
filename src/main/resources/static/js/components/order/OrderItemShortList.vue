<template>
  <v-card color="grey lighten-4" v-if="order">
    <v-card-title >
      <h3>Order #{{ order.id }} <span>({{ order.totalItems }} items)</span></h3>
      <v-spacer></v-spacer>
      <v-btn depressed round readonly :color="status">{{ order.status }}</v-btn>
    </v-card-title>
    <v-card-text class="py-0">
        <v-layout align-space-around justify-start column reverse>
          <order-item-short-card v-for="item in order.items"
                          :key="item.id"
                          :item="item"/>
        </v-layout>
    </v-card-text>
      <v-flex>
        <v-layout align-space-around justify-end column>
          <order-check :order="order"></order-check>
        </v-layout>
      </v-flex>
  </v-card>
</template>

<script>
import {mapGetters, mapState,} from 'vuex'
import OrderItemShortCard from "components/order/OrderItemShortCard.vue";
import Checkout from "components/cart/Checkout.vue";
import OrderCheck from "components/order/OrderCheck.vue";

export default {
  name: "OrderItemShortList",
  components: {
    OrderCheck,
    Checkout,
    OrderItemShortCard,
  },
  props: ['order'],
  computed: mapState(['styleInfo']),
  watch: {
    order: function (){
          this.changeBtn()
    }
  },
  data() {
    return {
      item: null,
      status: 'primary'
    }
  },
  methods: {
    changeBtn(){
      if(this.order) {
        console.log(this.order.status)
        this.status = this.styleInfo.statusColors[this.order.status.toLowerCase()]
        console.log(this.order.status.toLowerCase())
      }
    }
  }
}
</script>

<style scoped>

</style>