<template>
  <v-flex d-flex xs6 sm4 md3>
    <v-card class="ma-2">
      <v-card-media class="my-2">
        <v-layout justify-center>
          <v-avatar tile color="" size="88">
            <v-img
                :src="product.image"
                contain
                alt="PineMelon"
            />
          </v-avatar>
        </v-layout>
      </v-card-media>
        <v-card-title primary-title>
          <v-layout column>
            <div class="headline">
              <span>$</span>{{ product.price }}
            </div>
            <span class="text-truncate">{{ product.name }}</span>
          </v-layout>
        </v-card-title>
      <v-divider></v-divider>
      <v-card-actions>
        <v-layout justify-end>
          <v-flex>
          <v-btn icon small color="success" @click="addToCart" >
            <v-icon dark small>add</v-icon>
          </v-btn>
          </v-flex>
          <v-item-group>
          <v-btn icon color="warning" @click="edit" small>
            <v-icon dark small>edit</v-icon>
          </v-btn>
          <v-btn icon color="error" @click="del" small>
            <v-icon dark small>delete</v-icon>
          </v-btn>
          </v-item-group>
        </v-layout>
      </v-card-actions>
    </v-card>
  </v-flex>
</template>

<script>
import { mapActions, mapState } from 'vuex'
export default {
  name: "ProductCard",
  props: ['product', 'editProduct'],
  computed: mapState(['profile']),
  methods: {
    ...mapActions(['removeProductAction', 'addCartItemAction']),
    edit() {
      this.editProduct(this.product)
    },
    del() {
      this.removeProductAction(this.product)
    },
    addToCart(){
      this.addCartItemAction({
        id: null,
        quantity: 1,
        product: this.product,
        user: this.profile
      })
    }
  }
}
</script>

<style scoped>

</style>