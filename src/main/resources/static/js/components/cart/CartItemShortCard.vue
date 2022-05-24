<template>
  <v-card flat class="px-2">
    <v-card-text class="pa-2">
      <v-layout row>
        <span class="text-truncate">{{ item.product.name }}</span>
        <v-spacer></v-spacer>
        <div class="px-2">
          <h4 class="text-truncate">
            <span>$</span>
            {{ parseFloat(item.quantity * item.product.price).toFixed(2) }}
          </h4>
          <small class="text-truncate">
            <span>$</span>
            {{ parseFloat(item.product.price).toFixed(2) }}
            <span>ea</span>
          </small>
        </div>
      </v-layout>
    </v-card-text>
    <v-card-actions>
      <v-layout align-center justify-end row>
        <span small class="text-capitalize mx-2" style="cursor: pointer;" @click="del"><small>Remove</small></span>
        <v-item-group class=" v-btn--round">
          <v-btn icon @click="decrement" small><v-icon>remove</v-icon></v-btn>
          <v-btn icon small>{{ item.quantity }}</v-btn>
          <v-btn icon @click="increment" small><v-icon>add</v-icon></v-btn>
        </v-item-group>
      </v-layout>
    </v-card-actions>
    <v-divider></v-divider>
  </v-card>
</template>

<script>
import { mapActions, mapState } from 'vuex'
export default {
  name: "CartItemShortCard",
  props: ['item', 'addItem'],
  computed: mapState(['cart']),
  methods: {
    ...mapActions(['removeCartItemAction', 'addCartItemAction']),
    increment() {
      if(this.item.quantity < 100)
        this.addCartItemAction({
        product: this.item.product,
        quantity: 1
      })
    },
    decrement(){
      if(this.item.quantity > 1){
        this.addCartItemAction({
          product: this.item.product,
          quantity: -1
        })
      } else {
        this.del()
      }
    },
    del() {
      this.removeCartItemAction(this.item)
    }
  }
}
</script>

<style scoped>

</style>