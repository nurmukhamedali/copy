<template>
  <v-flex d-flex xs6 sm4 md3>
    <v-card flat class="ma-2 v-btn--round border-1">
      <div class="my-2">
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
      <v-card-title primary-title>
        <v-layout column>
          <div class="headline">
            <span>$</span>{{ product.price }}
          </div>
          <span class="text-truncate">{{ product.name }}</span>
        </v-layout>
      </v-card-title>
      <v-card-actions>
        <v-layout align-center justify-space-around>
          <v-btn icon color="warning" @click="edit" small>
            <v-icon dark small>edit</v-icon>
          </v-btn>
          <v-btn icon color="error" @click="del" small>
            <v-icon dark small>delete</v-icon>
          </v-btn>
        </v-layout>
      </v-card-actions>
    </v-card>
  </v-flex>
</template>

<script>
import { mapActions, mapState } from 'vuex'
export default {
  name: "DevProductCard",
  props: ['product', 'editProduct'],
  data(){
    return {
      defaultImage: 'https://pinemelon.com/static/platform/frontend/assets/en/banners/store_main.svg'
    }
  },
  computed: mapState(['profile']),
  methods: {
    ...mapActions(['removeProductAction', 'addCartItemAction']),
    edit() {
      this.editProduct(this.product)
    },
    del() {
      this.removeProductAction(this.product)
    }
  }
}
</script>

<style scoped>

</style>