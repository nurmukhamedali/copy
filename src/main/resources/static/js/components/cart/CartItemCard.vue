<template>
  <v-card class="ma-2">
    <v-card-title>
      <v-layout row>
        <v-flex xs11>
          <small>{{ item.product.name }}</small>
        </v-flex>
        <v-flex xs3>
          <h3 class="text-truncate"><span>$</span>{{ item.product.price }}</h3>
        </v-flex>
      </v-layout>
    </v-card-title>
    <v-divider></v-divider>
    <v-card-actions>
      <v-layout row>
        <v-flex xs3>
          <v-btn icon @click="del"><v-icon>delete</v-icon></v-btn>
        </v-flex>
        <v-flex xs9>
          <v-layout justify-end row >
            <v-btn icon @click="subtract"><v-icon>remove</v-icon></v-btn>
            <v-btn fab small flat>
              <h2>{{ item.quantity }}</h2>
            </v-btn>
            <v-btn icon @click="add"><v-icon>add</v-icon></v-btn>
          </v-layout>
        </v-flex>
      </v-layout>
    </v-card-actions>
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
    add() {
      this.addCartItemAction({
        product: this.item.product,
        quantity: 1
      })
    },
    subtract(){
      this.addCartItemAction({
        product: this.item.product,
        quantity: -1
      })
    },
    del() {
      this.removeCartItemAction(this.item)
    }
  }
}
</script>

<style scoped>

</style>