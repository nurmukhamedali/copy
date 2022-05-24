<template>
  <v-card>
    <v-layout>
      <div class="d-flex align-center">
        <v-avatar tile color="" size="88">
          <v-img
              :src="item.product.image"
              contain
              alt="PineMelon"
          />
        </v-avatar>
      </div>
      <v-flex>
        <v-card-title class="pa-2">
          <v-layout row>
              <span class="text-truncate">{{ item.product.name }}</span>
              <v-spacer></v-spacer>
              <div class="px-2">
                <h4 class="text-truncate">
                  <span>$</span>
                  {{ parseFloat(item.quantity * item.product.price).toFixed(2) }}
                </h4>
                <span class="text-truncate">
                  <span>$</span>
                  {{ parseFloat(item.product.price).toFixed(2) }}
                  <span>ea</span>
                </span>
              </div>
          </v-layout>
        </v-card-title>
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
      </v-flex>
    </v-layout>
  </v-card>
</template>

<script>
import { mapActions, mapState } from 'vuex'
export default {
  name: "CartItemCard",
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