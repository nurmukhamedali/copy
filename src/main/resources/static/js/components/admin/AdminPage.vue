<template>
  <v-container>
    <v-layout>
      <v-flex xs6 sm4 md3 @click="showDevOrders" style="cursor: pointer;">
        <v-card class="ma-2" color="info" dark>
          <v-card-text>
            <h3 class="d-flex justify-center">Orders</h3>
          </v-card-text>
        </v-card>
      </v-flex>
      <v-flex xs6 sm4 md3 @click="showDevCategories" style="cursor: pointer;">
        <v-card class="ma-2" color="warning darken-1" dark>
          <v-card-text>
            <h3 class="d-flex justify-center">Categories</h3>
          </v-card-text>
        </v-card>
      </v-flex>
      <v-flex xs6 sm4 md3  @click="showDevProducts" style="cursor: pointer;">
        <v-card class="ma-2" color="success" dark>
          <v-card-text>
            <h3 class="d-flex justify-center">Products</h3>
          </v-card-text>
        </v-card>
      </v-flex>
      <v-flex xs6 sm4 md3 @click="showDevProfiles" style="cursor: pointer;">
        <v-card class="ma-2" color="error" dark>
          <v-card-text>
            <h3 class="d-flex justify-center">Customers</h3>
          </v-card-text>
        </v-card>
      </v-flex>
    </v-layout>
  </v-container>
</template>

<script>
export default {
  name: "AdminPage",
  methods: {
    showDevProducts(){
      this.$router.push('/dev-products')
    },
    showDevProfiles(){
      this.$router.push('/dev-users')
    },
    showDevCategories(){
      this.$router.push('/dev-categories')
    },
    showDevOrders(){
      this.$router.push('/dev-orders')
    },
  }
}
</script>

<style scoped>

</style>