<template>
  <v-container color="grey lighten-4">
    <v-card-title >
      <h3>Orders
        <span>({{ adminInfo.orders.length }} orders)</span>
      </h3>
    </v-card-title>
    <v-layout justify-space-around row>
      <v-flex>
        <v-layout align-space-around justify-start column reverse>
          <dev-order-card v-for="order in adminInfo.orders" :key="order.id" :order="order"  :showOrder="showOrder"></dev-order-card>
        </v-layout>
      </v-flex>
      <v-flex sm6 md4>
        <dev-item-short-list :order="order"/>
      </v-flex>
    </v-layout>
  </v-container>
</template>

<script>
import {mapActions, mapGetters, mapState,} from 'vuex'
import DevItemShortList from "components/admin/order/DevItemShortList.vue";
import DevOrderCard from "components/admin/order/DevOrderCard.vue";

export default {
  name: "OrderList",
  components: {
    DevItemShortList,
    DevOrderCard,
  },
  data() {
    return {
      order: null,
      headers: [
        {text: 'ID', align: 'left', sortable: false, value: 'name'},
        {text: 'Total Items', value: 'totalItems'},
        {text: 'Total Price', value: 'totalPrice'},
      ]
    }
  },
  computed: {
    ...mapState(['adminInfo']),
  },
  created() {
    this.load()
  },
  methods: {
    ...mapActions(['loadOrdersPageAction']),
    showOrder(order){
      this.order = order;
    },
    load(){
      this.loadOrdersPageAction();
    }
  }
}
</script>

<style scoped>

</style>