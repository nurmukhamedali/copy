<template>
  <v-card flat class="px-2">
    <v-layout>
      <div class="mt-2">
        <v-layout justify-center>
          <v-avatar tile color="" size="88">
            <v-img
                v-if="item.product.image"
                :src="item.product.image"
                contain
                @error="$event.target.src=this.defaultImage"
            />
            <v-img v-if="!item.product.image" :src="this.defaultImage" contain/>
          </v-avatar>
        </v-layout>
      </div>
      <v-flex>
        <v-card-text class="pa-2">
          <v-layout align-center row>
            <h3 class="text-truncate">{{ item.product.name }}</h3>
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
      </v-flex>
    </v-layout>
  </v-card>
</template>

<script>
import { mapActions, mapState } from 'vuex'
export default {
  name: "CartItemCard",
  props: ['item', 'addItem'],
  computed: mapState(['personalInfo', 'defaultImage']),
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