<template>
  <v-card flat class="ma-1 v-btn--round" @click="show">
    <v-card-text class="py-2 px-0">
      <v-layout align-center justify-space-around>
        <small class="text-truncate"># {{ order.id }}</small>
        <small>({{ order.totalItems }} items)</small>
        <small class="text-truncate">{{ order.status }}</small>
        <small class="text-truncate font-weight-bold">
          <span>$</span>
          {{ parseFloat(order.totalPrice).toFixed(2) }}
        </small>
        <v-btn flat round small icon color="success" class="ma-0" @click="show">
          <v-icon>start</v-icon>
        </v-btn>
      </v-layout>
    </v-card-text>
  </v-card>
</template>

<script>
import { mapActions, mapState } from 'vuex'
export default {
  name: "DevOrderCard",
  props: ['order', 'showOrder'],
  methods: {
    show(){
      this.showOrder(this.order)
    }
  }
}
</script>

<style scoped>

</style>