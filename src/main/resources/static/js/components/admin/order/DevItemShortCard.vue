<template>
  <v-card flat class="px-2">
    <v-card-text class="pa-2">
      <v-layout align-center row>
        <span class="text-truncate">{{ item.product.name }}</span>
        <v-spacer></v-spacer>
        <v-btn icon small>{{ item.quantity }} pcs</v-btn>
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
    <v-divider></v-divider>
  </v-card>
</template>

<script>
import { mapActions, mapState } from 'vuex'
export default {
  name: "DevItemShortCard",
  props: ['item'],
}
</script>

<style scoped>

</style>