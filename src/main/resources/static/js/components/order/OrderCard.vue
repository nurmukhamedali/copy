<template>
  <v-card flat class="ma-2 v-btn--round">
    <v-card-text class="pa-2">
      <v-layout align-center justify-space-around>
        <span class="mx-2 text-truncate"># {{ order.id }}</span>
        &nbsp;
        <small>({{ order.totalItems }} items)</small>
        <v-spacer></v-spacer>
        <div class="px-2">
          <h4 class="text-truncate">
            <span>$</span>
            {{ parseFloat(order.totalPrice).toFixed(2) }}
          </h4>
        </div>
        <v-card-actions>
          <v-btn depressed small round color="success" class="text-lowercase" @click="show">View Items</v-btn>
        </v-card-actions>
      </v-layout>
    </v-card-text>
  </v-card>
</template>

<script>
import { mapActions, mapState } from 'vuex'
export default {
  name: "OrderCard",
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