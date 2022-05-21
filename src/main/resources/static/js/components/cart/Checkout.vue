<template>
  <v-card class="ma-2">
    <v-card-actions>
      <v-layout justify-center class="ma-2">
        <v-btn round depressed color="info">Order Items</v-btn>
      </v-layout>
    </v-card-actions>
    <v-divider></v-divider>
    <v-card-text>
      <v-layout>
        <v-flex xs9>
          <span>Subtotal</span>&nbsp;<span color="grey">(7 items)</span>
        </v-flex>
        <v-spacer></v-spacer>
        <v-flex xs3>
          <span>$22.5</span>
        </v-flex>
      </v-layout>
      <v-layout>
        <v-flex xs9>
          <span>Delivery</span>
        </v-flex>
        <v-flex xs3>
          <span>$2</span>
        </v-flex>
      </v-layout>
    </v-card-text>
    <v-divider></v-divider>
    <v-card-text>
      <v-layout>
        <v-flex xs9>
          <span>Estimated Total</span>
        </v-flex>
        <v-flex xs3>
          <h4>$24.5</h4>
        </v-flex>
      </v-layout>
    </v-card-text>
  </v-card>
</template>

<script>
export default {
  name: "Checkout"
}
</script>

<style scoped>

</style>