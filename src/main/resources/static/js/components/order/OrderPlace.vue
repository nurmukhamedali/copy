<template>
  <v-container>
    <h2 class="mb-2" xs12>Order Checkout</h2>
    <v-layout align-start justify-space-around row reverse wrap>
      <v-flex sm12 md6>
        <cart-item-list></cart-item-list>
      </v-flex>
      <v-flex sm12 md6>
        <order-form></order-form>
      </v-flex>

    </v-layout>

  </v-container>
</template>

<script>
import CartItemList from "components/cart/CartItemList.vue";
import OrderForm from "components/order/OrderForm.vue";
export default {
  name: "OrderPlace",
  components: {CartItemList, OrderForm}
}
</script>

<style scoped>

</style>