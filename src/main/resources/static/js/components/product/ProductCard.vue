<template>
  <v-flex d-flex xs6 sm4 md3>
    <v-card flat class="ma-2 v-btn--round border-1">
      <div class="mt-2">
        <v-layout justify-center>
          <v-avatar tile color="" size="88">
            <v-img
                v-if="product.image"
                :src="product.image"
                contain
                @error="$event.target.src=this.defaultImage"
            />
            <v-img v-if="!product.image" :src="this.defaultImage" contain/>
          </v-avatar>
        </v-layout>
      </div>
      <v-card-text class="py-0">
        <v-layout column>
          <h3>
            <span>$</span>{{ product.price }}
          </h3>
          <span class="text-truncate">{{ product.name }}</span>
        </v-layout>
      </v-card-text>
      <v-card-actions>
        <v-layout justify-end>
          <v-flex>
            <v-btn icon small color="success" @click="addToCart" class="ma-1">
              <v-icon dark small>add</v-icon>
            </v-btn>
          </v-flex>
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
  computed: mapState(['profile', 'defaultImage']),
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