<template>
  <v-card color="grey lighten-4" v-if="order">
    <v-card-title >
      <h3>Order #{{ order.id }} <span>({{ order.totalItems }} items)</span></h3>
      <v-spacer></v-spacer>
      <h3>{{ order.status }}</h3>
    </v-card-title>
    <v-card-text class="py-0">
        <v-layout align-space-around justify-start column reverse>
          <dev-item-short-card v-for="item in order.items"
                          :key="item.id"
                          :item="item"/>
        </v-layout>
    </v-card-text>
      <v-flex>
        <v-layout align-space-around justify-end column>
          <dev-order-check :order="order"></dev-order-check>
        </v-layout>
      </v-flex>
    <v-card-actions>
      <v-layout v-if="order.status === 'CREATED'">
        <v-btn depressed round color="warning" @click="acceptOrder" block>Accept</v-btn>
        <v-btn depressed round color="error" @click="declineOrder" block>Decline</v-btn>
      </v-layout>
      <v-layout v-if="order.status === 'IN_PROGRESS'">
        <v-btn depressed round color="success" @click="completeOrder" block>Delivered</v-btn>
        <v-btn depressed round color="error" @click="declineOrder" block>Decline</v-btn>
      </v-layout>
    </v-card-actions>
  </v-card>
</template>

<script>
import {mapActions, mapGetters, mapState,} from 'vuex'
import DevItemShortCard from "components/admin/order/DevItemShortCard.vue";
import DevOrderCheck from "components/admin/order/DevOrderCheck.vue";

export default {
  name: "DevItemShortList",
  components: {
    DevOrderCheck,
    DevItemShortCard,
  },
  props: ['order'],
  data() {
    return {
      item: null
    }
  },
  computed: {
  },
  methods: {
    ...mapActions(['updateOrderStatusAction']),
    acceptOrder(){
      this.order.status = 'IN_PROGRESS'
      this.updateOrderStatusAction(this.order)
    },
    declineOrder(){
      this.order.status = 'DECLINED'
      this.updateOrderStatusAction(this.order)
    },
    completeOrder(){
      this.order.status = 'DELIVERED'
      this.updateOrderStatusAction(this.order)
    }
  }
}
</script>

<style scoped>

</style>